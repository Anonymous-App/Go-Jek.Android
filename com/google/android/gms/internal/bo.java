package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;

@ez
public class bo
  extends br.a
  implements bq.a
{
  private final Object mw = new Object();
  private final String pl;
  private final Drawable pm;
  private final String pn;
  private final Drawable po;
  private final String pp;
  private final double pq;
  private final String pr;
  private final String ps;
  private bq pt;
  
  public bo(String paramString1, Drawable paramDrawable1, String paramString2, Drawable paramDrawable2, String paramString3, double paramDouble, String paramString4, String paramString5)
  {
    this.pl = paramString1;
    this.pm = paramDrawable1;
    this.pn = paramString2;
    this.po = paramDrawable2;
    this.pp = paramString3;
    this.pq = paramDouble;
    this.pr = paramString4;
    this.ps = paramString5;
  }
  
  public void a(bq parambq)
  {
    synchronized (this.mw)
    {
      this.pt = parambq;
      return;
    }
  }
  
  public void as()
  {
    synchronized (this.mw)
    {
      if (this.pt == null)
      {
        gs.T("Attempt to record impression before app install ad initialized.");
        return;
      }
      this.pt.as();
      return;
    }
  }
  
  public String bt()
  {
    return this.pl;
  }
  
  public d bu()
  {
    return e.k(this.pm);
  }
  
  public d bv()
  {
    return e.k(this.po);
  }
  
  public String bw()
  {
    return this.pp;
  }
  
  public double bx()
  {
    return this.pq;
  }
  
  public String by()
  {
    return this.pr;
  }
  
  public String bz()
  {
    return this.ps;
  }
  
  public String getBody()
  {
    return this.pn;
  }
  
  public void i(int paramInt)
  {
    synchronized (this.mw)
    {
      if (this.pt == null)
      {
        gs.T("Attempt to perform click before app install ad initialized.");
        return;
      }
      this.pt.b("2", paramInt);
      return;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/bo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */