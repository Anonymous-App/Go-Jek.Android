package com.google.android.gms.internal;

import android.os.Handler;
import android.os.RemoteException;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

@ez
public final class da<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters>
  implements MediationBannerListener, MediationInterstitialListener
{
  private final cv qF;
  
  public da(cv paramcv)
  {
    this.qF = paramcv;
  }
  
  public void onClick(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    gs.S("Adapter called onClick.");
    if (!gr.ds())
    {
      gs.W("onClick must be called on the main UI thread.");
      gr.wC.post(new Runnable()
      {
        public void run()
        {
          try
          {
            da.a(da.this).onAdClicked();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            gs.d("Could not call onAdClicked.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.qF.onAdClicked();
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      gs.d("Could not call onAdClicked.", paramMediationBannerAdapter);
    }
  }
  
  public void onDismissScreen(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    gs.S("Adapter called onDismissScreen.");
    if (!gr.ds())
    {
      gs.W("onDismissScreen must be called on the main UI thread.");
      gr.wC.post(new Runnable()
      {
        public void run()
        {
          try
          {
            da.a(da.this).onAdClosed();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            gs.d("Could not call onAdClosed.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.qF.onAdClosed();
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      gs.d("Could not call onAdClosed.", paramMediationBannerAdapter);
    }
  }
  
  public void onDismissScreen(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    gs.S("Adapter called onDismissScreen.");
    if (!gr.ds())
    {
      gs.W("onDismissScreen must be called on the main UI thread.");
      gr.wC.post(new Runnable()
      {
        public void run()
        {
          try
          {
            da.a(da.this).onAdClosed();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            gs.d("Could not call onAdClosed.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.qF.onAdClosed();
      return;
    }
    catch (RemoteException paramMediationInterstitialAdapter)
    {
      gs.d("Could not call onAdClosed.", paramMediationInterstitialAdapter);
    }
  }
  
  public void onFailedToReceiveAd(MediationBannerAdapter<?, ?> paramMediationBannerAdapter, final AdRequest.ErrorCode paramErrorCode)
  {
    gs.S("Adapter called onFailedToReceiveAd with error. " + paramErrorCode);
    if (!gr.ds())
    {
      gs.W("onFailedToReceiveAd must be called on the main UI thread.");
      gr.wC.post(new Runnable()
      {
        public void run()
        {
          try
          {
            da.a(da.this).onAdFailedToLoad(db.a(paramErrorCode));
            return;
          }
          catch (RemoteException localRemoteException)
          {
            gs.d("Could not call onAdFailedToLoad.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.qF.onAdFailedToLoad(db.a(paramErrorCode));
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      gs.d("Could not call onAdFailedToLoad.", paramMediationBannerAdapter);
    }
  }
  
  public void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter, final AdRequest.ErrorCode paramErrorCode)
  {
    gs.S("Adapter called onFailedToReceiveAd with error " + paramErrorCode + ".");
    if (!gr.ds())
    {
      gs.W("onFailedToReceiveAd must be called on the main UI thread.");
      gr.wC.post(new Runnable()
      {
        public void run()
        {
          try
          {
            da.a(da.this).onAdFailedToLoad(db.a(paramErrorCode));
            return;
          }
          catch (RemoteException localRemoteException)
          {
            gs.d("Could not call onAdFailedToLoad.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.qF.onAdFailedToLoad(db.a(paramErrorCode));
      return;
    }
    catch (RemoteException paramMediationInterstitialAdapter)
    {
      gs.d("Could not call onAdFailedToLoad.", paramMediationInterstitialAdapter);
    }
  }
  
  public void onLeaveApplication(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    gs.S("Adapter called onLeaveApplication.");
    if (!gr.ds())
    {
      gs.W("onLeaveApplication must be called on the main UI thread.");
      gr.wC.post(new Runnable()
      {
        public void run()
        {
          try
          {
            da.a(da.this).onAdLeftApplication();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            gs.d("Could not call onAdLeftApplication.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.qF.onAdLeftApplication();
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      gs.d("Could not call onAdLeftApplication.", paramMediationBannerAdapter);
    }
  }
  
  public void onLeaveApplication(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    gs.S("Adapter called onLeaveApplication.");
    if (!gr.ds())
    {
      gs.W("onLeaveApplication must be called on the main UI thread.");
      gr.wC.post(new Runnable()
      {
        public void run()
        {
          try
          {
            da.a(da.this).onAdLeftApplication();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            gs.d("Could not call onAdLeftApplication.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.qF.onAdLeftApplication();
      return;
    }
    catch (RemoteException paramMediationInterstitialAdapter)
    {
      gs.d("Could not call onAdLeftApplication.", paramMediationInterstitialAdapter);
    }
  }
  
  public void onPresentScreen(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    gs.S("Adapter called onPresentScreen.");
    if (!gr.ds())
    {
      gs.W("onPresentScreen must be called on the main UI thread.");
      gr.wC.post(new Runnable()
      {
        public void run()
        {
          try
          {
            da.a(da.this).onAdOpened();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            gs.d("Could not call onAdOpened.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.qF.onAdOpened();
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      gs.d("Could not call onAdOpened.", paramMediationBannerAdapter);
    }
  }
  
  public void onPresentScreen(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    gs.S("Adapter called onPresentScreen.");
    if (!gr.ds())
    {
      gs.W("onPresentScreen must be called on the main UI thread.");
      gr.wC.post(new Runnable()
      {
        public void run()
        {
          try
          {
            da.a(da.this).onAdOpened();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            gs.d("Could not call onAdOpened.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.qF.onAdOpened();
      return;
    }
    catch (RemoteException paramMediationInterstitialAdapter)
    {
      gs.d("Could not call onAdOpened.", paramMediationInterstitialAdapter);
    }
  }
  
  public void onReceivedAd(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    gs.S("Adapter called onReceivedAd.");
    if (!gr.ds())
    {
      gs.W("onReceivedAd must be called on the main UI thread.");
      gr.wC.post(new Runnable()
      {
        public void run()
        {
          try
          {
            da.a(da.this).onAdLoaded();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            gs.d("Could not call onAdLoaded.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.qF.onAdLoaded();
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      gs.d("Could not call onAdLoaded.", paramMediationBannerAdapter);
    }
  }
  
  public void onReceivedAd(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    gs.S("Adapter called onReceivedAd.");
    if (!gr.ds())
    {
      gs.W("onReceivedAd must be called on the main UI thread.");
      gr.wC.post(new Runnable()
      {
        public void run()
        {
          try
          {
            da.a(da.this).onAdLoaded();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            gs.d("Could not call onAdLoaded.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.qF.onAdLoaded();
      return;
    }
    catch (RemoteException paramMediationInterstitialAdapter)
    {
      gs.d("Could not call onAdLoaded.", paramMediationInterstitialAdapter);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/da.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */