package com.gojek.app.gobusway.map.presenter;

import com.gojek.app.gobusway.map.interactor.BusWayMapInteractor;
import com.gojek.app.gobusway.map.view.BusWayMapView;

public class BusWayMapPresenterImpl
  implements BusWayMapPresenter
{
  private BusWayMapInteractor mBusWayMapInteractor;
  private BusWayMapView mBusWayMapView;
  
  public BusWayMapPresenterImpl(BusWayMapView paramBusWayMapView, BusWayMapInteractor paramBusWayMapInteractor)
  {
    this.mBusWayMapView = paramBusWayMapView;
    this.mBusWayMapInteractor = paramBusWayMapInteractor;
  }
  
  public void onBusWayMapResume()
  {
    this.mBusWayMapView.initBusWayTimer();
  }
  
  public void onMapCreateView()
  {
    this.mBusWayMapInteractor.fetchHalteList(new BusWayMapPresenterImpl.1(this));
  }
  
  public void onRefreshBusWayPosition(double paramDouble1, double paramDouble2)
  {
    this.mBusWayMapInteractor.fetchCurrentBusWayPosition(new BusWayMapPresenterImpl.2(this), paramDouble1, paramDouble2);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/map/presenter/BusWayMapPresenterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */