package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.JsonPointer;

public class JsonPointerBasedFilter
  extends TokenFilter
{
  protected final JsonPointer _pathToMatch;
  
  public JsonPointerBasedFilter(JsonPointer paramJsonPointer)
  {
    this._pathToMatch = paramJsonPointer;
  }
  
  public JsonPointerBasedFilter(String paramString)
  {
    this(JsonPointer.compile(paramString));
  }
  
  protected boolean _includeScalar()
  {
    return this._pathToMatch.matches();
  }
  
  public TokenFilter filterStartArray()
  {
    return this;
  }
  
  public TokenFilter filterStartObject()
  {
    return this;
  }
  
  public TokenFilter includeElement(int paramInt)
  {
    JsonPointer localJsonPointer = this._pathToMatch.matchElement(paramInt);
    if (localJsonPointer == null) {
      return null;
    }
    if (localJsonPointer.matches()) {
      return TokenFilter.INCLUDE_ALL;
    }
    return new JsonPointerBasedFilter(localJsonPointer);
  }
  
  public TokenFilter includeProperty(String paramString)
  {
    paramString = this._pathToMatch.matchProperty(paramString);
    if (paramString == null) {
      return null;
    }
    if (paramString.matches()) {
      return TokenFilter.INCLUDE_ALL;
    }
    return new JsonPointerBasedFilter(paramString);
  }
  
  public String toString()
  {
    return "[JsonPointerFilter at: " + this._pathToMatch + "]";
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/filter/JsonPointerBasedFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */