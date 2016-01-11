package com.gojek.gobox.ConfirmPage.presenter;

import com.gojek.gobox.ConfirmPage.view.ConfirmView;
import com.gojek.gobox.model.CustomerResponse;
import rx.Subscriber;

class ConfirmPresenterImpl$2
  extends Subscriber<CustomerResponse>
{
  ConfirmPresenterImpl$2(ConfirmPresenterImpl paramConfirmPresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    ConfirmPresenterImpl.access$000(this.this$0).showErrorConfirmOrder(paramThrowable);
    ConfirmPresenterImpl.access$000(this.this$0).onFetchGoJekCreditFinished();
    ConfirmPresenterImpl.access$000(this.this$0).showTotalPrice(-1L);
  }
  
  public void onNext(CustomerResponse paramCustomerResponse)
  {
    ConfirmPresenterImpl.access$000(this.this$0).onFetchGoJekCreditFinished();
    ConfirmPresenterImpl.access$000(this.this$0).showTotalPrice(paramCustomerResponse.getCreditBalance());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/ConfirmPage/presenter/ConfirmPresenterImpl$2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */