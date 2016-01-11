package com.gojek.gotix.activities;

import android.content.Context;
import android.os.Bundle;
import android.webkit.WebView;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.R.string;
import lib.gojek.base.util.BaseConnectionManager;

public class GotixTermAndConditionActivity
  extends GotixBaseActivity
{
  private Context context;
  private WebView webView;
  
  private void bindViewById()
  {
    this.webView = ((WebView)findViewById(R.id.term_and_condition));
  }
  
  private void bindWebView()
  {
    this.webView.loadUrl(getAgreementText());
  }
  
  private String getAgreementText()
  {
    if (BaseConnectionManager.isConnected(this.context)) {
      return getString(R.string.term_and_conditions_url);
    }
    return getString(R.string.term_and_conditions_local);
  }
  
  private void setTitleBarPage()
  {
    setTitleActionBar(getString(R.string.term_and_conditions_title));
  }
  
  protected int getLayout()
  {
    return R.layout.activity_gotix_term_and_condition;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = getApplicationContext();
    setTitleBarPage();
    bindViewById();
    bindWebView();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/activities/GotixTermAndConditionActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */