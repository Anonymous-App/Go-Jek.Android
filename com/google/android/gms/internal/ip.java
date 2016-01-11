package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Log;

public class ip
{
  private static boolean GX = false;
  private boolean GY;
  private boolean GZ;
  private String Ha;
  private final String mTag;
  
  public ip(String paramString)
  {
    this(paramString, fS());
  }
  
  public ip(String paramString, boolean paramBoolean)
  {
    this.mTag = paramString;
    this.GY = paramBoolean;
    this.GZ = false;
  }
  
  private String e(String paramString, Object... paramVarArgs)
  {
    if (paramVarArgs.length == 0) {}
    for (;;)
    {
      paramVarArgs = paramString;
      if (!TextUtils.isEmpty(this.Ha)) {
        paramVarArgs = this.Ha + paramString;
      }
      return paramVarArgs;
      paramString = String.format(paramString, paramVarArgs);
    }
  }
  
  public static boolean fS()
  {
    return GX;
  }
  
  public void a(String paramString, Object... paramVarArgs)
  {
    if (fR()) {
      Log.v(this.mTag, e(paramString, paramVarArgs));
    }
  }
  
  public void a(Throwable paramThrowable, String paramString, Object... paramVarArgs)
  {
    if ((fQ()) || (GX)) {
      Log.d(this.mTag, e(paramString, paramVarArgs), paramThrowable);
    }
  }
  
  public void aK(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (paramString = null;; paramString = String.format("[%s] ", new Object[] { paramString }))
    {
      this.Ha = paramString;
      return;
    }
  }
  
  public void b(String paramString, Object... paramVarArgs)
  {
    if ((fQ()) || (GX)) {
      Log.d(this.mTag, e(paramString, paramVarArgs));
    }
  }
  
  public void c(String paramString, Object... paramVarArgs)
  {
    Log.i(this.mTag, e(paramString, paramVarArgs));
  }
  
  public void d(String paramString, Object... paramVarArgs)
  {
    Log.w(this.mTag, e(paramString, paramVarArgs));
  }
  
  public boolean fQ()
  {
    return this.GY;
  }
  
  public boolean fR()
  {
    return this.GZ;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ip.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */