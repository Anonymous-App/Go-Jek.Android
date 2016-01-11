package com.google.android.gms.fitness;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.Api.c;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.kk;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.kv;
import com.google.android.gms.internal.kx;
import com.google.android.gms.internal.ky;
import com.google.android.gms.internal.kz;
import com.google.android.gms.internal.la;
import com.google.android.gms.internal.lb;
import com.google.android.gms.internal.lc;
import com.google.android.gms.internal.ld;
import com.google.android.gms.internal.le;
import java.util.concurrent.TimeUnit;

public class Fitness
{
  public static final String ACTION_TRACK = "vnd.google.fitness.TRACK";
  public static final String ACTION_VIEW = "vnd.google.fitness.VIEW";
  public static final String ACTION_VIEW_GOAL = "vnd.google.fitness.VIEW_GOAL";
  public static final Api<Api.ApiOptions.NoOptions> API;
  public static final BleApi BleApi;
  public static final Api.c<kk> CU = new Api.c();
  private static final Api.b<kk, Api.ApiOptions.NoOptions> CV = new Api.b()
  {
    public kk c(Context paramAnonymousContext, Looper paramAnonymousLooper, ClientSettings paramAnonymousClientSettings, Api.ApiOptions.NoOptions paramAnonymousNoOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      paramAnonymousNoOptions = a.d(paramAnonymousClientSettings.getScopes());
      return new kl(paramAnonymousContext, paramAnonymousLooper, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener, paramAnonymousClientSettings.getAccountNameOrDefault(), paramAnonymousNoOptions);
    }
    
    public int getPriority()
    {
      return Integer.MAX_VALUE;
    }
  };
  public static final ConfigApi ConfigApi;
  public static final String EXTRA_END_TIME = "vnd.google.fitness.end_time";
  public static final String EXTRA_START_TIME = "vnd.google.fitness.start_time";
  public static final HistoryApi HistoryApi;
  public static final RecordingApi RecordingApi;
  public static final Scope SCOPE_ACTIVITY_READ;
  public static final Scope SCOPE_ACTIVITY_READ_WRITE;
  public static final Scope SCOPE_BODY_READ = new Scope("https://www.googleapis.com/auth/fitness.body.read");
  public static final Scope SCOPE_BODY_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.body.write");
  public static final Scope SCOPE_LOCATION_READ;
  public static final Scope SCOPE_LOCATION_READ_WRITE;
  public static final SensorsApi SensorsApi;
  public static final SessionsApi SessionsApi;
  public static final kv Sn;
  
  static
  {
    API = new Api(CV, CU, new Scope[0]);
    SensorsApi = new lc();
    RecordingApi = new lb();
    SessionsApi = new ld();
    HistoryApi = new kz();
    ConfigApi = new ky();
    BleApi = iy();
    Sn = new la();
    SCOPE_ACTIVITY_READ = new Scope("https://www.googleapis.com/auth/fitness.activity.read");
    SCOPE_ACTIVITY_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.activity.write");
    SCOPE_LOCATION_READ = new Scope("https://www.googleapis.com/auth/fitness.location.read");
    SCOPE_LOCATION_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.location.write");
  }
  
  public static long getEndTime(Intent paramIntent, TimeUnit paramTimeUnit)
  {
    long l = paramIntent.getLongExtra("vnd.google.fitness.end_time", -1L);
    if (l == -1L) {
      return -1L;
    }
    return paramTimeUnit.convert(l, TimeUnit.MILLISECONDS);
  }
  
  public static long getStartTime(Intent paramIntent, TimeUnit paramTimeUnit)
  {
    long l = paramIntent.getLongExtra("vnd.google.fitness.start_time", -1L);
    if (l == -1L) {
      return -1L;
    }
    return paramTimeUnit.convert(l, TimeUnit.MILLISECONDS);
  }
  
  private static BleApi iy()
  {
    if (Build.VERSION.SDK_INT >= 18) {
      return new kx();
    }
    return new le();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/Fitness.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */