package com.gojek.gobox.ConfirmPage.interactor;

import com.gojek.gobox.model.BookingRequestBody;
import com.gojek.gobox.model.CorporatePINResponse;
import com.gojek.gobox.model.CustomerResponse;
import com.gojek.gobox.model.OrderResponse;
import com.gojek.gobox.networking.NetworkService;
import rx.Observable;
import rx.Subscriber;

public class ConfirmInteractorImpl
  implements ConfirmInteractor
{
  private Observable<NetworkService> mNetworkServiceObservable;
  
  public ConfirmInteractorImpl(Observable<NetworkService> paramObservable)
  {
    this.mNetworkServiceObservable = paramObservable;
  }
  
  public void fetchCorporatePinResponse(String paramString, Subscriber<CorporatePINResponse> paramSubscriber)
  {
    this.mNetworkServiceObservable.subscribe(ConfirmInteractorImpl..Lambda.3.lambdaFactory$(paramString, paramSubscriber));
  }
  
  public void fetchCostumerResponse(String paramString, Subscriber<CustomerResponse> paramSubscriber)
  {
    this.mNetworkServiceObservable.subscribe(ConfirmInteractorImpl..Lambda.2.lambdaFactory$(paramSubscriber));
  }
  
  public void fetchOrderId(Subscriber<OrderResponse> paramSubscriber, int paramInt, BookingRequestBody paramBookingRequestBody)
  {
    this.mNetworkServiceObservable.subscribe(ConfirmInteractorImpl..Lambda.1.lambdaFactory$(paramInt, paramBookingRequestBody, paramSubscriber));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/ConfirmPage/interactor/ConfirmInteractorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */