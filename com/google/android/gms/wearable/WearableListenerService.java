package com.google.android.gms.wearable;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.internal.ae.a;
import com.google.android.gms.wearable.internal.ah;
import com.google.android.gms.wearable.internal.ak;

public abstract class WearableListenerService
  extends Service
  implements DataApi.DataListener, MessageApi.MessageListener, NodeApi.NodeListener
{
  public static final String BIND_LISTENER_INTENT_ACTION = "com.google.android.gms.wearable.BIND_LISTENER";
  private String BZ;
  private IBinder LZ;
  private volatile int NX = -1;
  private Handler avc;
  private Object avd = new Object();
  private boolean ave;
  
  private boolean bc(int paramInt)
  {
    boolean bool2 = false;
    String[] arrayOfString = getPackageManager().getPackagesForUid(paramInt);
    boolean bool1 = bool2;
    if (arrayOfString != null) {
      paramInt = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (paramInt < arrayOfString.length)
      {
        if ("com.google.android.gms".equals(arrayOfString[paramInt])) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      paramInt += 1;
    }
  }
  
  private void pU()
    throws SecurityException
  {
    int i = Binder.getCallingUid();
    if (i == this.NX) {
      return;
    }
    if ((GooglePlayServicesUtil.b(getPackageManager(), "com.google.android.gms")) && (bc(i)))
    {
      this.NX = i;
      return;
    }
    throw new SecurityException("Caller is not GooglePlayServices");
  }
  
  public final IBinder onBind(Intent paramIntent)
  {
    if ("com.google.android.gms.wearable.BIND_LISTENER".equals(paramIntent.getAction())) {
      return this.LZ;
    }
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    if (Log.isLoggable("WearableLS", 3)) {
      Log.d("WearableLS", "onCreate: " + getPackageName());
    }
    this.BZ = getPackageName();
    HandlerThread localHandlerThread = new HandlerThread("WearableListenerService");
    localHandlerThread.start();
    this.avc = new Handler(localHandlerThread.getLooper());
    this.LZ = new a(null);
  }
  
  public void onDataChanged(DataEventBuffer paramDataEventBuffer) {}
  
  public void onDestroy()
  {
    synchronized (this.avd)
    {
      this.ave = true;
      this.avc.getLooper().quit();
      super.onDestroy();
      return;
    }
  }
  
  public void onMessageReceived(MessageEvent paramMessageEvent) {}
  
  public void onPeerConnected(Node paramNode) {}
  
  public void onPeerDisconnected(Node paramNode) {}
  
  private class a
    extends ae.a
  {
    private a() {}
    
    public void Z(final DataHolder paramDataHolder)
    {
      if (Log.isLoggable("WearableLS", 3)) {
        Log.d("WearableLS", "onDataItemChanged: " + WearableListenerService.a(WearableListenerService.this) + ": " + paramDataHolder);
      }
      WearableListenerService.b(WearableListenerService.this);
      synchronized (WearableListenerService.c(WearableListenerService.this))
      {
        if (WearableListenerService.d(WearableListenerService.this))
        {
          paramDataHolder.close();
          return;
        }
        WearableListenerService.e(WearableListenerService.this).post(new Runnable()
        {
          public void run()
          {
            DataEventBuffer localDataEventBuffer = new DataEventBuffer(paramDataHolder);
            try
            {
              WearableListenerService.this.onDataChanged(localDataEventBuffer);
              return;
            }
            finally
            {
              localDataEventBuffer.release();
            }
          }
        });
        return;
      }
    }
    
    public void a(final ah paramah)
    {
      if (Log.isLoggable("WearableLS", 3)) {
        Log.d("WearableLS", "onMessageReceived: " + paramah);
      }
      WearableListenerService.b(WearableListenerService.this);
      synchronized (WearableListenerService.c(WearableListenerService.this))
      {
        if (WearableListenerService.d(WearableListenerService.this)) {
          return;
        }
        WearableListenerService.e(WearableListenerService.this).post(new Runnable()
        {
          public void run()
          {
            WearableListenerService.this.onMessageReceived(paramah);
          }
        });
        return;
      }
    }
    
    public void a(final ak paramak)
    {
      if (Log.isLoggable("WearableLS", 3)) {
        Log.d("WearableLS", "onPeerConnected: " + WearableListenerService.a(WearableListenerService.this) + ": " + paramak);
      }
      WearableListenerService.b(WearableListenerService.this);
      synchronized (WearableListenerService.c(WearableListenerService.this))
      {
        if (WearableListenerService.d(WearableListenerService.this)) {
          return;
        }
        WearableListenerService.e(WearableListenerService.this).post(new Runnable()
        {
          public void run()
          {
            WearableListenerService.this.onPeerConnected(paramak);
          }
        });
        return;
      }
    }
    
    public void b(final ak paramak)
    {
      if (Log.isLoggable("WearableLS", 3)) {
        Log.d("WearableLS", "onPeerDisconnected: " + WearableListenerService.a(WearableListenerService.this) + ": " + paramak);
      }
      WearableListenerService.b(WearableListenerService.this);
      synchronized (WearableListenerService.c(WearableListenerService.this))
      {
        if (WearableListenerService.d(WearableListenerService.this)) {
          return;
        }
        WearableListenerService.e(WearableListenerService.this).post(new Runnable()
        {
          public void run()
          {
            WearableListenerService.this.onPeerDisconnected(paramak);
          }
        });
        return;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/WearableListenerService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */