package com.gojek.gobox.bookingstatus.presenter;

import com.gojek.gobox.bookingstatus.view.BookingStatusView;
import com.gojek.gobox.model.BookingDriverStatusResponse;
import rx.Subscriber;

class BookingStatusPresenterImpl$2
  extends Subscriber<BookingDriverStatusResponse>
{
  BookingStatusPresenterImpl$2(BookingStatusPresenterImpl paramBookingStatusPresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    BookingStatusPresenterImpl.access$000(this.this$0).hideToolBarProgress();
  }
  
  public void onNext(BookingDriverStatusResponse paramBookingDriverStatusResponse)
  {
    BookingStatusPresenterImpl.access$000(this.this$0).hideToolBarProgress();
    BookingStatusPresenterImpl.access$000(this.this$0).showNextBookingStatusIfNeeded(paramBookingDriverStatusResponse.getState());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/bookingstatus/presenter/BookingStatusPresenterImpl$2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */