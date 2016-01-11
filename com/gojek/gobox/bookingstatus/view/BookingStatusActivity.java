package com.gojek.gobox.bookingstatus.view;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gojek.gobox.R.array;
import com.gojek.gobox.R.drawable;
import com.gojek.gobox.R.id;
import com.gojek.gobox.R.layout;
import com.gojek.gobox.R.menu;
import com.gojek.gobox.R.string;
import com.gojek.gobox.base.BaseActivity;
import com.gojek.gobox.base.PresenterFactory;
import com.gojek.gobox.bookingstatus.presenter.BookingStatusPresenter;
import com.gojek.gobox.driverstatus.view.DriverStatusActivity;
import com.gojek.gobox.escrowissue.EscrowIssueActivity;
import com.gojek.gobox.networking.ConnectionManager;
import com.gojek.gobox.orderForm.view.OrderFormActivity;
import com.gojek.gobox.timeoutOrder.view.TimeoutOrderActivity;
import com.gojek.gobox.util.GoBoxPreferences;
import java.util.Timer;

public class BookingStatusActivity
  extends BaseActivity
  implements View.OnClickListener, BookingStatusView
{
  private boolean hasOpenNextScreen = false;
  private String mBookingId;
  private BookingStatusPresenter mBookingStatusPresenter;
  private LinearLayout mCallCenterButton;
  private View mCancelBookingButton;
  private TextView mDynamicText;
  private int mDynamicTextCounter = 0;
  private String[] mDynamicTextList;
  private Timer mDynamicTextTimer;
  private ImageView mImageGoboxStatus;
  private View mNewBookingButton;
  private BroadcastReceiver mReceiver;
  private Menu optionsMenu;
  
  private void animateStatusImage()
  {
    this.mImageGoboxStatus = ((ImageView)findViewById(R.id.image_gobox_status));
    this.mImageGoboxStatus.setImageResource(R.drawable.frame_animation_gobox_status);
    ((AnimationDrawable)this.mImageGoboxStatus.getDrawable()).start();
  }
  
  public void bindReceiver()
  {
    this.mReceiver = new BookingStatusActivity.1(this);
    registerReceiver(this.mReceiver, new IntentFilter("com.gojek.app.GOBOX_PUSH_RECEIVER"));
  }
  
  public void hideToolBarProgress()
  {
    if (this.optionsMenu != null)
    {
      MenuItem localMenuItem = this.optionsMenu.findItem(R.id.refresh);
      if (localMenuItem != null) {
        localMenuItem.setActionView(null);
      }
    }
  }
  
  public void initView()
  {
    this.mCancelBookingButton = findViewById(R.id.cancel_booking_button);
    this.mNewBookingButton = findViewById(R.id.new_booking_button);
    this.mDynamicText = ((TextView)findViewById(R.id.booking_status_dynamic_text));
    this.mCallCenterButton = ((LinearLayout)findViewById(R.id.call_center_button));
    this.mCallCenterButton.setOnClickListener(this);
    this.mCancelBookingButton.setOnClickListener(this);
    this.mNewBookingButton.setOnClickListener(this);
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
    if (paramView.getId() == R.id.cancel_booking_button) {
      if (ConnectionManager.isConnected(this)) {
        this.mBookingStatusPresenter.onCancelButtonClicked(this.mBookingId + "");
      }
    }
    do
    {
      return;
      noInternetConnectionHandler();
      return;
      if (paramView.getId() == R.id.new_booking_button)
      {
        paramView = new Intent(this, OrderFormActivity.class);
        paramView.addFlags(131072);
        startActivity(paramView);
        finish();
        return;
      }
    } while (paramView.getId() != R.id.call_center_button);
    paramView = new Intent("android.intent.action.DIAL");
    paramView.setData(Uri.parse("tel:" + new GoBoxPreferences(this).getCallCenterNumber()));
    startActivity(paramView);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_booking_status);
    initToolbarView();
    setTitle(getString(R.string.gobox_title));
    initView();
    this.mBookingId = getIntent().getStringExtra("order id");
    this.mDynamicTextList = getResources().getStringArray(R.array.randomised_text_list);
    this.mBookingStatusPresenter = PresenterFactory.createBookingStatusPresenter(this, getNetworkManager());
    this.mBookingStatusPresenter.onBookingStatusViewCreated();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    this.optionsMenu = paramMenu;
    getMenuInflater().inflate(R.menu.menu_driver_status, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == R.id.refresh)
    {
      this.mBookingStatusPresenter.onRefreshButtonClicked(this.mBookingId);
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onResume()
  {
    bindReceiver();
    startRunningText();
    super.onResume();
  }
  
  protected void onStop()
  {
    unregisterReceiver(this.mReceiver);
    super.onStop();
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    animateStatusImage();
    super.onWindowFocusChanged(paramBoolean);
  }
  
  public void showCancelConfirmationDialog()
  {
    new AlertDialog.Builder(this).setTitle(getString(R.string.confirmation_title)).setMessage(getString(R.string.cancel_booking_confirmation_message)).setPositiveButton("Yes", new BookingStatusActivity.3(this)).setNegativeButton("No", null).show();
  }
  
  public void showCancelFailedMessage()
  {
    dismissSimpleProgressDialog();
    showSimpleDialog(null, getString(R.string.failed_canceling_booking_message), null, true, null);
  }
  
  public void showCancelSuccessMessage()
  {
    dismissSimpleProgressDialog();
    showSimpleDialog(null, getString(R.string.cancel_booking_success_message), null, false, BookingStatusActivity..Lambda.1.lambdaFactory$(this));
  }
  
  public void showCancelingProgress()
  {
    showSimpleProgressDialog(getString(R.string.canceling_progress_message));
  }
  
  public void showNextBookingStatusIfNeeded(int paramInt)
  {
    for (;;)
    {
      try
      {
        if ((!this.hasOpenNextScreen) && (paramInt != -1))
        {
          this.hasOpenNextScreen = true;
          localIntent1 = new Intent();
        }
        switch (paramInt)
        {
        case 5: 
          localIntent1.putExtra("order id", this.mBookingId);
          startActivity(localIntent1);
          finish();
          return;
        }
      }
      finally {}
      Intent localIntent1 = new Intent(this, EscrowIssueActivity.class);
      continue;
      ((Intent)localObject).putExtra("is complete", false);
      Intent localIntent2 = new Intent(this, DriverStatusActivity.class);
      continue;
      localIntent2.putExtra("is complete", false);
      localIntent2 = new Intent(this, DriverStatusActivity.class);
      continue;
      localIntent2 = new Intent(this, TimeoutOrderActivity.class);
    }
  }
  
  public void showToolBarProgress()
  {
    if (this.optionsMenu != null)
    {
      MenuItem localMenuItem = this.optionsMenu.findItem(R.id.refresh);
      if (localMenuItem != null) {
        localMenuItem.setActionView(R.layout.refresh_progress);
      }
    }
  }
  
  public void startRunningText()
  {
    this.mDynamicTextTimer = new Timer();
    this.mDynamicTextTimer.schedule(new BookingStatusActivity.2(this), 0L, 4000L);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/bookingstatus/view/BookingStatusActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */