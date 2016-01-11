package com.gojek.gobox.bookingstatus.presenter;

import com.gojek.gobox.bookingstatus.view.BookingStatusView;
import rx.Subscriber;

class BookingStatusPresenterImpl$1
  extends Subscriber<Boolean>
{
  BookingStatusPresenterImpl$1(BookingStatusPresenterImpl paramBookingStatusPresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    BookingStatusPresenterImpl.access$000(this.this$0).showCancelFailedMessage();
  }
  
  public void onNext(Boolean paramBoolean)
  {
    if (paramBoolean.booleanValue())
    {
      BookingStatusPresenterImpl.access$000(this.this$0).showCancelSuccessMessage();
      return;
    }
    BookingStatusPresenterImpl.access$000(this.this$0).showCancelFailedMessage();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/bookingstatus/presenter/BookingStatusPresenterImpl$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */