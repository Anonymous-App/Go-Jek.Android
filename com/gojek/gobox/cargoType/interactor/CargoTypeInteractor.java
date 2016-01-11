package com.gojek.gobox.cargoType.interactor;

import com.gojek.gobox.model.CargoTypeResponse;
import rx.Subscriber;

public abstract interface CargoTypeInteractor
{
  public abstract void fetchAllCargoType(Subscriber<CargoTypeResponse> paramSubscriber);
  
  public abstract void fetchGoBoxConfiguration(Subscriber<Boolean> paramSubscriber);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/cargoType/interactor/CargoTypeInteractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */