package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.internal.gs;

public final class CustomEventAdapter
  implements MediationBannerAdapter, MediationInterstitialAdapter
{
  private View n;
  private CustomEventBanner xf;
  private CustomEventInterstitial xg;
  
  private static <T> T a(String paramString)
  {
    try
    {
      Object localObject = Class.forName(paramString).newInstance();
      return (T)localObject;
    }
    catch (Throwable localThrowable)
    {
      gs.W("Could not instantiate custom event adapter: " + paramString + ". " + localThrowable.getMessage());
    }
    return null;
  }
  
  private void a(View paramView)
  {
    this.n = paramView;
  }
  
  public View getBannerView()
  {
    return this.n;
  }
  
  public void onDestroy()
  {
    if (this.xf != null) {
      this.xf.onDestroy();
    }
    if (this.xg != null) {
      this.xg.onDestroy();
    }
  }
  
  public void onPause()
  {
    if (this.xf != null) {
      this.xf.onPause();
    }
    if (this.xg != null) {
      this.xg.onPause();
    }
  }
  
  public void onResume()
  {
    if (this.xf != null) {
      this.xf.onResume();
    }
    if (this.xg != null) {
      this.xg.onResume();
    }
  }
  
  public void requestBannerAd(Context paramContext, MediationBannerListener paramMediationBannerListener, Bundle paramBundle1, AdSize paramAdSize, MediationAdRequest paramMediationAdRequest, Bundle paramBundle2)
  {
    this.xf = ((CustomEventBanner)a(paramBundle1.getString("class_name")));
    if (this.xf == null)
    {
      paramMediationBannerListener.onAdFailedToLoad(this, 0);
      return;
    }
    if (paramBundle2 == null) {}
    for (paramBundle2 = null;; paramBundle2 = paramBundle2.getBundle(paramBundle1.getString("class_name")))
    {
      this.xf.requestBannerAd(paramContext, new a(this, paramMediationBannerListener), paramBundle1.getString("parameter"), paramAdSize, paramMediationAdRequest, paramBundle2);
      return;
    }
  }
  
  public void requestInterstitialAd(Context paramContext, MediationInterstitialListener paramMediationInterstitialListener, Bundle paramBundle1, MediationAdRequest paramMediationAdRequest, Bundle paramBundle2)
  {
    this.xg = ((CustomEventInterstitial)a(paramBundle1.getString("class_name")));
    if (this.xg == null)
    {
      paramMediationInterstitialListener.onAdFailedToLoad(this, 0);
      return;
    }
    if (paramBundle2 == null) {}
    for (paramBundle2 = null;; paramBundle2 = paramBundle2.getBundle(paramBundle1.getString("class_name")))
    {
      this.xg.requestInterstitialAd(paramContext, new b(this, paramMediationInterstitialListener), paramBundle1.getString("parameter"), paramMediationAdRequest, paramBundle2);
      return;
    }
  }
  
  public void showInterstitial()
  {
    this.xg.showInterstitial();
  }
  
  private static final class a
    implements CustomEventBannerListener
  {
    private final MediationBannerListener l;
    private final CustomEventAdapter xh;
    
    public a(CustomEventAdapter paramCustomEventAdapter, MediationBannerListener paramMediationBannerListener)
    {
      this.xh = paramCustomEventAdapter;
      this.l = paramMediationBannerListener;
    }
    
    public void onAdClicked()
    {
      gs.S("Custom event adapter called onAdClicked.");
      this.l.onAdClicked(this.xh);
    }
    
    public void onAdClosed()
    {
      gs.S("Custom event adapter called onAdClosed.");
      this.l.onAdClosed(this.xh);
    }
    
    public void onAdFailedToLoad(int paramInt)
    {
      gs.S("Custom event adapter called onAdFailedToLoad.");
      this.l.onAdFailedToLoad(this.xh, paramInt);
    }
    
    public void onAdLeftApplication()
    {
      gs.S("Custom event adapter called onAdLeftApplication.");
      this.l.onAdLeftApplication(this.xh);
    }
    
    public void onAdLoaded(View paramView)
    {
      gs.S("Custom event adapter called onAdLoaded.");
      CustomEventAdapter.a(this.xh, paramView);
      this.l.onAdLoaded(this.xh);
    }
    
    public void onAdOpened()
    {
      gs.S("Custom event adapter called onAdOpened.");
      this.l.onAdOpened(this.xh);
    }
  }
  
  private class b
    implements CustomEventInterstitialListener
  {
    private final MediationInterstitialListener m;
    private final CustomEventAdapter xh;
    
    public b(CustomEventAdapter paramCustomEventAdapter, MediationInterstitialListener paramMediationInterstitialListener)
    {
      this.xh = paramCustomEventAdapter;
      this.m = paramMediationInterstitialListener;
    }
    
    public void onAdClicked()
    {
      gs.S("Custom event adapter called onAdClicked.");
      this.m.onAdClicked(this.xh);
    }
    
    public void onAdClosed()
    {
      gs.S("Custom event adapter called onAdClosed.");
      this.m.onAdClosed(this.xh);
    }
    
    public void onAdFailedToLoad(int paramInt)
    {
      gs.S("Custom event adapter called onFailedToReceiveAd.");
      this.m.onAdFailedToLoad(this.xh, paramInt);
    }
    
    public void onAdLeftApplication()
    {
      gs.S("Custom event adapter called onAdLeftApplication.");
      this.m.onAdLeftApplication(this.xh);
    }
    
    public void onAdLoaded()
    {
      gs.S("Custom event adapter called onReceivedAd.");
      this.m.onAdLoaded(CustomEventAdapter.this);
    }
    
    public void onAdOpened()
    {
      gs.S("Custom event adapter called onAdOpened.");
      this.m.onAdOpened(this.xh);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/ads/mediation/customevent/CustomEventAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */