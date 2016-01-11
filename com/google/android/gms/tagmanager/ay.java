package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

class ay
{
  private static String apy;
  static Map<String, String> apz = new HashMap();
  
  static void cF(String paramString)
  {
    try
    {
      apy = paramString;
      return;
    }
    finally {}
  }
  
  static void d(Context paramContext, String paramString)
  {
    cz.a(paramContext, "gtm_install_referrer", "referrer", paramString);
    f(paramContext, paramString);
  }
  
  /* Error */
  static String e(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: getstatic 24	com/google/android/gms/tagmanager/ay:apy	Ljava/lang/String;
    //   3: ifnonnull +40 -> 43
    //   6: ldc 2
    //   8: monitorenter
    //   9: getstatic 24	com/google/android/gms/tagmanager/ay:apy	Ljava/lang/String;
    //   12: ifnonnull +28 -> 40
    //   15: aload_0
    //   16: ldc 28
    //   18: iconst_0
    //   19: invokevirtual 47	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   22: astore_0
    //   23: aload_0
    //   24: ifnull +27 -> 51
    //   27: aload_0
    //   28: ldc 30
    //   30: ldc 49
    //   32: invokeinterface 55 3 0
    //   37: putstatic 24	com/google/android/gms/tagmanager/ay:apy	Ljava/lang/String;
    //   40: ldc 2
    //   42: monitorexit
    //   43: getstatic 24	com/google/android/gms/tagmanager/ay:apy	Ljava/lang/String;
    //   46: aload_1
    //   47: invokestatic 58	com/google/android/gms/tagmanager/ay:x	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   50: areturn
    //   51: ldc 49
    //   53: putstatic 24	com/google/android/gms/tagmanager/ay:apy	Ljava/lang/String;
    //   56: goto -16 -> 40
    //   59: astore_0
    //   60: ldc 2
    //   62: monitorexit
    //   63: aload_0
    //   64: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	65	0	paramContext	Context
    //   0	65	1	paramString	String
    // Exception table:
    //   from	to	target	type
    //   9	23	59	finally
    //   27	40	59	finally
    //   40	43	59	finally
    //   51	56	59	finally
    //   60	63	59	finally
  }
  
  static String f(Context paramContext, String paramString1, String paramString2)
  {
    String str = (String)apz.get(paramString1);
    Object localObject = str;
    if (str == null)
    {
      paramContext = paramContext.getSharedPreferences("gtm_click_referrers", 0);
      if (paramContext == null) {
        break label63;
      }
    }
    label63:
    for (paramContext = paramContext.getString(paramString1, "");; paramContext = "")
    {
      apz.put(paramString1, paramContext);
      localObject = paramContext;
      return x((String)localObject, paramString2);
    }
  }
  
  static void f(Context paramContext, String paramString)
  {
    String str = x(paramString, "conv");
    if ((str != null) && (str.length() > 0))
    {
      apz.put(str, paramString);
      cz.a(paramContext, "gtm_click_referrers", str, paramString);
    }
  }
  
  static String x(String paramString1, String paramString2)
  {
    if (paramString2 == null)
    {
      if (paramString1.length() > 0) {
        return paramString1;
      }
      return null;
    }
    return Uri.parse("http://hostname/?" + paramString1).getQueryParameter(paramString2);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/ay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */