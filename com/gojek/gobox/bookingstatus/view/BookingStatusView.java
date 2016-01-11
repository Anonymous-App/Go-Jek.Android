package com.gojek.gobox.bookingstatus.view;

public abstract interface BookingStatusView
{
  public abstract void bindReceiver();
  
  public abstract void hideToolBarProgress();
  
  public abstract void initView();
  
  public abstract void showCancelConfirmationDialog();
  
  public abstract void showCancelFailedMessage();
  
  public abstract void showCancelSuccessMessage();
  
  public abstract void showCancelingProgress();
  
  public abstract void showNextBookingStatusIfNeeded(int paramInt);
  
  public abstract void showToolBarProgress();
  
  public abstract void startRunningText();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/bookingstatus/view/BookingStatusView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */