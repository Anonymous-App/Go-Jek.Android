package com.gojek.app.gobusway.networking;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.gojek.app.gobusway.model.BusWayResponse;
import com.gojek.app.gobusway.model.HalteResponse;
import com.gojek.app.gobusway.model.HalteScheduleResponse;
import com.gojek.app.gobusway.util.GoBusWayPreferences;
import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NetworkService
  extends Service
{
  private final IBinder mBinder = new LocalBinder();
  private GoBusWayPreferences mGoBusWayPreferences;
  private NetworkManager mNetworkManager;
  
  private boolean isTokenExpired(Throwable paramThrowable)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    try
    {
      if ((paramThrowable instanceof RetrofitError))
      {
        int i = ((RetrofitError)paramThrowable).getResponse().getStatus();
        bool1 = bool2;
        if (i == 401) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (NullPointerException paramThrowable) {}
    return false;
  }
  
  private Observable<Boolean> refreshToken()
  {
    return this.mNetworkManager.refreshToken(this.mGoBusWayPreferences.getRefreshToken()).map(NetworkService..Lambda.2.lambdaFactory$(this));
  }
  
  public Observable<BusWayResponse> getBusWayObservable(double paramDouble1, double paramDouble2)
  {
    return this.mNetworkManager.getBusWayObservable(paramDouble1, paramDouble2).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<HalteResponse> getHalteObservable(String paramString)
  {
    return handleRefreshToken(this.mNetworkManager.getHalteObservable(this.mGoBusWayPreferences.getBearerAccessToken(), paramString)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<HalteScheduleResponse> getHalteScheduleObservable(String paramString)
  {
    return handleRefreshToken(this.mNetworkManager.getHalteScheduleObservable(this.mGoBusWayPreferences.getBearerAccessToken(), paramString)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public <T> Observable<T> handleRefreshToken(Observable<T> paramObservable)
  {
    return paramObservable.onErrorResumeNext(NetworkService..Lambda.1.lambdaFactory$(this, paramObservable));
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    this.mGoBusWayPreferences = new GoBusWayPreferences(this);
    this.mNetworkManager = ((NetworkManager)new RestAdapter.Builder().setEndpoint("https://gobusway.gojek.co.id").build().create(NetworkManager.class));
    return this.mBinder;
  }
  
  public class LocalBinder
    extends Binder
  {
    public LocalBinder() {}
    
    public NetworkService getService()
    {
      return NetworkService.this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/networking/NetworkService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */