package com.gojek.gotix.activities;

import com.gojek.gotix.Event;

public abstract interface GotixMainNavigationListener
{
  public abstract void navigateToCancelPage();
  
  public abstract void navigateToEventDetail(int paramInt);
  
  public abstract void navigateToEventDetail(Event paramEvent);
  
  public abstract void navigateToFoundDriverPage(int paramInt);
  
  public abstract void navigateToPaymentFailedPage(int paramInt);
  
  public abstract void navigateToReceiptPage(int paramInt1, int paramInt2);
  
  public abstract void navigateToWaitingDriverPage(int paramInt);
  
  public abstract void navigateToWaitingPayment(int paramInt1, int paramInt2);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/activities/GotixMainNavigationListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */