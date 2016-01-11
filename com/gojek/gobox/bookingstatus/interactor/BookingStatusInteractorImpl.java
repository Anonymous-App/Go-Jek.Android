package com.gojek.gobox.bookingstatus.interactor;

import com.gojek.gobox.model.BookingDriverStatusResponse;
import com.gojek.gobox.networking.NetworkService;
import rx.Observable;
import rx.Subscriber;

public class BookingStatusInteractorImpl
  implements BookingStatusInteractor
{
  private Observable<NetworkService> mNetworkServiceObservable;
  
  public BookingStatusInteractorImpl(Observable<NetworkService> paramObservable)
  {
    this.mNetworkServiceObservable = paramObservable;
  }
  
  public void cancelBooking(String paramString, Subscriber<Boolean> paramSubscriber)
  {
    this.mNetworkServiceObservable.subscribe(BookingStatusInteractorImpl..Lambda.1.lambdaFactory$(paramString, paramSubscriber));
  }
  
  public void refreshBookingStatus(String paramString, Subscriber<BookingDriverStatusResponse> paramSubscriber)
  {
    this.mNetworkServiceObservable.subscribe(BookingStatusInteractorImpl..Lambda.4.lambdaFactory$(paramString, paramSubscriber));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/bookingstatus/interactor/BookingStatusInteractorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */