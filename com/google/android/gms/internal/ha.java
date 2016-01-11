package com.google.android.gms.internal;

import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.common.internal.n;
import java.net.URI;
import java.net.URISyntaxException;

@ez
public class ha
  extends WebViewClient
{
  private final gv md;
  private final String xc = Z(paramString);
  private boolean xd = false;
  private final fc xe;
  
  public ha(fc paramfc, gv paramgv, String paramString)
  {
    this.md = paramgv;
    this.xe = paramfc;
  }
  
  private String Z(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return paramString;
      try
      {
        if (paramString.endsWith("/"))
        {
          String str = paramString.substring(0, paramString.length() - 1);
          return str;
        }
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
      {
        gs.T(localIndexOutOfBoundsException.getMessage());
      }
    }
    return paramString;
  }
  
  protected boolean Y(String paramString)
  {
    paramString = Z(paramString);
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      try
      {
        Object localObject1 = new URI(paramString);
        if ("passback".equals(((URI)localObject1).getScheme()))
        {
          gs.S("Passback received");
          this.xe.cz();
          return true;
        }
        if (!TextUtils.isEmpty(this.xc))
        {
          Object localObject2 = new URI(this.xc);
          paramString = ((URI)localObject2).getHost();
          String str = ((URI)localObject1).getHost();
          localObject2 = ((URI)localObject2).getPath();
          localObject1 = ((URI)localObject1).getPath();
          if ((n.equal(paramString, str)) && (n.equal(localObject2, localObject1)))
          {
            gs.S("Passback received");
            this.xe.cz();
            return true;
          }
        }
      }
      catch (URISyntaxException paramString)
      {
        gs.T(paramString.getMessage());
      }
    }
    return false;
  }
  
  public void onLoadResource(WebView paramWebView, String paramString)
  {
    gs.S("JavascriptAdWebViewClient::onLoadResource: " + paramString);
    if (!Y(paramString)) {
      this.md.du().onLoadResource(this.md, paramString);
    }
  }
  
  public void onPageFinished(WebView paramWebView, String paramString)
  {
    gs.S("JavascriptAdWebViewClient::onPageFinished: " + paramString);
    if (!this.xd)
    {
      this.xe.cy();
      this.xd = true;
    }
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    gs.S("JavascriptAdWebViewClient::shouldOverrideUrlLoading: " + paramString);
    if (Y(paramString))
    {
      gs.S("shouldOverrideUrlLoading: received passback url");
      return true;
    }
    return this.md.du().shouldOverrideUrlLoading(this.md, paramString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ha.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */