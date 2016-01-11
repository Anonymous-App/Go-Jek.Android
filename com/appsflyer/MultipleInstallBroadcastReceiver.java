package com.appsflyer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

public class MultipleInstallBroadcastReceiver
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.i("AppsFlyer_1.15", "MultipleInstallBroadcastReceiver called");
    Iterator localIterator = paramContext.getPackageManager().queryBroadcastReceivers(new Intent("com.android.vending.INSTALL_REFERRER"), 0).iterator();
    while (localIterator.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
      String str = paramIntent.getAction();
      if ((localResolveInfo.activityInfo.packageName.equals(paramContext.getPackageName())) && ("com.android.vending.INSTALL_REFERRER".equals(str)) && (!getClass().getName().equals(localResolveInfo.activityInfo.name)))
      {
        Log.i("AppsFlyer_1.15", "trigger onReceive: class: " + localResolveInfo.activityInfo.name);
        try
        {
          ((BroadcastReceiver)Class.forName(localResolveInfo.activityInfo.name).newInstance()).onReceive(paramContext, paramIntent);
        }
        catch (Throwable localThrowable)
        {
          Log.e("AppsFlyer_1.15", "error in BroadcastReceiver " + localResolveInfo.activityInfo.name, localThrowable);
        }
      }
    }
    new AppsFlyerLib().onReceive(paramContext, paramIntent);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/appsflyer/MultipleInstallBroadcastReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */