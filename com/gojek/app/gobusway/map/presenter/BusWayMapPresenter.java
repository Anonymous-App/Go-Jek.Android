package com.gojek.app.gobusway.map.presenter;

public abstract interface BusWayMapPresenter
{
  public abstract void onBusWayMapResume();
  
  public abstract void onMapCreateView();
  
  public abstract void onRefreshBusWayPosition(double paramDouble1, double paramDouble2);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/map/presenter/BusWayMapPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */