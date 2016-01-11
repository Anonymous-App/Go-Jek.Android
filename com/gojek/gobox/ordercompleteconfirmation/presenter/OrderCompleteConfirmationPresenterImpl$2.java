package com.gojek.gobox.ordercompleteconfirmation.presenter;

import com.gojek.gobox.ordercompleteconfirmation.view.OrderCompleteConfirmationView;
import rx.Subscriber;

class OrderCompleteConfirmationPresenterImpl$2
  extends Subscriber<Boolean>
{
  OrderCompleteConfirmationPresenterImpl$2(OrderCompleteConfirmationPresenterImpl paramOrderCompleteConfirmationPresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    paramThrowable.printStackTrace();
    OrderCompleteConfirmationPresenterImpl.access$000(this.this$0).hideProgressBar();
    OrderCompleteConfirmationPresenterImpl.access$000(this.this$0).showErrorMessage(paramThrowable);
  }
  
  public void onNext(Boolean paramBoolean)
  {
    OrderCompleteConfirmationPresenterImpl.access$000(this.this$0).hideProgressBar();
    OrderCompleteConfirmationPresenterImpl.access$000(this.this$0).showDriverStatusScreen(true);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/ordercompleteconfirmation/presenter/OrderCompleteConfirmationPresenterImpl$2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */