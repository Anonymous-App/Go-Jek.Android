package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesUtil;

@ez
public final class ff
{
  public static gg a(Context paramContext, fi paramfi, a parama)
  {
    if (paramfi.lD.wG) {
      return b(paramContext, paramfi, parama);
    }
    return c(paramContext, paramfi, parama);
  }
  
  private static gg b(Context paramContext, fi paramfi, a parama)
  {
    gs.S("Fetching ad response from local ad request service.");
    paramContext = new fg.a(paramContext, paramfi, parama);
    paramContext.start();
    return paramContext;
  }
  
  private static gg c(Context paramContext, fi paramfi, a parama)
  {
    gs.S("Fetching ad response from remote ad request service.");
    if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext) != 0)
    {
      gs.W("Failed to connect to remote ad request service.");
      return null;
    }
    return new fg.b(paramContext, paramfi, parama);
  }
  
  public static abstract interface a
  {
    public abstract void a(fk paramfk);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ff.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */