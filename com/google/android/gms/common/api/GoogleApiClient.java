package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.o;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract interface GoogleApiClient
{
  public abstract <C extends Api.a> C a(Api.c<C> paramc);
  
  public abstract <A extends Api.a, R extends Result, T extends BaseImplementation.a<R, A>> T a(T paramT);
  
  public abstract boolean a(Scope paramScope);
  
  public abstract <A extends Api.a, T extends BaseImplementation.a<? extends Result, A>> T b(T paramT);
  
  public abstract ConnectionResult blockingConnect();
  
  public abstract ConnectionResult blockingConnect(long paramLong, TimeUnit paramTimeUnit);
  
  public abstract <L> c<L> c(L paramL);
  
  public abstract void connect();
  
  public abstract void disconnect();
  
  public abstract Looper getLooper();
  
  public abstract boolean isConnected();
  
  public abstract boolean isConnecting();
  
  public abstract boolean isConnectionCallbacksRegistered(ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public abstract void reconnect();
  
  public abstract void registerConnectionCallbacks(ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void registerConnectionFailedListener(OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public abstract void stopAutoManage(FragmentActivity paramFragmentActivity);
  
  public abstract void unregisterConnectionCallbacks(ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void unregisterConnectionFailedListener(OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public static final class Builder
  {
    private String Dd;
    private Looper IH;
    private final Set<String> IJ = new HashSet();
    private int IK;
    private View IL;
    private String IM;
    private final Map<Api<?>, Api.ApiOptions> IN = new HashMap();
    private FragmentActivity IO;
    private int IP = -1;
    private GoogleApiClient.OnConnectionFailedListener IQ;
    private int IR = 2;
    private final Set<GoogleApiClient.ConnectionCallbacks> IS = new HashSet();
    private final Set<GoogleApiClient.OnConnectionFailedListener> IT = new HashSet();
    private final Context mContext;
    
    public Builder(Context paramContext)
    {
      this.mContext = paramContext;
      this.IH = paramContext.getMainLooper();
      this.IM = paramContext.getPackageName();
    }
    
    public Builder(Context paramContext, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      this(paramContext);
      o.b(paramConnectionCallbacks, "Must provide a connected listener");
      this.IS.add(paramConnectionCallbacks);
      o.b(paramOnConnectionFailedListener, "Must provide a connection failed listener");
      this.IT.add(paramOnConnectionFailedListener);
    }
    
    private GoogleApiClient gl()
    {
      d locald = d.a(this.IO);
      GoogleApiClient localGoogleApiClient = locald.ak(this.IP);
      Object localObject = localGoogleApiClient;
      if (localGoogleApiClient == null) {
        localObject = new b(this.mContext.getApplicationContext(), this.IH, gk(), this.IN, this.IS, this.IT, this.IP, this.IR);
      }
      locald.a(this.IP, (GoogleApiClient)localObject, this.IQ);
      return (GoogleApiClient)localObject;
    }
    
    public Builder addApi(Api<? extends Api.ApiOptions.NotRequiredOptions> paramApi)
    {
      this.IN.put(paramApi, null);
      paramApi = paramApi.gd();
      int j = paramApi.size();
      int i = 0;
      while (i < j)
      {
        this.IJ.add(((Scope)paramApi.get(i)).gs());
        i += 1;
      }
      return this;
    }
    
    public <O extends Api.ApiOptions.HasOptions> Builder addApi(Api<O> paramApi, O paramO)
    {
      o.b(paramO, "Null options are not permitted for this Api");
      this.IN.put(paramApi, paramO);
      paramApi = paramApi.gd();
      int j = paramApi.size();
      int i = 0;
      while (i < j)
      {
        this.IJ.add(((Scope)paramApi.get(i)).gs());
        i += 1;
      }
      return this;
    }
    
    public Builder addConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
    {
      this.IS.add(paramConnectionCallbacks);
      return this;
    }
    
    public Builder addOnConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      this.IT.add(paramOnConnectionFailedListener);
      return this;
    }
    
    public Builder addScope(Scope paramScope)
    {
      this.IJ.add(paramScope.gs());
      return this;
    }
    
    public GoogleApiClient build()
    {
      if (!this.IN.isEmpty()) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "must call addApi() to add at least one API");
        if (this.IP < 0) {
          break;
        }
        return gl();
      }
      return new b(this.mContext, this.IH, gk(), this.IN, this.IS, this.IT, -1, this.IR);
    }
    
    public Builder enableAutoManage(FragmentActivity paramFragmentActivity, int paramInt, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      if (paramInt >= 0) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "clientId must be non-negative");
        this.IP = paramInt;
        this.IO = ((FragmentActivity)o.b(paramFragmentActivity, "Null activity is not permitted."));
        this.IQ = paramOnConnectionFailedListener;
        return this;
      }
    }
    
    public ClientSettings gk()
    {
      return new ClientSettings(this.Dd, this.IJ, this.IK, this.IL, this.IM);
    }
    
    public Builder setAccountName(String paramString)
    {
      this.Dd = paramString;
      return this;
    }
    
    public Builder setGravityForPopups(int paramInt)
    {
      this.IK = paramInt;
      return this;
    }
    
    public Builder setHandler(Handler paramHandler)
    {
      o.b(paramHandler, "Handler must not be null");
      this.IH = paramHandler.getLooper();
      return this;
    }
    
    public Builder setViewForPopups(View paramView)
    {
      this.IL = paramView;
      return this;
    }
    
    public Builder useDefaultAccount()
    {
      return setAccountName("<<default account>>");
    }
  }
  
  public static abstract interface ConnectionCallbacks
  {
    public static final int CAUSE_NETWORK_LOST = 2;
    public static final int CAUSE_SERVICE_DISCONNECTED = 1;
    
    public abstract void onConnected(Bundle paramBundle);
    
    public abstract void onConnectionSuspended(int paramInt);
  }
  
  public static abstract interface OnConnectionFailedListener
    extends GooglePlayServicesClient.OnConnectionFailedListener
  {
    public abstract void onConnectionFailed(ConnectionResult paramConnectionResult);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/api/GoogleApiClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */