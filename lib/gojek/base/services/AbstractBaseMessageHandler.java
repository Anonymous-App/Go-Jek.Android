package lib.gojek.base.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public abstract class AbstractBaseMessageHandler
  extends IntentService
{
  public AbstractBaseMessageHandler(String paramString)
  {
    super(paramString);
  }
  
  public abstract void message(Bundle paramBundle);
  
  public void messageTypeDeleted(Bundle paramBundle) {}
  
  public void messageTypeSendError(Bundle paramBundle) {}
  
  protected void onHandleIntent(Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    Object localObject = GoogleCloudMessaging.getInstance(this);
    if (!localBundle.isEmpty())
    {
      localObject = ((GoogleCloudMessaging)localObject).getMessageType(paramIntent);
      if (!"send_error".equals(localObject)) {
        break label43;
      }
      messageTypeSendError(localBundle);
    }
    for (;;)
    {
      WakefulBroadcastReceiver.completeWakefulIntent(paramIntent);
      return;
      label43:
      if ("deleted_messages".equals(localObject)) {
        messageTypeDeleted(localBundle);
      } else if ("gcm".equals(localObject)) {
        message(localBundle);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/services/AbstractBaseMessageHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */