package com.gojek.app.gcm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.text.TextUtils;
import android.util.Log;
import com.gojek.gobox.timeoutOrder.view.GcmMessageHandler;
import com.gojek.gotix.services.GotixMessageHandler;
import lib.gojek.base.helper.GoServicesMessageFactory;

public class GcmBroadcastReceiver
  extends WakefulBroadcastReceiver
{
  ComponentName comp;
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    GoServicesMessageFactory localGoServicesMessageFactory = new GoServicesMessageFactory(paramIntent);
    localGoServicesMessageFactory.addHandler(GotixMessageHandler.class.getName(), new GotixMessageHandler());
    if (localGoServicesMessageFactory.isConsumeMessage())
    {
      this.comp = localGoServicesMessageFactory.getComponentName(paramContext);
      Log.d(GcmBroadcastReceiver.class.getSimpleName(), "Comp" + this.comp);
    }
    for (;;)
    {
      startWakefulService(paramContext, paramIntent.setComponent(this.comp));
      setResultCode(-1);
      return;
      if (TextUtils.isEmpty(paramIntent.getStringExtra("push_type"))) {
        this.comp = new ComponentName(paramContext.getPackageName(), GcmIntentService.class.getName());
      } else {
        this.comp = new ComponentName(paramContext.getPackageName(), GcmMessageHandler.class.getName());
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gcm/GcmBroadcastReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */