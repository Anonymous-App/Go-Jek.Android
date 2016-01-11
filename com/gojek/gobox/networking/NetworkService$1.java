package com.gojek.gobox.networking;

import com.gojek.gobox.model.CargoTypeResponse;
import com.gojek.gobox.util.GoBoxPreferences;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

class NetworkService$1
  implements Observable.OnSubscribe<CargoTypeResponse>
{
  NetworkService$1(NetworkService paramNetworkService) {}
  
  public void call(Subscriber<? super CargoTypeResponse> paramSubscriber)
  {
    paramSubscriber.onNext(NetworkService.access$000(this.this$0).getCargoTypeData());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/networking/NetworkService$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */