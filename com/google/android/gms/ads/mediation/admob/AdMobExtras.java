package com.google.android.gms.ads.mediation.admob;

import android.os.Bundle;
import com.google.ads.mediation.NetworkExtras;

@Deprecated
public final class AdMobExtras
  implements NetworkExtras
{
  private final Bundle mExtras;
  
  public AdMobExtras(Bundle paramBundle)
  {
    if (paramBundle != null) {}
    for (paramBundle = new Bundle(paramBundle);; paramBundle = null)
    {
      this.mExtras = paramBundle;
      return;
    }
  }
  
  public Bundle getExtras()
  {
    return this.mExtras;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/ads/mediation/admob/AdMobExtras.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */