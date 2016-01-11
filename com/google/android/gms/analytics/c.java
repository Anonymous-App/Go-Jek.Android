package com.google.android.gms.analytics;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.hb;
import com.google.android.gms.internal.hc;
import com.google.android.gms.internal.hc.a;
import java.util.List;
import java.util.Map;

class c
  implements b
{
  private Context mContext;
  private ServiceConnection xG;
  private b xH;
  private c xI;
  private hc xJ;
  
  public c(Context paramContext, b paramb, c paramc)
  {
    this.mContext = paramContext;
    if (paramb == null) {
      throw new IllegalArgumentException("onConnectedListener cannot be null");
    }
    this.xH = paramb;
    if (paramc == null) {
      throw new IllegalArgumentException("onConnectionFailedListener cannot be null");
    }
    this.xI = paramc;
  }
  
  private hc dI()
  {
    dJ();
    return this.xJ;
  }
  
  private void dK()
  {
    dL();
  }
  
  private void dL()
  {
    this.xH.onConnected();
  }
  
  public void a(Map<String, String> paramMap, long paramLong, String paramString, List<hb> paramList)
  {
    try
    {
      dI().a(paramMap, paramLong, paramString, paramList);
      return;
    }
    catch (RemoteException paramMap)
    {
      z.T("sendHit failed: " + paramMap);
    }
  }
  
  public void connect()
  {
    Intent localIntent = new Intent("com.google.android.gms.analytics.service.START");
    localIntent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
    localIntent.putExtra("app_package_name", this.mContext.getPackageName());
    if (this.xG != null) {
      z.T("Calling connect() while still connected, missing disconnect().");
    }
    boolean bool;
    do
    {
      return;
      this.xG = new a();
      bool = this.mContext.bindService(localIntent, this.xG, 129);
      z.V("connect: bindService returned " + bool + " for " + localIntent);
    } while (bool);
    this.xG = null;
    this.xI.a(1, null);
  }
  
  public void dH()
  {
    try
    {
      dI().dH();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      z.T("clear hits failed: " + localRemoteException);
    }
  }
  
  protected void dJ()
  {
    if (!isConnected()) {
      throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
    }
  }
  
  public void disconnect()
  {
    this.xJ = null;
    if (this.xG != null) {}
    try
    {
      this.mContext.unbindService(this.xG);
      this.xG = null;
      this.xH.onDisconnected();
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;) {}
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;) {}
    }
  }
  
  public boolean isConnected()
  {
    return this.xJ != null;
  }
  
  final class a
    implements ServiceConnection
  {
    a() {}
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      z.V("service connected, binder: " + paramIBinder);
      try
      {
        if ("com.google.android.gms.analytics.internal.IAnalyticsService".equals(paramIBinder.getInterfaceDescriptor()))
        {
          z.V("bound to service");
          c.a(c.this, hc.a.E(paramIBinder));
          c.a(c.this);
          return;
        }
      }
      catch (RemoteException paramComponentName) {}
      try
      {
        c.b(c.this).unbindService(this);
        c.a(c.this, null);
        c.c(c.this).a(2, null);
        return;
      }
      catch (IllegalArgumentException paramComponentName)
      {
        for (;;) {}
      }
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      z.V("service disconnected: " + paramComponentName);
      c.a(c.this, null);
      c.d(c.this).onDisconnected();
    }
  }
  
  public static abstract interface b
  {
    public abstract void onConnected();
    
    public abstract void onDisconnected();
  }
  
  public static abstract interface c
  {
    public abstract void a(int paramInt, Intent paramIntent);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */