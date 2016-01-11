package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import com.google.ads.mediation.admob.AdMobAdapter;
import java.util.List;

@ez
public class fe
  extends gg
  implements gw.a
{
  private final ct lq;
  private final Context mContext;
  private final gv md;
  private final Object mw = new Object();
  private cm pR;
  private final Object sV = new Object();
  private fk sZ;
  private final fd.a tm;
  private final fz.a tn;
  private boolean to = false;
  private ck tp;
  private cq tq;
  
  public fe(Context paramContext, fz.a parama, gv paramgv, ct paramct, fd.a parama1)
  {
    this.mContext = paramContext;
    this.tn = parama;
    this.sZ = parama.vw;
    this.md = paramgv;
    this.lq = paramct;
    this.tm = parama1;
    this.pR = parama.vq;
  }
  
  private void a(fi paramfi, long paramLong)
    throws fe.a
  {
    synchronized (this.sV)
    {
      this.tp = new ck(this.mContext, paramfi, this.lq, this.pR);
      this.tq = this.tp.a(paramLong, 60000L);
      switch (this.tq.qx)
      {
      default: 
        throw new a("Unexpected mediation result: " + this.tq.qx, 0);
      }
    }
    throw new a("No fill from any mediation ad networks.", 3);
  }
  
  private boolean c(long paramLong)
    throws fe.a
  {
    paramLong = 60000L - (SystemClock.elapsedRealtime() - paramLong);
    if (paramLong <= 0L) {
      return false;
    }
    try
    {
      this.mw.wait(paramLong);
      return true;
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new a("Ad request cancelled.", -1);
    }
  }
  
  private void f(long paramLong)
    throws fe.a
  {
    gr.wC.post(new Runnable()
    {
      public void run()
      {
        for (;;)
        {
          synchronized (fe.a(fe.this))
          {
            if (fe.c(fe.this).errorCode != -2) {
              return;
            }
            fe.d(fe.this).du().a(fe.this);
            if (fe.c(fe.this).errorCode == -3)
            {
              gs.V("Loading URL in WebView: " + fe.c(fe.this).rP);
              fe.d(fe.this).loadUrl(fe.c(fe.this).rP);
              return;
            }
          }
          gs.V("Loading HTML in WebView.");
          fe.d(fe.this).loadDataWithBaseURL(gj.L(fe.c(fe.this).rP), fe.c(fe.this).tG, "text/html", "UTF-8", null);
        }
      }
    });
    h(paramLong);
  }
  
  private void h(long paramLong)
    throws fe.a
  {
    do
    {
      if (!c(paramLong)) {
        throw new a("Timed out waiting for WebView to finish loading.", 2);
      }
    } while (!this.to);
  }
  
  public void a(gv arg1)
  {
    synchronized (this.mw)
    {
      gs.S("WebView finished loading.");
      this.to = true;
      this.mw.notify();
      return;
    }
  }
  
  public void co()
  {
    for (;;)
    {
      int i;
      long l;
      synchronized (this.mw)
      {
        gs.S("AdRendererBackgroundTask started.");
        final Object localObject1 = this.tn.vv;
        i = this.tn.errorCode;
        try
        {
          l = SystemClock.elapsedRealtime();
          if (this.sZ.tI)
          {
            a((fi)localObject1, l);
            av localav = ((fi)localObject1).tx;
            gv localgv = this.md;
            List localList1 = this.sZ.qf;
            List localList2 = this.sZ.qg;
            List localList3 = this.sZ.tK;
            int j = this.sZ.orientation;
            l = this.sZ.qj;
            String str2 = ((fi)localObject1).tA;
            boolean bool = this.sZ.tI;
            if (this.tq == null) {
              break label448;
            }
            localObject1 = this.tq.qy;
            if (this.tq == null) {
              break label454;
            }
            cu localcu = this.tq.qz;
            if (this.tq == null) {
              break label431;
            }
            str1 = this.tq.qA;
            cm localcm = this.pR;
            if (this.tq == null) {
              break label442;
            }
            localco = this.tq.qB;
            localObject1 = new fz(localav, localgv, localList1, i, localList2, localList3, j, l, str2, bool, (cl)localObject1, localcu, str1, localcm, localco, this.sZ.tJ, this.tn.lH, this.sZ.tH, this.tn.vs, this.sZ.tM, this.sZ.tN, this.tn.vp, null);
            gr.wC.post(new Runnable()
            {
              public void run()
              {
                synchronized (fe.a(fe.this))
                {
                  fe.b(fe.this).a(localObject1);
                  return;
                }
              }
            });
            return;
          }
          if (!this.sZ.tO) {
            break label390;
          }
          g(l);
          continue;
          if (i != -1) {
            break label398;
          }
        }
        catch (a locala)
        {
          i = locala.getErrorCode();
          if (i == 3) {}
        }
        gs.U(locala.getMessage());
        if (this.sZ != null) {
          break label409;
        }
        this.sZ = new fk(i);
        gr.wC.post(new Runnable()
        {
          public void run()
          {
            fe.this.onStop();
          }
        });
      }
      label390:
      f(l);
      continue;
      label398:
      gs.W(locala.getMessage());
      continue;
      label409:
      this.sZ = new fk(i, this.sZ.qj);
      continue;
      label431:
      String str1 = AdMobAdapter.class.getName();
      continue;
      label442:
      co localco = null;
      continue;
      label448:
      Object localObject3 = null;
      continue;
      label454:
      Object localObject4 = null;
    }
  }
  
  protected void g(long paramLong)
    throws fe.a
  {
    final Object localObject = this.md.Y();
    int j;
    if (((ay)localObject).og) {
      j = this.mContext.getResources().getDisplayMetrics().widthPixels;
    }
    for (int i = this.mContext.getResources().getDisplayMetrics().heightPixels;; i = ((ay)localObject).heightPixels)
    {
      localObject = new fc(this, this.md, j, i);
      gr.wC.post(new Runnable()
      {
        public void run()
        {
          synchronized (fe.a(fe.this))
          {
            if (fe.c(fe.this).errorCode != -2) {
              return;
            }
            fe.d(fe.this).du().a(fe.this);
            localObject.b(fe.c(fe.this));
            return;
          }
        }
      });
      h(paramLong);
      if (!((fc)localObject).cA()) {
        break;
      }
      gs.S("Ad-Network indicated no fill with passback URL.");
      throw new a("AdNetwork sent passback url", 3);
      j = ((ay)localObject).widthPixels;
    }
    if (!((fc)localObject).cB()) {
      throw new a("AdNetwork timed out", 2);
    }
  }
  
  public void onStop()
  {
    synchronized (this.sV)
    {
      this.md.stopLoading();
      gj.a(this.md);
      if (this.tp != null) {
        this.tp.cancel();
      }
      return;
    }
  }
  
  private static final class a
    extends Exception
  {
    private final int tc;
    
    public a(String paramString, int paramInt)
    {
      super();
      this.tc = paramInt;
    }
    
    public int getErrorCode()
    {
      return this.tc;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/fe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */