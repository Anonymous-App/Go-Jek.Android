package com.gojek.gobox.cargoType.interactor;

import com.gojek.gobox.model.CargoTypeResponse;
import com.gojek.gobox.networking.NetworkService;
import rx.Observable;
import rx.Subscriber;

public class CargoTypeInteractorImpl
  implements CargoTypeInteractor
{
  private Observable<NetworkService> mNetworkServiceProvider;
  
  public CargoTypeInteractorImpl(Observable<NetworkService> paramObservable)
  {
    this.mNetworkServiceProvider = paramObservable;
  }
  
  public void fetchAllCargoType(Subscriber<CargoTypeResponse> paramSubscriber)
  {
    this.mNetworkServiceProvider.subscribe(CargoTypeInteractorImpl..Lambda.1.lambdaFactory$(paramSubscriber));
  }
  
  public void fetchGoBoxConfiguration(Subscriber<Boolean> paramSubscriber)
  {
    this.mNetworkServiceProvider.subscribe(CargoTypeInteractorImpl..Lambda.2.lambdaFactory$(paramSubscriber));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/cargoType/interactor/CargoTypeInteractorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */