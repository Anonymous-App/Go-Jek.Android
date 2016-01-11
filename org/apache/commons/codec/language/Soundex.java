package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

public class Soundex
  implements StringEncoder
{
  public static final Soundex US_ENGLISH = new Soundex();
  private static final char[] US_ENGLISH_MAPPING = "01230120022455012623010202".toCharArray();
  public static final String US_ENGLISH_MAPPING_STRING = "01230120022455012623010202";
  @Deprecated
  private int maxLength = 4;
  private final char[] soundexMapping;
  
  public Soundex()
  {
    this.soundexMapping = US_ENGLISH_MAPPING;
  }
  
  public Soundex(String paramString)
  {
    this.soundexMapping = paramString.toCharArray();
  }
  
  public Soundex(char[] paramArrayOfChar)
  {
    this.soundexMapping = new char[paramArrayOfChar.length];
    System.arraycopy(paramArrayOfChar, 0, this.soundexMapping, 0, paramArrayOfChar.length);
  }
  
  private char getMappingCode(String paramString, int paramInt)
  {
    char c2 = map(paramString.charAt(paramInt));
    char c1 = c2;
    if (paramInt > 1)
    {
      c1 = c2;
      if (c2 != '0')
      {
        int i = paramString.charAt(paramInt - 1);
        if (72 != i)
        {
          c1 = c2;
          if (87 != i) {}
        }
        else
        {
          char c3 = paramString.charAt(paramInt - 2);
          if ((map(c3) != c2) && ('H' != c3))
          {
            c1 = c2;
            if ('W' != c3) {}
          }
          else
          {
            c1 = '\000';
          }
        }
      }
    }
    return c1;
  }
  
  private char[] getSoundexMapping()
  {
    return this.soundexMapping;
  }
  
  private char map(char paramChar)
  {
    int i = paramChar - 'A';
    if ((i < 0) || (i >= getSoundexMapping().length)) {
      throw new IllegalArgumentException("The character is not mapped: " + paramChar);
    }
    return getSoundexMapping()[i];
  }
  
  public int difference(String paramString1, String paramString2)
    throws EncoderException
  {
    return SoundexUtils.difference(this, paramString1, paramString2);
  }
  
  public Object encode(Object paramObject)
    throws EncoderException
  {
    if (!(paramObject instanceof String)) {
      throw new EncoderException("Parameter supplied to Soundex encode is not of type java.lang.String");
    }
    return soundex((String)paramObject);
  }
  
  public String encode(String paramString)
  {
    return soundex(paramString);
  }
  
  @Deprecated
  public int getMaxLength()
  {
    return this.maxLength;
  }
  
  @Deprecated
  public void setMaxLength(int paramInt)
  {
    this.maxLength = paramInt;
  }
  
  public String soundex(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    paramString = SoundexUtils.clean(paramString);
    if (paramString.length() == 0) {
      return paramString;
    }
    char[] arrayOfChar = new char[4];
    char[] tmp27_25 = arrayOfChar;
    tmp27_25[0] = 48;
    char[] tmp32_27 = tmp27_25;
    tmp32_27[1] = 48;
    char[] tmp37_32 = tmp32_27;
    tmp37_32[2] = 48;
    char[] tmp42_37 = tmp37_32;
    tmp42_37[3] = 48;
    tmp42_37;
    int j = 1;
    int k = 1;
    arrayOfChar[0] = paramString.charAt(0);
    int m = getMappingCode(paramString, 0);
    for (;;)
    {
      int n;
      if ((j < paramString.length()) && (k < arrayOfChar.length))
      {
        n = j + 1;
        int i = getMappingCode(paramString, j);
        if (i != 0)
        {
          j = k;
          if (i != 48)
          {
            j = k;
            if (i != m)
            {
              arrayOfChar[k] = i;
              j = k + 1;
            }
          }
          m = i;
          k = j;
          j = n;
        }
      }
      else
      {
        return new String(arrayOfChar);
      }
      j = n;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/apache/commons/codec/language/Soundex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */