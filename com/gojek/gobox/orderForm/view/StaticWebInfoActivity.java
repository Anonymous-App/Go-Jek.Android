package com.gojek.gobox.orderForm.view;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.gojek.gobox.R.id;
import com.gojek.gobox.R.layout;
import com.gojek.gobox.base.BaseActivity;

public class StaticWebInfoActivity
  extends BaseActivity
{
  private ProgressBar mProgressWebView;
  private WebView mWebTermsAndConditins;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_terms_and_conditions);
    initToolbarView();
    paramBundle = getIntent().getStringExtra("info url");
    setTitle(getIntent().getStringExtra("title url"));
    this.mWebTermsAndConditins = ((WebView)findViewById(R.id.webview_terms_and_conditions));
    this.mProgressWebView = ((ProgressBar)findViewById(R.id.progress_bar_loading_webview));
    this.mWebTermsAndConditins.setWebViewClient(new WebViewclientGojek(null));
    this.mWebTermsAndConditins.loadUrl("https://gobox-api.gojek.co.id/" + paramBundle);
  }
  
  private class WebViewclientGojek
    extends WebViewClient
  {
    private WebViewclientGojek() {}
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      StaticWebInfoActivity.this.mProgressWebView.setVisibility(8);
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      StaticWebInfoActivity.this.mProgressWebView.setVisibility(0);
      paramWebView.loadUrl(paramString);
      return true;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/orderForm/view/StaticWebInfoActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */