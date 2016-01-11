package com.mixpanel.android.mpmetrics;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.util.Log;
import com.mixpanel.android.surveys.SurveyActivity;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class ConfigurationChecker
{
  public static String LOGTAG = "MixpanelAPI.ConfigurationChecker";
  
  public static boolean checkBasicConfiguration(Context paramContext)
  {
    if (paramContext.getPackageManager().checkPermission("android.permission.INTERNET", paramContext.getPackageName()) != 0)
    {
      Log.w(LOGTAG, "Package does not have permission android.permission.INTERNET - Mixpanel will not work at all!");
      Log.i(LOGTAG, "You can fix this by adding the following to your AndroidManifest.xml file:\n<uses-permission android:name=\"android.permission.INTERNET\" />");
      return false;
    }
    return true;
  }
  
  public static boolean checkPushConfiguration(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 8) {
      Log.i(LOGTAG, "Mixpanel push notifications not supported in SDK " + Build.VERSION.SDK_INT);
    }
    int j;
    int i;
    do
    {
      Object localObject1;
      do
      {
        Object localObject2;
        String str;
        do
        {
          return false;
          localObject2 = paramContext.getPackageManager();
          str = paramContext.getPackageName();
          localObject1 = str + ".permission.C2D_MESSAGE";
          try
          {
            ((PackageManager)localObject2).getPermissionInfo((String)localObject1, 4096);
            if (((PackageManager)localObject2).checkPermission("com.google.android.c2dm.permission.RECEIVE", str) != 0)
            {
              Log.w(LOGTAG, "Package does not have permission com.google.android.c2dm.permission.RECEIVE");
              Log.i(LOGTAG, "You can fix this by adding the following to your AndroidManifest.xml file:\n<uses-permission android:name=\"com.google.android.c2dm.permission.RECEIVE\" />");
              return false;
            }
          }
          catch (PackageManager.NameNotFoundException paramContext)
          {
            Log.w(LOGTAG, "Application does not define permission " + (String)localObject1);
            Log.i(LOGTAG, "You will need to add the following lines to your application manifest:\n<permission android:name=\"" + str + ".permission.C2D_MESSAGE\" android:protectionLevel=\"signature\" />\n" + "<uses-permission android:name=\"" + str + ".permission.C2D_MESSAGE\" />");
            return false;
          }
          if (((PackageManager)localObject2).checkPermission("android.permission.INTERNET", str) != 0)
          {
            Log.w(LOGTAG, "Package does not have permission android.permission.INTERNET");
            Log.i(LOGTAG, "You can fix this by adding the following to your AndroidManifest.xml file:\n<uses-permission android:name=\"android.permission.INTERNET\" />");
            return false;
          }
          if (((PackageManager)localObject2).checkPermission("android.permission.WAKE_LOCK", str) != 0)
          {
            Log.w(LOGTAG, "Package does not have permission android.permission.WAKE_LOCK");
            Log.i(LOGTAG, "You can fix this by adding the following to your AndroidManifest.xml file:\n<uses-permission android:name=\"android.permission.WAKE_LOCK\" />");
            return false;
          }
          if (((PackageManager)localObject2).checkPermission("android.permission.GET_ACCOUNTS", str) == 0) {
            break;
          }
          Log.i(LOGTAG, "Package does not have permission android.permission.GET_ACCOUNTS");
          Log.i(LOGTAG, "Android versions below 4.1 require GET_ACCOUNTS to receive Mixpanel push notifications.\nDevices with later OS versions will still be able to receive messages, but if you'd like to support older devices, you'll need to add the following to your AndroidManifest.xml file:\n<uses-permission android:name=\"android.permission.GET_ACCOUNTS\" />");
        } while (Build.VERSION.SDK_INT < 16);
        try
        {
          localObject1 = ((PackageManager)localObject2).getPackageInfo(str, 2);
          localObject2 = ((PackageInfo)localObject1).receivers;
          if ((localObject2 == null) || (localObject2.length == 0))
          {
            Log.w(LOGTAG, "No receiver for package " + str);
            Log.i(LOGTAG, "You can fix this with the following into your <application> tag:\n" + samplePushConfigurationMessage(str));
            return false;
          }
        }
        catch (PackageManager.NameNotFoundException paramContext)
        {
          Log.w(LOGTAG, "Could not get receivers for package " + str);
          return false;
        }
        localObject1 = new HashSet();
        j = localObject2.length;
        i = 0;
        while (i < j)
        {
          Object localObject3 = localObject2[i];
          if ("com.google.android.c2dm.permission.SEND".equals(((ActivityInfo)localObject3).permission)) {
            ((Set)localObject1).add(((ActivityInfo)localObject3).name);
          }
          i += 1;
        }
        if (((Set)localObject1).isEmpty())
        {
          Log.w(LOGTAG, "No receiver allowed to receive com.google.android.c2dm.permission.SEND");
          Log.i(LOGTAG, "You can fix by pasting the following into the <application> tag in your AndroidManifest.xml:\n" + samplePushConfigurationMessage(str));
          return false;
        }
      } while (!checkReceiver(paramContext, (Set)localObject1, "com.google.android.c2dm.intent.RECEIVE"));
      i = 0;
      try
      {
        Class.forName("com.google.android.gms.common.GooglePlayServicesUtil");
        i = 1;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        for (;;)
        {
          Log.w(LOGTAG, "Google Play Services aren't included in your build- push notifications won't work on Lollipop/API 21 or greater");
          Log.i(LOGTAG, "You can fix this by adding com.google.android.gms:play-services as a dependency of your gradle or maven project");
        }
      }
      j = 1;
      if (!checkReceiver(paramContext, (Set)localObject1, "com.google.android.c2dm.intent.REGISTRATION"))
      {
        Log.i(LOGTAG, "(You can still receive push notifications on Lollipop/API 21 or greater with this configuration)");
        j = 0;
      }
    } while ((i == 0) && (j == 0));
    return true;
  }
  
  private static boolean checkReceiver(Context paramContext, Set<String> paramSet, String paramString)
  {
    Object localObject = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    Intent localIntent = new Intent(paramString);
    localIntent.setPackage(paramContext);
    localObject = ((PackageManager)localObject).queryBroadcastReceivers(localIntent, 32);
    if (((List)localObject).isEmpty())
    {
      Log.w(LOGTAG, "No receivers for action " + paramString);
      Log.i(LOGTAG, "You can fix by pasting the following into the <application> tag in your AndroidManifest.xml:\n" + samplePushConfigurationMessage(paramContext));
      return false;
    }
    paramContext = ((List)localObject).iterator();
    while (paramContext.hasNext())
    {
      paramString = ((ResolveInfo)paramContext.next()).activityInfo.name;
      if (!paramSet.contains(paramString))
      {
        Log.w(LOGTAG, "Receiver " + paramString + " is not set with permission com.google.android.c2dm.permission.SEND");
        Log.i(LOGTAG, "Please add the attribute 'android:permission=\"com.google.android.c2dm.permission.SEND\"' to your <receiver> tag");
        return false;
      }
    }
    return true;
  }
  
  public static boolean checkSurveyActivityAvailable(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 16) {
      return false;
    }
    Intent localIntent = new Intent(paramContext, SurveyActivity.class);
    localIntent.addFlags(268435456);
    localIntent.addFlags(131072);
    if (paramContext.getPackageManager().queryIntentActivities(localIntent, 0).size() == 0)
    {
      Log.w(LOGTAG, SurveyActivity.class.getName() + " is not registered as an activity in your application, so surveys can't be shown.");
      Log.i(LOGTAG, "Please add the child tag <activity android:name=\"com.mixpanel.android.surveys.SurveyActivity\" /> to your <application> tag.");
      return false;
    }
    return true;
  }
  
  private static String samplePushConfigurationMessage(String paramString)
  {
    return "<receiver android:name=\"com.mixpanel.android.mpmetrics.GCMReceiver\"\n          android:permission=\"com.google.android.c2dm.permission.SEND\" >\n    <intent-filter>\n       <action android:name=\"com.google.android.c2dm.intent.RECEIVE\" />\n       <action android:name=\"com.google.android.c2dm.intent.REGISTRATION\" />\n       <category android:name=\"" + paramString + "\" />\n" + "    </intent-filter>\n" + "</receiver>";
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/mixpanel/android/mpmetrics/ConfigurationChecker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */