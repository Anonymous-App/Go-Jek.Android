package com.fasterxml.jackson.core;

public abstract class JsonStreamContext
{
  protected static final int TYPE_ARRAY = 1;
  protected static final int TYPE_OBJECT = 2;
  protected static final int TYPE_ROOT = 0;
  protected int _index;
  protected int _type;
  
  public final int getCurrentIndex()
  {
    if (this._index < 0) {
      return 0;
    }
    return this._index;
  }
  
  public abstract String getCurrentName();
  
  public Object getCurrentValue()
  {
    return null;
  }
  
  public final int getEntryCount()
  {
    return this._index + 1;
  }
  
  public abstract JsonStreamContext getParent();
  
  public final String getTypeDesc()
  {
    switch (this._type)
    {
    default: 
      return "?";
    case 0: 
      return "ROOT";
    case 1: 
      return "ARRAY";
    }
    return "OBJECT";
  }
  
  public final boolean inArray()
  {
    return this._type == 1;
  }
  
  public final boolean inObject()
  {
    return this._type == 2;
  }
  
  public final boolean inRoot()
  {
    return this._type == 0;
  }
  
  public void setCurrentValue(Object paramObject) {}
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/JsonStreamContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */