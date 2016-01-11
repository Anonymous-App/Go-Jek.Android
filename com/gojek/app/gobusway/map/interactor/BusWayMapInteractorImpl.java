package com.gojek.app.gobusway.map.interactor;

import com.gojek.app.gobusway.model.BusWayResponse;
import com.gojek.app.gobusway.model.HalteResponse;
import com.gojek.app.gobusway.networking.NetworkService;
import rx.Observable;
import rx.Subscriber;

public class BusWayMapInteractorImpl
  implements BusWayMapInteractor
{
  private Observable<NetworkService> mNetworkManagerObservable;
  
  public BusWayMapInteractorImpl(Observable<NetworkService> paramObservable)
  {
    this.mNetworkManagerObservable = paramObservable;
  }
  
  public void fetchCurrentBusWayPosition(Subscriber<BusWayResponse> paramSubscriber, double paramDouble1, double paramDouble2)
  {
    this.mNetworkManagerObservable.subscribe(BusWayMapInteractorImpl..Lambda.4.lambdaFactory$(paramDouble1, paramDouble2, paramSubscriber));
  }
  
  public void fetchHalteList(Subscriber<HalteResponse> paramSubscriber)
  {
    this.mNetworkManagerObservable.subscribe(BusWayMapInteractorImpl..Lambda.1.lambdaFactory$(paramSubscriber));
  }
  
  public void registerGcm(Subscriber<String> paramSubscriber) {}
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/map/interactor/BusWayMapInteractorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */