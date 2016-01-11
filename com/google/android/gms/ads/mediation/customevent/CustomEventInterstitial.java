package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdRequest;

public abstract interface CustomEventInterstitial
  extends CustomEvent
{
  public abstract void requestInterstitialAd(Context paramContext, CustomEventInterstitialListener paramCustomEventInterstitialListener, String paramString, MediationAdRequest paramMediationAdRequest, Bundle paramBundle);
  
  public abstract void showInterstitial();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/ads/mediation/customevent/CustomEventInterstitial.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */