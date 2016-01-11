package lib.gojek.base.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.view.Window;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class BaseUtils
{
  public static String getJsonStringFromResponse(Response paramResponse)
  {
    try
    {
      paramResponse = new String(((TypedByteArray)paramResponse.getBody()).getBytes(), "utf-8");
      return paramResponse;
    }
    catch (UnsupportedEncodingException paramResponse)
    {
      paramResponse.printStackTrace();
    }
    return "";
  }
  
  public static boolean isKitkat()
  {
    return Build.VERSION.SDK_INT == 19;
  }
  
  public static boolean isLollipopOrNewer()
  {
    return Build.VERSION.SDK_INT >= 21;
  }
  
  public static String loadJSONFromAsset(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getAssets().open("json/" + paramString);
      paramString = new byte[paramContext.available()];
      paramContext.read(paramString);
      paramContext.close();
      paramContext = new String(paramString, "UTF-8");
      return paramContext;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static void setColorStatusBar(Activity paramActivity, int paramInt)
  {
    if (isLollipopOrNewer())
    {
      localWindow = paramActivity.getWindow();
      localWindow.addFlags(Integer.MIN_VALUE);
      localWindow.clearFlags(67108864);
      localWindow.setStatusBarColor(paramActivity.getResources().getColor(paramInt));
    }
    while (!isKitkat())
    {
      Window localWindow;
      return;
    }
    paramActivity = new SystemBarTintManager(paramActivity);
    paramActivity.setStatusBarTintEnabled(true);
    paramActivity.setStatusBarTintResource(paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/util/BaseUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */