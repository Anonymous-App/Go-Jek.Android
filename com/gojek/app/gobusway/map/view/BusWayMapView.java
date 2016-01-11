package com.gojek.app.gobusway.map.view;

import com.gojek.app.gobusway.model.BusWay;
import com.gojek.app.gobusway.model.Halte;
import java.util.ArrayList;

public abstract interface BusWayMapView
{
  public abstract void initBusWayTimer();
  
  public abstract void initMapView();
  
  public abstract void showBusMarker(ArrayList<BusWay> paramArrayList);
  
  public abstract void showHalteInfoWindow(Halte paramHalte);
  
  public abstract void showHalteList(ArrayList<Halte> paramArrayList);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/map/view/BusWayMapView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */