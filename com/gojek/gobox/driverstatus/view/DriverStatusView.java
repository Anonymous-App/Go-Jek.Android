package com.gojek.gobox.driverstatus.view;

import com.gojek.gobox.model.BookingDriverStatusResponse;
import com.gojek.gobox.model.BookingInfoResponse;

public abstract interface DriverStatusView
{
  public abstract void callingCallCenterButtonClicked();
  
  public abstract void hideProgressBar();
  
  public abstract void hideToolBarProgress();
  
  public abstract void initViews();
  
  public abstract void openCallDriverScreen();
  
  public abstract void openMessageDriverScreen();
  
  public abstract void showBookingData(BookingInfoResponse paramBookingInfoResponse, BookingDriverStatusResponse paramBookingDriverStatusResponse);
  
  public abstract void showCancelConfirmationDialog();
  
  public abstract void showCancelFailedMessage();
  
  public abstract void showCancelSuccessMessage();
  
  public abstract void showCancelingProgress();
  
  public abstract void showErrorMessage(Throwable paramThrowable);
  
  public abstract void showProgressBar(boolean paramBoolean);
  
  public abstract void showRatingScreen();
  
  public abstract void showSendInvoiceSuccessMessage();
  
  public abstract void showToolBarProgress();
  
  public abstract void updateBookingState(BookingDriverStatusResponse paramBookingDriverStatusResponse);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/driverstatus/view/DriverStatusView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */