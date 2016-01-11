package com.gojek.gobox.bookingstatus.interactor;

import com.gojek.gobox.model.BookingDriverStatusResponse;
import rx.Subscriber;

public abstract interface BookingStatusInteractor
{
  public abstract void cancelBooking(String paramString, Subscriber<Boolean> paramSubscriber);
  
  public abstract void refreshBookingStatus(String paramString, Subscriber<BookingDriverStatusResponse> paramSubscriber);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/bookingstatus/interactor/BookingStatusInteractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */