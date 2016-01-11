package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import java.util.Iterator;
import java.util.List;

@ez
public final class ck
{
  private final ct lq;
  private final Context mContext;
  private final Object mw = new Object();
  private final fi pQ;
  private final cm pR;
  private boolean pS = false;
  private cp pT;
  
  public ck(Context paramContext, fi paramfi, ct paramct, cm paramcm)
  {
    this.mContext = paramContext;
    this.pQ = paramfi;
    this.lq = paramct;
    this.pR = paramcm;
  }
  
  public cq a(long paramLong1, long paramLong2)
  {
    gs.S("Starting mediation.");
    Object localObject2 = this.pR.qd.iterator();
    while (((Iterator)localObject2).hasNext())
    {
      cl localcl = (cl)((Iterator)localObject2).next();
      gs.U("Trying mediation network: " + localcl.pX);
      Iterator localIterator = localcl.pY.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        synchronized (this.mw)
        {
          if (this.pS)
          {
            localObject2 = new cq(-1);
            return (cq)localObject2;
          }
          this.pT = new cp(this.mContext, str, this.lq, this.pR, localcl, this.pQ.tx, this.pQ.lH, this.pQ.lD);
          ??? = this.pT.b(paramLong1, paramLong2);
          if (((cq)???).qx == 0)
          {
            gs.S("Adapter succeeded.");
            return (cq)???;
          }
        }
        if (((cq)???).qz != null) {
          gr.wC.post(new Runnable()
          {
            public void run()
            {
              try
              {
                localObject1.qz.destroy();
                return;
              }
              catch (RemoteException localRemoteException)
              {
                gs.d("Could not destroy mediation adapter.", localRemoteException);
              }
            }
          });
        }
      }
    }
    return new cq(1);
  }
  
  public void cancel()
  {
    synchronized (this.mw)
    {
      this.pS = true;
      if (this.pT != null) {
        this.pT.cancel();
      }
      return;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ck.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */