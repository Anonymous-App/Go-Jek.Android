package com.gojek.gobox.bookingstatus.presenter;

public abstract interface BookingStatusPresenter
{
  public abstract void onBookingStatusViewCreated();
  
  public abstract void onCancelButtonClicked(String paramString);
  
  public abstract void onDoCanceling();
  
  public abstract void onRefreshButtonClicked(String paramString);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/bookingstatus/presenter/BookingStatusPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */