package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.e;
import com.google.android.gms.common.internal.e.e;
import com.google.android.gms.common.internal.l;

public class nd
  extends e<nb>
{
  public nd(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, (String[])null);
  }
  
  protected void a(l paraml, e.e parame)
    throws RemoteException
  {
    Bundle localBundle = new Bundle();
    paraml.a(parame, 6171000, getContext().getPackageName(), localBundle);
  }
  
  public nb bB(IBinder paramIBinder)
  {
    return nb.a.bA(paramIBinder);
  }
  
  protected String getServiceDescriptor()
  {
    return "com.google.android.gms.panorama.internal.IPanoramaService";
  }
  
  protected String getStartServiceAction()
  {
    return "com.google.android.gms.panorama.service.START";
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/nd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */