package com.gojek.gobox.driverreview.view;

public abstract interface DriverReviewView
{
  public abstract void hideProgressBar();
  
  public abstract void hideSubmitButton();
  
  public abstract void initOrderData();
  
  public abstract void initView();
  
  public abstract void showProgressBar();
  
  public abstract void showReviewErrorMessage(Throwable paramThrowable);
  
  public abstract void showReviewSuccessMessage();
  
  public abstract void showSubmitButton();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/driverreview/view/DriverReviewView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */