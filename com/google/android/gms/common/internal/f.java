package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import java.util.ArrayList;
import java.util.Iterator;

public final class f
{
  private final b LM;
  private final ArrayList<GoogleApiClient.ConnectionCallbacks> LN = new ArrayList();
  final ArrayList<GoogleApiClient.ConnectionCallbacks> LO = new ArrayList();
  private boolean LP = false;
  private final ArrayList<GooglePlayServicesClient.OnConnectionFailedListener> LQ = new ArrayList();
  private final Handler mHandler;
  
  public f(Context paramContext, Looper paramLooper, b paramb)
  {
    this.LM = paramb;
    this.mHandler = new a(paramLooper);
  }
  
  public void aB(int paramInt)
  {
    this.mHandler.removeMessages(1);
    synchronized (this.LN)
    {
      this.LP = true;
      Iterator localIterator = new ArrayList(this.LN).iterator();
      GoogleApiClient.ConnectionCallbacks localConnectionCallbacks;
      do
      {
        if (localIterator.hasNext())
        {
          localConnectionCallbacks = (GoogleApiClient.ConnectionCallbacks)localIterator.next();
          if (this.LM.gq()) {}
        }
        else
        {
          this.LP = false;
          return;
        }
      } while (!this.LN.contains(localConnectionCallbacks));
      localConnectionCallbacks.onConnectionSuspended(paramInt);
    }
  }
  
  public void b(ConnectionResult paramConnectionResult)
  {
    this.mHandler.removeMessages(1);
    synchronized (this.LQ)
    {
      Iterator localIterator = new ArrayList(this.LQ).iterator();
      while (localIterator.hasNext())
      {
        GooglePlayServicesClient.OnConnectionFailedListener localOnConnectionFailedListener = (GooglePlayServicesClient.OnConnectionFailedListener)localIterator.next();
        if (!this.LM.gq()) {
          return;
        }
        if (this.LQ.contains(localOnConnectionFailedListener)) {
          localOnConnectionFailedListener.onConnectionFailed(paramConnectionResult);
        }
      }
    }
  }
  
  public void d(Bundle paramBundle)
  {
    boolean bool2 = true;
    for (;;)
    {
      synchronized (this.LN)
      {
        if (!this.LP)
        {
          bool1 = true;
          o.I(bool1);
          this.mHandler.removeMessages(1);
          this.LP = true;
          if (this.LO.size() != 0) {
            break label165;
          }
          bool1 = bool2;
          o.I(bool1);
          Iterator localIterator = new ArrayList(this.LN).iterator();
          GoogleApiClient.ConnectionCallbacks localConnectionCallbacks;
          if (localIterator.hasNext())
          {
            localConnectionCallbacks = (GoogleApiClient.ConnectionCallbacks)localIterator.next();
            if ((this.LM.gq()) && (this.LM.isConnected())) {}
          }
          else
          {
            this.LO.clear();
            this.LP = false;
            return;
          }
          if (this.LO.contains(localConnectionCallbacks)) {
            continue;
          }
          localConnectionCallbacks.onConnected(paramBundle);
        }
      }
      boolean bool1 = false;
      continue;
      label165:
      bool1 = false;
    }
  }
  
  protected void dL()
  {
    synchronized (this.LN)
    {
      d(this.LM.fC());
      return;
    }
  }
  
  public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    o.i(paramConnectionCallbacks);
    synchronized (this.LN)
    {
      boolean bool = this.LN.contains(paramConnectionCallbacks);
      return bool;
    }
  }
  
  public boolean isConnectionFailedListenerRegistered(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    o.i(paramOnConnectionFailedListener);
    synchronized (this.LQ)
    {
      boolean bool = this.LQ.contains(paramOnConnectionFailedListener);
      return bool;
    }
  }
  
  public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    o.i(paramConnectionCallbacks);
    synchronized (this.LN)
    {
      if (this.LN.contains(paramConnectionCallbacks))
      {
        Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + paramConnectionCallbacks + " is already registered");
        if (this.LM.isConnected()) {
          this.mHandler.sendMessage(this.mHandler.obtainMessage(1, paramConnectionCallbacks));
        }
        return;
      }
      this.LN.add(paramConnectionCallbacks);
    }
  }
  
  public void registerConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    o.i(paramOnConnectionFailedListener);
    synchronized (this.LQ)
    {
      if (this.LQ.contains(paramOnConnectionFailedListener))
      {
        Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + paramOnConnectionFailedListener + " is already registered");
        return;
      }
      this.LQ.add(paramOnConnectionFailedListener);
    }
  }
  
  public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    o.i(paramConnectionCallbacks);
    synchronized (this.LN)
    {
      if (this.LN != null)
      {
        if (this.LN.remove(paramConnectionCallbacks)) {
          break label63;
        }
        Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + paramConnectionCallbacks + " not found");
      }
      label63:
      while (!this.LP) {
        return;
      }
      this.LO.add(paramConnectionCallbacks);
    }
  }
  
  public void unregisterConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    o.i(paramOnConnectionFailedListener);
    synchronized (this.LQ)
    {
      if ((this.LQ != null) && (!this.LQ.remove(paramOnConnectionFailedListener))) {
        Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + paramOnConnectionFailedListener + " not found");
      }
      return;
    }
  }
  
  final class a
    extends Handler
  {
    public a(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.what == 1) {
        synchronized (f.a(f.this))
        {
          if ((f.b(f.this).gq()) && (f.b(f.this).isConnected()) && (f.a(f.this).contains(paramMessage.obj)))
          {
            Bundle localBundle = f.b(f.this).fC();
            ((GoogleApiClient.ConnectionCallbacks)paramMessage.obj).onConnected(localBundle);
          }
          return;
        }
      }
      Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
    }
  }
  
  public static abstract interface b
  {
    public abstract Bundle fC();
    
    public abstract boolean gq();
    
    public abstract boolean isConnected();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/internal/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */