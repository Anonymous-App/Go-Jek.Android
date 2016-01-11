package com.gojek.gobox.timeoutOrder.view;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import com.gojek.gobox.R.drawable;
import com.gojek.gobox.R.string;
import com.gojek.gobox.driverstatus.view.DriverStatusActivity;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GcmMessageHandler
  extends IntentService
{
  public static final int NOTIFICATION_ID = 126861;
  private static final String TAG = GcmMessageHandler.class.getSimpleName();
  private NotificationManager mNotificationManager;
  
  public GcmMessageHandler()
  {
    super("GcmMessageHandler");
  }
  
  private PendingIntent createPendingIntent(Context paramContext, Class paramClass, Intent paramIntent)
  {
    if (paramClass != null)
    {
      paramContext = TaskStackBuilder.create(paramContext);
      paramContext.addParentStack(paramClass);
      paramContext.addNextIntent(paramIntent);
      return paramContext.getPendingIntent(0, 1207959552);
    }
    return PendingIntent.getActivity(paramContext, 0, paramIntent, 134217728);
  }
  
  private void sendNotification(Bundle paramBundle)
  {
    showNotification(paramBundle);
    Intent localIntent = new Intent("com.gojek.app.GOBOX_PUSH_RECEIVER");
    localIntent.putExtras(paramBundle);
    sendBroadcast(localIntent);
  }
  
  private void showNotification(Bundle paramBundle)
  {
    String str1 = paramBundle.getString("gcm.notification.body");
    String str3 = paramBundle.getString("push_type");
    String str2 = paramBundle.getString("order_id");
    Uri localUri = RingtoneManager.getDefaultUri(2);
    Intent localIntent = new Intent();
    if (str3.equals("1")) {
      if (paramBundle.getString("is_success").equalsIgnoreCase("true"))
      {
        paramBundle = new Intent(this, DriverStatusActivity.class);
        paramBundle.putExtra("order id", str2);
      }
    }
    for (;;)
    {
      paramBundle.addFlags(268435456);
      paramBundle.addFlags(32768);
      paramBundle = createPendingIntent(this, null, paramBundle);
      paramBundle = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.icon_gojek).setTicker(str1).setContentTitle(getString(R.string.app_name)).setContentText(str1).setSound(localUri).setPriority(2).setWhen(0L).setAutoCancel(true).setContentIntent(paramBundle);
      NotificationManagerCompat.from(this).notify(126861, paramBundle.build());
      return;
      paramBundle = new Intent(this, TimeoutOrderActivity.class);
      paramBundle.putExtra("order id", str2);
      continue;
      paramBundle = localIntent;
      if (str3.equals("2"))
      {
        paramBundle = new Intent(this, DriverStatusActivity.class);
        paramBundle.putExtra("order id", str2);
      }
    }
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    String str = GoogleCloudMessaging.getInstance(this).getMessageType(paramIntent);
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/timeoutOrder/view/GcmMessageHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */