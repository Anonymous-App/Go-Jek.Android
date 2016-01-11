package com.google.android.gms.internal;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.io.File;

@ez
public final class gn
{
  public static void a(Context paramContext, WebSettings paramWebSettings)
  {
    paramWebSettings.setAppCachePath(paramContext.getCacheDir().getAbsolutePath());
    paramWebSettings.setAppCacheMaxSize(0L);
    paramWebSettings.setAppCacheEnabled(true);
    paramWebSettings.setDatabasePath(paramContext.getDatabasePath("com.google.android.gms.ads.db").getAbsolutePath());
    paramWebSettings.setDatabaseEnabled(true);
    paramWebSettings.setDomStorageEnabled(true);
    paramWebSettings.setDisplayZoomControls(false);
    paramWebSettings.setBuiltInZoomControls(true);
    paramWebSettings.setSupportZoom(true);
  }
  
  public static void a(Window paramWindow)
  {
    paramWindow.setFlags(16777216, 16777216);
  }
  
  public static void a(WebView paramWebView)
  {
    paramWebView.onPause();
  }
  
  public static void b(WebView paramWebView)
  {
    paramWebView.onResume();
  }
  
  public static void i(View paramView)
  {
    paramView.setLayerType(1, null);
  }
  
  public static void j(View paramView)
  {
    paramView.setLayerType(0, null);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/gn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */