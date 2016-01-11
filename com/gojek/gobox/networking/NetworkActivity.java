package com.gojek.gobox.networking;

import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.gojek.gobox.R.color;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import java.util.ArrayList;
import java.util.Iterator;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NetworkActivity
  extends AppCompatActivity
  implements NetworkServiceProvider, Observable.OnSubscribe<NetworkService>
{
  private boolean mBound;
  private ServiceConnection mConnection = new NetworkActivity.1(this);
  private NetworkService mNetworkManager;
  private Observable<NetworkService> mNetworkServiceObservable;
  private Subscriber networkSubscriber;
  private ArrayList<Subscriber> networkSubscribers = new ArrayList();
  
  private void adjustSystemBarTintAsNeeded()
  {
    if (Build.VERSION.SDK_INT == 19)
    {
      SystemBarTintManager localSystemBarTintManager = new SystemBarTintManager(this);
      localSystemBarTintManager.setStatusBarTintEnabled(true);
      localSystemBarTintManager.setTintColor(getResources().getColor(R.color.primaryDark));
      localSystemBarTintManager.setStatusBarTintColor(getResources().getColor(R.color.primaryDark));
    }
  }
  
  private void triggerServiceActive()
  {
    try
    {
      Iterator localIterator = this.networkSubscribers.iterator();
      while (localIterator.hasNext())
      {
        Subscriber localSubscriber = (Subscriber)localIterator.next();
        localSubscriber.onNext(this.mNetworkManager);
        localSubscriber.onCompleted();
        localIterator.remove();
      }
    }
    finally {}
  }
  
  public void call(Subscriber<? super NetworkService> paramSubscriber)
  {
    this.networkSubscribers.add(paramSubscriber);
    if (this.mBound) {
      triggerServiceActive();
    }
  }
  
  public Observable<NetworkService> getNetworkManager()
  {
    return this.mNetworkServiceObservable;
  }
  
  public NetworkServiceProvider getNetworkServiceProvider()
  {
    return this;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    adjustSystemBarTintAsNeeded();
    this.mNetworkServiceObservable = Observable.create(this).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  protected void onStart()
  {
    super.onStart();
    bindService(new Intent(this, NetworkService.class), this.mConnection, 1);
  }
  
  protected void onStop()
  {
    super.onStop();
    if (this.mBound)
    {
      this.networkSubscriber = null;
      unbindService(this.mConnection);
      this.mBound = false;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/networking/NetworkActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */