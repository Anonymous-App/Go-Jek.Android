package com.gojek.gobox.driverstatus.presenter;

import com.gojek.gobox.driverstatus.view.DriverStatusView;
import com.gojek.gobox.model.BookingDriverStatusResponse;
import rx.Subscriber;

class DriverStatusPresenterImpl$2
  extends Subscriber<BookingDriverStatusResponse>
{
  DriverStatusPresenterImpl$2(DriverStatusPresenterImpl paramDriverStatusPresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    DriverStatusPresenterImpl.access$000(this.this$0).hideToolBarProgress();
    DriverStatusPresenterImpl.access$000(this.this$0).showErrorMessage(paramThrowable);
  }
  
  public void onNext(BookingDriverStatusResponse paramBookingDriverStatusResponse)
  {
    DriverStatusPresenterImpl.access$000(this.this$0).hideToolBarProgress();
    DriverStatusPresenterImpl.access$000(this.this$0).updateBookingState(paramBookingDriverStatusResponse);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/driverstatus/presenter/DriverStatusPresenterImpl$2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */