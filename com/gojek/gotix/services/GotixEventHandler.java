package com.gojek.gotix.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import com.gojek.gotix.R.string;
import com.gojek.gotix.activities.GotixCallCenterActivity;
import com.gojek.gotix.activities.GotixEventDetailActivity;
import com.gojek.gotix.activities.GotixMainActivity;
import com.gojek.gotix.activities.GotixReceiptTicketActivity;
import com.gojek.gotix.services.model.ProduceEvent;
import com.gojek.gotix.services.model.ProduceOrder;
import com.gojek.gotix.tools.AndroidBus;
import com.gojek.gotix.tools.BusProvider;
import lib.gojek.base.util.NotificationHelper;

public class GotixEventHandler
{
  public static final String EVENT_CANCELED = "canceled";
  public static final String EVENT_ID = "event_id";
  public static final String EVENT_POSTPONE = "postponed";
  public static final String EVENT_STATUS = "event_status";
  public static final String NOTIFICATION_BODY = "gcm.notification.body";
  public static final String NOTIFICATION_TITLE = "gcm.notification.title";
  public static final String ORDER_ID = "order_id";
  private static final String ORDER_ID_KEY = "orderIdKey";
  private static final String REQUEST_CODE = "requestCode";
  public static final String TICKET_READY = "ticket_ready";
  private String body;
  private Bundle bundle;
  private int eventId;
  private String eventStatus;
  private IntentService intentService;
  private NotificationHelper notificationHelper;
  private int orderId;
  private String title;
  
  public GotixEventHandler(IntentService paramIntentService, Bundle paramBundle)
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
    this.eventId = Integer.parseInt(this.bundle.getString("event_id"));
    this.eventStatus = this.bundle.getString("event_status");
    this.body = this.bundle.getString("gcm.notification.body");
    this.title = this.bundle.getString("gcm.notification.title");
  }
  
  private Intent getIntent(int paramInt)
  {
    Intent localIntent = new Intent(this.intentService, GotixMainActivity.class);
    localIntent.setFlags(268468224);
    localIntent.putExtra("orderIdKey", this.orderId);
    localIntent.putExtra("requestCode", paramInt);
    if (paramInt != 26) {
      localIntent.putExtra("event_id", this.eventId);
    }
    return localIntent;
  }
  
  private void handleEventStatus()
  {
    getBundleFromNotif();
    if (isEventTicketReady())
    {
      if (!GotixReceiptTicketActivity.isRunning) {
        showNotif(getIntent(101));
      }
      BusProvider.getInstance().post(new ProduceOrder(this.orderId, this.eventId, 101));
    }
    do
    {
      return;
      if (isEventCanceled())
      {
        if (!GotixCallCenterActivity.isRunning) {
          showNotif(getIntent(26));
        }
        BusProvider.getInstance().post(new ProduceOrder(this.orderId, 0, 26));
        return;
      }
    } while (!isEventPostPoned());
    if (!GotixEventDetailActivity.isRunning) {
      showNotif(getIntent(21));
    }
    BusProvider.getInstance().post(new ProduceEvent(this.eventId));
  }
  
  private boolean isEventCanceled()
  {
    return this.eventStatus.equals("canceled");
  }
  
  private boolean isEventPostPoned()
  {
    return this.eventStatus.equals("postponed");
  }
  
  private boolean isEventTicketReady()
  {
    return this.eventStatus.equals("ticket_ready");
  }
  
  private void showNotif(Intent paramIntent)
  {
    this.notificationHelper.showNotif(this.eventId, 81, paramIntent, this.intentService.getString(R.string.app_name), this.title, this.body);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/services/GotixEventHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */