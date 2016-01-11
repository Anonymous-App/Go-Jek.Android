package com.google.android.gms.analytics;

import android.content.Context;

class u
  extends j<v>
{
  public u(Context paramContext)
  {
    super(paramContext, new a());
  }
  
  private static class a
    implements j.a<v>
  {
    private final v Ar = new v();
    
    public void c(String paramString, int paramInt)
    {
      if ("ga_dispatchPeriod".equals(paramString))
      {
        this.Ar.At = paramInt;
        return;
      }
      z.W("int configuration name not recognized:  " + paramString);
    }
    
    public void d(String paramString, boolean paramBoolean)
    {
      if ("ga_dryRun".equals(paramString))
      {
        paramString = this.Ar;
        if (paramBoolean) {}
        for (int i = 1;; i = 0)
        {
          paramString.Au = i;
          return;
        }
      }
      z.W("bool configuration name not recognized:  " + paramString);
    }
    
    public v es()
    {
      return this.Ar;
    }
    
    public void f(String paramString1, String paramString2) {}
    
    public void g(String paramString1, String paramString2)
    {
      if ("ga_appName".equals(paramString1))
      {
        this.Ar.xL = paramString2;
        return;
      }
      if ("ga_appVersion".equals(paramString1))
      {
        this.Ar.xM = paramString2;
        return;
      }
      if ("ga_logLevel".equals(paramString1))
      {
        this.Ar.As = paramString2;
        return;
      }
      z.W("string configuration name not recognized:  " + paramString1);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/u.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */