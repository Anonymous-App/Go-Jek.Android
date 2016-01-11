package com.gojek.gobox.ConfirmPage.presenter;

import com.gojek.gobox.model.BookingRequestBody;

public abstract interface ConfirmPresenter
{
  public abstract void onConfirmScreenResumed(String paramString);
  
  public abstract void onCreateView();
  
  public abstract void onOrderAction(int paramInt, BookingRequestBody paramBookingRequestBody);
  
  public abstract void onPopulateData();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/ConfirmPage/presenter/ConfirmPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */