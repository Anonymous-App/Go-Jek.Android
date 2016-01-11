package lib.gojek.base.helper;

import android.content.Context;
import android.content.Intent;

public class BaseToPickServiceGojek
{
  private static String GOJEK_SERVICE_CLASS = "com.gojek.app.Services";
  private static String INTENT_SERVICE_DESTINATION = "destination";
  private static String INTENT_SERVICE_LAT = "lat";
  private static String INTENT_SERVICE_LONG = "lon";
  
  public void openGoRide(Context paramContext, Double paramDouble1, Double paramDouble2, String paramString)
  {
    try
    {
      Intent localIntent = new Intent(paramContext, Class.forName(GOJEK_SERVICE_CLASS));
      localIntent.setFlags(268435456);
      localIntent.putExtra(INTENT_SERVICE_LAT, paramDouble1);
      localIntent.putExtra(INTENT_SERVICE_LONG, paramDouble2);
      localIntent.putExtra(INTENT_SERVICE_DESTINATION, paramString);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ClassNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/helper/BaseToPickServiceGojek.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */