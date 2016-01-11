package com.gojek.gobox.ConfirmPage.view;

import com.gojek.gobox.model.OrderResponse;

public abstract interface ConfirmView
{
  public abstract void hideConfirmProgressBar();
  
  public abstract void initView();
  
  public abstract void onFetchGoJekCreditFinished();
  
  public abstract void onFetchingGoJekCredit();
  
  public abstract void openBookingStatusActivity(OrderResponse paramOrderResponse);
  
  public abstract void populateView();
  
  public abstract void showConfirmProgressBar();
  
  public abstract void showErrorConfirmOrder(Throwable paramThrowable);
  
  public abstract void showTotalPrice(long paramLong);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/ConfirmPage/view/ConfirmView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */