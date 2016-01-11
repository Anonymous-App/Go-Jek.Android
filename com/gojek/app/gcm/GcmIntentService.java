package com.gojek.app.gcm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;
import com.gojek.app.Home;
import com.gojek.app.model.Push;
import com.gojek.app.model.PushModel;
import com.gojek.app.persistence.dao.BookingRateDao;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;

public class GcmIntentService
  extends IntentService
{
  public static final int NOTIFICATION_ID = 1;
  private static final String TAG = GcmIntentService.class.getSimpleName();
  private BookingRateDao bookingRateDao;
  private NotificationManager mNotificationManager;
  
  public GcmIntentService()
  {
    super("GcmIntentService");
  }
  
  private int getNotificationIcon()
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    for (int i = 1; i != 0; i = 0) {
      return 2130837887;
    }
    return 2130837882;
  }
  
  private void sendNotification(Bundle paramBundle)
  {
    Log.i(TAG, "GCM RECEIVED : " + paramBundle.toString());
    Object localObject = paramBundle.getString("message");
    Log.i(TAG, "MESSAGE RECEIVED : " + (String)localObject);
    paramBundle = new Gson();
    if (!(paramBundle instanceof Gson)) {}
    for (paramBundle = paramBundle.fromJson((String)localObject, Push.class);; paramBundle = GsonInstrumentation.fromJson((Gson)paramBundle, (String)localObject, Push.class))
    {
      paramBundle = (Push)paramBundle;
      if ((paramBundle != null) && (paramBundle.model != null) && (paramBundle.model.message != null))
      {
        Log.i(TAG, "get push notification message : " + (String)localObject);
        this.mNotificationManager = ((NotificationManager)getSystemService("notification"));
        localObject = new NotificationCompat.Builder(this).setSmallIcon(getNotificationIcon()).setContentTitle(getString(2131165311)).setTicker(paramBundle.model.message).setStyle(new NotificationCompat.BigTextStyle().bigText(paramBundle.model.message)).setSound(RingtoneManager.getDefaultUri(2)).setVibrate(new long[] { 1000L, 1000L }).setContentText(paramBundle.model.message).setAutoCancel(true);
        Intent localIntent = new Intent(this, Home.class);
        localIntent.putExtra("FEED_BACK", 2);
        localIntent.putExtra("PUSH_DATA", paramBundle);
        ((NotificationCompat.Builder)localObject).setContentIntent(PendingIntent.getActivity(this, 0, localIntent, 268435456));
        this.mNotificationManager.notify(1, ((NotificationCompat.Builder)localObject).build());
        updateBookingRate(paramBundle);
        localObject = new Intent("com.gojek.app.PUSH_RECEIVER");
        ((Intent)localObject).putExtra("PUSH_DATA", paramBundle);
        sendBroadcast((Intent)localObject);
      }
      return;
    }
  }
  
  private void updateBookingRate(Push paramPush)
  {
    if (paramPush.type.equals("BOOKING_COMPLETE")) {
      this.bookingRateDao.updateBookingStatus(paramPush.model.id, 4);
    }
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    String str = GoogleCloudMessaging.getInstance(this).getMessageType(paramIntent);
    this.bookingRateDao = new BookingRateDao(this);
    if ((localBundle.isEmpty()) || ("send_error".equals(str))) {}
    for (;;)
    {
      GcmBroadcastReceiver.completeWakefulIntent(paramIntent);
      return;
      if ((!"deleted_messages".equals(str)) && ("gcm".equals(str))) {
        sendNotification(localBundle);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gcm/GcmIntentService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */