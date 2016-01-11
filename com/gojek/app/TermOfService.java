package com.gojek.app;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class TermOfService
  extends GojekActivityBase
{
  private ProgressBar mProgress;
  private WebView mWebView;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle(getString(2131165826));
    setContentView(2130968837);
    this.mWebView = ((WebView)findViewById(2131625028));
    this.mProgress = ((ProgressBar)findViewById(2131624286));
    paramBundle = this.mWebView.getSettings();
    paramBundle.setJavaScriptEnabled(true);
    paramBundle.setDefaultTextEncodingName("utf-8");
    this.mWebView.setBackgroundColor(0);
    this.mWebView.setWebViewClient(new WebViewClient()
    {
      public void onLoadResource(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        super.onLoadResource(paramAnonymousWebView, paramAnonymousString);
      }
      
      public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        super.onPageFinished(paramAnonymousWebView, paramAnonymousString);
        TermOfService.this.mProgress.setVisibility(4);
      }
      
      public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
      {
        super.onPageStarted(paramAnonymousWebView, paramAnonymousString, paramAnonymousBitmap);
        TermOfService.this.mProgress.setProgress(5);
      }
      
      public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
      {
        paramAnonymousWebView.loadData("ERROR", "text/html", "UTF-8");
        TermOfService.this.mProgress.setVisibility(4);
      }
      
      public void onReceivedSslError(WebView paramAnonymousWebView, SslErrorHandler paramAnonymousSslErrorHandler, SslError paramAnonymousSslError)
      {
        paramAnonymousSslErrorHandler.proceed();
      }
    });
    this.mWebView.setWebChromeClient(new WebChromeClient()
    {
      public void onProgressChanged(WebView paramAnonymousWebView, int paramAnonymousInt)
      {
        super.onProgressChanged(paramAnonymousWebView, paramAnonymousInt);
        TermOfService.this.mProgress.setProgress(paramAnonymousInt);
      }
    });
    this.mWebView.loadUrl("http://go-jek.com/terms-of-service");
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/TermOfService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */