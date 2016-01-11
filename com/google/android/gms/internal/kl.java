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

public class kl
  extends e<kp>
  implements kk
{
  private final String Dd;
  
  public kl(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, String[] paramArrayOfString)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramArrayOfString);
    this.Dd = paramString;
  }
  
  protected void a(l paraml, e.e parame)
    throws RemoteException
  {
    paraml.a(parame, 6171000, getContext().getPackageName(), this.Dd, gR(), new Bundle());
  }
  
  protected kp ao(IBinder paramIBinder)
  {
    return kp.a.as(paramIBinder);
  }
  
  protected String getServiceDescriptor()
  {
    return "com.google.android.gms.fitness.internal.IGoogleFitnessService";
  }
  
  protected String getStartServiceAction()
  {
    return "com.google.android.gms.fitness.GoogleFitnessService.START";
  }
  
  public kp jb()
  {
    return (kp)gS();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/kl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */