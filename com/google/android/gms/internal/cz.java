package com.google.android.gms.internal;

import android.app.Activity;
import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

@ez
public final class cz<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters>
  extends cu.a
{
  private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> qG;
  private final NETWORK_EXTRAS qH;
  
  public cz(MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> paramMediationAdapter, NETWORK_EXTRAS paramNETWORK_EXTRAS)
  {
    this.qG = paramMediationAdapter;
    this.qH = paramNETWORK_EXTRAS;
  }
  
  private SERVER_PARAMETERS b(String paramString1, int paramInt, String paramString2)
    throws RemoteException
  {
    if (paramString1 != null) {
      try
      {
        localObject = JSONObjectInstrumentation.init(paramString1);
        paramString2 = new HashMap(((JSONObject)localObject).length());
        Iterator localIterator = ((JSONObject)localObject).keys();
        for (;;)
        {
          paramString1 = paramString2;
          if (!localIterator.hasNext()) {
            break;
          }
          paramString1 = (String)localIterator.next();
          paramString2.put(paramString1, ((JSONObject)localObject).getString(paramString1));
        }
        paramString1 = new HashMap(0);
      }
      catch (Throwable paramString1)
      {
        gs.d("Could not get MediationServerParameters.", paramString1);
        throw new RemoteException();
      }
    }
    Object localObject = this.qG.getServerParametersType();
    paramString2 = null;
    if (localObject != null)
    {
      paramString2 = (MediationServerParameters)((Class)localObject).newInstance();
      paramString2.load(paramString1);
    }
    return paramString2;
  }
  
  public void a(d paramd, av paramav, String paramString, cv paramcv)
    throws RemoteException
  {
    a(paramd, paramav, paramString, null, paramcv);
  }
  
  public void a(d paramd, av paramav, String paramString1, String paramString2, cv paramcv)
    throws RemoteException
  {
    if (!(this.qG instanceof MediationInterstitialAdapter))
    {
      gs.W("MediationAdapter is not a MediationInterstitialAdapter: " + this.qG.getClass().getCanonicalName());
      throw new RemoteException();
    }
    gs.S("Requesting interstitial ad from adapter.");
    try
    {
      ((MediationInterstitialAdapter)this.qG).requestInterstitialAd(new da(paramcv), (Activity)e.f(paramd), b(paramString1, paramav.nX, paramString2), db.d(paramav), this.qH);
      return;
    }
    catch (Throwable paramd)
    {
      gs.d("Could not request interstitial ad from adapter.", paramd);
      throw new RemoteException();
    }
  }
  
  public void a(d paramd, ay paramay, av paramav, String paramString, cv paramcv)
    throws RemoteException
  {
    a(paramd, paramay, paramav, paramString, null, paramcv);
  }
  
  public void a(d paramd, ay paramay, av paramav, String paramString1, String paramString2, cv paramcv)
    throws RemoteException
  {
    if (!(this.qG instanceof MediationBannerAdapter))
    {
      gs.W("MediationAdapter is not a MediationBannerAdapter: " + this.qG.getClass().getCanonicalName());
      throw new RemoteException();
    }
    gs.S("Requesting banner ad from adapter.");
    try
    {
      ((MediationBannerAdapter)this.qG).requestBannerAd(new da(paramcv), (Activity)e.f(paramd), b(paramString1, paramav.nX, paramString2), db.b(paramay), db.d(paramav), this.qH);
      return;
    }
    catch (Throwable paramd)
    {
      gs.d("Could not request banner ad from adapter.", paramd);
      throw new RemoteException();
    }
  }
  
  public void destroy()
    throws RemoteException
  {
    try
    {
      this.qG.destroy();
      return;
    }
    catch (Throwable localThrowable)
    {
      gs.d("Could not destroy adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public d getView()
    throws RemoteException
  {
    if (!(this.qG instanceof MediationBannerAdapter))
    {
      gs.W("MediationAdapter is not a MediationBannerAdapter: " + this.qG.getClass().getCanonicalName());
      throw new RemoteException();
    }
    try
    {
      d locald = e.k(((MediationBannerAdapter)this.qG).getBannerView());
      return locald;
    }
    catch (Throwable localThrowable)
    {
      gs.d("Could not get banner view from adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void pause()
    throws RemoteException
  {
    throw new RemoteException();
  }
  
  public void resume()
    throws RemoteException
  {
    throw new RemoteException();
  }
  
  public void showInterstitial()
    throws RemoteException
  {
    if (!(this.qG instanceof MediationInterstitialAdapter))
    {
      gs.W("MediationAdapter is not a MediationInterstitialAdapter: " + this.qG.getClass().getCanonicalName());
      throw new RemoteException();
    }
    gs.S("Showing interstitial from adapter.");
    try
    {
      ((MediationInterstitialAdapter)this.qG).showInterstitial();
      return;
    }
    catch (Throwable localThrowable)
    {
      gs.d("Could not show interstitial from adapter.", localThrowable);
      throw new RemoteException();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/cz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */