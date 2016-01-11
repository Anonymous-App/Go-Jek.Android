package com.google.android.gms.drive;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.Api.c;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.drive.internal.o;
import com.google.android.gms.drive.internal.q;
import com.google.android.gms.drive.internal.t;
import com.google.android.gms.drive.internal.x;
import java.util.List;

public final class Drive
{
  public static final Api<Api.ApiOptions.NoOptions> API;
  public static final Api.c<q> CU = new Api.c();
  public static final DriveApi DriveApi = new o();
  public static final Scope Nc;
  public static final Scope Nd;
  public static final Api<b> Ne;
  public static final b Nf = new t();
  public static final e Ng = new x();
  public static final Scope SCOPE_APPFOLDER;
  public static final Scope SCOPE_FILE = new Scope("https://www.googleapis.com/auth/drive.file");
  
  static
  {
    SCOPE_APPFOLDER = new Scope("https://www.googleapis.com/auth/drive.appdata");
    Nc = new Scope("https://www.googleapis.com/auth/drive");
    Nd = new Scope("https://www.googleapis.com/auth/drive.apps");
    API = new Api(new a()
    {
      protected Bundle a(Api.ApiOptions.NoOptions paramAnonymousNoOptions)
      {
        return new Bundle();
      }
    }, CU, new Scope[0]);
    Ne = new Api(new a()
    {
      protected Bundle a(Drive.b paramAnonymousb)
      {
        if (paramAnonymousb == null) {
          return new Bundle();
        }
        return paramAnonymousb.hM();
      }
    }, CU, new Scope[0]);
  }
  
  public static abstract class a<O extends Api.ApiOptions>
    implements Api.b<q, O>
  {
    protected abstract Bundle a(O paramO);
    
    public q a(Context paramContext, Looper paramLooper, ClientSettings paramClientSettings, O paramO, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      List localList = paramClientSettings.getScopes();
      return new q(paramContext, paramLooper, paramClientSettings, paramConnectionCallbacks, paramOnConnectionFailedListener, (String[])localList.toArray(new String[localList.size()]), a(paramO));
    }
    
    public int getPriority()
    {
      return Integer.MAX_VALUE;
    }
  }
  
  public static class b
    implements Api.ApiOptions.Optional
  {
    private final Bundle Nh;
    
    private b()
    {
      this(new Bundle());
    }
    
    private b(Bundle paramBundle)
    {
      this.Nh = paramBundle;
    }
    
    public Bundle hM()
    {
      return this.Nh;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/Drive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */