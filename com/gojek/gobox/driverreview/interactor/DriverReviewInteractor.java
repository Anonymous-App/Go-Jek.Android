package com.gojek.gobox.driverreview.interactor;

import rx.Subscriber;

public abstract interface DriverReviewInteractor
{
  public abstract void submitReview(String paramString1, int paramInt, String paramString2, Subscriber<Boolean> paramSubscriber);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/driverreview/interactor/DriverReviewInteractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */