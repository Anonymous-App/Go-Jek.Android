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
public final class en
  extends g<ej>
{
  private static final en sK = new en();
  
  private en()
  {
    super("com.google.android.gms.ads.InAppPurchaseManagerCreatorImpl");
  }
  
  private static boolean c(Activity paramActivity)
    throws en.a
  {
    paramActivity = paramActivity.getIntent();
    if (!paramActivity.hasExtra("com.google.android.gms.ads.internal.purchase.useClientJar")) {
      throw new a("InAppPurchaseManager requires the useClientJar flag in intent extras.");
    }
    return paramActivity.getBooleanExtra("com.google.android.gms.ads.internal.purchase.useClientJar", false);
  }
  
  public static ei e(Activity paramActivity)
  {
    try
    {
      if (c(paramActivity))
      {
        gs.S("Using AdOverlay from the client jar.");
        return new dz(paramActivity);
      }
      paramActivity = sK.f(paramActivity);
      return paramActivity;
    }
    catch (a paramActivity)
    {
      gs.W(paramActivity.getMessage());
    }
    return null;
  }
  
  private ei f(Activity paramActivity)
  {
    try
    {
      d locald = e.k(paramActivity);
      paramActivity = ei.a.u(((ej)L(paramActivity)).b(locald));
      return paramActivity;
    }
    catch (RemoteException paramActivity)
    {
      gs.d("Could not create remote InAppPurchaseManager.", paramActivity);
      return null;
    }
    catch (g.a paramActivity)
    {
      gs.d("Could not create remote InAppPurchaseManager.", paramActivity);
    }
    return null;
  }
  
  protected ej y(IBinder paramIBinder)
  {
    return ej.a.v(paramIBinder);
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/en.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */