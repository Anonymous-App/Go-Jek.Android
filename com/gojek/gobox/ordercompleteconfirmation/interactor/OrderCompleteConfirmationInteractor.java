package com.gojek.gobox.ordercompleteconfirmation.interactor;

import com.gojek.gobox.model.ConfirmationRemainingTimeResponse;
import rx.Subscriber;

public abstract interface OrderCompleteConfirmationInteractor
{
  public abstract void confirmOrder(String paramString, int paramInt, Subscriber<Boolean> paramSubscriber);
  
  public abstract void fetchConfirmationRemainingTime(String paramString, Subscriber<ConfirmationRemainingTimeResponse> paramSubscriber);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/ordercompleteconfirmation/interactor/OrderCompleteConfirmationInteractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */