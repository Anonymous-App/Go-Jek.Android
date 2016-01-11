package com.google.android.gms.internal;

import android.content.Context;
import android.view.MotionEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

@ez
class w
  implements g, Runnable
{
  private u.b lr;
  private final List<Object[]> me = new Vector();
  private final AtomicReference<g> mf = new AtomicReference();
  CountDownLatch mg = new CountDownLatch(1);
  
  public w(u.b paramb)
  {
    this.lr = paramb;
    if (gr.ds())
    {
      gi.a(this);
      return;
    }
    run();
  }
  
  private void ax()
  {
    if (this.me.isEmpty()) {}
    for (;;)
    {
      return;
      Iterator localIterator = this.me.iterator();
      while (localIterator.hasNext())
      {
        Object[] arrayOfObject = (Object[])localIterator.next();
        if (arrayOfObject.length == 1) {
          ((g)this.mf.get()).a((MotionEvent)arrayOfObject[0]);
        } else if (arrayOfObject.length == 3) {
          ((g)this.mf.get()).a(((Integer)arrayOfObject[0]).intValue(), ((Integer)arrayOfObject[1]).intValue(), ((Integer)arrayOfObject[2]).intValue());
        }
      }
    }
  }
  
  public String a(Context paramContext)
  {
    aw();
    g localg = (g)this.mf.get();
    if (localg != null)
    {
      ax();
      return localg.a(paramContext);
    }
    return "";
  }
  
  public String a(Context paramContext, String paramString)
  {
    aw();
    g localg = (g)this.mf.get();
    if (localg != null)
    {
      ax();
      return localg.a(paramContext, paramString);
    }
    return "";
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3)
  {
    g localg = (g)this.mf.get();
    if (localg != null)
    {
      ax();
      localg.a(paramInt1, paramInt2, paramInt3);
      return;
    }
    this.me.add(new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) });
  }
  
  public void a(MotionEvent paramMotionEvent)
  {
    g localg = (g)this.mf.get();
    if (localg != null)
    {
      ax();
      localg.a(paramMotionEvent);
      return;
    }
    this.me.add(new Object[] { paramMotionEvent });
  }
  
  protected void a(g paramg)
  {
    this.mf.set(paramg);
  }
  
  protected void aw()
  {
    try
    {
      this.mg.await();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      gs.d("Interrupted during GADSignals creation.", localInterruptedException);
    }
  }
  
  public void run()
  {
    try
    {
      a(j.a(this.lr.lD.wD, this.lr.lB));
      return;
    }
    finally
    {
      this.mg.countDown();
      this.lr = null;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/w.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */