package com.gojek.app;

import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.flurry.android.FlurryAgent;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CancelReason;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.Push;
import com.gojek.app.model.PushModel;
import com.gojek.app.persistence.dao.BookingHistoryDao;
import com.gojek.app.persistence.dao.BookingRateDao;
import com.gojek.app.util.TrackingNotificationUtils;
import com.gojek.app.util.Util;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

public class WaitingDriver
  extends GojekActivityBase
{
  private static final String TAG = WaitingDriver.class.getSimpleName();
  private BookingHistoryDao bookingHistoryDao;
  private BookingRateDao bookingRateDao;
  private AppEventsLogger cancelFBLogger;
  private com.gojek.app.parcelable.BookingStatus mBookingData;
  private CustomerSaved mCustomerSaved;
  private ImageView mIV1;
  private int[] mImage = { 2130837883, 2130837884, 2130837885, 2130837886 };
  private int mIndexImage = 0;
  private int mIndexInfo = 0;
  private String[] mInfo = { "All transport booking comes with free hair cover and masker", "That GO-JEK can buy anything and we will pay for it first" };
  private boolean mIsPreBooking;
  private Push mPushData;
  private BroadcastReceiver mPushReceiver;
  private TextView mTV1;
  private TextView mTV2;
  private TextView mTVBookingTime;
  private TextView mTVInfo;
  private Timer mTimer;
  private Timer mTimerImage;
  private Menu optionsMenu;
  private VolleyClient volleyClient;
  
  private void bindReceiver()
  {
    this.mPushReceiver = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        WaitingDriver.access$602(WaitingDriver.this, (Push)paramAnonymousIntent.getParcelableExtra("PUSH_DATA"));
        WaitingDriver.this.mBookingData.id = WaitingDriver.this.mPushData.model.id;
        WaitingDriver.this.mBookingData.setOrderNo(WaitingDriver.this.mPushData.model.no);
        Log.i(WaitingDriver.TAG, "receive push message " + WaitingDriver.this.mPushData.toString());
        Log.i(WaitingDriver.TAG, "receive bookingData " + WaitingDriver.this.mBookingData.getId() + " order_no " + WaitingDriver.this.mBookingData.getOrderNo());
        if (WaitingDriver.this.mPushData != null)
        {
          if (WaitingDriver.this.mPushData.model.status == 5)
          {
            TrackingNotificationUtils.getInstance().trackTimeAndRate(WaitingDriver.this.mBookingData.getServiceType(), 4, WaitingDriver.this.mixPanel);
            paramAnonymousContext = new Intent(WaitingDriver.this, BookingTryAgain.class);
            paramAnonymousContext.putExtra("BOOKING_DATA", WaitingDriver.this.mBookingData);
            WaitingDriver.this.startActivity(paramAnonymousContext);
            WaitingDriver.this.finish();
          }
        }
        else {
          return;
        }
        TrackingNotificationUtils.getInstance().trackTimeAndRate(WaitingDriver.this.mBookingData.getServiceType(), 1, WaitingDriver.this.mixPanel);
        TrackingNotificationUtils.getInstance().setStartDate(new Date());
        paramAnonymousContext = new Intent(WaitingDriver.this, BookingStatus.class);
        paramAnonymousContext.putExtra("PUSH_DATA", WaitingDriver.this.mPushData);
        paramAnonymousContext.putExtra("BOOKING_DATA", WaitingDriver.this.mBookingData);
        WaitingDriver.this.startActivity(paramAnonymousContext);
        WaitingDriver.this.finish();
      }
    };
    registerReceiver(this.mPushReceiver, new IntentFilter("com.gojek.app.PUSH_RECEIVER"));
  }
  
  private void doCancel(CancelReason paramCancelReason)
  {
    showSimpleProgressDialog("Canceling...", new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        WaitingDriver.this.volleyClient.cancelAllRequest();
      }
    });
    FlurryAgent.logEvent("Cancel_Clicked");
    FlurryAgent.endTimedEvent("Order_Clicked");
    paramCancelReason = new JSONObject();
    try
    {
      paramCancelReason.put("orderNo", this.mBookingData.orderNo);
      paramCancelReason.put("bookingId", "");
      paramCancelReason.put("cancelReasonId", 4);
      paramCancelReason.put("cancelDescription", getString(2131165437));
      paramCancelReason.put("activitySource", 2);
      this.volleyClient.putAndGetJSON("https://api.gojek.co.id/gojek/v2/booking/cancelBooking", paramCancelReason, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.e(WaitingDriver.TAG, "error cancel booking " + paramAnonymousVolleyError);
          WaitingDriver.this.dismissSimpleProgressDialog();
          if (!(paramAnonymousVolleyError instanceof AuthFailureError))
          {
            paramAnonymousVolleyError = WaitingDriver.this.volleyClient.getErrorResponse(paramAnonymousVolleyError);
            paramAnonymousVolleyError = "Failed cancel your booking\n " + paramAnonymousVolleyError;
            WaitingDriver.this.showSimpleDialog(null, paramAnonymousVolleyError, null, true, null);
            return;
          }
          WaitingDriver.this.isUnauthorizedApiAccess();
        }
        
        public void onResponse(JSONObject paramAnonymousJSONObject)
        {
          WaitingDriver.this.dismissSimpleProgressDialog();
          String str2 = WaitingDriver.TAG;
          StringBuilder localStringBuilder = new StringBuilder().append("response cancel= ");
          if (!(paramAnonymousJSONObject instanceof JSONObject)) {}
          for (String str1 = paramAnonymousJSONObject.toString();; str1 = JSONObjectInstrumentation.toString((JSONObject)paramAnonymousJSONObject))
          {
            Log.e(str2, str1);
            if (paramAnonymousJSONObject != null)
            {
              WaitingDriver.this.bookingHistoryDao.updateBookingToCancel(WaitingDriver.this.mBookingData.getId());
              WaitingDriver.this.mixPanel.track("TR: Booking cancelled");
              WaitingDriver.this.showSimpleDialog(null, "Booking canceled", null, false, new WaitingDriver.6.1(this));
            }
            return;
          }
        }
      }, this.mCustomerSaved.getAccessToken());
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  private void doLoadBooking()
  {
    setRefreshActionButtonState(true);
    String str = String.format("https://api.gojek.co.id/gojek/v2/booking/findByOrderNo/%s", new Object[] { this.mBookingData.orderNo });
    this.volleyClient.get(str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(WaitingDriver.TAG, "error doLoad booking " + paramAnonymousVolleyError);
        WaitingDriver.this.setRefreshActionButtonState(false);
        if ((paramAnonymousVolleyError instanceof AuthFailureError)) {
          WaitingDriver.this.doSessionAuthenticate();
        }
      }
      
      public void onResponse(com.gojek.app.parcelable.BookingStatus paramAnonymousBookingStatus)
      {
        WaitingDriver.this.setRefreshActionButtonState(false);
        if (paramAnonymousBookingStatus != null)
        {
          WaitingDriver.access$702(WaitingDriver.this, paramAnonymousBookingStatus);
          if ((WaitingDriver.this.mBookingData.statusBooking != 6) && (WaitingDriver.this.mBookingData.statusBooking != 1))
          {
            if (WaitingDriver.this.mBookingData.getStatusBooking() == 4)
            {
              WaitingDriver.this.bookingRateDao.updateBookingStatus(WaitingDriver.this.mBookingData.getId(), 4);
              WaitingDriver.this.bookingHistoryDao.updateBookingStatus(WaitingDriver.this.mBookingData.getId(), 4);
            }
            if (WaitingDriver.this.mBookingData.getStatusBooking() != 5) {
              break label164;
            }
            paramAnonymousBookingStatus = new Intent(WaitingDriver.this, BookingTryAgain.class);
            paramAnonymousBookingStatus.putExtra("BOOKING_DATA", WaitingDriver.this.mBookingData);
            WaitingDriver.this.startActivity(paramAnonymousBookingStatus);
            WaitingDriver.this.finish();
          }
        }
        return;
        label164:
        paramAnonymousBookingStatus = new Intent(WaitingDriver.this, BookingStatus.class);
        paramAnonymousBookingStatus.putExtra("BOOKING_DATA", WaitingDriver.this.mBookingData);
        WaitingDriver.this.startActivity(paramAnonymousBookingStatus);
        WaitingDriver.this.finish();
      }
    }, com.gojek.app.parcelable.BookingStatus.class, this.mCustomerSaved.getAccessToken());
  }
  
  private void init()
  {
    this.mCustomerSaved = new CustomerSaved(this);
    this.bookingRateDao = new BookingRateDao(this);
    this.bookingHistoryDao = new BookingHistoryDao(this);
    this.volleyClient = VolleyClient.getInstance(this);
    this.mBookingData = ((com.gojek.app.parcelable.BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.mIsPreBooking = getIntent().getBooleanExtra("IS_PRE_BOOKING", false);
    this.mTVBookingTime.append(" " + Util.formatDateFromAPI(this.mBookingData.timeField, null));
    if (this.mBookingData.timeField == null) {
      this.mTVBookingTime.append(" " + getString(2131165702));
    }
    this.bookingHistoryDao.updateBookingStatus(this.mBookingData.getId(), 6);
  }
  
  private void renderView()
  {
    setContentView(2130968844);
    this.mTVInfo = ((TextView)findViewById(2131625034));
    this.mIV1 = ((ImageView)findViewById(2131624311));
    this.mTV1 = ((TextView)findViewById(2131624309));
    this.mTV2 = ((TextView)findViewById(2131624310));
    this.mTVBookingTime = ((TextView)findViewById(2131625032));
  }
  
  @Deprecated
  private void startAnimation()
  {
    this.mTimerImage = new Timer();
    this.mTimerImage.schedule(new TimerTask()
    {
      public void run()
      {
        WaitingDriver.this.runOnUiThread(new WaitingDriver.2.1(this));
      }
    }, 0L, 600L);
  }
  
  private void startRunningText()
  {
    this.mTimer = new Timer();
    this.mTimer.schedule(new TimerTask()
    {
      public void run()
      {
        WaitingDriver.this.runOnUiThread(new WaitingDriver.1.1(this));
      }
    }, 0L, 4000L);
  }
  
  private void stopRunningText()
  {
    if (this.mTimer != null)
    {
      this.mTimer.cancel();
      this.mTimer = null;
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt2 == -1) && (paramInt1 == 401)) {
      doLoadBooking();
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    Intent localIntent = new Intent(this, Home.class);
    localIntent.putExtra("FEED_BACK", 2);
    localIntent.setFlags(67108864);
    startActivity(localIntent);
    super.onBackPressed();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131624302: 
      showCancelDialog();
      return;
    }
    paramView = new com.gojek.app.parcelable.BookingStatus();
    paramView.serviceType = this.mBookingData.serviceType;
    Intent localIntent = new Intent(this, Home.class);
    localIntent.putExtra("BOOKING_DATA", paramView);
    localIntent.putExtra("FLAG", 25);
    localIntent.addFlags(67108864);
    if (paramView.serviceType == 5)
    {
      finish();
      return;
    }
    startActivity(localIntent);
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    renderView();
    init();
    bindReceiver();
    Log.e(TAG, "waiting driver created");
    FacebookSdk.sdkInitialize(getApplicationContext());
    this.cancelFBLogger = AppEventsLogger.newLogger(getApplicationContext());
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    this.optionsMenu = paramMenu;
    getMenuInflater().inflate(2131755018, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  protected void onDestroy()
  {
    try
    {
      unregisterReceiver(this.mPushReceiver);
      super.onDestroy();
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    }
    doLoadBooking();
    return true;
  }
  
  protected void onPause()
  {
    super.onPause();
    stopRunningText();
    AppEventsLogger localAppEventsLogger = this.cancelFBLogger;
    AppEventsLogger.deactivateApp(this);
  }
  
  protected void onResume()
  {
    this.mCustomerSaved = new CustomerSaved(this);
    super.onResume();
    AppEventsLogger localAppEventsLogger = this.cancelFBLogger;
    AppEventsLogger.activateApp(this);
  }
  
  protected void onStart()
  {
    super.onStart();
    startRunningText();
  }
  
  public void setRefreshActionButtonState(boolean paramBoolean)
  {
    MenuItem localMenuItem;
    if (this.optionsMenu != null)
    {
      localMenuItem = this.optionsMenu.findItem(2131625037);
      if (localMenuItem != null)
      {
        if (!paramBoolean) {
          break label39;
        }
        localMenuItem.setActionView(2130968814);
      }
    }
    return;
    label39:
    localMenuItem.setActionView(null);
  }
  
  public void showCancelDialog()
  {
    new AlertDialog.Builder(this).setTitle(getString(2131165424)).setMessage(getString(2131165353)).setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        WaitingDriver.this.doCancel(null);
        WaitingDriver.this.cancelFBLogger.logEvent(WaitingDriver.this.getString(2131165550));
      }
    }).setNegativeButton("No", null).show();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/WaitingDriver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */