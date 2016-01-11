package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public class fb
  extends gg
  implements ff.a
{
  private final Context mContext;
  private final Object mw = new Object();
  private cm pR;
  private final fa.a sU;
  private final Object sV = new Object();
  private final fi.a sW;
  private final k sX;
  private gg sY;
  private fk sZ;
  
  public fb(Context paramContext, fi.a parama, k paramk, fa.a parama1)
  {
    this.sU = parama1;
    this.mContext = paramContext;
    this.sW = parama;
    this.sX = paramk;
  }
  
  private ay a(fi paramfi)
    throws fb.a
  {
    if (this.sZ.tL == null) {
      throw new a("The ad response must specify one of the supported ad sizes.", 0);
    }
    Object localObject = this.sZ.tL.split("x");
    if (localObject.length != 2) {
      throw new a("Could not parse the ad size from the ad response: " + this.sZ.tL, 0);
    }
    for (;;)
    {
      int i;
      ay localay;
      try
      {
        int m = Integer.parseInt(localObject[0]);
        int n = Integer.parseInt(localObject[1]);
        localObject = paramfi.lH.oh;
        int i1 = localObject.length;
        i = 0;
        if (i >= i1) {
          break;
        }
        localay = localObject[i];
        float f = this.mContext.getResources().getDisplayMetrics().density;
        if (localay.width == -1)
        {
          j = (int)(localay.widthPixels / f);
          if (localay.height != -2) {
            break label253;
          }
          k = (int)(localay.heightPixels / f);
          if ((m != j) || (n != k)) {
            break label263;
          }
          return new ay(localay, paramfi.lH.oh);
        }
      }
      catch (NumberFormatException paramfi)
      {
        throw new a("Could not parse the ad size from the ad response: " + this.sZ.tL, 0);
      }
      int j = localay.width;
      continue;
      label253:
      int k = localay.height;
      continue;
      label263:
      i += 1;
    }
    throw new a("The ad size from the ad response was not one of the requested sizes: " + this.sZ.tL, 0);
  }
  
  private boolean c(long paramLong)
    throws fb.a
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
  
  private void cx()
    throws fb.a
  {
    if (this.sZ.errorCode == -3) {}
    do
    {
      return;
      if (TextUtils.isEmpty(this.sZ.tG)) {
        throw new a("No fill from ad server.", 3);
      }
      gb.a(this.mContext, this.sZ.tF);
    } while (!this.sZ.tI);
    try
    {
      this.pR = new cm(this.sZ.tG);
      return;
    }
    catch (JSONException localJSONException)
    {
      throw new a("Could not parse mediation config: " + this.sZ.tG, 0);
    }
  }
  
  private void e(long paramLong)
    throws fb.a
  {
    do
    {
      if (!c(paramLong)) {
        throw new a("Timed out waiting for ad response.", 2);
      }
    } while (this.sZ == null);
    synchronized (this.sV)
    {
      this.sY = null;
      if ((this.sZ.errorCode != -2) && (this.sZ.errorCode != -3)) {
        throw new a("There was a problem getting an ad response. ErrorCode: " + this.sZ.errorCode, this.sZ.errorCode);
      }
    }
  }
  
  private void r(boolean paramBoolean)
  {
    gb.cU().v(paramBoolean);
    an localan = gb.cU().l(this.mContext);
    if ((localan != null) && (!localan.isAlive()))
    {
      gs.S("start fetching content...");
      localan.aV();
    }
  }
  
  public void a(fk paramfk)
  {
    synchronized (this.mw)
    {
      gs.S("Received ad response.");
      this.sZ = paramfk;
      this.mw.notify();
      return;
    }
  }
  
  public void co()
  {
    for (;;)
    {
      int i;
      long l2;
      long l1;
      synchronized (this.mw)
      {
        gs.S("AdLoaderBackgroundTask started.");
        final Object localObject1 = this.sX.z().a(this.mContext);
        fi localfi = new fi(this.sW, (String)localObject1);
        i = -2;
        l2 = -1L;
        l1 = l2;
        long l3;
        try
        {
          l3 = SystemClock.elapsedRealtime();
          l1 = l2;
          gg localgg = ff.a(this.mContext, localfi, this);
          l1 = l2;
          localObject1 = this.sV;
          l1 = l2;
          try
          {
            this.sY = localgg;
            if (this.sY != null) {
              continue;
            }
            throw new a("Could not start the ad request service.", 0);
          }
          finally
          {
            l1 = l2;
          }
          i = locala1.getErrorCode();
        }
        catch (a locala1)
        {
          localObject1 = null;
        }
        if ((i == 3) || (i == -1))
        {
          gs.U(locala1.getMessage());
          if (this.sZ != null) {
            break label362;
          }
          this.sZ = new fk(i);
          gr.wC.post(new Runnable()
          {
            public void run()
            {
              fb.this.onStop();
            }
          });
          boolean bool = TextUtils.isEmpty(this.sZ.tQ);
          if (bool) {
            break label394;
          }
        }
        try
        {
          localJSONObject = JSONObjectInstrumentation.init(this.sZ.tQ);
          localObject1 = new fz.a(localfi, this.sZ, this.pR, (ay)localObject1, i, l1, this.sZ.tM, localJSONObject);
          gr.wC.post(new Runnable()
          {
            public void run()
            {
              synchronized (fb.a(fb.this))
              {
                fb.b(fb.this).a(localObject1);
                return;
              }
            }
          });
          return;
        }
        catch (Exception localException)
        {
          JSONObject localJSONObject;
          gs.b("Error parsing the JSON for Active View.", localException);
        }
        l1 = l2;
        e(l3);
        l1 = l2;
        l2 = SystemClock.elapsedRealtime();
        l1 = l2;
        cx();
        l1 = l2;
        if (localfi.lH.oh == null) {
          break label408;
        }
        l1 = l2;
        localObject1 = a(localfi);
        try
        {
          r(this.sZ.tT);
          l1 = l2;
        }
        catch (a locala2)
        {
          Object localObject5;
          l1 = l2;
        }
        gs.W(localJSONObject.getMessage());
      }
      label362:
      this.sZ = new fk(i, this.sZ.qj);
      continue;
      label394:
      localObject5 = null;
      continue;
      continue;
      label408:
      Object localObject3 = null;
    }
  }
  
  public void onStop()
  {
    synchronized (this.sV)
    {
      if (this.sY != null) {
        this.sY.cancel();
      }
      return;
    }
  }
  
  @ez
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/fb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */