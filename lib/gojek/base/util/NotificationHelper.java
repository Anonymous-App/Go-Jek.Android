package lib.gojek.base.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Style;
import lib.gojek.base.R.drawable;

public class NotificationHelper
{
  private static NotificationManager notificationManager;
  private static android.support.v7.app.NotificationCompat.Builder notifictionBuilder;
  private static final long vibratePattern = 1000L;
  private Context context;
  private PendingIntent pendingIntent;
  
  public NotificationHelper(Context paramContext)
  {
    this.context = paramContext;
    notificationManager = (NotificationManager)paramContext.getSystemService("notification");
    notifictionBuilder = new android.support.v7.app.NotificationCompat.Builder(paramContext);
  }
  
  public static void cancelAllNotification()
  {
    if (notificationManager != null) {
      notificationManager.cancelAll();
    }
  }
  
  public static void cancelNotificationById(int paramInt)
  {
    if (notificationManager != null) {
      notificationManager.cancel(paramInt);
    }
  }
  
  private void createNotification(String paramString1, String paramString2, String paramString3)
  {
    notifictionBuilder.setSmallIcon(getNotificationIcon()).setContentTitle(paramString1).setTicker(paramString2).setStyle(getStyleNotif(paramString3)).setContentText(paramString3).setPriority(2).setWhen(0L).setAutoCancel(true);
  }
  
  private int getNotificationIcon()
  {
    if (BaseUtils.isLollipopOrNewer()) {
      return R.drawable.base_ic_gojek_silhouette;
    }
    return R.drawable.base_ic_actionbar;
  }
  
  private NotificationCompat.Style getStyleNotif(String paramString)
  {
    return new NotificationCompat.BigTextStyle().bigText(paramString);
  }
  
  private static void setSound(Uri paramUri)
  {
    notifictionBuilder.setSound(paramUri);
  }
  
  public void setSound()
  {
    notifictionBuilder.setSound(RingtoneManager.getDefaultUri(2));
  }
  
  public void setVibrate()
  {
    notifictionBuilder.setVibrate(new long[] { 1000L, 1000L });
  }
  
  public void setVibrate(long paramLong)
  {
    notifictionBuilder.setVibrate(new long[] { paramLong, paramLong });
  }
  
  public void showNotif(int paramInt1, int paramInt2, Intent paramIntent, String paramString1, String paramString2, String paramString3)
  {
    createNotification(paramString1, paramString2, paramString3);
    this.pendingIntent = PendingIntent.getActivity(this.context, paramInt2, paramIntent, 134217728);
    notifictionBuilder.setContentIntent(this.pendingIntent);
    notificationManager.notify(paramInt1, notifictionBuilder.build());
  }
  
  public void updateNotification(int paramInt)
  {
    if (notificationManager != null) {
      notificationManager.notify(paramInt, notifictionBuilder.build());
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/util/NotificationHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */