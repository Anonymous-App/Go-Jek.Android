package com.gojek.gobox.orderForm.component;

import com.gojek.gobox.model.EstimationResponse;

public abstract interface OnCalculateEstimationListener
{
  public abstract void onCalculating();
  
  public abstract void onCalculatingError(Throwable paramThrowable);
  
  public abstract void onEstimationReceived(EstimationResponse paramEstimationResponse);
  
  public abstract void onNoDestinationPicked();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/orderForm/component/OnCalculateEstimationListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */