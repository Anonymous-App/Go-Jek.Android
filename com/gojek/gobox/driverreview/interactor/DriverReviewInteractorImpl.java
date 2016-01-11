package com.gojek.gobox.driverreview.interactor;

import com.gojek.gobox.model.DriverReviewRequestBody;
import com.gojek.gobox.networking.NetworkService;
import rx.Observable;
import rx.Subscriber;

public class DriverReviewInteractorImpl
  implements DriverReviewInteractor
{
  private final Observable<NetworkService> mNetworkServiceObservable;
  
  public DriverReviewInteractorImpl(Observable<NetworkService> paramObservable)
  {
    this.mNetworkServiceObservable = paramObservable;
  }
  
  public void submitReview(String paramString1, int paramInt, String paramString2, Subscriber<Boolean> paramSubscriber)
  {
    paramString2 = new DriverReviewRequestBody(paramInt, paramString2);
    this.mNetworkServiceObservable.subscribe(DriverReviewInteractorImpl..Lambda.1.lambdaFactory$(paramString1, paramString2, paramSubscriber));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/driverreview/interactor/DriverReviewInteractorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */