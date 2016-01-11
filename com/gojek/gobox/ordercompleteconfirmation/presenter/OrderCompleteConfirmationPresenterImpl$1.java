package com.gojek.gobox.ordercompleteconfirmation.presenter;

import com.gojek.gobox.model.ConfirmationRemainingTimeResponse;
import com.gojek.gobox.ordercompleteconfirmation.view.OrderCompleteConfirmationView;
import java.util.Calendar;
import java.util.Date;
import rx.Subscriber;

class OrderCompleteConfirmationPresenterImpl$1
  extends Subscriber<ConfirmationRemainingTimeResponse>
{
  OrderCompleteConfirmationPresenterImpl$1(OrderCompleteConfirmationPresenterImpl paramOrderCompleteConfirmationPresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    OrderCompleteConfirmationPresenterImpl.access$000(this.this$0).showErrorMessage(paramThrowable);
  }
  
  public void onNext(ConfirmationRemainingTimeResponse paramConfirmationRemainingTimeResponse)
  {
    OrderCompleteConfirmationPresenterImpl.access$000(this.this$0).hideProgressBar();
    Calendar localCalendar = Calendar.getInstance();
    OrderCompleteConfirmationPresenterImpl.access$000(this.this$0).startRemainingTime(paramConfirmationRemainingTimeResponse.getExpiredTime() - localCalendar.getTime().getTime());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/ordercompleteconfirmation/presenter/OrderCompleteConfirmationPresenterImpl$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */