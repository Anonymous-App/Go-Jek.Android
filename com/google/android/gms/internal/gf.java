package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import java.math.BigInteger;
import java.util.Locale;

@ez
public final class gf
{
  private static final Object uf = new Object();
  private static String we;
  
  public static String a(Context paramContext, String paramString1, String paramString2)
  {
    synchronized (uf)
    {
      if ((we == null) && (!TextUtils.isEmpty(paramString1))) {
        b(paramContext, paramString1, paramString2);
      }
      paramContext = we;
      return paramContext;
    }
  }
  
  private static void b(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramString2 = paramContext.createPackageContext(paramString2, 3).getClassLoader();
      Class localClass = Class.forName("com.google.ads.mediation.MediationAdapter", false, paramString2);
      paramContext = new BigInteger(new byte[1]);
      String[] arrayOfString = paramString1.split(",");
      int i = 0;
      while (i < arrayOfString.length)
      {
        paramString1 = paramContext;
        if (gj.a(paramString2, localClass, arrayOfString[i])) {
          paramString1 = paramContext.setBit(i);
        }
        i += 1;
        paramContext = paramString1;
      }
    }
    catch (Throwable paramContext)
    {
      we = "err";
      return;
    }
    tmp93_90[0] = paramContext;
    we = String.format(Locale.US, "%X", tmp93_90);
  }
  
  public static String di()
  {
    synchronized (uf)
    {
      String str = we;
      return str;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/gf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */