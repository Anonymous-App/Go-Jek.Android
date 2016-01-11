package com.gojek.gobox.driverstatus.presenter;

import com.gojek.gobox.driverstatus.view.DriverStatusView;
import com.gojek.gobox.model.BookingDriverStatusResponse;
import com.gojek.gobox.model.BookingInfoResponse;
import java.util.HashMap;
import rx.Subscriber;

class DriverStatusPresenterImpl$1
  extends Subscriber<HashMap<String, Object>>
{
  DriverStatusPresenterImpl$1(DriverStatusPresenterImpl paramDriverStatusPresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    DriverStatusPresenterImpl.access$000(this.this$0).hideProgressBar();
    DriverStatusPresenterImpl.access$000(this.this$0).showErrorMessage(paramThrowable);
  }
  
  public void onNext(HashMap<String, Object> paramHashMap)
  {
    DriverStatusPresenterImpl.access$102(this.this$0, (BookingInfoResponse)paramHashMap.get("booking info"));
    paramHashMap = (BookingDriverStatusResponse)paramHashMap.get("booking status");
    DriverStatusPresenterImpl.access$000(this.this$0).showBookingData(DriverStatusPresenterImpl.access$100(this.this$0), paramHashMap);
    DriverStatusPresenterImpl.access$000(this.this$0).hideProgressBar();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/driverstatus/presenter/DriverStatusPresenterImpl$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */