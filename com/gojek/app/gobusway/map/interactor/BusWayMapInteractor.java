package com.gojek.app.gobusway.map.interactor;

import com.gojek.app.gobusway.model.BusWayResponse;
import com.gojek.app.gobusway.model.HalteResponse;
import rx.Subscriber;

public abstract interface BusWayMapInteractor
{
  public abstract void fetchCurrentBusWayPosition(Subscriber<BusWayResponse> paramSubscriber, double paramDouble1, double paramDouble2);
  
  public abstract void fetchHalteList(Subscriber<HalteResponse> paramSubscriber);
  
  public abstract void registerGcm(Subscriber<String> paramSubscriber);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/map/interactor/BusWayMapInteractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */