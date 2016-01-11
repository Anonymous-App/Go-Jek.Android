package com.google.android.gms.ads.purchase;

public abstract interface InAppPurchase
{
  public static final int RESOLUTION_CANCELED = 2;
  public static final int RESOLUTION_FAILURE = 0;
  public static final int RESOLUTION_INVALID_PRODUCT = 3;
  public static final int RESOLUTION_SUCCESS = 1;
  
  public abstract String getProductId();
  
  public abstract void recordPlayBillingResolution(int paramInt);
  
  public abstract void recordResolution(int paramInt);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/ads/purchase/InAppPurchase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */