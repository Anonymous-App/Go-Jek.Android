package com.gojek.app.gobusway.util;

public class StringUtil
{
  public static String upperCaseFirstLatter(String paramString)
  {
    paramString = paramString.split(" ");
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramString.length;
    int i = 0;
    if (i < j)
    {
      String str = paramString[i];
      if (str.length() == 2) {
        localStringBuilder.append(str.toUpperCase() + " ");
      }
      for (;;)
      {
        i += 1;
        break;
        str = str.substring(0, 1) + str.substring(1).toLowerCase();
        localStringBuilder.append(str + " ");
      }
    }
    return localStringBuilder.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/util/StringUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */