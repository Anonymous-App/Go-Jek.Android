package com.gojek.app.gobusway.schedule.presenter;

import com.gojek.app.gobusway.schedule.interactor.ScheduleInteractor;
import com.gojek.app.gobusway.schedule.view.ScheduleView;

public class SchedulePresenterImpl
  implements SchedulePresenter
{
  private ScheduleInteractor mScheduleInteractor;
  private ScheduleView mScheduleView;
  
  public SchedulePresenterImpl(ScheduleView paramScheduleView, ScheduleInteractor paramScheduleInteractor)
  {
    this.mScheduleView = paramScheduleView;
    this.mScheduleInteractor = paramScheduleInteractor;
  }
  
  public void onScheduleCreateView()
  {
    this.mScheduleView.showProgress();
    this.mScheduleInteractor.fetchHalteSchedule(this.mScheduleView.getHalteId(), new SchedulePresenterImpl.1(this));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/schedule/presenter/SchedulePresenterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */