package com.android.volley;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class InternalUtils
{
  private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
  
  private static String convertToHex(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = new char[paramArrayOfByte.length * 2];
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      int j = paramArrayOfByte[i] & 0xFF;
      arrayOfChar[(i * 2)] = HEX_CHARS[(j >>> 4)];
      arrayOfChar[(i * 2 + 1)] = HEX_CHARS[(j & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar);
  }
  
  public static String sha1Hash(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      paramString = paramString.getBytes("UTF-8");
      localMessageDigest.update(paramString, 0, paramString.length);
      paramString = convertToHex(localMessageDigest.digest());
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
      return null;
    }
    catch (UnsupportedEncodingException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/android/volley/InternalUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */