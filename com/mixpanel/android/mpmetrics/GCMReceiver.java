package com.mixpanel.android.mpmetrics;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Log;

public class GCMReceiver
  extends BroadcastReceiver
{
  private static final String LOGTAG = "MixpanelAPI.GCMReceiver";
  
  private Notification buildNotification(Context paramContext, Intent paramIntent, ResourceIds paramResourceIds)
  {
    paramIntent = readInboundIntent(paramContext, paramIntent, paramResourceIds);
    if (paramIntent == null) {
      return null;
    }
    if (MPConfig.DEBUG) {
      Log.d("MixpanelAPI.GCMReceiver", "MP GCM notification received: " + paramIntent.message);
    }
    paramResourceIds = PendingIntent.getActivity(paramContext, 0, paramIntent.intent, 134217728);
    if (Build.VERSION.SDK_INT >= 16) {
      return makeNotificationSDK16OrHigher(paramContext, paramResourceIds, paramIntent);
    }
    if (Build.VERSION.SDK_INT >= 11) {
      return makeNotificationSDK11OrHigher(paramContext, paramResourceIds, paramIntent);
    }
    return makeNotificationSDKLessThan11(paramContext, paramResourceIds, paramIntent);
  }
  
  private Intent buildNotificationIntent(Context paramContext, String paramString)
  {
    Uri localUri = null;
    if (paramString != null) {
      localUri = Uri.parse(paramString);
    }
    if (localUri == null) {
      return getDefaultIntent(paramContext);
    }
    return new Intent("android.intent.action.VIEW", localUri);
  }
  
  private void handleNotificationIntent(Context paramContext, Intent paramIntent)
  {
    String str = MPConfig.getInstance(paramContext).getResourcePackageName();
    Object localObject = str;
    if (str == null) {
      localObject = paramContext.getPackageName();
    }
    localObject = new ResourceReader.Drawables((String)localObject, paramContext);
    paramIntent = buildNotification(paramContext.getApplicationContext(), paramIntent, (ResourceIds)localObject);
    if (paramIntent != null) {
      ((NotificationManager)paramContext.getSystemService("notification")).notify(0, paramIntent);
    }
  }
  
  private void handleRegistrationIntent(Intent paramIntent)
  {
    final String str = paramIntent.getStringExtra("registration_id");
    if (paramIntent.getStringExtra("error") != null) {
      Log.e("MixpanelAPI.GCMReceiver", "Error when registering for GCM: " + paramIntent.getStringExtra("error"));
    }
    do
    {
      return;
      if (str != null)
      {
        if (MPConfig.DEBUG) {
          Log.d("MixpanelAPI.GCMReceiver", "Registering GCM ID: " + str);
        }
        MixpanelAPI.allInstances(new MixpanelAPI.InstanceProcessor()
        {
          public void process(MixpanelAPI paramAnonymousMixpanelAPI)
          {
            paramAnonymousMixpanelAPI.getPeople().setPushRegistrationId(str);
          }
        });
        return;
      }
    } while (paramIntent.getStringExtra("unregistered") == null);
    if (MPConfig.DEBUG) {
      Log.d("MixpanelAPI.GCMReceiver", "Unregistering from GCM");
    }
    MixpanelAPI.allInstances(new MixpanelAPI.InstanceProcessor()
    {
      public void process(MixpanelAPI paramAnonymousMixpanelAPI)
      {
        paramAnonymousMixpanelAPI.getPeople().clearPushRegistrationId();
      }
    });
  }
  
  @TargetApi(11)
  private Notification makeNotificationSDK11OrHigher(Context paramContext, PendingIntent paramPendingIntent, NotificationData paramNotificationData)
  {
    paramContext = new Notification.Builder(paramContext).setSmallIcon(paramNotificationData.icon).setTicker(paramNotificationData.message).setWhen(System.currentTimeMillis()).setContentTitle(paramNotificationData.title).setContentText(paramNotificationData.message).setContentIntent(paramPendingIntent).getNotification();
    paramContext.flags |= 0x10;
    return paramContext;
  }
  
  @SuppressLint({"NewApi"})
  @TargetApi(16)
  private Notification makeNotificationSDK16OrHigher(Context paramContext, PendingIntent paramPendingIntent, NotificationData paramNotificationData)
  {
    paramContext = new Notification.Builder(paramContext).setSmallIcon(paramNotificationData.icon).setTicker(paramNotificationData.message).setWhen(System.currentTimeMillis()).setContentTitle(paramNotificationData.title).setContentText(paramNotificationData.message).setContentIntent(paramPendingIntent).setStyle(new Notification.BigTextStyle().bigText(paramNotificationData.message)).build();
    paramContext.flags |= 0x10;
    return paramContext;
  }
  
  @TargetApi(9)
  private Notification makeNotificationSDKLessThan11(Context paramContext, PendingIntent paramPendingIntent, NotificationData paramNotificationData)
  {
    Notification localNotification = new Notification(paramNotificationData.icon, paramNotificationData.message, System.currentTimeMillis());
    localNotification.flags |= 0x10;
    localNotification.setLatestEventInfo(paramContext, paramNotificationData.title, paramNotificationData.message, paramPendingIntent);
    return localNotification;
  }
  
  Intent getDefaultIntent(Context paramContext)
  {
    return paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    if ("com.google.android.c2dm.intent.REGISTRATION".equals(str)) {
      handleRegistrationIntent(paramIntent);
    }
    while (!"com.google.android.c2dm.intent.RECEIVE".equals(str)) {
      return;
    }
    handleNotificationIntent(paramContext, paramIntent);
  }
  
  NotificationData readInboundIntent(Context paramContext, Intent paramIntent, ResourceIds paramResourceIds)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    String str2 = paramIntent.getStringExtra("mp_message");
    String str4 = paramIntent.getStringExtra("mp_icnm");
    String str3 = paramIntent.getStringExtra("mp_cta");
    String str1 = paramIntent.getStringExtra("mp_title");
    if (str2 == null) {
      return null;
    }
    int j = -1;
    int i = j;
    if (str4 != null)
    {
      i = j;
      if (paramResourceIds.knownIdName(str4)) {
        i = paramResourceIds.idFromName(str4);
      }
    }
    try
    {
      paramResourceIds = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0);
      j = i;
      if (i == -1)
      {
        j = i;
        if (paramResourceIds != null) {
          j = paramResourceIds.icon;
        }
      }
      i = j;
      if (j == -1) {
        i = 17301651;
      }
      paramIntent = str1;
      if (str1 == null)
      {
        paramIntent = str1;
        if (paramResourceIds != null) {
          paramIntent = localPackageManager.getApplicationLabel(paramResourceIds);
        }
      }
      paramResourceIds = paramIntent;
      if (paramIntent == null) {
        paramResourceIds = "A message for you";
      }
      return new NotificationData(i, paramResourceIds, str2, buildNotificationIntent(paramContext, str3), null);
    }
    catch (PackageManager.NameNotFoundException paramIntent)
    {
      for (;;)
      {
        paramResourceIds = null;
      }
    }
  }
  
  static class NotificationData
  {
    public final int icon;
    public final Intent intent;
    public final String message;
    public final CharSequence title;
    
    private NotificationData(int paramInt, CharSequence paramCharSequence, String paramString, Intent paramIntent)
    {
      this.icon = paramInt;
      this.title = paramCharSequence;
      this.message = paramString;
      this.intent = paramIntent;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/mixpanel/android/mpmetrics/GCMReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */