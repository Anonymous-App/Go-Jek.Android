package com.gojek.gotix.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gojek.gotix.R.color;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.R.string;
import com.gojek.gotix.presenter.GotixPaymentPresenter;
import com.gojek.gotix.tools.AndroidBus;
import com.gojek.gotix.tools.BusProvider;
import com.gojek.gotix.tools.ObservableIntervalHelper;
import com.jakewharton.rxbinding.view.RxView;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.norbsoft.typefacehelper.TypefaceHelper;
import lib.gojek.base.helper.FontFaceHelper;
import lib.gojek.base.util.BaseDialogFragment.AlertDialogListener;
import nucleus.factory.RequiresPresenter;
import rx.Observable;

@RequiresPresenter(GotixPaymentPresenter.class)
public class GotixWaitingPaymentActivity
  extends GotixBaseActivity<GotixPaymentPresenter>
  implements BaseDialogFragment.AlertDialogListener
{
  public static final String EVENT_ID_KEY = "eventIdKey";
  public static final String ORDER_ID_KEY = "orderIdKey";
  public static final String PAYMENT_FAILED_KEY = "paymentFailedKey";
  private static final String PHONE_PREFIX = "tel:";
  private static final int TIME_DELAY_REFRESH = 10;
  private TextView callCenterPhoneNumber;
  private TextView callCenterTitle;
  private ImageView callNowBtn;
  private CircleProgressBar circleProgressBar;
  private Context context;
  private int eventId;
  private LinearLayout linearLayout;
  private ObservableIntervalHelper observableIntervalHelper;
  private int orderId;
  private boolean paymentFailed;
  private String phoneNumber;
  private TextView pleaseWaitTitle;
  
  private void addViewListeners()
  {
    if (this.callNowBtn != null) {
      RxView.clicks(this.callNowBtn).subscribe(GotixWaitingPaymentActivity..Lambda.1.lambdaFactory$(this));
    }
  }
  
  private void bindViewById()
  {
    this.linearLayout = ((LinearLayout)findViewById(R.id.waiting_payment_container_layout));
    this.pleaseWaitTitle = ((TextView)findViewById(R.id.waiting_payment_please_wait_title));
    this.callCenterTitle = ((TextView)findViewById(R.id.waiting_payment_call_center_title));
    this.callCenterPhoneNumber = ((TextView)findViewById(R.id.waiting_payment_phone_number));
    this.callNowBtn = ((ImageView)findViewById(R.id.waiting_payment_call_button));
    this.circleProgressBar = ((CircleProgressBar)findViewById(R.id.waiting_payment_progresbar));
    configCircleProgresbar();
  }
  
  private void configCircleProgresbar()
  {
    this.circleProgressBar.setColorSchemeResources(new int[] { R.color.bg_base_green });
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
    Intent localIntent = getIntent();
    this.eventId = localIntent.getIntExtra("eventIdKey", 0);
    this.orderId = localIntent.getIntExtra("orderIdKey", 0);
    this.paymentFailed = localIntent.getBooleanExtra("paymentFailedKey", false);
  }
  
  private void navigateToMyTicketPage()
  {
    Intent localIntent = getIntent();
    localIntent.putExtra("lastActiveTab", 2);
    setResult(-1, localIntent);
    finish();
  }
  
  private void setFontFace()
  {
    TypefaceHelper.typeface(this.linearLayout, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.pleaseWaitTitle, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.callCenterTitle, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.callCenterPhoneNumber, FontFaceHelper.getBebasNeue());
  }
  
  private void showProgressBar()
  {
    runOnUiThread(GotixWaitingPaymentActivity..Lambda.2.lambdaFactory$(this));
  }
  
  private void stopIntervalRefresh()
  {
    if (this.observableIntervalHelper != null) {
      this.observableIntervalHelper.unsubscribe();
    }
  }
  
  public void doRefresh()
  {
    showProgressBar();
    ((GotixPaymentPresenter)getPresenter()).refreshPurchaseCall(this.orderId);
  }
  
  protected int getLayout()
  {
    return R.layout.activity_gotix_waiting_payment;
  }
  
  public void hideProgressBar()
  {
    runOnUiThread(GotixWaitingPaymentActivity..Lambda.3.lambdaFactory$(this));
  }
  
  public void navigateToPaymentFailedPage()
  {
    Intent localIntent = new Intent(this.context, GotixPaymentFailedActivity.class);
    localIntent.putExtra("orderIdKey", this.orderId);
    startActivity(localIntent);
    finish();
  }
  
  public void navigateToReceiptPage(int paramInt)
  {
    navigateToReceiptPage(paramInt, 1);
  }
  
  public void navigateToReceiptPage(int paramInt1, int paramInt2)
  {
    openReceiptPage(paramInt1, paramInt2);
  }
  
  public void negativeButtonClicked() {}
  
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
    navigateToMyTicketPage();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = this;
    getIntentDatas();
    bindViewById();
    addViewListeners();
    setFontFace();
    if (this.paymentFailed) {
      navigateToPaymentFailedPage();
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    setRefreshVisible(true);
    return true;
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    stopIntervalRefresh();
  }
  
  public void onNetworkProblem()
  {
    super.onNetworkProblem();
  }
  
  public void onNoInternetConnection()
  {
    super.onNoInternetConnection();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == R.id.action_refresh)
    {
      doRefresh();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public void onPause()
  {
    super.onPause();
    stopIntervalRefresh();
    BusProvider.getInstance().unregister(this);
  }
  
  public void onResume()
  {
    super.onResume();
    startIntervalRefresh();
    BusProvider.getInstance().register(this);
  }
  
  public void openEventDetail()
  {
    Intent localIntent = new Intent(this, GotixEventDetailActivity.class);
    localIntent.putExtra("type_id", this.eventId);
    startActivity(localIntent);
    finish();
  }
  
  public void openReceiptPage(int paramInt1, int paramInt2)
  {
    Intent localIntent = new Intent(this.context, GotixReceiptTicketActivity.class);
    localIntent.putExtra("done_flag", paramInt2);
    localIntent.putExtra("orderIdKey", paramInt1);
    localIntent.putExtra("event_id", this.eventId);
    startActivityForResult(localIntent, 101);
    finish();
  }
  
  public void positiveButtonClicked()
  {
    showRefreshButton(false);
    openEventDetail();
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
  
  public void showRefreshButton(boolean paramBoolean)
  {
    setRefreshVisible(paramBoolean);
  }
  
  public void startIntervalRefresh()
  {
    this.observableIntervalHelper = new ObservableIntervalHelper(10)
    {
      public void doOnTimerStart()
      {
        GotixWaitingPaymentActivity.this.doRefresh();
      }
    };
    this.observableIntervalHelper.startTimer();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/activities/GotixWaitingPaymentActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */