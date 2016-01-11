package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class FilteringParserDelegate
  extends JsonParserDelegate
{
  protected boolean _allowMultipleMatches;
  protected JsonToken _currToken;
  protected TokenFilterContext _exposedContext;
  protected TokenFilterContext _headContext;
  @Deprecated
  protected boolean _includeImmediateParent = false;
  protected boolean _includePath;
  protected TokenFilter _itemFilter;
  protected JsonToken _lastClearedToken;
  protected int _matchCount;
  protected TokenFilter rootFilter;
  
  public FilteringParserDelegate(JsonParser paramJsonParser, TokenFilter paramTokenFilter, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramJsonParser);
    this.rootFilter = paramTokenFilter;
    this._itemFilter = paramTokenFilter;
    this._headContext = TokenFilterContext.createRootContext(paramTokenFilter);
    this._includePath = paramBoolean1;
    this._allowMultipleMatches = paramBoolean2;
  }
  
  private JsonToken _nextBuffered(TokenFilterContext paramTokenFilterContext)
    throws IOException
  {
    this._exposedContext = paramTokenFilterContext;
    JsonToken localJsonToken = paramTokenFilterContext.nextTokenToRead();
    if (localJsonToken != null) {
      return localJsonToken;
    }
    do
    {
      if (paramTokenFilterContext == this._headContext) {
        throw _constructError("Internal error: failed to locate expected buffered tokens");
      }
      paramTokenFilterContext = this._exposedContext.findChildOf(paramTokenFilterContext);
      this._exposedContext = paramTokenFilterContext;
      if (paramTokenFilterContext == null) {
        throw _constructError("Unexpected problem: chain of filtered context broken");
      }
      localJsonToken = this._exposedContext.nextTokenToRead();
    } while (localJsonToken == null);
    return localJsonToken;
  }
  
  protected JsonStreamContext _filterContext()
  {
    if (this._exposedContext != null) {
      return this._exposedContext;
    }
    return this._headContext;
  }
  
  protected final JsonToken _nextToken2()
    throws IOException
  {
    JsonToken localJsonToken;
    Object localObject;
    label563:
    label618:
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              for (;;)
              {
                localJsonToken = this.delegate.nextToken();
                if (localJsonToken == null)
                {
                  this._currToken = localJsonToken;
                  return localJsonToken;
                }
                TokenFilter localTokenFilter;
                switch (localJsonToken.id())
                {
                default: 
                  localObject = this._itemFilter;
                  if (localObject != TokenFilter.INCLUDE_ALL) {
                    break label618;
                  }
                  this._currToken = localJsonToken;
                  return localJsonToken;
                case 3: 
                  localObject = this._itemFilter;
                  if (localObject == TokenFilter.INCLUDE_ALL)
                  {
                    this._headContext = this._headContext.createChildArrayContext((TokenFilter)localObject, true);
                    this._currToken = localJsonToken;
                    return localJsonToken;
                  }
                  if (localObject == null)
                  {
                    this.delegate.skipChildren();
                  }
                  else
                  {
                    localTokenFilter = this._headContext.checkValue((TokenFilter)localObject);
                    if (localTokenFilter == null)
                    {
                      this.delegate.skipChildren();
                    }
                    else
                    {
                      localObject = localTokenFilter;
                      if (localTokenFilter != TokenFilter.INCLUDE_ALL) {
                        localObject = localTokenFilter.filterStartArray();
                      }
                      this._itemFilter = ((TokenFilter)localObject);
                      if (localObject == TokenFilter.INCLUDE_ALL)
                      {
                        this._headContext = this._headContext.createChildArrayContext((TokenFilter)localObject, true);
                        this._currToken = localJsonToken;
                        return localJsonToken;
                      }
                      this._headContext = this._headContext.createChildArrayContext((TokenFilter)localObject, false);
                      if (this._includePath)
                      {
                        localObject = _nextTokenWithBuffering(this._headContext);
                        if (localObject != null)
                        {
                          this._currToken = ((JsonToken)localObject);
                          return (JsonToken)localObject;
                        }
                      }
                    }
                  }
                  break;
                case 1: 
                  localObject = this._itemFilter;
                  if (localObject == TokenFilter.INCLUDE_ALL)
                  {
                    this._headContext = this._headContext.createChildObjectContext((TokenFilter)localObject, true);
                    this._currToken = localJsonToken;
                    return localJsonToken;
                  }
                  if (localObject == null)
                  {
                    this.delegate.skipChildren();
                  }
                  else
                  {
                    localTokenFilter = this._headContext.checkValue((TokenFilter)localObject);
                    if (localTokenFilter == null)
                    {
                      this.delegate.skipChildren();
                    }
                    else
                    {
                      localObject = localTokenFilter;
                      if (localTokenFilter != TokenFilter.INCLUDE_ALL) {
                        localObject = localTokenFilter.filterStartObject();
                      }
                      this._itemFilter = ((TokenFilter)localObject);
                      if (localObject == TokenFilter.INCLUDE_ALL)
                      {
                        this._headContext = this._headContext.createChildObjectContext((TokenFilter)localObject, true);
                        this._currToken = localJsonToken;
                        return localJsonToken;
                      }
                      this._headContext = this._headContext.createChildObjectContext((TokenFilter)localObject, false);
                      if (this._includePath)
                      {
                        localObject = _nextTokenWithBuffering(this._headContext);
                        if (localObject != null)
                        {
                          this._currToken = ((JsonToken)localObject);
                          return (JsonToken)localObject;
                        }
                      }
                    }
                  }
                  break;
                case 2: 
                case 4: 
                  boolean bool = this._headContext.isStartHandled();
                  localObject = this._headContext.getFilter();
                  if ((localObject != null) && (localObject != TokenFilter.INCLUDE_ALL)) {
                    ((TokenFilter)localObject).filterFinishArray();
                  }
                  this._headContext = this._headContext.getParent();
                  this._itemFilter = this._headContext.getFilter();
                  if (bool)
                  {
                    this._currToken = localJsonToken;
                    return localJsonToken;
                  }
                  break;
                case 5: 
                  localObject = this.delegate.getCurrentName();
                  localTokenFilter = this._headContext.setFieldName((String)localObject);
                  if (localTokenFilter == TokenFilter.INCLUDE_ALL)
                  {
                    this._itemFilter = localTokenFilter;
                    this._currToken = localJsonToken;
                    return localJsonToken;
                  }
                  if (localTokenFilter == null)
                  {
                    this.delegate.nextToken();
                    this.delegate.skipChildren();
                  }
                  else
                  {
                    localObject = localTokenFilter.includeProperty((String)localObject);
                    if (localObject != null) {
                      break label563;
                    }
                    this.delegate.nextToken();
                    this.delegate.skipChildren();
                  }
                  break;
                }
              }
              this._itemFilter = ((TokenFilter)localObject);
              if (localObject != TokenFilter.INCLUDE_ALL) {
                break;
              }
            } while (!this._includePath);
            this._currToken = localJsonToken;
            return localJsonToken;
          } while (!this._includePath);
          localObject = _nextTokenWithBuffering(this._headContext);
        } while (localObject == null);
        this._currToken = ((JsonToken)localObject);
        return (JsonToken)localObject;
      } while (localObject == null);
      localObject = this._headContext.checkValue((TokenFilter)localObject);
    } while ((localObject != TokenFilter.INCLUDE_ALL) && ((localObject == null) || (!((TokenFilter)localObject).includeValue(this.delegate))));
    this._currToken = localJsonToken;
    return localJsonToken;
  }
  
  protected final JsonToken _nextTokenWithBuffering(TokenFilterContext paramTokenFilterContext)
    throws IOException
  {
    Object localObject;
    label14:
    label356:
    label417:
    label519:
    label539:
    do
    {
      do
      {
        do
        {
          for (;;)
          {
            localObject = this.delegate.nextToken();
            if (localObject == null) {
              return (JsonToken)localObject;
            }
            TokenFilter localTokenFilter;
            switch (((JsonToken)localObject).id())
            {
            default: 
              localObject = this._itemFilter;
              if (localObject != TokenFilter.INCLUDE_ALL) {
                break label539;
              }
              return _nextBuffered(paramTokenFilterContext);
            case 3: 
              localTokenFilter = this._headContext.checkValue(this._itemFilter);
              if (localTokenFilter == null)
              {
                this.delegate.skipChildren();
              }
              else
              {
                localObject = localTokenFilter;
                if (localTokenFilter != TokenFilter.INCLUDE_ALL) {
                  localObject = localTokenFilter.filterStartArray();
                }
                this._itemFilter = ((TokenFilter)localObject);
                if (localObject == TokenFilter.INCLUDE_ALL)
                {
                  this._headContext = this._headContext.createChildArrayContext((TokenFilter)localObject, true);
                  return _nextBuffered(paramTokenFilterContext);
                }
                this._headContext = this._headContext.createChildArrayContext((TokenFilter)localObject, false);
              }
              break;
            case 1: 
              localTokenFilter = this._itemFilter;
              if (localTokenFilter == TokenFilter.INCLUDE_ALL)
              {
                this._headContext = this._headContext.createChildObjectContext(localTokenFilter, true);
                return (JsonToken)localObject;
              }
              if (localTokenFilter == null)
              {
                this.delegate.skipChildren();
              }
              else
              {
                localTokenFilter = this._headContext.checkValue(localTokenFilter);
                if (localTokenFilter == null)
                {
                  this.delegate.skipChildren();
                }
                else
                {
                  localObject = localTokenFilter;
                  if (localTokenFilter != TokenFilter.INCLUDE_ALL) {
                    localObject = localTokenFilter.filterStartObject();
                  }
                  this._itemFilter = ((TokenFilter)localObject);
                  if (localObject == TokenFilter.INCLUDE_ALL)
                  {
                    this._headContext = this._headContext.createChildObjectContext((TokenFilter)localObject, true);
                    return _nextBuffered(paramTokenFilterContext);
                  }
                  this._headContext = this._headContext.createChildObjectContext((TokenFilter)localObject, false);
                }
              }
              break;
            case 2: 
            case 4: 
              localTokenFilter = this._headContext.getFilter();
              if ((localTokenFilter != null) && (localTokenFilter != TokenFilter.INCLUDE_ALL)) {
                localTokenFilter.filterFinishArray();
              }
              int i;
              if (this._headContext == paramTokenFilterContext)
              {
                i = 1;
                if ((i == 0) || (!this._headContext.isStartHandled())) {
                  break label417;
                }
              }
              for (int j = 1;; j = 0)
              {
                this._headContext = this._headContext.getParent();
                this._itemFilter = this._headContext.getFilter();
                if (j != 0) {
                  break label14;
                }
                if ((i == 0) && (this._headContext != paramTokenFilterContext)) {
                  break;
                }
                return null;
                i = 0;
                break label356;
              }
            case 5: 
              localObject = this.delegate.getCurrentName();
              localTokenFilter = this._headContext.setFieldName((String)localObject);
              if (localTokenFilter == TokenFilter.INCLUDE_ALL)
              {
                this._itemFilter = localTokenFilter;
                return _nextBuffered(paramTokenFilterContext);
              }
              if (localTokenFilter == null)
              {
                this.delegate.nextToken();
                this.delegate.skipChildren();
              }
              else
              {
                localObject = localTokenFilter.includeProperty((String)localObject);
                if (localObject != null) {
                  break label519;
                }
                this.delegate.nextToken();
                this.delegate.skipChildren();
              }
              break;
            }
          }
          this._itemFilter = ((TokenFilter)localObject);
        } while (localObject != TokenFilter.INCLUDE_ALL);
        return _nextBuffered(paramTokenFilterContext);
      } while (localObject == null);
      localObject = this._headContext.checkValue((TokenFilter)localObject);
    } while ((localObject != TokenFilter.INCLUDE_ALL) && ((localObject == null) || (!((TokenFilter)localObject).includeValue(this.delegate))));
    return _nextBuffered(paramTokenFilterContext);
  }
  
  public void clearCurrentToken()
  {
    if (this._currToken != null)
    {
      this._lastClearedToken = this._currToken;
      this._currToken = null;
    }
  }
  
  public BigInteger getBigIntegerValue()
    throws IOException
  {
    return this.delegate.getBigIntegerValue();
  }
  
  public byte[] getBinaryValue(Base64Variant paramBase64Variant)
    throws IOException
  {
    return this.delegate.getBinaryValue(paramBase64Variant);
  }
  
  public boolean getBooleanValue()
    throws IOException
  {
    return this.delegate.getBooleanValue();
  }
  
  public byte getByteValue()
    throws IOException
  {
    return this.delegate.getByteValue();
  }
  
  public JsonLocation getCurrentLocation()
  {
    return this.delegate.getCurrentLocation();
  }
  
  public String getCurrentName()
    throws IOException
  {
    JsonStreamContext localJsonStreamContext = _filterContext();
    if ((this._currToken == JsonToken.START_OBJECT) || (this._currToken == JsonToken.START_ARRAY))
    {
      localJsonStreamContext = localJsonStreamContext.getParent();
      if (localJsonStreamContext == null) {
        return null;
      }
      return localJsonStreamContext.getCurrentName();
    }
    return localJsonStreamContext.getCurrentName();
  }
  
  public JsonToken getCurrentToken()
  {
    return this._currToken;
  }
  
  public final int getCurrentTokenId()
  {
    JsonToken localJsonToken = this._currToken;
    if (localJsonToken == null) {
      return 0;
    }
    return localJsonToken.id();
  }
  
  public BigDecimal getDecimalValue()
    throws IOException
  {
    return this.delegate.getDecimalValue();
  }
  
  public double getDoubleValue()
    throws IOException
  {
    return this.delegate.getDoubleValue();
  }
  
  public Object getEmbeddedObject()
    throws IOException
  {
    return this.delegate.getEmbeddedObject();
  }
  
  public TokenFilter getFilter()
  {
    return this.rootFilter;
  }
  
  public float getFloatValue()
    throws IOException
  {
    return this.delegate.getFloatValue();
  }
  
  public int getIntValue()
    throws IOException
  {
    return this.delegate.getIntValue();
  }
  
  public JsonToken getLastClearedToken()
  {
    return this._lastClearedToken;
  }
  
  public long getLongValue()
    throws IOException
  {
    return this.delegate.getLongValue();
  }
  
  public int getMatchCount()
  {
    return this._matchCount;
  }
  
  public JsonParser.NumberType getNumberType()
    throws IOException
  {
    return this.delegate.getNumberType();
  }
  
  public Number getNumberValue()
    throws IOException
  {
    return this.delegate.getNumberValue();
  }
  
  public JsonStreamContext getParsingContext()
  {
    return _filterContext();
  }
  
  public short getShortValue()
    throws IOException
  {
    return this.delegate.getShortValue();
  }
  
  public String getText()
    throws IOException
  {
    return this.delegate.getText();
  }
  
  public char[] getTextCharacters()
    throws IOException
  {
    return this.delegate.getTextCharacters();
  }
  
  public int getTextLength()
    throws IOException
  {
    return this.delegate.getTextLength();
  }
  
  public int getTextOffset()
    throws IOException
  {
    return this.delegate.getTextOffset();
  }
  
  public JsonLocation getTokenLocation()
  {
    return this.delegate.getTokenLocation();
  }
  
  public boolean getValueAsBoolean()
    throws IOException
  {
    return this.delegate.getValueAsBoolean();
  }
  
  public boolean getValueAsBoolean(boolean paramBoolean)
    throws IOException
  {
    return this.delegate.getValueAsBoolean(paramBoolean);
  }
  
  public double getValueAsDouble()
    throws IOException
  {
    return this.delegate.getValueAsDouble();
  }
  
  public double getValueAsDouble(double paramDouble)
    throws IOException
  {
    return this.delegate.getValueAsDouble(paramDouble);
  }
  
  public int getValueAsInt()
    throws IOException
  {
    return this.delegate.getValueAsInt();
  }
  
  public int getValueAsInt(int paramInt)
    throws IOException
  {
    return this.delegate.getValueAsInt(paramInt);
  }
  
  public long getValueAsLong()
    throws IOException
  {
    return this.delegate.getValueAsLong();
  }
  
  public long getValueAsLong(long paramLong)
    throws IOException
  {
    return this.delegate.getValueAsLong(paramLong);
  }
  
  public String getValueAsString()
    throws IOException
  {
    return this.delegate.getValueAsString();
  }
  
  public String getValueAsString(String paramString)
    throws IOException
  {
    return this.delegate.getValueAsString(paramString);
  }
  
  public boolean hasCurrentToken()
  {
    return this._currToken != null;
  }
  
  public boolean hasTextCharacters()
  {
    return this.delegate.hasTextCharacters();
  }
  
  public final boolean hasToken(JsonToken paramJsonToken)
  {
    return this._currToken == paramJsonToken;
  }
  
  public boolean hasTokenId(int paramInt)
  {
    JsonToken localJsonToken = this._currToken;
    if (localJsonToken == null) {
      if (paramInt != 0) {}
    }
    while (localJsonToken.id() == paramInt)
    {
      return true;
      return false;
    }
    return false;
  }
  
  public boolean isExpectedStartArrayToken()
  {
    return this._currToken == JsonToken.START_ARRAY;
  }
  
  public boolean isExpectedStartObjectToken()
  {
    return this._currToken == JsonToken.START_OBJECT;
  }
  
  public JsonToken nextToken()
    throws IOException
  {
    Object localObject1 = this._exposedContext;
    if (localObject1 != null)
    {
      do
      {
        localObject2 = ((TokenFilterContext)localObject1).nextTokenToRead();
        if (localObject2 != null)
        {
          this._currToken = ((JsonToken)localObject2);
          return (JsonToken)localObject2;
        }
        if (localObject1 == this._headContext)
        {
          this._exposedContext = null;
          if (!((TokenFilterContext)localObject1).inArray()) {
            break;
          }
          localObject1 = this.delegate.getCurrentToken();
          this._currToken = ((JsonToken)localObject1);
          return (JsonToken)localObject1;
        }
        localObject2 = this._headContext.findChildOf((TokenFilterContext)localObject1);
        this._exposedContext = ((TokenFilterContext)localObject2);
        localObject1 = localObject2;
      } while (localObject2 != null);
      throw _constructError("Unexpected problem: chain of filtered context broken");
    }
    Object localObject2 = this.delegate.nextToken();
    if (localObject2 == null)
    {
      this._currToken = ((JsonToken)localObject2);
      return (JsonToken)localObject2;
    }
    switch (((JsonToken)localObject2).id())
    {
    default: 
      localObject1 = this._itemFilter;
      if (localObject1 == TokenFilter.INCLUDE_ALL)
      {
        this._currToken = ((JsonToken)localObject2);
        return (JsonToken)localObject2;
      }
      break;
    case 3: 
      localObject1 = this._itemFilter;
      if (localObject1 == TokenFilter.INCLUDE_ALL)
      {
        this._headContext = this._headContext.createChildArrayContext((TokenFilter)localObject1, true);
        this._currToken = ((JsonToken)localObject2);
        return (JsonToken)localObject2;
      }
      if (localObject1 == null) {
        this.delegate.skipChildren();
      }
      break;
    }
    do
    {
      do
      {
        do
        {
          do
          {
            for (;;)
            {
              return _nextToken2();
              TokenFilter localTokenFilter = this._headContext.checkValue((TokenFilter)localObject1);
              if (localTokenFilter == null)
              {
                this.delegate.skipChildren();
              }
              else
              {
                localObject1 = localTokenFilter;
                if (localTokenFilter != TokenFilter.INCLUDE_ALL) {
                  localObject1 = localTokenFilter.filterStartArray();
                }
                this._itemFilter = ((TokenFilter)localObject1);
                if (localObject1 == TokenFilter.INCLUDE_ALL)
                {
                  this._headContext = this._headContext.createChildArrayContext((TokenFilter)localObject1, true);
                  this._currToken = ((JsonToken)localObject2);
                  return (JsonToken)localObject2;
                }
                this._headContext = this._headContext.createChildArrayContext((TokenFilter)localObject1, false);
                if (this._includePath)
                {
                  localObject1 = _nextTokenWithBuffering(this._headContext);
                  if (localObject1 != null)
                  {
                    this._currToken = ((JsonToken)localObject1);
                    return (JsonToken)localObject1;
                    localObject1 = this._itemFilter;
                    if (localObject1 == TokenFilter.INCLUDE_ALL)
                    {
                      this._headContext = this._headContext.createChildObjectContext((TokenFilter)localObject1, true);
                      this._currToken = ((JsonToken)localObject2);
                      return (JsonToken)localObject2;
                    }
                    if (localObject1 == null)
                    {
                      this.delegate.skipChildren();
                    }
                    else
                    {
                      localTokenFilter = this._headContext.checkValue((TokenFilter)localObject1);
                      if (localTokenFilter == null)
                      {
                        this.delegate.skipChildren();
                      }
                      else
                      {
                        localObject1 = localTokenFilter;
                        if (localTokenFilter != TokenFilter.INCLUDE_ALL) {
                          localObject1 = localTokenFilter.filterStartObject();
                        }
                        this._itemFilter = ((TokenFilter)localObject1);
                        if (localObject1 == TokenFilter.INCLUDE_ALL)
                        {
                          this._headContext = this._headContext.createChildObjectContext((TokenFilter)localObject1, true);
                          this._currToken = ((JsonToken)localObject2);
                          return (JsonToken)localObject2;
                        }
                        this._headContext = this._headContext.createChildObjectContext((TokenFilter)localObject1, false);
                        if (this._includePath)
                        {
                          localObject1 = _nextTokenWithBuffering(this._headContext);
                          if (localObject1 != null)
                          {
                            this._currToken = ((JsonToken)localObject1);
                            return (JsonToken)localObject1;
                            boolean bool = this._headContext.isStartHandled();
                            localObject1 = this._headContext.getFilter();
                            if ((localObject1 != null) && (localObject1 != TokenFilter.INCLUDE_ALL)) {
                              ((TokenFilter)localObject1).filterFinishArray();
                            }
                            this._headContext = this._headContext.getParent();
                            this._itemFilter = this._headContext.getFilter();
                            if (bool)
                            {
                              this._currToken = ((JsonToken)localObject2);
                              return (JsonToken)localObject2;
                              localObject1 = this.delegate.getCurrentName();
                              localTokenFilter = this._headContext.setFieldName((String)localObject1);
                              if (localTokenFilter == TokenFilter.INCLUDE_ALL)
                              {
                                this._itemFilter = localTokenFilter;
                                localObject1 = localObject2;
                                if (!this._includePath)
                                {
                                  localObject1 = localObject2;
                                  if (this._includeImmediateParent)
                                  {
                                    localObject1 = localObject2;
                                    if (!this._headContext.isStartHandled())
                                    {
                                      localObject1 = this._headContext.nextTokenToRead();
                                      this._exposedContext = this._headContext;
                                    }
                                  }
                                }
                                this._currToken = ((JsonToken)localObject1);
                                return (JsonToken)localObject1;
                              }
                              if (localTokenFilter == null)
                              {
                                this.delegate.nextToken();
                                this.delegate.skipChildren();
                              }
                              else
                              {
                                localObject1 = localTokenFilter.includeProperty((String)localObject1);
                                if (localObject1 != null) {
                                  break;
                                }
                                this.delegate.nextToken();
                                this.delegate.skipChildren();
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
            this._itemFilter = ((TokenFilter)localObject1);
            if ((localObject1 == TokenFilter.INCLUDE_ALL) && (this._includePath))
            {
              this._currToken = ((JsonToken)localObject2);
              return (JsonToken)localObject2;
            }
          } while (!this._includePath);
          localObject1 = _nextTokenWithBuffering(this._headContext);
        } while (localObject1 == null);
        this._currToken = ((JsonToken)localObject1);
        return (JsonToken)localObject1;
      } while (localObject1 == null);
      localObject1 = this._headContext.checkValue((TokenFilter)localObject1);
    } while ((localObject1 != TokenFilter.INCLUDE_ALL) && ((localObject1 == null) || (!((TokenFilter)localObject1).includeValue(this.delegate))));
    this._currToken = ((JsonToken)localObject2);
    return (JsonToken)localObject2;
  }
  
  public JsonToken nextValue()
    throws IOException
  {
    JsonToken localJsonToken2 = nextToken();
    JsonToken localJsonToken1 = localJsonToken2;
    if (localJsonToken2 == JsonToken.FIELD_NAME) {
      localJsonToken1 = nextToken();
    }
    return localJsonToken1;
  }
  
  public void overrideCurrentName(String paramString)
  {
    throw new UnsupportedOperationException("Can not currently override name during filtering read");
  }
  
  public int readBinaryValue(Base64Variant paramBase64Variant, OutputStream paramOutputStream)
    throws IOException
  {
    return this.delegate.readBinaryValue(paramBase64Variant, paramOutputStream);
  }
  
  public JsonParser skipChildren()
    throws IOException
  {
    if ((this._currToken != JsonToken.START_OBJECT) && (this._currToken != JsonToken.START_ARRAY)) {
      return this;
    }
    int i = 1;
    label47:
    int j;
    do
    {
      JsonToken localJsonToken;
      do
      {
        for (;;)
        {
          localJsonToken = nextToken();
          if (localJsonToken == null) {
            break;
          }
          if (!localJsonToken.isStructStart()) {
            break label47;
          }
          i += 1;
        }
      } while (!localJsonToken.isStructEnd());
      j = i - 1;
      i = j;
    } while (j != 0);
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/filter/FilteringParserDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */