package com.google.android.gms.analytics;

public final class n
{
  public static String A(int paramInt)
  {
    return d("&promo", paramInt);
  }
  
  public static String B(int paramInt)
  {
    return d("pi", paramInt);
  }
  
  public static String C(int paramInt)
  {
    return d("&il", paramInt);
  }
  
  public static String D(int paramInt)
  {
    return d("cd", paramInt);
  }
  
  public static String E(int paramInt)
  {
    return d("cm", paramInt);
  }
  
  private static String d(String paramString, int paramInt)
  {
    if (paramInt < 1)
    {
      z.T("index out of range for " + paramString + " (" + paramInt + ")");
      return "";
    }
    return paramString + paramInt;
  }
  
  static String x(int paramInt)
  {
    return d("&cd", paramInt);
  }
  
  static String y(int paramInt)
  {
    return d("&cm", paramInt);
  }
  
  public static String z(int paramInt)
  {
    return d("&pr", paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */