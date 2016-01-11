package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;

public class JsonWriteContext
  extends JsonStreamContext
{
  public static final int STATUS_EXPECT_NAME = 5;
  public static final int STATUS_EXPECT_VALUE = 4;
  public static final int STATUS_OK_AFTER_COLON = 2;
  public static final int STATUS_OK_AFTER_COMMA = 1;
  public static final int STATUS_OK_AFTER_SPACE = 3;
  public static final int STATUS_OK_AS_IS = 0;
  protected JsonWriteContext _child = null;
  protected String _currentName;
  protected Object _currentValue;
  protected DupDetector _dups;
  protected boolean _gotName;
  protected final JsonWriteContext _parent;
  
  protected JsonWriteContext(int paramInt, JsonWriteContext paramJsonWriteContext, DupDetector paramDupDetector)
  {
    this._type = paramInt;
    this._parent = paramJsonWriteContext;
    this._dups = paramDupDetector;
    this._index = -1;
  }
  
  private final void _checkDup(DupDetector paramDupDetector, String paramString)
    throws JsonProcessingException
  {
    if (paramDupDetector.isDup(paramString)) {
      throw new JsonGenerationException("Duplicate field '" + paramString + "'");
    }
  }
  
  @Deprecated
  public static JsonWriteContext createRootContext()
  {
    return createRootContext(null);
  }
  
  public static JsonWriteContext createRootContext(DupDetector paramDupDetector)
  {
    return new JsonWriteContext(0, null, paramDupDetector);
  }
  
  protected void appendDesc(StringBuilder paramStringBuilder)
  {
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
  
  public JsonWriteContext createChildArrayContext()
  {
    Object localObject = this._child;
    if (localObject == null)
    {
      if (this._dups == null) {}
      for (localObject = null;; localObject = this._dups.child())
      {
        localObject = new JsonWriteContext(1, this, (DupDetector)localObject);
        this._child = ((JsonWriteContext)localObject);
        return (JsonWriteContext)localObject;
      }
    }
    return ((JsonWriteContext)localObject).reset(1);
  }
  
  public JsonWriteContext createChildObjectContext()
  {
    Object localObject = this._child;
    if (localObject == null)
    {
      if (this._dups == null) {}
      for (localObject = null;; localObject = this._dups.child())
      {
        localObject = new JsonWriteContext(2, this, (DupDetector)localObject);
        this._child = ((JsonWriteContext)localObject);
        return (JsonWriteContext)localObject;
      }
    }
    return ((JsonWriteContext)localObject).reset(2);
  }
  
  public final String getCurrentName()
  {
    return this._currentName;
  }
  
  public Object getCurrentValue()
  {
    return this._currentValue;
  }
  
  public DupDetector getDupDetector()
  {
    return this._dups;
  }
  
  public final JsonWriteContext getParent()
  {
    return this._parent;
  }
  
  protected JsonWriteContext reset(int paramInt)
  {
    this._type = paramInt;
    this._index = -1;
    this._currentName = null;
    this._gotName = false;
    this._currentValue = null;
    if (this._dups != null) {
      this._dups.reset();
    }
    return this;
  }
  
  public void setCurrentValue(Object paramObject)
  {
    this._currentValue = paramObject;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(64);
    appendDesc(localStringBuilder);
    return localStringBuilder.toString();
  }
  
  public JsonWriteContext withDupDetector(DupDetector paramDupDetector)
  {
    this._dups = paramDupDetector;
    return this;
  }
  
  public int writeFieldName(String paramString)
    throws JsonProcessingException
  {
    int i = 1;
    if (this._gotName) {
      i = 4;
    }
    do
    {
      return i;
      this._gotName = true;
      this._currentName = paramString;
      if (this._dups != null) {
        _checkDup(this._dups, paramString);
      }
    } while (this._index >= 0);
    return 0;
  }
  
  public int writeValue()
  {
    int i = 0;
    if (this._type == 2) {
      if (!this._gotName) {
        i = 5;
      }
    }
    do
    {
      int j;
      do
      {
        return i;
        this._gotName = false;
        this._index += 1;
        return 2;
        if (this._type != 1) {
          break;
        }
        j = this._index;
        this._index += 1;
      } while (j < 0);
      return 1;
      this._index += 1;
    } while (this._index == 0);
    return 3;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/json/JsonWriteContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */