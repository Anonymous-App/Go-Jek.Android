package lib.gojek.base.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class BaseConnectionManager
{
  private static int TYPE_MOBILE = 2;
  private static int TYPE_NOT_CONNECTED = 0;
  private static int TYPE_WIFI = 1;
  
  private static int getConnectivityStatus(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (paramContext != null)
    {
      if (paramContext.getType() == 1) {
        return TYPE_WIFI;
      }
      if (paramContext.getType() == 0) {
        return TYPE_MOBILE;
      }
    }
    return TYPE_NOT_CONNECTED;
  }
  
  public static boolean isConnected(Context paramContext)
  {
    return getConnectivityStatus(paramContext) != TYPE_NOT_CONNECTED;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/util/BaseConnectionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */