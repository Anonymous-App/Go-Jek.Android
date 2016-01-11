package com.gojek.app.gcm;

import android.content.Context;
import android.os.AsyncTask;
import com.gojek.app.util.GojekPreference;
import com.gojek.app.util.Util;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;

public class GcmUtil
{
  private static final String PROPERTY_APP_VERSION = "PROPERTY_APP_VERSION";
  private static final String PROPERTY_GOBOX_REG_ID = "PROPERTY_GOBOX_REG_ID";
  private static final String PROPERTY_REG_ID = "PROPERTY_REG_ID";
  
  public static String getGoboxRegistrationId(Context paramContext)
  {
    GojekPreference localGojekPreference = GojekPreference.getInstance(paramContext);
    String str = localGojekPreference.getString("PROPERTY_GOBOX_REG_ID", "");
    if (str.isEmpty()) {
      str = "";
    }
    while (localGojekPreference.getInt("PROPERTY_APP_VERSION", Integer.MIN_VALUE) == Util.getAppVersion(paramContext)) {
      return str;
    }
    return "";
  }
  
  public static String getRegistrationId(Context paramContext)
  {
    GojekPreference localGojekPreference = GojekPreference.getInstance(paramContext);
    String str = localGojekPreference.getString("PROPERTY_REG_ID", "");
    if (str.isEmpty()) {
      str = "";
    }
    while (localGojekPreference.getInt("PROPERTY_APP_VERSION", Integer.MIN_VALUE) == Util.getAppVersion(paramContext)) {
      return str;
    }
    return "";
  }
  
  public static void registerGcm(GoogleCloudMessaging paramGoogleCloudMessaging, String paramString, OnGcmRegisteredListener paramOnGcmRegisteredListener)
  {
    paramGoogleCloudMessaging = new GcmUtil.1(paramGoogleCloudMessaging, paramString, paramOnGcmRegisteredListener);
    paramString = new Void[0];
    if (!(paramGoogleCloudMessaging instanceof AsyncTask))
    {
      paramGoogleCloudMessaging.execute(paramString);
      return;
    }
    AsyncTaskInstrumentation.execute((AsyncTask)paramGoogleCloudMessaging, paramString);
  }
  
  public static void registerGoboxGcm(GoogleCloudMessaging paramGoogleCloudMessaging, String paramString, OnGcmRegisteredListener paramOnGcmRegisteredListener)
  {
    paramGoogleCloudMessaging = new GcmUtil.2(paramGoogleCloudMessaging, paramString, paramOnGcmRegisteredListener);
    paramString = new Void[0];
    if (!(paramGoogleCloudMessaging instanceof AsyncTask))
    {
      paramGoogleCloudMessaging.execute(paramString);
      return;
    }
    AsyncTaskInstrumentation.execute((AsyncTask)paramGoogleCloudMessaging, paramString);
  }
  
  public static void storeGoboxRegistrationId(Context paramContext, String paramString)
  {
    GojekPreference.getInstance(paramContext).setString("PROPERTY_GOBOX_REG_ID", paramString);
  }
  
  public static void storeRegistrationId(Context paramContext, String paramString)
  {
    GojekPreference localGojekPreference = GojekPreference.getInstance(paramContext);
    int i = Util.getAppVersion(paramContext);
    localGojekPreference.setString("PROPERTY_REG_ID", paramString);
    localGojekPreference.setInt("PROPERTY_APP_VERSION", i);
  }
  
  public static abstract interface OnGcmRegisteredListener
  {
    public abstract void onRegister(String paramString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gcm/GcmUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */