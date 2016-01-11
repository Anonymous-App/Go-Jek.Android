package com.android.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import com.android.volley.RequestQueue;
import java.io.File;

public class Volley
{
  private static final String DEFAULT_CACHE_DIR = "volley";
  
  public static RequestQueue newRequestQueue(Context paramContext)
  {
    return newRequestQueue(paramContext, null);
  }
  
  public static RequestQueue newRequestQueue(Context paramContext, int paramInt)
  {
    return newRequestQueue(paramContext, null, paramInt);
  }
  
  public static RequestQueue newRequestQueue(Context paramContext, HttpStack paramHttpStack)
  {
    return newRequestQueue(paramContext, paramHttpStack, -1);
  }
  
  public static RequestQueue newRequestQueue(Context paramContext, HttpStack paramHttpStack, int paramInt)
  {
    File localFile = new File(paramContext.getCacheDir(), "volley");
    Object localObject = "volley/0";
    try
    {
      String str = paramContext.getPackageName();
      paramContext = paramContext.getPackageManager().getPackageInfo(str, 0);
      paramContext = str + "/" + paramContext.versionCode;
      localObject = paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      label140:
      for (;;) {}
    }
    paramContext = paramHttpStack;
    if (paramHttpStack == null)
    {
      if (Build.VERSION.SDK_INT >= 9) {
        paramContext = new HurlStack();
      }
    }
    else
    {
      paramContext = new BasicNetwork(paramContext);
      if (paramInt > -1) {
        break label140;
      }
    }
    for (paramContext = new RequestQueue(new DiskBasedCache(localFile), paramContext);; paramContext = new RequestQueue(new DiskBasedCache(localFile, paramInt), paramContext))
    {
      paramContext.start();
      return paramContext;
      paramContext = new HttpClientStack(AndroidHttpClient.newInstance((String)localObject));
      break;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/android/volley/toolbox/Volley.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */