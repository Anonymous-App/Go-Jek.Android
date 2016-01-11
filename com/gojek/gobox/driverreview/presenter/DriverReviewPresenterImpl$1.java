package com.gojek.gobox.driverreview.presenter;

import com.gojek.gobox.driverreview.view.DriverReviewView;
import rx.Subscriber;

class DriverReviewPresenterImpl$1
  extends Subscriber<Boolean>
{
  DriverReviewPresenterImpl$1(DriverReviewPresenterImpl paramDriverReviewPresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    DriverReviewPresenterImpl.access$000(this.this$0).hideProgressBar();
    DriverReviewPresenterImpl.access$000(this.this$0).showSubmitButton();
    DriverReviewPresenterImpl.access$000(this.this$0).showReviewErrorMessage(paramThrowable);
  }
  
  public void onNext(Boolean paramBoolean)
  {
    if (paramBoolean.booleanValue())
    {
      DriverReviewPresenterImpl.access$000(this.this$0).hideProgressBar();
      DriverReviewPresenterImpl.access$000(this.this$0).showReviewSuccessMessage();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/driverreview/presenter/DriverReviewPresenterImpl$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */