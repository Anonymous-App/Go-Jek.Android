package com.gojek.gobox.orderForm.interactor;

import com.gojek.gobox.model.EstimationResponse;
import com.gojek.gobox.orderForm.component.OnCalculateEstimationListener;
import rx.Subscriber;

class OrderFormInteractorImpl$1
  extends Subscriber<EstimationResponse>
{
  OrderFormInteractorImpl$1(OrderFormInteractorImpl paramOrderFormInteractorImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    paramThrowable.printStackTrace();
    OrderFormInteractorImpl.access$000(this.this$0).onCalculatingError(paramThrowable);
  }
  
  public void onNext(EstimationResponse paramEstimationResponse)
  {
    OrderFormInteractorImpl.access$102(this.this$0, paramEstimationResponse);
    OrderFormInteractorImpl.access$000(this.this$0).onEstimationReceived(paramEstimationResponse);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/orderForm/interactor/OrderFormInteractorImpl$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */