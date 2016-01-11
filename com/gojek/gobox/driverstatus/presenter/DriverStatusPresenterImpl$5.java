package com.gojek.gobox.driverstatus.presenter;

import com.gojek.gobox.driverstatus.view.DriverStatusView;
import rx.Subscriber;

class DriverStatusPresenterImpl$5
  extends Subscriber<Boolean>
{
  DriverStatusPresenterImpl$5(DriverStatusPresenterImpl paramDriverStatusPresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    DriverStatusPresenterImpl.access$000(this.this$0).showErrorMessage(paramThrowable);
  }
  
  public void onNext(Boolean paramBoolean)
  {
    DriverStatusPresenterImpl.access$000(this.this$0).showRatingScreen();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/driverstatus/presenter/DriverStatusPresenterImpl$5.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */