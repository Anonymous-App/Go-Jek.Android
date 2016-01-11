package com.gojek.gotix.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.R.string;
import com.gojek.gotix.presenter.GotixCallCenterPresenter;
import com.norbsoft.typefacehelper.TypefaceHelper;
import lib.gojek.base.helper.FontFaceHelper;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(GotixCallCenterPresenter.class)
public class GotixCallCenterActivity
  extends GotixBaseActivity<GotixCallCenterPresenter>
{
  private static final String EMPTY_STRING = "";
  public static final String PHONE_PREFIX = "tel:";
  private static final String REQUEST_CODE = "requestCode";
  public static boolean isRunning;
  private ImageButton callNow;
  private TextView callText;
  private TextView cancelDesc;
  private TextView cancelTitle;
  private TextView desc;
  private TextView gojekHqAddr;
  private TextView gojekHqTitle;
  private TextView titleCallCenter;
  
  private void bindViewById()
  {
    this.titleCallCenter = ((TextView)findViewById(R.id.title_call_center));
    this.desc = ((TextView)findViewById(R.id.desc));
    this.callText = ((TextView)findViewById(R.id.text_call));
    this.gojekHqTitle = ((TextView)findViewById(R.id.gojek_hq_title));
    this.gojekHqAddr = ((TextView)findViewById(R.id.gojek_hq_address));
    this.callNow = ((ImageButton)findViewById(R.id.call_now));
    this.cancelTitle = ((TextView)findViewById(R.id.cancel_title));
    this.cancelDesc = ((TextView)findViewById(R.id.desc_event_cancel));
  }
  
  private void doCallAction(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("tel:");
    String str;
    if (paramString != null)
    {
      str = paramString;
      if (!paramString.equals("")) {}
    }
    else
    {
      str = getString(R.string.call_center_number);
    }
    startActivity(new Intent("android.intent.action.CALL", Uri.parse(str)));
  }
  
  private void openMainActivity()
  {
    Intent localIntent = getIntent();
    localIntent.putExtra("requestCode", 0);
    localIntent.putExtra("lastActiveTab", 2);
    setResult(-1, localIntent);
    GotixMainActivity.isRunning = true;
    finish();
  }
  
  private void setFontScreen()
  {
    TypefaceHelper.typeface(this.titleCallCenter, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.desc, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.callText, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.gojekHqTitle, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.gojekHqAddr, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.cancelTitle, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.cancelDesc, FontFaceHelper.getLatoFont());
  }
  
  protected int getLayout()
  {
    return R.layout.activity_gotix_call_center;
  }
  
  public void onBackPressed()
  {
    openMainActivity();
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    bindViewById();
    setFontScreen();
  }
  
  public void onNetworkProblem()
  {
    super.onNetworkProblem();
  }
  
  public void onNoInternetConnection()
  {
    super.onNoInternetConnection();
  }
  
  protected void onPause()
  {
    super.onPause();
    isRunning = false;
  }
  
  protected void onResume()
  {
    super.onResume();
    isRunning = true;
  }
  
  public void setCallCenterText(String paramString1, String paramString2)
  {
    TextView localTextView = this.callText;
    String str;
    if (paramString1 != null)
    {
      str = paramString1;
      if (!paramString1.equals("")) {}
    }
    else
    {
      str = getString(R.string.call_center_text);
    }
    localTextView.setText(str);
    this.callNow.setOnClickListener(GotixCallCenterActivity..Lambda.1.lambdaFactory$(this, paramString2));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/activities/GotixCallCenterActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */