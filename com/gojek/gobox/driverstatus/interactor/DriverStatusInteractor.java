package com.gojek.gobox.driverstatus.interactor;

import com.gojek.gobox.model.BookingDriverStatusResponse;
import java.util.HashMap;
import rx.Subscriber;

public abstract interface DriverStatusInteractor
{
  public static final String BOOKING_INFO_KEY = "booking info";
  public static final String BOOKING_STATUS_KEY = "booking status";
  
  public abstract void cancelBooking(Subscriber<Boolean> paramSubscriber, String paramString);
  
  public abstract void confirmOrder(String paramString, int paramInt, Subscriber<Boolean> paramSubscriber);
  
  public abstract void fetchBookingData(Subscriber<HashMap<String, Object>> paramSubscriber, String paramString);
  
  public abstract void fetchBookingStatus(Subscriber<BookingDriverStatusResponse> paramSubscriber, String paramString);
  
  public abstract void sendInvoice(String paramString1, String paramString2, Subscriber<Boolean> paramSubscriber);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/driverstatus/interactor/DriverStatusInteractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */