package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@ez
public class gy
  extends gw
{
  public gy(gv paramgv, boolean paramBoolean)
  {
    super(paramgv, paramBoolean);
  }
  
  protected WebResourceResponse d(Context paramContext, String paramString1, String paramString2)
    throws IOException
  {
    paramString2 = (HttpURLConnection)HttpInstrumentation.openConnection(new URL(paramString2).openConnection());
    try
    {
      gj.a(paramContext, paramString1, true, paramString2, true);
      paramString2.addRequestProperty("Cache-Control", "max-stale=3600");
      paramString2.connect();
      paramContext = new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(gj.a(new InputStreamReader(paramString2.getInputStream())).getBytes("UTF-8")));
      return paramContext;
    }
    finally
    {
      paramString2.disconnect();
    }
  }
  
  public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString)
  {
    try
    {
      if (!"mraid.js".equalsIgnoreCase(new File(paramString).getName())) {
        return super.shouldInterceptRequest(paramWebView, paramString);
      }
      if (!(paramWebView instanceof gv))
      {
        gs.W("Tried to intercept request from a WebView that wasn't an AdWebView.");
        return super.shouldInterceptRequest(paramWebView, paramString);
      }
      Object localObject = (gv)paramWebView;
      ((gv)localObject).du().bX();
      if (((gv)localObject).Y().og)
      {
        gs.V("shouldInterceptRequest(https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_interstitial.js)");
        return d(((gv)localObject).getContext(), this.md.dx().wD, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_interstitial.js");
      }
      if (((gv)localObject).dy())
      {
        gs.V("shouldInterceptRequest(https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_expanded_banner.js)");
        return d(((gv)localObject).getContext(), this.md.dx().wD, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_expanded_banner.js");
      }
      gs.V("shouldInterceptRequest(https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_banner.js)");
      localObject = d(((gv)localObject).getContext(), this.md.dx().wD, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_banner.js");
      return (WebResourceResponse)localObject;
    }
    catch (IOException localIOException)
    {
      gs.W("Could not fetch MRAID JS. " + localIOException.getMessage());
    }
    return super.shouldInterceptRequest(paramWebView, paramString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/gy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */