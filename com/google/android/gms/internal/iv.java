package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.content.Context;

public abstract class iv<T>
{
  private static a JN = null;
  private static final Object mw = new Object();
  protected final String JO;
  protected final T JP;
  private T JQ = null;
  
  protected iv(String paramString, T paramT)
  {
    this.JO = paramString;
    this.JP = paramT;
  }
  
  public static void H(Context paramContext)
  {
    synchronized (mw)
    {
      if (JN == null) {
        JN = new b(paramContext.getContentResolver());
      }
      return;
    }
  }
  
  public static iv<Integer> a(String paramString, Integer paramInteger)
  {
    new iv(paramString, paramInteger) {};
  }
  
  public static iv<Boolean> g(String paramString, boolean paramBoolean)
  {
    new iv(paramString, Boolean.valueOf(paramBoolean)) {};
  }
  
  public static iv<String> m(String paramString1, String paramString2)
  {
    new iv(paramString1, paramString2) {};
  }
  
  public String getKey()
  {
    return this.JO;
  }
  
  private static abstract interface a {}
  
  private static class b
    implements iv.a
  {
    private final ContentResolver mContentResolver;
    
    public b(ContentResolver paramContentResolver)
    {
      this.mContentResolver = paramContentResolver;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/iv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */