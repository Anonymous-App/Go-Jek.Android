package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.NumberInput;

public class JsonPointer
{
  protected static final JsonPointer EMPTY = new JsonPointer();
  protected final String _asString;
  protected volatile JsonPointer _head;
  protected final int _matchingElementIndex;
  protected final String _matchingPropertyName;
  protected final JsonPointer _nextSegment;
  
  protected JsonPointer()
  {
    this._nextSegment = null;
    this._matchingPropertyName = "";
    this._matchingElementIndex = -1;
    this._asString = "";
  }
  
  protected JsonPointer(String paramString1, String paramString2, int paramInt, JsonPointer paramJsonPointer)
  {
    this._asString = paramString1;
    this._nextSegment = paramJsonPointer;
    this._matchingPropertyName = paramString2;
    this._matchingElementIndex = paramInt;
  }
  
  protected JsonPointer(String paramString1, String paramString2, JsonPointer paramJsonPointer)
  {
    this._asString = paramString1;
    this._nextSegment = paramJsonPointer;
    this._matchingPropertyName = paramString2;
    this._matchingElementIndex = _parseIndex(paramString2);
  }
  
  private static void _appendEscape(StringBuilder paramStringBuilder, char paramChar)
  {
    if (paramChar == '0') {
      paramChar = '~';
    }
    for (;;)
    {
      paramStringBuilder.append(paramChar);
      return;
      if (paramChar == '1') {
        paramChar = '/';
      } else {
        paramStringBuilder.append('~');
      }
    }
  }
  
  private static final int _parseIndex(String paramString)
  {
    int i = 0;
    int j = paramString.length();
    if ((j == 0) || (j > 10)) {}
    label87:
    do
    {
      int k;
      do
      {
        return -1;
        k = paramString.charAt(0);
        if (k <= 48)
        {
          if ((j == 1) && (k == 48)) {}
          for (;;)
          {
            return i;
            i = -1;
          }
        }
      } while (k > 57);
      i = 1;
      for (;;)
      {
        if (i >= j) {
          break label87;
        }
        k = paramString.charAt(i);
        if ((k > 57) || (k < 48)) {
          break;
        }
        i += 1;
      }
    } while ((j == 10) && (NumberInput.parseLong(paramString) > 2147483647L));
    return NumberInput.parseInt(paramString);
  }
  
  protected static JsonPointer _parseQuotedTail(String paramString, int paramInt)
  {
    int i = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(Math.max(16, i));
    if (paramInt > 2) {
      localStringBuilder.append(paramString, 1, paramInt - 1);
    }
    _appendEscape(localStringBuilder, paramString.charAt(paramInt));
    paramInt += 1;
    while (paramInt < i)
    {
      char c = paramString.charAt(paramInt);
      if (c == '/') {
        return new JsonPointer(paramString, localStringBuilder.toString(), _parseTail(paramString.substring(paramInt)));
      }
      paramInt += 1;
      if ((c == '~') && (paramInt < i))
      {
        _appendEscape(localStringBuilder, paramString.charAt(paramInt));
        paramInt += 1;
      }
      else
      {
        localStringBuilder.append(c);
      }
    }
    return new JsonPointer(paramString, localStringBuilder.toString(), EMPTY);
  }
  
  protected static JsonPointer _parseTail(String paramString)
  {
    int k = paramString.length();
    int i = 1;
    while (i < k)
    {
      int m = paramString.charAt(i);
      if (m == 47) {
        return new JsonPointer(paramString, paramString.substring(1, i), _parseTail(paramString.substring(i)));
      }
      int j = i + 1;
      i = j;
      if (m == 126)
      {
        i = j;
        if (j < k) {
          return _parseQuotedTail(paramString, j);
        }
      }
    }
    return new JsonPointer(paramString, paramString.substring(1), EMPTY);
  }
  
  public static JsonPointer compile(String paramString)
    throws IllegalArgumentException
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return EMPTY;
    }
    if (paramString.charAt(0) != '/') {
      throw new IllegalArgumentException("Invalid input: JSON Pointer expression must start with '/': \"" + paramString + "\"");
    }
    return _parseTail(paramString);
  }
  
  public static JsonPointer valueOf(String paramString)
  {
    return compile(paramString);
  }
  
  protected JsonPointer _constructHead()
  {
    JsonPointer localJsonPointer1 = last();
    if (localJsonPointer1 == this) {
      return EMPTY;
    }
    int i = localJsonPointer1._asString.length();
    JsonPointer localJsonPointer2 = this._nextSegment;
    return new JsonPointer(this._asString.substring(0, this._asString.length() - i), this._matchingPropertyName, this._matchingElementIndex, localJsonPointer2._constructHead(i, localJsonPointer1));
  }
  
  protected JsonPointer _constructHead(int paramInt, JsonPointer paramJsonPointer)
  {
    if (this == paramJsonPointer) {
      return EMPTY;
    }
    JsonPointer localJsonPointer = this._nextSegment;
    String str = this._asString;
    return new JsonPointer(str.substring(0, str.length() - paramInt), this._matchingPropertyName, this._matchingElementIndex, localJsonPointer._constructHead(paramInt, paramJsonPointer));
  }
  
  public JsonPointer append(JsonPointer paramJsonPointer)
  {
    if (this == EMPTY) {
      return paramJsonPointer;
    }
    if (paramJsonPointer == EMPTY) {
      return this;
    }
    String str2 = this._asString;
    String str1 = str2;
    if (str2.endsWith("/")) {
      str1 = str2.substring(0, str2.length() - 1);
    }
    return compile(str1 + paramJsonPointer._asString);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramObject == null);
      bool1 = bool2;
    } while (!(paramObject instanceof JsonPointer));
    return this._asString.equals(((JsonPointer)paramObject)._asString);
  }
  
  public int getMatchingIndex()
  {
    return this._matchingElementIndex;
  }
  
  public String getMatchingProperty()
  {
    return this._matchingPropertyName;
  }
  
  public int hashCode()
  {
    return this._asString.hashCode();
  }
  
  public JsonPointer head()
  {
    JsonPointer localJsonPointer1 = this._head;
    JsonPointer localJsonPointer2 = localJsonPointer1;
    if (localJsonPointer1 == null)
    {
      if (this != EMPTY) {
        localJsonPointer1 = _constructHead();
      }
      this._head = localJsonPointer1;
      localJsonPointer2 = localJsonPointer1;
    }
    return localJsonPointer2;
  }
  
  public JsonPointer last()
  {
    JsonPointer localJsonPointer2 = this;
    JsonPointer localJsonPointer1 = localJsonPointer2;
    if (localJsonPointer2 == EMPTY) {
      return null;
    }
    for (;;)
    {
      localJsonPointer2 = localJsonPointer1._nextSegment;
      if (localJsonPointer2 == EMPTY) {
        break;
      }
      localJsonPointer1 = localJsonPointer2;
    }
    return localJsonPointer1;
  }
  
  public JsonPointer matchElement(int paramInt)
  {
    if ((paramInt != this._matchingElementIndex) || (paramInt < 0)) {
      return null;
    }
    return this._nextSegment;
  }
  
  public JsonPointer matchProperty(String paramString)
  {
    if ((this._nextSegment != null) && (this._matchingPropertyName.equals(paramString))) {
      return this._nextSegment;
    }
    return null;
  }
  
  public boolean matches()
  {
    return this._nextSegment == null;
  }
  
  public boolean matchesElement(int paramInt)
  {
    return (paramInt == this._matchingElementIndex) && (paramInt >= 0);
  }
  
  public boolean matchesProperty(String paramString)
  {
    return (this._nextSegment != null) && (this._matchingPropertyName.equals(paramString));
  }
  
  public boolean mayMatchElement()
  {
    return this._matchingElementIndex >= 0;
  }
  
  public boolean mayMatchProperty()
  {
    return this._matchingPropertyName != null;
  }
  
  public JsonPointer tail()
  {
    return this._nextSegment;
  }
  
  public String toString()
  {
    return this._asString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/JsonPointer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */