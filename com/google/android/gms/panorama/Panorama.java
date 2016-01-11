package com.google.android.gms.panorama;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.Api.c;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.nc;
import com.google.android.gms.internal.nd;

public final class Panorama
{
  public static final Api<Api.ApiOptions.NoOptions> API = new Api(CV, CU, new Scope[0]);
  public static final Api.c<nd> CU = new Api.c();
  static final Api.b<nd, Api.ApiOptions.NoOptions> CV = new Api.b()
  {
    public nd e(Context paramAnonymousContext, Looper paramAnonymousLooper, ClientSettings paramAnonymousClientSettings, Api.ApiOptions.NoOptions paramAnonymousNoOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      return new nd(paramAnonymousContext, paramAnonymousLooper, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
    }
    
    public int getPriority()
    {
      return Integer.MAX_VALUE;
    }
  };
  public static final PanoramaApi PanoramaApi = new nc();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/panorama/Panorama.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */