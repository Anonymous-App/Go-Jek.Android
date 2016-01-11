package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;

public abstract interface CustomEventBanner
  extends CustomEvent
{
  public abstract void requestBannerAd(Context paramContext, CustomEventBannerListener paramCustomEventBannerListener, String paramString, AdSize paramAdSize, MediationAdRequest paramMediationAdRequest, Bundle paramBundle);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/ads/mediation/customevent/CustomEventBanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */