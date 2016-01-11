package com.gojek.gobox.ConfirmPage.presenter;

import com.gojek.gobox.ConfirmPage.view.ConfirmView;
import com.gojek.gobox.model.OrderResponse;
import rx.Subscriber;

class ConfirmPresenterImpl$1
  extends Subscriber<OrderResponse>
{
  ConfirmPresenterImpl$1(ConfirmPresenterImpl paramConfirmPresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    ConfirmPresenterImpl.access$000(this.this$0).hideConfirmProgressBar();
    ConfirmPresenterImpl.access$000(this.this$0).showErrorConfirmOrder(paramThrowable);
  }
  
  public void onNext(OrderResponse paramOrderResponse)
  {
    ConfirmPresenterImpl.access$000(this.this$0).openBookingStatusActivity(paramOrderResponse);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/ConfirmPage/presenter/ConfirmPresenterImpl$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */