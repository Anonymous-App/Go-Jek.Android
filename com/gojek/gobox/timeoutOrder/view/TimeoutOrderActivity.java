package com.gojek.gobox.timeoutOrder.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.gojek.gobox.R.id;
import com.gojek.gobox.R.layout;
import com.gojek.gobox.R.string;
import com.gojek.gobox.base.BaseActivity;
import com.gojek.gobox.base.PresenterFactory;
import com.gojek.gobox.bookingstatus.view.BookingStatusActivity;
import com.gojek.gobox.networking.ConnectionManager;
import com.gojek.gobox.timeoutOrder.presenter.TimeoutPresenter;
import com.gojek.gobox.util.GoBoxPreferences;

public class TimeoutOrderActivity
  extends BaseActivity
  implements View.OnClickListener, TimeoutOrderView
{
  private ImageView mButtonTryAgain;
  private LinearLayout mCallCenterButton;
  private String mOrderId;
  private ProgressBar mProgressBarTryAgain;
  private TimeoutPresenter mTimeoutPresenter;
  
  private String getOrderIdFromExtras()
  {
    return getIntent().getStringExtra("order id");
  }
  
  public void initView()
  {
    this.mProgressBarTryAgain = ((ProgressBar)findViewById(R.id.progress_bar_tryagain));
    this.mButtonTryAgain = ((ImageView)findViewById(R.id.button_tryagain));
    this.mCallCenterButton = ((LinearLayout)findViewById(R.id.call_center_button));
    this.mCallCenterButton.setOnClickListener(this);
    this.mButtonTryAgain.setOnClickListener(this);
  }
  
  public void onBackPressed()
  {
    Intent localIntent = new Intent("com.gojek.app.HOME");
    localIntent.putExtra("FEED_BACK", 2);
    localIntent.addFlags(131072);
    startActivity(localIntent);
    finish();
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() == R.id.button_tryagain)
    {
      this.mProgressBarTryAgain.setVisibility(0);
      this.mButtonTryAgain.setVisibility(8);
      if (ConnectionManager.isConnected(this)) {
        this.mTimeoutPresenter.onReorderAction(String.valueOf(this.mOrderId));
      }
    }
    while (paramView.getId() != R.id.call_center_button)
    {
      return;
      noInternetConnectionHandler();
      return;
    }
    paramView = new Intent("android.intent.action.DIAL");
    paramView.setData(Uri.parse("tel:" + new GoBoxPreferences(this).getCallCenterNumber()));
    startActivity(paramView);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_timeout_order);
    initToolbarView();
    setTitle(getString(R.string.booking_title));
    this.mOrderId = getOrderIdFromExtras();
    this.mTimeoutPresenter = PresenterFactory.createTimeoutPresenterFactory(this, getNetworkManager());
    this.mTimeoutPresenter.onInitView();
  }
  
  public void openBookingStatusActivity(Boolean paramBoolean)
  {
    if (paramBoolean.booleanValue())
    {
      this.mProgressBarTryAgain.setVisibility(8);
      this.mButtonTryAgain.setVisibility(0);
      paramBoolean = new Intent(this, BookingStatusActivity.class);
      paramBoolean.putExtra("order id", this.mOrderId);
      startActivity(paramBoolean);
      finish();
    }
  }
  
  public void showErrorConfirmOrder(Throwable paramThrowable)
  {
    errorConnectionHandler(paramThrowable);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/timeoutOrder/view/TimeoutOrderActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */