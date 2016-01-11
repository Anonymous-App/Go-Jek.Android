package com.google.android.gms.internal;

import java.util.Map;
import java.util.concurrent.Future;

@ez
public final class ft
{
  private gv md;
  private final Object mw = new Object();
  private String uq;
  private gk<fv> ur = new gk();
  public final by us = new by()
  {
    public void a(gv arg1, Map<String, String> paramAnonymousMap)
    {
      synchronized (ft.a(ft.this))
      {
        if (ft.b(ft.this).isDone()) {
          return;
        }
        paramAnonymousMap = new fv(1, paramAnonymousMap);
        gs.W("Invalid " + paramAnonymousMap.getType() + " request error: " + paramAnonymousMap.cL());
        ft.b(ft.this).a(paramAnonymousMap);
        return;
      }
    }
  };
  public final by ut = new by()
  {
    public void a(gv paramAnonymousgv, Map<String, String> paramAnonymousMap)
    {
      fv localfv;
      String str;
      synchronized (ft.a(ft.this))
      {
        if (ft.b(ft.this).isDone()) {
          return;
        }
        localfv = new fv(-2, paramAnonymousMap);
        str = localfv.getUrl();
        if (str == null)
        {
          gs.W("URL missing in loadAdUrl GMSG.");
          return;
        }
      }
      if (str.contains("%40mediation_adapters%40"))
      {
        paramAnonymousgv = str.replaceAll("%40mediation_adapters%40", gf.a(paramAnonymousgv.getContext(), (String)paramAnonymousMap.get("check_adapters"), ft.c(ft.this)));
        localfv.setUrl(paramAnonymousgv);
        gs.V("Ad request URL modified to " + paramAnonymousgv);
      }
      ft.b(ft.this).a(localfv);
    }
  };
  
  public ft(String paramString)
  {
    this.uq = paramString;
  }
  
  public void b(gv paramgv)
  {
    this.md = paramgv;
  }
  
  public Future<fv> cK()
  {
    return this.ur;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */