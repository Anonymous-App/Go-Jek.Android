package com.gojek.gotix.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gojek.gotix.R.array;
import com.gojek.gotix.R.color;
import com.gojek.gotix.R.drawable;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.R.string;
import com.gojek.gotix.presenter.GotixWaitingDriverPresenter;
import com.gojek.gotix.tools.AndroidBus;
import com.gojek.gotix.tools.BusProvider;
import com.gojek.gotix.tools.GotixUtils;
import com.gojek.gotix.tools.ObservableIntervalHelper;
import com.jakewharton.rxbinding.view.RxView;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.norbsoft.typefacehelper.TypefaceHelper;
import lib.gojek.base.helper.FontFaceHelper;
import lib.gojek.base.util.BaseDialogFragment.AlertDialogListener;
import nucleus.factory.RequiresPresenter;
import rx.Observable;

@RequiresPresenter(GotixWaitingDriverPresenter.class)
public class GotixWaitingDriverActivity
  extends GotixBaseActivity<GotixWaitingDriverPresenter>
{
  private static final String DATE_FORMAT = "dd MMM yyyy - hh:mma";
  private static final String REQUEST_CODE = "requestCode";
  private static final int RUNNING_TEXT_TIMES = 4;
  private static final String TAG = GotixWaitingDriverActivity.class.getSimpleName();
  public static boolean isRunning;
  private TextView bookingNotifyText;
  private TextView bookingSubmitText;
  private TextView bookingTimeDetail;
  private TextView bookingTimeText;
  private LinearLayout cancelBookingButton;
  private CircleProgressBar circleProgressBar;
  private TextView didYouKnowDesc;
  private TextView didYouKnowText;
  private ImageView driverBike;
  private int indexInfo;
  private ObservableIntervalHelper observableIntervalHelperText;
  private int orderId;
  private LinearLayout tryAgainButton;
  
  private void bindViewById()
  {
    this.bookingSubmitText = ((TextView)findViewById(R.id.booking_submitted_text));
    this.bookingNotifyText = ((TextView)findViewById(R.id.booking_notify_text));
    this.bookingTimeText = ((TextView)findViewById(R.id.booking_time_text));
    this.bookingTimeDetail = ((TextView)findViewById(R.id.booking_time_detail));
    this.didYouKnowText = ((TextView)findViewById(R.id.did_you_know_text));
    this.didYouKnowDesc = ((TextView)findViewById(R.id.did_you_know_desc));
    this.driverBike = ((ImageView)findViewById(R.id.driver_bike));
    this.cancelBookingButton = ((LinearLayout)findViewById(R.id.cancel_booking_button));
    this.tryAgainButton = ((LinearLayout)findViewById(R.id.try_again_button));
    this.circleProgressBar = ((CircleProgressBar)findViewById(R.id.waiting_driver_bar));
  }
  
  private void cancelBookingConfirmation(String paramString)
  {
    showDialog(getString(R.string.confirmation), paramString, new BaseDialogFragment.AlertDialogListener()
    {
      public void negativeButtonClicked() {}
      
      public void positiveButtonClicked()
      {
        ((GotixWaitingDriverPresenter)GotixWaitingDriverActivity.this.getPresenter()).doCancelbooking(GotixWaitingDriverActivity.this.orderId);
      }
    }, 2);
  }
  
  private void configCircleProgresbar()
  {
    this.circleProgressBar.setColorSchemeResources(new int[] { R.color.bg_base_green });
  }
  
  private void onRefreshFindingDriver()
  {
    showProgressBar();
    ((GotixWaitingDriverPresenter)getPresenter()).checkStatusBooking(this.orderId);
  }
  
  private void openMainActivity()
  {
    Intent localIntent = getIntent();
    localIntent.putExtra("lastActiveTab", 2);
    setResult(-1, localIntent);
    finish();
  }
  
  private void prepareListener()
  {
    RxView.clicks(this.tryAgainButton).subscribe(GotixWaitingDriverActivity..Lambda.1.lambdaFactory$(this));
    RxView.clicks(this.cancelBookingButton).subscribe(GotixWaitingDriverActivity..Lambda.2.lambdaFactory$(this));
  }
  
  private void setFontTextView()
  {
    TypefaceHelper.typeface(this.bookingSubmitText, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.didYouKnowText, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.bookingNotifyText, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.bookingTimeText, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.bookingTimeDetail, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.didYouKnowDesc, FontFaceHelper.getLatoFont());
  }
  
  public void animateInfoWaitingDriver(final String[] paramArrayOfString)
  {
    this.indexInfo = 0;
    this.observableIntervalHelperText = new ObservableIntervalHelper(4)
    {
      public void doOnTimerStart()
      {
        if (GotixWaitingDriverActivity.this.indexInfo == paramArrayOfString.length) {
          GotixWaitingDriverActivity.access$002(GotixWaitingDriverActivity.this, 0);
        }
        GotixWaitingDriverActivity.this.setRunningText(paramArrayOfString[GotixWaitingDriverActivity.this.indexInfo]);
        GotixWaitingDriverActivity.access$008(GotixWaitingDriverActivity.this);
      }
    };
    this.observableIntervalHelperText.startTimerWithoutDelay();
  }
  
  public void callDriverFoundActivity()
  {
    this.observableIntervalHelperText.unsubscribe();
    Intent localIntent = new Intent(this, GotixAcceptedByDriverActivity.class);
    localIntent.putExtra("orderIdKey", this.orderId);
    startActivityForResult(localIntent, 24);
  }
  
  public void callReviewActivity()
  {
    Intent localIntent = getIntent();
    localIntent.putExtra("lastActiveTab", 2);
    setResult(-1, localIntent);
    this.observableIntervalHelperText.unsubscribe();
    localIntent = new Intent(this, GotixReviewActivity.class);
    localIntent.putExtra("orderIdKey", this.orderId);
    startActivityForResult(localIntent, 25);
    finish();
  }
  
  public void doWhenTryAgainSuccess()
  {
    hideProgressBar();
    this.driverBike.setImageResource(R.drawable.gotix_icon_bike_green);
    this.bookingTimeText.setVisibility(0);
    this.bookingTimeDetail.setVisibility(0);
    this.tryAgainButton.setVisibility(8);
    this.bookingSubmitText.setText(getResources().getString(R.string.waiting_driver_booking_submitted));
    this.bookingNotifyText.setText(getResources().getString(R.string.waiting_driver_notify_text));
  }
  
  public void failedCancelBookingInformation()
  {
    runOnUiThread(GotixWaitingDriverActivity..Lambda.3.lambdaFactory$(this));
  }
  
  protected int getLayout()
  {
    return R.layout.activity_gotix_waiting_driver;
  }
  
  public void hideProgressBar()
  {
    runOnUiThread(GotixWaitingDriverActivity..Lambda.5.lambdaFactory$(this));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 24)
    {
      setResult(-1);
      finish();
      return;
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    this.observableIntervalHelperText.unsubscribe();
    if (this.bookingTimeText.getVisibility() == 0)
    {
      setResult(-1);
      finish();
      return;
    }
    openMainActivity();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.orderId = getIntent().getIntExtra("orderIdKey", 0);
    bindViewById();
    prepareListener();
    configCircleProgresbar();
    setFontTextView();
    hideBackNavigation();
    animateInfoWaitingDriver(getResources().getStringArray(R.array.info_text));
    ((GotixWaitingDriverPresenter)getPresenter()).findingDriver(this.orderId);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    setRefreshVisible(true);
    return true;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
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
      onRefreshFindingDriver();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause()
  {
    super.onPause();
    this.observableIntervalHelperText.unsubscribe();
    BusProvider.getInstance().unregister(this);
    isRunning = false;
  }
  
  protected void onResume()
  {
    super.onResume();
    BusProvider.getInstance().register(this);
    isRunning = true;
  }
  
  public void setBookingTime(int paramInt)
  {
    this.bookingTimeDetail.setText(GotixUtils.getDateFromTimestamp(paramInt, "dd MMM yyyy - hh:mma"));
  }
  
  public void setRunningText(String paramString)
  {
    this.didYouKnowDesc.setText(paramString);
  }
  
  public void showProgressBar()
  {
    runOnUiThread(GotixWaitingDriverActivity..Lambda.4.lambdaFactory$(this));
  }
  
  public void successCancelBooking()
  {
    setResult(0);
    finish();
  }
  
  public void viewForNotFoundDriver()
  {
    this.driverBike.setImageResource(R.drawable.gotix_icon_bike_grey);
    this.bookingTimeText.setVisibility(8);
    this.bookingTimeDetail.setVisibility(8);
    this.tryAgainButton.setVisibility(0);
    this.bookingSubmitText.setText(getResources().getString(R.string.sorry));
    this.bookingNotifyText.setText(getResources().getString(R.string.waiting_driver_not_found_text));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/activities/GotixWaitingDriverActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */