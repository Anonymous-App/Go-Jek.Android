package com.gojek.gobox.driverstatus.presenter;

import com.gojek.gobox.driverstatus.view.DriverStatusView;
import rx.Subscriber;

class DriverStatusPresenterImpl$4
  extends Subscriber<Boolean>
{
  DriverStatusPresenterImpl$4(DriverStatusPresenterImpl paramDriverStatusPresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    DriverStatusPresenterImpl.access$000(this.this$0).showErrorMessage(paramThrowable);
  }
  
  public void onNext(Boolean paramBoolean)
  {
    if (paramBoolean.booleanValue()) {
      DriverStatusPresenterImpl.access$000(this.this$0).showSendInvoiceSuccessMessage();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/driverstatus/presenter/DriverStatusPresenterImpl$4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */