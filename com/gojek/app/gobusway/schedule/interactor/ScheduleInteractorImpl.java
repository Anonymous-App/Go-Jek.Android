package com.gojek.app.gobusway.schedule.interactor;

import com.gojek.app.gobusway.model.HalteScheduleResponse;
import com.gojek.app.gobusway.networking.NetworkService;
import rx.Observable;
import rx.Subscriber;

public class ScheduleInteractorImpl
  implements ScheduleInteractor
{
  Observable<NetworkService> mNetworkServiceObservable;
  
  public ScheduleInteractorImpl(Observable<NetworkService> paramObservable)
  {
    this.mNetworkServiceObservable = paramObservable;
  }
  
  public void fetchHalteSchedule(String paramString, Subscriber<HalteScheduleResponse> paramSubscriber)
  {
    this.mNetworkServiceObservable.subscribe(ScheduleInteractorImpl..Lambda.1.lambdaFactory$(paramString, paramSubscriber));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/schedule/interactor/ScheduleInteractorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */