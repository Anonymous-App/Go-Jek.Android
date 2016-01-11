package com.gojek.app.massage;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.gojek.app.GojekActivityBase;

public class MassageActivity
  extends GojekActivityBase
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968623);
    setTitle(getString(2131165649));
    paramBundle = (WebView)findViewById(2131624202);
    paramBundle.getSettings().setJavaScriptEnabled(true);
    paramBundle.loadUrl("http://www.go-life.co.id/golifeFORM/go_massage.html");
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/massage/MassageActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */