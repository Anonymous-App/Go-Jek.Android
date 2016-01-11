package com.gojek.gotix.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import com.gojek.gotix.R.string;
import com.gojek.gotix.activities.GotixAcceptedByDriverActivity;
import com.gojek.gotix.activities.GotixMainActivity;
import com.gojek.gotix.activities.GotixReviewActivity;
import com.gojek.gotix.activities.GotixWaitingDriverActivity;
import com.gojek.gotix.services.model.ProduceOrder;
import com.gojek.gotix.tools.AndroidBus;
import com.gojek.gotix.tools.BusProvider;
import lib.gojek.base.util.NotificationHelper;

public class GotixBookingHandler
{
  public static final String BOOKING_STATUS = "booking_status";
  public static final int DONE = 4;
  public static final String NOTIFICATION_BODY = "gcm.notification.body";
  public static final String NOTIFICATION_TITLE = "gcm.notification.title";
  public static final int NO_DRIVER = 5;
  public static final String ORDER_ID = "order_id";
  private static final String ORDER_ID_KEY = "orderIdKey";
  private static final String REQUEST_CODE = "requestCode";
  private String body;
  private String bookingStatus;
  private Bundle bundle;
  private IntentService intentService;
  private NotificationHelper notificationHelper;
  private int orderId;
  private String title;
  
  public GotixBookingHandler(IntentService paramIntentService, Bundle paramBundle)
  {
    this.intentService = paramIntentService;
    this.bundle = paramBundle;
    this.notificationHelper = new NotificationHelper(paramIntentService);
    this.notificationHelper.setSound();
    this.notificationHelper.setVibrate();
    handleEventStatus();
  }
  
  private void getBundleFromNotif()
  {
    this.orderId = Integer.parseInt(this.bundle.getString("order_id"));
    this.bookingStatus = this.bundle.getString("booking_status");
    this.body = this.bundle.getString("gcm.notification.body");
    this.title = this.bundle.getString("gcm.notification.title");
  }
  
  private Intent getIntent(int paramInt)
  {
    Intent localIntent = new Intent(this.intentService, GotixMainActivity.class);
    localIntent.setFlags(268468224);
    localIntent.putExtra("orderIdKey", this.orderId);
    localIntent.putExtra("requestCode", paramInt);
    return localIntent;
  }
  
  private void handleEventStatus()
  {
    getBundleFromNotif();
    if (isNoDriver())
    {
      if (!GotixWaitingDriverActivity.isRunning) {
        showNotif(getIntent(22));
      }
      BusProvider.getInstance().post(new ProduceOrder(this.orderId, 0, 22));
      return;
    }
    if (isDone())
    {
      if (!GotixReviewActivity.isRunning) {
        showNotif(getIntent(25));
      }
      BusProvider.getInstance().post(new ProduceOrder(this.orderId, 0, 25));
      return;
    }
    if (!GotixAcceptedByDriverActivity.isRunning) {
      showNotif(getIntent(24));
    }
    BusProvider.getInstance().post(new ProduceOrder(this.orderId, 0, 24));
  }
  
  private boolean isDone()
  {
    return this.bookingStatus.equals(Integer.toString(4));
  }
  
  private boolean isNoDriver()
  {
    return this.bookingStatus.equals(Integer.toString(5));
  }
  
  private void showNotif(Intent paramIntent)
  {
    this.notificationHelper.showNotif(this.orderId, 83, paramIntent, this.intentService.getString(R.string.app_name), this.title, this.body);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/services/GotixBookingHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */