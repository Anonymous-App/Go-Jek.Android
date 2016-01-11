package com.google.android.gms.analytics;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class aj
{
  private static final char[] BJ = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
  
  static String C(boolean paramBoolean)
  {
    if (paramBoolean) {
      return "1";
    }
    return "0";
  }
  
  public static double a(String paramString, double paramDouble)
  {
    if (paramString == null) {
      return paramDouble;
    }
    try
    {
      double d = Double.parseDouble(paramString);
      return d;
    }
    catch (NumberFormatException paramString) {}
    return paramDouble;
  }
  
  static String a(Locale paramLocale)
  {
    if (paramLocale == null) {}
    while (TextUtils.isEmpty(paramLocale.getLanguage())) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramLocale.getLanguage().toLowerCase());
    if (!TextUtils.isEmpty(paramLocale.getCountry())) {
      localStringBuilder.append("-").append(paramLocale.getCountry().toLowerCase());
    }
    return localStringBuilder.toString();
  }
  
  public static void a(Map<String, String> paramMap, String paramString, l paraml)
  {
    if (!paramMap.containsKey(paramString)) {
      paramMap.put(paramString, paraml.getValue(paramString));
    }
  }
  
  public static void a(Map<String, String> paramMap, String paramString1, String paramString2)
  {
    if (!paramMap.containsKey(paramString1)) {
      paramMap.put(paramString1, paramString2);
    }
  }
  
  public static Map<String, String> an(String paramString)
  {
    HashMap localHashMap = new HashMap();
    paramString = paramString.split("&");
    int j = paramString.length;
    int i = 0;
    if (i < j)
    {
      String[] arrayOfString = paramString[i].split("=");
      if (arrayOfString.length > 1) {
        localHashMap.put(arrayOfString[0], arrayOfString[1]);
      }
      for (;;)
      {
        i += 1;
        break;
        if ((arrayOfString.length == 1) && (arrayOfString[0].length() != 0)) {
          localHashMap.put(arrayOfString[0], null);
        }
      }
    }
    return localHashMap;
  }
  
  public static String ao(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    Object localObject1 = paramString;
    Object localObject2;
    if (paramString.contains("?"))
    {
      localObject2 = paramString.split("[\\?]");
      localObject1 = paramString;
      if (localObject2.length > 1) {
        localObject1 = localObject2[1];
      }
    }
    if (((String)localObject1).contains("%3D")) {}
    while (((String)localObject1).contains("=")) {
      try
      {
        paramString = URLDecoder.decode((String)localObject1, "UTF-8");
        paramString = an(paramString);
        localObject1 = new String[9];
        localObject1[0] = "dclid";
        localObject1[1] = "utm_source";
        localObject1[2] = "gclid";
        localObject1[3] = "utm_campaign";
        localObject1[4] = "utm_medium";
        localObject1[5] = "utm_term";
        localObject1[6] = "utm_content";
        localObject1[7] = "utm_id";
        localObject1[8] = "gmob_t";
        localObject2 = new StringBuilder();
        int i = 0;
        while (i < localObject1.length)
        {
          if (!TextUtils.isEmpty((CharSequence)paramString.get(localObject1[i])))
          {
            if (((StringBuilder)localObject2).length() > 0) {
              ((StringBuilder)localObject2).append("&");
            }
            ((StringBuilder)localObject2).append(localObject1[i]).append("=").append((String)paramString.get(localObject1[i]));
          }
          i += 1;
        }
        paramString = (String)localObject1;
      }
      catch (UnsupportedEncodingException paramString)
      {
        return null;
      }
    }
    return null;
    return ((StringBuilder)localObject2).toString();
  }
  
  public static MessageDigest ap(String paramString)
  {
    int i = 0;
    while (i < 2) {
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance(paramString);
        if (localMessageDigest != null) {
          return localMessageDigest;
        }
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        i += 1;
      }
    }
    return null;
  }
  
  public static boolean e(String paramString, boolean paramBoolean)
  {
    boolean bool = paramBoolean;
    if (paramString != null)
    {
      if ((!paramString.equalsIgnoreCase("true")) && (!paramString.equalsIgnoreCase("yes")) && (!paramString.equalsIgnoreCase("1"))) {
        break label37;
      }
      bool = true;
    }
    label37:
    do
    {
      return bool;
      if ((paramString.equalsIgnoreCase("false")) || (paramString.equalsIgnoreCase("no"))) {
        break;
      }
      bool = paramBoolean;
    } while (!paramString.equalsIgnoreCase("0"));
    return false;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/aj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */