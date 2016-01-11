package com.gojek.app.gobusway.schedule.presenter;

import com.gojek.app.gobusway.model.HalteScheduleResponse;
import com.gojek.app.gobusway.schedule.view.ScheduleView;
import rx.Subscriber;

class SchedulePresenterImpl$1
  extends Subscriber<HalteScheduleResponse>
{
  SchedulePresenterImpl$1(SchedulePresenterImpl paramSchedulePresenterImpl) {}
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    SchedulePresenterImpl.access$000(this.this$0).hideProgress();
    SchedulePresenterImpl.access$000(this.this$0).showErrorWhileFetchData();
  }
  
  public void onNext(HalteScheduleResponse paramHalteScheduleResponse)
  {
    SchedulePresenterImpl.access$000(this.this$0).hideProgress();
    SchedulePresenterImpl.access$000(this.this$0).showSchedule(paramHalteScheduleResponse.getHalteSchedules());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/schedule/presenter/SchedulePresenterImpl$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */