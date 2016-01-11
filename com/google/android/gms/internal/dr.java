package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.g;
import com.google.android.gms.dynamic.g.a;

@ez
public final class dr
  extends g<dt>
{
  private static final dr sh = new dr();
  
  private dr()
  {
    super("com.google.android.gms.ads.AdOverlayCreatorImpl");
  }
  
  public static ds b(Activity paramActivity)
  {
    try
    {
      if (c(paramActivity))
      {
        gs.S("Using AdOverlay from the client jar.");
        return new dk(paramActivity);
      }
      paramActivity = sh.d(paramActivity);
      return paramActivity;
    }
    catch (a paramActivity)
    {
      gs.W(paramActivity.getMessage());
    }
    return null;
  }
  
  private static boolean c(Activity paramActivity)
    throws dr.a
  {
    paramActivity = paramActivity.getIntent();
    if (!paramActivity.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
      throw new a("Ad overlay requires the useClientJar flag in intent extras.");
    }
    return paramActivity.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
  }
  
  private ds d(Activity paramActivity)
  {
    try
    {
      d locald = e.k(paramActivity);
      paramActivity = ds.a.p(((dt)L(paramActivity)).a(locald));
      return paramActivity;
    }
    catch (RemoteException paramActivity)
    {
      gs.d("Could not create remote AdOverlay.", paramActivity);
      return null;
    }
    catch (g.a paramActivity)
    {
      gs.d("Could not create remote AdOverlay.", paramActivity);
    }
    return null;
  }
  
  protected dt o(IBinder paramIBinder)
  {
    return dt.a.q(paramIBinder);
  }
  
  private static final class a
    extends Exception
  {
    public a(String paramString)
    {
      super();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/dr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */