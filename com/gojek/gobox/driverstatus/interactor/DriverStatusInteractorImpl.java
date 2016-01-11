package com.gojek.gobox.driverstatus.interactor;

import com.gojek.gobox.model.BookingDriverStatusResponse;
import com.gojek.gobox.networking.NetworkService;
import java.util.HashMap;
import rx.Observable;
import rx.Subscriber;

public class DriverStatusInteractorImpl
  implements DriverStatusInteractor
{
  private Observable<NetworkService> mNetworkServiceObservable;
  
  public DriverStatusInteractorImpl(Observable<NetworkService> paramObservable)
  {
    this.mNetworkServiceObservable = paramObservable;
  }
  
  public void cancelBooking(Subscriber<Boolean> paramSubscriber, String paramString)
  {
    this.mNetworkServiceObservable.subscribe(DriverStatusInteractorImpl..Lambda.3.lambdaFactory$(paramString, paramSubscriber));
  }
  
  public void confirmOrder(String paramString, int paramInt, Subscriber<Boolean> paramSubscriber)
  {
    this.mNetworkServiceObservable.subscribe(DriverStatusInteractorImpl..Lambda.5.lambdaFactory$(paramInt, paramString, paramSubscriber));
  }
  
  public void fetchBookingData(Subscriber<HashMap<String, Object>> paramSubscriber, String paramString)
  {
    this.mNetworkServiceObservable.subscribe(DriverStatusInteractorImpl..Lambda.1.lambdaFactory$(paramString, paramSubscriber));
  }
  
  public void fetchBookingStatus(Subscriber<BookingDriverStatusResponse> paramSubscriber, String paramString)
  {
    this.mNetworkServiceObservable.subscribe(DriverStatusInteractorImpl..Lambda.2.lambdaFactory$(paramString, paramSubscriber));
  }
  
  public void sendInvoice(String paramString1, String paramString2, Subscriber<Boolean> paramSubscriber)
  {
    this.mNetworkServiceObservable.subscribe(DriverStatusInteractorImpl..Lambda.4.lambdaFactory$(paramString1, paramString2, paramSubscriber));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/driverstatus/interactor/DriverStatusInteractorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */