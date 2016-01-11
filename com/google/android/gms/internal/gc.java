package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;

@ez
public class gc
{
  private final Object mw = new Object();
  private final String vL;
  private int vX = 0;
  private long vY = -1L;
  private long vZ = -1L;
  private int wa = 0;
  private int wb = -1;
  
  public gc(String paramString)
  {
    this.vL = paramString;
  }
  
  public static boolean m(Context paramContext)
  {
    int i = paramContext.getResources().getIdentifier("Theme.Translucent", "style", "android");
    if (i == 0)
    {
      gs.U("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
      return false;
    }
    ComponentName localComponentName = new ComponentName(paramContext.getPackageName(), "com.google.android.gms.ads.AdActivity");
    try
    {
      if (i == paramContext.getPackageManager().getActivityInfo(localComponentName, 0).theme) {
        return true;
      }
      gs.U("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      gs.W("Fail to fetch AdActivity theme");
      gs.U("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
    }
    return false;
  }
  
  public Bundle b(Context paramContext, String paramString)
  {
    synchronized (this.mw)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("session_id", this.vL);
      localBundle.putLong("basets", this.vZ);
      localBundle.putLong("currts", this.vY);
      localBundle.putString("seq_num", paramString);
      localBundle.putInt("preqs", this.wb);
      localBundle.putInt("pclick", this.vX);
      localBundle.putInt("pimp", this.wa);
      localBundle.putBoolean("support_transparent_background", m(paramContext));
      return localBundle;
    }
  }
  
  public void b(av paramav, long paramLong)
  {
    synchronized (this.mw)
    {
      if (this.vZ == -1L)
      {
        this.vZ = paramLong;
        this.vY = this.vZ;
        if ((paramav.extras == null) || (paramav.extras.getInt("gw", 2) != 1)) {}
      }
      else
      {
        this.vY = paramLong;
      }
    }
    this.wb += 1;
  }
  
  public void cO()
  {
    synchronized (this.mw)
    {
      this.wa += 1;
      return;
    }
  }
  
  public void cP()
  {
    synchronized (this.mw)
    {
      this.vX += 1;
      return;
    }
  }
  
  public long dh()
  {
    return this.vZ;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/gc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */