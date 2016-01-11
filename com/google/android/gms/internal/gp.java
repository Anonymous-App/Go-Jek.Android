package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebSettings;

@ez
public final class gp
{
  public static void a(Context paramContext, WebSettings paramWebSettings)
  {
    gn.a(paramContext, paramWebSettings);
    paramWebSettings.setMediaPlaybackRequiresUserGesture(false);
  }
  
  public static String getDefaultUserAgent(Context paramContext)
  {
    return WebSettings.getDefaultUserAgent(paramContext);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/gp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */