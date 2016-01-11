package com.google.android.gms.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

@ez
public class dz
  extends ei.a
  implements ServiceConnection
{
  private final Activity nr;
  private el sm;
  private dw sn;
  private final ec so;
  private ee sq;
  private Context sw;
  private eg sx;
  private ea sy;
  private String sz = null;
  
  public dz(Activity paramActivity)
  {
    this.nr = paramActivity;
    this.so = ec.j(this.nr.getApplicationContext());
  }
  
  public static void a(Context paramContext, boolean paramBoolean, dv paramdv)
  {
    Intent localIntent = new Intent();
    localIntent.setClassName(paramContext, "com.google.android.gms.ads.purchase.InAppPurchaseActivity");
    localIntent.putExtra("com.google.android.gms.ads.internal.purchase.useClientJar", paramBoolean);
    dv.a(localIntent, paramdv);
    paramContext.startActivity(localIntent);
  }
  
  private void a(String paramString, boolean paramBoolean, int paramInt, Intent paramIntent)
  {
    try
    {
      this.sm.a(new eb(this.sw, paramString, paramBoolean, paramInt, paramIntent, this.sy));
      return;
    }
    catch (RemoteException paramString)
    {
      gs.W("Fail to invoke PlayStorePurchaseListener.");
    }
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 1001) {}
    for (;;)
    {
      try
      {
        paramInt1 = ed.d(paramIntent);
        if ((paramInt2 != -1) || (paramInt1 != 0)) {
          continue;
        }
        if (!this.sq.a(this.sz, paramInt2, paramIntent)) {
          continue;
        }
        a(this.sx.getProductId(), true, paramInt2, paramIntent);
      }
      catch (RemoteException paramIntent)
      {
        gs.W("Fail to process purchase result.");
        return;
        this.so.a(this.sy);
        a(this.sx.getProductId(), false, paramInt2, paramIntent);
        continue;
      }
      finally
      {
        this.sz = null;
        this.nr.finish();
      }
      this.sx.recordPlayBillingResolution(paramInt1);
      this.sz = null;
      this.nr.finish();
      return;
      a(this.sx.getProductId(), false, paramInt2, paramIntent);
    }
  }
  
  public void onCreate()
  {
    Object localObject = dv.c(this.nr.getIntent());
    this.sm = ((dv)localObject).lM;
    this.sq = ((dv)localObject).lT;
    this.sx = ((dv)localObject).si;
    this.sn = new dw(this.nr.getApplicationContext());
    this.sw = ((dv)localObject).sj;
    localObject = new Intent("com.android.vending.billing.InAppBillingService.BIND");
    ((Intent)localObject).setPackage("com.android.vending");
    this.nr.bindService((Intent)localObject, this, 1);
  }
  
  public void onDestroy()
  {
    this.nr.unbindService(this);
    this.sn.destroy();
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    this.sn.r(paramIBinder);
    try
    {
      this.sz = this.sq.ct();
      paramComponentName = this.sn.a(this.nr.getPackageName(), this.sx.getProductId(), this.sz);
      paramIBinder = (PendingIntent)paramComponentName.getParcelable("BUY_INTENT");
      if (paramIBinder == null)
      {
        int i = ed.b(paramComponentName);
        this.sx.recordPlayBillingResolution(i);
        a(this.sx.getProductId(), false, i, null);
        this.nr.finish();
        return;
      }
      this.sy = new ea(this.sx.getProductId(), this.sz);
      this.so.b(this.sy);
      this.nr.startIntentSenderForResult(paramIBinder.getIntentSender(), 1001, new Intent(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue());
      return;
    }
    catch (RemoteException paramComponentName)
    {
      gs.d("Error when connecting in-app billing service", paramComponentName);
      this.nr.finish();
      return;
    }
    catch (IntentSender.SendIntentException paramComponentName)
    {
      for (;;) {}
    }
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    gs.U("In-app billing service disconnected.");
    this.sn.destroy();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/dz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */