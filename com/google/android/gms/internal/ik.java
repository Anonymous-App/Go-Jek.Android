package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.Locale;

public final class ik
{
  public static <T> boolean a(T paramT1, T paramT2)
  {
    return ((paramT1 == null) && (paramT2 == null)) || ((paramT1 != null) && (paramT2 != null) && (paramT1.equals(paramT2)));
  }
  
  public static void aF(String paramString)
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("Namespace cannot be null or empty");
    }
    if (paramString.length() > 128) {
      throw new IllegalArgumentException("Invalid namespace length");
    }
    if (!paramString.startsWith("urn:x-cast:")) {
      throw new IllegalArgumentException("Namespace must begin with the prefix \"urn:x-cast:\"");
    }
    if (paramString.length() == "urn:x-cast:".length()) {
      throw new IllegalArgumentException("Namespace must begin with the prefix \"urn:x-cast:\" and have non-empty suffix");
    }
  }
  
  public static String aG(String paramString)
  {
    return "urn:x-cast:" + paramString;
  }
  
  public static long b(double paramDouble)
  {
    return (1000.0D * paramDouble);
  }
  
  public static String b(Locale paramLocale)
  {
    StringBuilder localStringBuilder = new StringBuilder(20);
    localStringBuilder.append(paramLocale.getLanguage());
    String str = paramLocale.getCountry();
    if (!TextUtils.isEmpty(str)) {
      localStringBuilder.append('-').append(str);
    }
    paramLocale = paramLocale.getVariant();
    if (!TextUtils.isEmpty(paramLocale)) {
      localStringBuilder.append('-').append(paramLocale);
    }
    return localStringBuilder.toString();
  }
  
  public static double o(long paramLong)
  {
    return paramLong / 1000.0D;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ik.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */