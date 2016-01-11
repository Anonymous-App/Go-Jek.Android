package com.google.android.gms.analytics;

import android.text.TextUtils;

class w
{
  private String AE;
  private final long AF;
  private final long AG;
  private String AH = "https:";
  
  w(String paramString, long paramLong1, long paramLong2)
  {
    this.AE = paramString;
    this.AF = paramLong1;
    this.AG = paramLong2;
  }
  
  void aj(String paramString)
  {
    this.AE = paramString;
  }
  
  void ak(String paramString)
  {
    if ((paramString == null) || (TextUtils.isEmpty(paramString.trim()))) {}
    while (!paramString.toLowerCase().startsWith("http:")) {
      return;
    }
    this.AH = "http:";
  }
  
  String eF()
  {
    return this.AE;
  }
  
  long eG()
  {
    return this.AF;
  }
  
  long eH()
  {
    return this.AG;
  }
  
  String eI()
  {
    return this.AH;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/w.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */