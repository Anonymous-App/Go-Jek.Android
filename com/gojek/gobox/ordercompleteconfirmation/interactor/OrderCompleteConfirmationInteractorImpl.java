package com.gojek.gobox.ordercompleteconfirmation.interactor;

import com.gojek.gobox.model.ConfirmationRemainingTimeResponse;
import com.gojek.gobox.networking.NetworkService;
import rx.Observable;
import rx.Subscriber;

public class OrderCompleteConfirmationInteractorImpl
  implements OrderCompleteConfirmationInteractor
{
  private Observable<NetworkService> mNetworkServiceObservable;
  
  public OrderCompleteConfirmationInteractorImpl(Observable<NetworkService> paramObservable)
  {
    this.mNetworkServiceObservable = paramObservable;
  }
  
  public void confirmOrder(String paramString, int paramInt, Subscriber<Boolean> paramSubscriber)
  {
    this.mNetworkServiceObservable.subscribe(OrderCompleteConfirmationInteractorImpl..Lambda.2.lambdaFactory$(paramInt, paramString, paramSubscriber));
  }
  
  public void fetchConfirmationRemainingTime(String paramString, Subscriber<ConfirmationRemainingTimeResponse> paramSubscriber)
  {
    this.mNetworkServiceObservable.subscribe(OrderCompleteConfirmationInteractorImpl..Lambda.1.lambdaFactory$(paramString, paramSubscriber));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/ordercompleteconfirmation/interactor/OrderCompleteConfirmationInteractorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */