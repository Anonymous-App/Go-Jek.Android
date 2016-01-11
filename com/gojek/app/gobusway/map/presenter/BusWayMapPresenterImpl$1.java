package com.gojek.app.gobusway.map.presenter;

import android.util.Log;
import com.gojek.app.gobusway.map.view.BusWayMapView;
import com.gojek.app.gobusway.model.HalteResponse;
import rx.Subscriber;

class BusWayMapPresenterImpl$1
  extends Subscriber<HalteResponse>
{
  BusWayMapPresenterImpl$1(BusWayMapPresenterImpl paramBusWayMapPresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    paramThrowable.printStackTrace();
    Log.d("Halte", "Error");
  }
  
  public void onNext(HalteResponse paramHalteResponse)
  {
    BusWayMapPresenterImpl.access$000(this.this$0).showHalteList(paramHalteResponse.getHalte());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/map/presenter/BusWayMapPresenterImpl$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */