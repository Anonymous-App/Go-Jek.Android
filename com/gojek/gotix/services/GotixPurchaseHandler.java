package com.gojek.gotix.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import com.gojek.gotix.R.string;
import com.gojek.gotix.activities.GotixMainActivity;
import com.gojek.gotix.activities.GotixPaymentFailedActivity;
import com.gojek.gotix.activities.GotixReceiptTicketActivity;
import com.gojek.gotix.services.model.ProduceOrder;
import com.gojek.gotix.tools.AndroidBus;
import com.gojek.gotix.tools.BusProvider;
import lib.gojek.base.util.NotificationHelper;

public class GotixPurchaseHandler
{
  public static final String EVENT_ID = "event_id";
  public static final String NOTIFICATION_BODY = "gcm.notification.body";
  public static final int NOTIFICATION_ID = 103;
  public static final String NOTIFICATION_TITLE = "gcm.notification.title";
  public static final String ORDER_ID = "order_id";
  private static final String ORDER_ID_KEY = "orderIdKey";
  public static final String PURCHASE_STATUS = "purchase_status";
  private static final String REQUEST_CODE = "requestCode";
  private String body;
  private Bundle bundle;
  private IntentService intentService;
  private NotificationHelper notificationHelper;
  private int orderId;
  private String purchaseStatus;
  private String title;
  
  public GotixPurchaseHandler(IntentService paramIntentService, Bundle paramBundle)
  {
    this.intentService = paramIntentService;
    this.bundle = paramBundle;
    this.notificationHelper = new NotificationHelper(paramIntentService);
    this.notificationHelper.setSound();
    this.notificationHelper.setVibrate();
    handlePurchaseStatus();
  }
  
  private void getBundleFromNotif()
  {
    this.orderId = Integer.parseInt(this.bundle.getString("order_id"));
    this.purchaseStatus = this.bundle.getString("purchase_status");
    this.body = this.bundle.getString("gcm.notification.body");
    this.title = this.bundle.getString("gcm.notification.title");
  }
  
  private Intent getIntent(int paramInt)
  {
    Intent localIntent = new Intent(this.intentService, GotixMainActivity.class);
    localIntent.setFlags(268468224);
    localIntent.putExtra("orderIdKey", this.orderId);
    localIntent.putExtra("requestCode", paramInt);
    if (isPurchaseSuccessDeliveryReady()) {}
    for (paramInt = 1;; paramInt = 0)
    {
      localIntent.putExtra("event_id", paramInt);
      return localIntent;
    }
  }
  
  private void handlePurchaseStatus()
  {
    getBundleFromNotif();
    if (isPurchaseSuccess())
    {
      if (!GotixReceiptTicketActivity.isRunning) {
        showNotif(getIntent(101));
      }
      BusProvider.getInstance().post(new ProduceOrder(this.orderId, 0, 101));
    }
    do
    {
      return;
      if (isPurchaseFailed())
      {
        if (!GotixPaymentFailedActivity.isRunning) {
          showNotif(getIntent(104));
        }
        BusProvider.getInstance().post(new ProduceOrder(this.orderId, 0, 104));
        return;
      }
    } while (!isPurchaseSuccessDeliveryReady());
    if (!GotixReceiptTicketActivity.isRunning) {
      showNotif(getIntent(101));
    }
    BusProvider.getInstance().post(new ProduceOrder(this.orderId, 0, 101));
  }
  
  private boolean isPurchaseFailed()
  {
    return (this.purchaseStatus.equals(Integer.toString(101))) || (this.purchaseStatus.equals(Integer.toString(201)));
  }
  
  private boolean isPurchaseSuccess()
  {
    return this.purchaseStatus.equals(Integer.toString(400));
  }
  
  private boolean isPurchaseSuccessDeliveryReady()
  {
    return this.purchaseStatus.equals(Integer.toString(500));
  }
  
  private void showNotif(Intent paramIntent)
  {
    this.notificationHelper.showNotif(103, 82, paramIntent, this.intentService.getString(R.string.app_name), this.title, this.body);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/services/GotixPurchaseHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */