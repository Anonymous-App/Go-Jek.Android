package com.gojek.gobox.networking;

import com.gojek.gobox.util.GoBoxDataInitializationListener;
import rx.Subscriber;

class NetworkService$2
  extends Subscriber<Boolean>
{
  NetworkService$2(NetworkService paramNetworkService, GoBoxDataInitializationListener paramGoBoxDataInitializationListener) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    this.val$listener.onError();
  }
  
  public void onNext(Boolean paramBoolean)
  {
    this.val$listener.onSuccess();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/networking/NetworkService$2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */