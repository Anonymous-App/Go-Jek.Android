package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;

@ez
public final class at
  extends bc.a
{
  private final AdListener nR;
  
  public at(AdListener paramAdListener)
  {
    this.nR = paramAdListener;
  }
  
  public void onAdClosed()
  {
    this.nR.onAdClosed();
  }
  
  public void onAdFailedToLoad(int paramInt)
  {
    this.nR.onAdFailedToLoad(paramInt);
  }
  
  public void onAdLeftApplication()
  {
    this.nR.onAdLeftApplication();
  }
  
  public void onAdLoaded()
  {
    this.nR.onAdLoaded();
  }
  
  public void onAdOpened()
  {
    this.nR.onAdOpened();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/at.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */