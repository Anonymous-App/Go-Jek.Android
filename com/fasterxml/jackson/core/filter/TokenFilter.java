package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.JsonParser;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class TokenFilter
{
  public static final TokenFilter INCLUDE_ALL = new TokenFilter();
  
  protected boolean _includeScalar()
  {
    return true;
  }
  
  public void filterFinishArray() {}
  
  public void filterFinishObject() {}
  
  public TokenFilter filterStartArray()
  {
    return this;
  }
  
  public TokenFilter filterStartObject()
  {
    return this;
  }
  
  public boolean includeBinary()
  {
    return _includeScalar();
  }
  
  public boolean includeBoolean(boolean paramBoolean)
  {
    return _includeScalar();
  }
  
  public TokenFilter includeElement(int paramInt)
  {
    return this;
  }
  
  public boolean includeEmbeddedValue(Object paramObject)
  {
    return _includeScalar();
  }
  
  public boolean includeNull()
  {
    return _includeScalar();
  }
  
  public boolean includeNumber(double paramDouble)
  {
    return _includeScalar();
  }
  
  public boolean includeNumber(float paramFloat)
  {
    return _includeScalar();
  }
  
  public boolean includeNumber(int paramInt)
  {
    return _includeScalar();
  }
  
  public boolean includeNumber(long paramLong)
  {
    return _includeScalar();
  }
  
  public boolean includeNumber(BigDecimal paramBigDecimal)
  {
    return _includeScalar();
  }
  
  public boolean includeNumber(BigInteger paramBigInteger)
  {
    return _includeScalar();
  }
  
  public TokenFilter includeProperty(String paramString)
  {
    return this;
  }
  
  public boolean includeRawValue()
  {
    return _includeScalar();
  }
  
  public TokenFilter includeRootValue(int paramInt)
  {
    return this;
  }
  
  public boolean includeString(String paramString)
  {
    return _includeScalar();
  }
  
  public boolean includeValue(JsonParser paramJsonParser)
    throws IOException
  {
    return _includeScalar();
  }
  
  public String toString()
  {
    if (this == INCLUDE_ALL) {
      return "TokenFilter.INCLUDE_ALL";
    }
    return super.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/filter/TokenFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */