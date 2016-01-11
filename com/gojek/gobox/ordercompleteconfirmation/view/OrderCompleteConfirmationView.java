package com.gojek.gobox.ordercompleteconfirmation.view;

public abstract interface OrderCompleteConfirmationView
{
  public abstract void hideProgressBar();
  
  public abstract void initView();
  
  public abstract void showDriverStatusScreen(boolean paramBoolean);
  
  public abstract void showErrorMessage(Throwable paramThrowable);
  
  public abstract void showProgressBar(String paramString);
  
  public abstract void startRemainingTime(long paramLong);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/ordercompleteconfirmation/view/OrderCompleteConfirmationView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */