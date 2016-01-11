package com.gojek.app.gobusway.map.presenter;

import android.util.Log;
import com.gojek.app.gobusway.map.view.BusWayMapView;
import com.gojek.app.gobusway.model.BusWayResponse;
import rx.Subscriber;

class BusWayMapPresenterImpl$2
  extends Subscriber<BusWayResponse>
{
  BusWayMapPresenterImpl$2(BusWayMapPresenterImpl paramBusWayMapPresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    paramThrowable.printStackTrace();
    Log.d("Busway Position", "Error");
  }
  
  public void onNext(BusWayResponse paramBusWayResponse)
  {
    BusWayMapPresenterImpl.access$000(this.this$0).showBusMarker(paramBusWayResponse.getBusWays());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/map/presenter/BusWayMapPresenterImpl$2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */