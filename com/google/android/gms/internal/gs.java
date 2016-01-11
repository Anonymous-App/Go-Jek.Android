package com.google.android.gms.internal;

import android.util.Log;

@ez
public final class gs
{
  public static void S(String paramString)
  {
    if (u(3)) {
      Log.d("Ads", paramString);
    }
  }
  
  public static void T(String paramString)
  {
    if (u(6)) {
      Log.e("Ads", paramString);
    }
  }
  
  public static void U(String paramString)
  {
    if (u(4)) {
      Log.i("Ads", paramString);
    }
  }
  
  public static void V(String paramString)
  {
    if (u(2)) {
      Log.v("Ads", paramString);
    }
  }
  
  public static void W(String paramString)
  {
    if (u(5)) {
      Log.w("Ads", paramString);
    }
  }
  
  public static void a(String paramString, Throwable paramThrowable)
  {
    if (u(3)) {
      Log.d("Ads", paramString, paramThrowable);
    }
  }
  
  public static void b(String paramString, Throwable paramThrowable)
  {
    if (u(6)) {
      Log.e("Ads", paramString, paramThrowable);
    }
  }
  
  public static void c(String paramString, Throwable paramThrowable)
  {
    if (u(4)) {
      Log.i("Ads", paramString, paramThrowable);
    }
  }
  
  public static void d(String paramString, Throwable paramThrowable)
  {
    if (u(5)) {
      Log.w("Ads", paramString, paramThrowable);
    }
  }
  
  public static boolean u(int paramInt)
  {
    return ((paramInt >= 5) || (Log.isLoggable("Ads", paramInt))) && (paramInt != 2);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/gs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */