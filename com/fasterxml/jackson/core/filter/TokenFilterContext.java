package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;

public class TokenFilterContext
  extends JsonStreamContext
{
  protected TokenFilterContext _child = null;
  protected String _currentName;
  protected TokenFilter _filter;
  protected boolean _needToHandleName;
  protected final TokenFilterContext _parent;
  protected boolean _startHandled;
  
  protected TokenFilterContext(int paramInt, TokenFilterContext paramTokenFilterContext, TokenFilter paramTokenFilter, boolean paramBoolean)
  {
    this._type = paramInt;
    this._parent = paramTokenFilterContext;
    this._filter = paramTokenFilter;
    this._index = -1;
    this._startHandled = paramBoolean;
    this._needToHandleName = false;
  }
  
  private void _writePath(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if ((this._filter == null) || (this._filter == TokenFilter.INCLUDE_ALL)) {}
    do
    {
      do
      {
        do
        {
          return;
          if (this._parent != null) {
            this._parent._writePath(paramJsonGenerator);
          }
          if (!this._startHandled) {
            break;
          }
        } while (!this._needToHandleName);
        this._needToHandleName = false;
        paramJsonGenerator.writeFieldName(this._currentName);
        return;
        this._startHandled = true;
        if (this._type != 2) {
          break;
        }
        paramJsonGenerator.writeStartObject();
      } while (!this._needToHandleName);
      this._needToHandleName = false;
      paramJsonGenerator.writeFieldName(this._currentName);
      return;
    } while (this._type != 1);
    paramJsonGenerator.writeStartArray();
  }
  
  public static TokenFilterContext createRootContext(TokenFilter paramTokenFilter)
  {
    return new TokenFilterContext(0, null, paramTokenFilter, true);
  }
  
  protected void appendDesc(StringBuilder paramStringBuilder)
  {
    if (this._parent != null) {
      this._parent.appendDesc(paramStringBuilder);
    }
    if (this._type == 2)
    {
      paramStringBuilder.append('{');
      if (this._currentName != null)
      {
        paramStringBuilder.append('"');
        paramStringBuilder.append(this._currentName);
        paramStringBuilder.append('"');
      }
      for (;;)
      {
        paramStringBuilder.append('}');
        return;
        paramStringBuilder.append('?');
      }
    }
    if (this._type == 1)
    {
      paramStringBuilder.append('[');
      paramStringBuilder.append(getCurrentIndex());
      paramStringBuilder.append(']');
      return;
    }
    paramStringBuilder.append("/");
  }
  
  public TokenFilter checkValue(TokenFilter paramTokenFilter)
  {
    if (this._type == 2) {
      return paramTokenFilter;
    }
    int i = this._index + 1;
    this._index = i;
    if (this._type == 1) {
      return paramTokenFilter.includeElement(i);
    }
    return paramTokenFilter.includeRootValue(i);
  }
  
  public TokenFilterContext closeArray(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if (this._startHandled) {
      paramJsonGenerator.writeEndArray();
    }
    if ((this._filter != null) && (this._filter != TokenFilter.INCLUDE_ALL)) {
      this._filter.filterFinishArray();
    }
    return this._parent;
  }
  
  public TokenFilterContext closeObject(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if (this._startHandled) {
      paramJsonGenerator.writeEndObject();
    }
    if ((this._filter != null) && (this._filter != TokenFilter.INCLUDE_ALL)) {
      this._filter.filterFinishObject();
    }
    return this._parent;
  }
  
  public TokenFilterContext createChildArrayContext(TokenFilter paramTokenFilter, boolean paramBoolean)
  {
    TokenFilterContext localTokenFilterContext = this._child;
    if (localTokenFilterContext == null)
    {
      paramTokenFilter = new TokenFilterContext(1, this, paramTokenFilter, paramBoolean);
      this._child = paramTokenFilter;
      return paramTokenFilter;
    }
    return localTokenFilterContext.reset(1, paramTokenFilter, paramBoolean);
  }
  
  public TokenFilterContext createChildObjectContext(TokenFilter paramTokenFilter, boolean paramBoolean)
  {
    TokenFilterContext localTokenFilterContext = this._child;
    if (localTokenFilterContext == null)
    {
      paramTokenFilter = new TokenFilterContext(2, this, paramTokenFilter, paramBoolean);
      this._child = paramTokenFilter;
      return paramTokenFilter;
    }
    return localTokenFilterContext.reset(2, paramTokenFilter, paramBoolean);
  }
  
  public TokenFilterContext findChildOf(TokenFilterContext paramTokenFilterContext)
  {
    if (this._parent == paramTokenFilterContext) {
      return this;
    }
    TokenFilterContext localTokenFilterContext;
    for (Object localObject = this._parent; localObject != null; localObject = localTokenFilterContext)
    {
      localTokenFilterContext = ((TokenFilterContext)localObject)._parent;
      if (localTokenFilterContext == paramTokenFilterContext) {
        return (TokenFilterContext)localObject;
      }
    }
    return null;
  }
  
  public final String getCurrentName()
  {
    return this._currentName;
  }
  
  public Object getCurrentValue()
  {
    return null;
  }
  
  public TokenFilter getFilter()
  {
    return this._filter;
  }
  
  public final TokenFilterContext getParent()
  {
    return this._parent;
  }
  
  public boolean isStartHandled()
  {
    return this._startHandled;
  }
  
  public JsonToken nextTokenToRead()
  {
    if (!this._startHandled)
    {
      this._startHandled = true;
      if (this._type == 2) {
        return JsonToken.START_OBJECT;
      }
      return JsonToken.START_ARRAY;
    }
    if ((this._needToHandleName) && (this._type == 2))
    {
      this._needToHandleName = false;
      return JsonToken.FIELD_NAME;
    }
    return null;
  }
  
  protected TokenFilterContext reset(int paramInt, TokenFilter paramTokenFilter, boolean paramBoolean)
  {
    this._type = paramInt;
    this._filter = paramTokenFilter;
    this._index = -1;
    this._currentName = null;
    this._startHandled = paramBoolean;
    this._needToHandleName = false;
    return this;
  }
  
  public void setCurrentValue(Object paramObject) {}
  
  public TokenFilter setFieldName(String paramString)
    throws JsonProcessingException
  {
    this._currentName = paramString;
    this._needToHandleName = true;
    return this._filter;
  }
  
  public void skipParentChecks()
  {
    this._filter = null;
    for (TokenFilterContext localTokenFilterContext = this._parent; localTokenFilterContext != null; localTokenFilterContext = localTokenFilterContext._parent) {
      this._parent._filter = null;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(64);
    appendDesc(localStringBuilder);
    return localStringBuilder.toString();
  }
  
  public void writeImmediatePath(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if ((this._filter == null) || (this._filter == TokenFilter.INCLUDE_ALL)) {}
    do
    {
      do
      {
        do
        {
          return;
          if (!this._startHandled) {
            break;
          }
        } while (!this._needToHandleName);
        paramJsonGenerator.writeFieldName(this._currentName);
        return;
        this._startHandled = true;
        if (this._type != 2) {
          break;
        }
        paramJsonGenerator.writeStartObject();
      } while (!this._needToHandleName);
      paramJsonGenerator.writeFieldName(this._currentName);
      return;
    } while (this._type != 1);
    paramJsonGenerator.writeStartArray();
  }
  
  public void writePath(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if ((this._filter == null) || (this._filter == TokenFilter.INCLUDE_ALL)) {}
    do
    {
      do
      {
        return;
        if (this._parent != null) {
          this._parent._writePath(paramJsonGenerator);
        }
        if (!this._startHandled) {
          break;
        }
      } while (!this._needToHandleName);
      paramJsonGenerator.writeFieldName(this._currentName);
      return;
      this._startHandled = true;
      if (this._type == 2)
      {
        paramJsonGenerator.writeStartObject();
        paramJsonGenerator.writeFieldName(this._currentName);
        return;
      }
    } while (this._type != 1);
    paramJsonGenerator.writeStartArray();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/filter/TokenFilterContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */