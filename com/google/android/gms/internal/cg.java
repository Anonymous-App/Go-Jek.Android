package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.e;
import com.google.android.gms.common.internal.e.e;
import com.google.android.gms.common.internal.l;

@ez
public class cg
  extends e<ch>
{
  final int pP;
  
  public cg(Context paramContext, GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener, int paramInt)
  {
    super(paramContext, paramConnectionCallbacks, paramOnConnectionFailedListener, new String[0]);
    this.pP = paramInt;
  }
  
  protected void a(l paraml, e.e parame)
    throws RemoteException
  {
    Bundle localBundle = new Bundle();
    paraml.g(parame, this.pP, getContext().getPackageName(), localBundle);
  }
  
  public ch bC()
  {
    return (ch)super.gS();
  }
  
  protected String getServiceDescriptor()
  {
    return "com.google.android.gms.ads.internal.gservice.IGservicesValueService";
  }
  
  protected String getStartServiceAction()
  {
    return "com.google.android.gms.ads.gservice.START";
  }
  
  protected ch i(IBinder paramIBinder)
  {
    return ch.a.k(paramIBinder);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/cg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */