package com.gojek.app.gobusway.schedule.interactor;

import com.gojek.app.gobusway.model.HalteScheduleResponse;
import rx.Subscriber;

public abstract interface ScheduleInteractor
{
  public abstract void fetchHalteSchedule(String paramString, Subscriber<HalteScheduleResponse> paramSubscriber);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/schedule/interactor/ScheduleInteractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */