package com.google.android.gms.internal;

import com.google.android.gms.fitness.data.DataSource;

public class kw
{
  private static final ThreadLocal<String> Ty = new ThreadLocal();
  
  public static String bt(String paramString)
  {
    return s(paramString, (String)Ty.get());
  }
  
  public static DataSource c(DataSource paramDataSource)
  {
    if (!paramDataSource.iO()) {}
    String str;
    do
    {
      return paramDataSource;
      str = (String)Ty.get();
    } while ((jc()) || (str.equals(paramDataSource.getAppPackageName())));
    return paramDataSource.iP();
  }
  
  public static boolean jc()
  {
    String str = (String)Ty.get();
    return (str == null) || (str.startsWith("com.google"));
  }
  
  private static String s(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      return paramString1;
    }
    byte[] arrayOfByte = new byte[paramString1.length() + paramString2.length()];
    System.arraycopy(paramString1.getBytes(), 0, arrayOfByte, 0, paramString1.length());
    System.arraycopy(paramString2.getBytes(), 0, arrayOfByte, paramString1.length(), paramString2.length());
    return Integer.toHexString(kb.a(arrayOfByte, 0, arrayOfByte.length, 0));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/kw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */