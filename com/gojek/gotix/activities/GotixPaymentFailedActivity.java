package com.gojek.gotix.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.R.string;
import com.gojek.gotix.presenter.GotixPaymentFailedPresenter;
import com.gojek.gotix.tools.AndroidBus;
import com.gojek.gotix.tools.BusProvider;
import com.jakewharton.rxbinding.view.RxView;
import com.norbsoft.typefacehelper.TypefaceHelper;
import lib.gojek.base.helper.FontFaceHelper;
import nucleus.factory.RequiresPresenter;
import rx.Observable;

@RequiresPresenter(GotixPaymentFailedPresenter.class)
public class GotixPaymentFailedActivity
  extends GotixBaseActivity<GotixPaymentFailedPresenter>
{
  public static final String ORDER_ID_KEY = "orderIdKey";
  private static final String PHONE_PREFIX = "tel:";
  public static boolean isRunning;
  private TextView callCenterPhoneNumber;
  private TextView callCenterTitle;
  private ImageView callNowBtn;
  private LinearLayout containerLayout;
  private TextView failedTitle;
  private ImageView okBtn;
  private int orderId;
  private String phoneNumber;
  
  private void ackPurchaseFailed()
  {
    ((GotixPaymentFailedPresenter)getPresenter()).ackPurchaseFailed(this.orderId);
  }
  
  private void addViewListeners()
  {
    RxView.clicks(this.callNowBtn).subscribe(GotixPaymentFailedActivity..Lambda.1.lambdaFactory$(this));
    RxView.clicks(this.okBtn).subscribe(GotixPaymentFailedActivity..Lambda.2.lambdaFactory$(this));
  }
  
  private void backToMainPage()
  {
    Intent localIntent = getIntent();
    localIntent.putExtra("lastActiveTab", 2);
    setResult(-1, localIntent);
    finish();
  }
  
  private void bindViewById()
  {
    this.containerLayout = ((LinearLayout)findViewById(R.id.payment_failed_container));
    this.failedTitle = ((TextView)findViewById(R.id.payment_failed_title));
    this.callCenterTitle = ((TextView)findViewById(R.id.payment_failed_call_center_title));
    this.callCenterPhoneNumber = ((TextView)findViewById(R.id.payment_failed_phone_number));
    this.callNowBtn = ((ImageView)findViewById(R.id.payment_failed_call_button));
    this.okBtn = ((ImageView)findViewById(R.id.payment_failed_ok_button));
  }
  
  private void doActionCall(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("tel:");
    String str = paramString;
    if (paramString == null) {
      str = getString(R.string.call_center_number);
    }
    startActivity(new Intent("android.intent.action.CALL", Uri.parse(str)));
  }
  
  private void getIntentDatas()
  {
    this.orderId = getIntent().getIntExtra("orderIdKey", 0);
  }
  
  private void setFontFace()
  {
    TypefaceHelper.typeface(this.containerLayout, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.failedTitle, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.callCenterTitle, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.callCenterPhoneNumber, FontFaceHelper.getBebasNeue());
  }
  
  protected int getLayout()
  {
    return R.layout.activity_gotix_payment_failed;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == -1) {
      finish();
    }
    while (paramInt2 != 0) {
      return;
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    backToMainPage();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    hideBackNavigation();
    getIntentDatas();
    bindViewById();
    addViewListeners();
    setFontFace();
  }
  
  public void onNetworkProblem()
  {
    super.onNetworkProblem();
  }
  
  public void onNoInternetConnection()
  {
    super.onNoInternetConnection();
  }
  
  public void onPause()
  {
    super.onPause();
    BusProvider.getInstance().unregister(this);
    isRunning = false;
  }
  
  protected void onResume()
  {
    super.onResume();
    BusProvider.getInstance().register(this);
    isRunning = true;
  }
  
  public void setCallCenterData(String paramString1, String paramString2)
  {
    this.phoneNumber = paramString2;
    this.callCenterPhoneNumber.setText(paramString1);
  }
  
  public void showDialogNetworkProblem()
  {
    onNetworkProblem();
  }
  
  public void showDialogWhenNoInternetConnection()
  {
    onNoInternetConnection();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/activities/GotixPaymentFailedActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */