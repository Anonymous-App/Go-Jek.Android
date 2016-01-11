package com.gojek.gobox.timeoutOrder.interactor;

import rx.Subscriber;

public abstract interface TimeoutOrderInteractor
{
  public abstract void fetchReorderResponse(Subscriber<Boolean> paramSubscriber, String paramString);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/timeoutOrder/interactor/TimeoutOrderInteractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */