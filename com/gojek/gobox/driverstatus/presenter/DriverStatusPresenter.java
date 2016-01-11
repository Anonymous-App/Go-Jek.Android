package com.gojek.gobox.driverstatus.presenter;

public abstract interface DriverStatusPresenter
{
  public abstract void onCallingCallCenterButtonClicked();
  
  public abstract void onCancelButtonClicked(String paramString);
  
  public abstract void onDoCanceling();
  
  public abstract void onDriverStatusCreateView(String paramString);
  
  public abstract void onOrderUnconfirmedState(String paramString);
  
  public abstract void onRefreshStatus(String paramString);
  
  public abstract void onSendInvoiceButtonClicked(String paramString1, String paramString2);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/driverstatus/presenter/DriverStatusPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */