package com.google.android.gms.internal;

import android.content.Context;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

@ez
public final class ae
  implements ag
{
  private final Object mw = new Object();
  private final WeakHashMap<fz, af> mx = new WeakHashMap();
  private final ArrayList<af> my = new ArrayList();
  
  public af a(Context paramContext, ay paramay, fz paramfz, View paramView, gt paramgt)
  {
    synchronized (this.mw)
    {
      if (c(paramfz))
      {
        paramContext = (af)this.mx.get(paramfz);
        return paramContext;
      }
      paramContext = new af(paramContext, paramay, paramfz, paramView, paramgt);
      paramContext.a(this);
      this.mx.put(paramfz, paramContext);
      this.my.add(paramContext);
      return paramContext;
    }
  }
  
  public af a(ay paramay, fz paramfz)
  {
    return a(paramfz.rN.getContext(), paramay, paramfz, paramfz.rN, paramfz.rN.dx());
  }
  
  public void a(af paramaf)
  {
    synchronized (this.mw)
    {
      if (!paramaf.aH()) {
        this.my.remove(paramaf);
      }
      return;
    }
  }
  
  public boolean c(fz paramfz)
  {
    for (;;)
    {
      synchronized (this.mw)
      {
        paramfz = (af)this.mx.get(paramfz);
        if ((paramfz != null) && (paramfz.aH()))
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public void d(fz paramfz)
  {
    synchronized (this.mw)
    {
      paramfz = (af)this.mx.get(paramfz);
      if (paramfz != null) {
        paramfz.aF();
      }
      return;
    }
  }
  
  public void pause()
  {
    synchronized (this.mw)
    {
      Iterator localIterator = this.my.iterator();
      if (localIterator.hasNext()) {
        ((af)localIterator.next()).pause();
      }
    }
  }
  
  public void resume()
  {
    synchronized (this.mw)
    {
      Iterator localIterator = this.my.iterator();
      if (localIterator.hasNext()) {
        ((af)localIterator.next()).resume();
      }
    }
  }
  
  public void stop()
  {
    synchronized (this.mw)
    {
      Iterator localIterator = this.my.iterator();
      if (localIterator.hasNext()) {
        ((af)localIterator.next()).stop();
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ae.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */