package com.google.android.gms.wearable;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.Api.c;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.wearable.internal.ag;
import com.google.android.gms.wearable.internal.aj;
import com.google.android.gms.wearable.internal.aw;
import com.google.android.gms.wearable.internal.e;
import com.google.android.gms.wearable.internal.f;

public class Wearable
{
  public static final Api<WearableOptions> API = new Api(CV, CU, new Scope[0]);
  public static final Api.c<aw> CU;
  private static final Api.b<aw, WearableOptions> CV;
  public static final DataApi DataApi = new f();
  public static final MessageApi MessageApi = new ag();
  public static final NodeApi NodeApi = new aj();
  public static final b avb = new e();
  
  static
  {
    CU = new Api.c();
    CV = new Api.b()
    {
      public aw a(Context paramAnonymousContext, Looper paramAnonymousLooper, ClientSettings paramAnonymousClientSettings, Wearable.WearableOptions paramAnonymousWearableOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
      {
        if (paramAnonymousWearableOptions != null) {}
        for (;;)
        {
          return new aw(paramAnonymousContext, paramAnonymousLooper, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
          new Wearable.WearableOptions(new Wearable.WearableOptions.Builder(), null);
        }
      }
      
      public int getPriority()
      {
        return Integer.MAX_VALUE;
      }
    };
  }
  
  public static final class WearableOptions
    implements Api.ApiOptions.Optional
  {
    private WearableOptions(Builder paramBuilder) {}
    
    public static class Builder
    {
      public Wearable.WearableOptions build()
      {
        return new Wearable.WearableOptions(this, null);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/Wearable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */