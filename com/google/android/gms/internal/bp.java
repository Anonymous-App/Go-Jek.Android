package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;

@ez
public class bp
  extends bs.a
  implements bq.a
{
  private final Object mw = new Object();
  private final String pl;
  private final Drawable pm;
  private final String pn;
  private final String pp;
  private bq pt;
  private final Drawable pu;
  private final String pv;
  
  public bp(String paramString1, Drawable paramDrawable1, String paramString2, Drawable paramDrawable2, String paramString3, String paramString4)
  {
    this.pl = paramString1;
    this.pm = paramDrawable1;
    this.pn = paramString2;
    this.pu = paramDrawable2;
    this.pp = paramString3;
    this.pv = paramString4;
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
        gs.T("Attempt to record impression before content ad initialized.");
        return;
      }
      this.pt.as();
      return;
    }
  }
  
  public d bA()
  {
    return e.k(this.pu);
  }
  
  public String bB()
  {
    return this.pv;
  }
  
  public String bt()
  {
    return this.pl;
  }
  
  public d bu()
  {
    return e.k(this.pm);
  }
  
  public String bw()
  {
    return this.pp;
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
        gs.T("Attempt to perform click before content ad initialized.");
        return;
      }
      this.pt.b("1", paramInt);
      return;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/bp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */