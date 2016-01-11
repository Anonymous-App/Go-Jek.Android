package com.kartuku.digitalwallet.common;

public class StringUtils
{
  public static boolean isNullOrEmpty(String paramString)
  {
    if (paramString == null) {
      return true;
    }
    return paramString.isEmpty();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/StringUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */