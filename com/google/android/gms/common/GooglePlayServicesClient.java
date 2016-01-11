package com.google.android.gms.common;

import android.os.Bundle;

@Deprecated
public abstract interface GooglePlayServicesClient
{
  public abstract void connect();
  
  public abstract void disconnect();
  
  public abstract boolean isConnected();
  
  public abstract boolean isConnecting();
  
  public abstract boolean isConnectionCallbacksRegistered(ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public abstract void registerConnectionCallbacks(ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void registerConnectionFailedListener(OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public abstract void unregisterConnectionCallbacks(ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void unregisterConnectionFailedListener(OnConnectionFailedListener paramOnConnectionFailedListener);
  
  @Deprecated
  public static abstract interface ConnectionCallbacks
  {
    public abstract void onConnected(Bundle paramBundle);
    
    public abstract void onDisconnected();
  }
  
  @Deprecated
  public static abstract interface OnConnectionFailedListener
  {
    public abstract void onConnectionFailed(ConnectionResult paramConnectionResult);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/GooglePlayServicesClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */