package com.gojek.gobox.timeoutOrder.interactor;

import com.gojek.gobox.networking.NetworkService;
import rx.Observable;
import rx.Subscriber;

public class TimeoutOrderInteractorImpl
  implements TimeoutOrderInteractor
{
  Observable<NetworkService> mNetworkServiceObservable;
  
  public TimeoutOrderInteractorImpl(Observable<NetworkService> paramObservable)
  {
    this.mNetworkServiceObservable = paramObservable;
  }
  
  public void fetchReorderResponse(Subscriber<Boolean> paramSubscriber, String paramString)
  {
    this.mNetworkServiceObservable.subscribe(TimeoutOrderInteractorImpl..Lambda.1.lambdaFactory$(paramString, paramSubscriber));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/timeoutOrder/interactor/TimeoutOrderInteractorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */