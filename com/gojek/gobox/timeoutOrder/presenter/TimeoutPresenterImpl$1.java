package com.gojek.gobox.timeoutOrder.presenter;

import com.gojek.gobox.timeoutOrder.view.TimeoutOrderView;
import rx.Subscriber;

class TimeoutPresenterImpl$1
  extends Subscriber<Boolean>
{
  TimeoutPresenterImpl$1(TimeoutPresenterImpl paramTimeoutPresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    TimeoutPresenterImpl.access$000(this.this$0).showErrorConfirmOrder(paramThrowable);
  }
  
  public void onNext(Boolean paramBoolean)
  {
    TimeoutPresenterImpl.access$000(this.this$0).openBookingStatusActivity(paramBoolean);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/timeoutOrder/presenter/TimeoutPresenterImpl$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */