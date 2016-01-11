package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.g;
import com.google.android.gms.dynamic.g.a;

@ez
public final class au
  extends g<be>
{
  private static final au nS = new au();
  
  private au()
  {
    super("com.google.android.gms.ads.AdManagerCreatorImpl");
  }
  
  public static bd a(Context paramContext, ay paramay, String paramString, cs paramcs)
  {
    Object localObject;
    if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext) == 0)
    {
      bd localbd = nS.b(paramContext, paramay, paramString, paramcs);
      localObject = localbd;
      if (localbd != null) {}
    }
    else
    {
      gs.S("Using AdManager from the client jar.");
      localObject = new u(paramContext, paramay, paramString, paramcs, new gt(6171000, 6171000, true));
    }
    return (bd)localObject;
  }
  
  private bd b(Context paramContext, ay paramay, String paramString, cs paramcs)
  {
    try
    {
      d locald = e.k(paramContext);
      paramContext = bd.a.f(((be)L(paramContext)).a(locald, paramay, paramString, paramcs, 6171000));
      return paramContext;
    }
    catch (RemoteException paramContext)
    {
      gs.d("Could not create remote AdManager.", paramContext);
      return null;
    }
    catch (g.a paramContext)
    {
      gs.d("Could not create remote AdManager.", paramContext);
    }
    return null;
  }
  
  protected be c(IBinder paramIBinder)
  {
    return be.a.g(paramIBinder);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/au.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */