package com.mixpanel.android.mpmetrics;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import java.security.GeneralSecurityException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public class MPConfig
{
  public static boolean DEBUG = false;
  private static final String LOGTAG = "MixpanelAPI.Conf";
  static final int MAX_NOTIFICATION_CACHE_COUNT = 2;
  static final String REFERRER_PREFS_NAME = "com.mixpanel.android.mpmetrics.ReferralInfo";
  public static final int UI_FEATURES_MIN_API = 16;
  public static final String VERSION = "4.6.4";
  private static MPConfig sInstance;
  private static final Object sInstanceLock = new Object();
  private final boolean mAutoShowMixpanelUpdates;
  private final int mBulkUploadLimit;
  private final int mDataExpiration;
  private final int mDebugFlushInterval;
  private final String mDecideEndpoint;
  private final String mDecideFallbackEndpoint;
  private final boolean mDisableAppOpenEvent;
  private final boolean mDisableEmulatorBindingUI;
  private final boolean mDisableFallback;
  private final boolean mDisableGestureBindingUI;
  private final String mEditorUrl;
  private final String mEventsEndpoint;
  private final String mEventsFallbackEndpoint;
  private final int mFlushInterval;
  private final int mMinimumDatabaseLimit;
  private final String mPeopleEndpoint;
  private final String mPeopleFallbackEndpoint;
  private final String mResourcePackageName;
  private SSLSocketFactory mSSLSocketFactory;
  private final boolean mTestMode;
  
  MPConfig(Bundle paramBundle, Context paramContext)
  {
    try
    {
      Object localObject1 = SSLContext.getInstance("TLS");
      ((SSLContext)localObject1).init(null, null, null);
      localObject1 = ((SSLContext)localObject1).getSocketFactory();
      this.mSSLSocketFactory = ((SSLSocketFactory)localObject1);
      DEBUG = paramBundle.getBoolean("com.mixpanel.android.MPConfig.EnableDebugLogging", false);
      if (paramBundle.containsKey("com.mixpanel.android.MPConfig.AutoCheckForSurveys")) {
        Log.w("MixpanelAPI.Conf", "com.mixpanel.android.MPConfig.AutoCheckForSurveys has been deprecated in favor of com.mixpanel.android.MPConfig.AutoShowMixpanelUpdates. Please update this key as soon as possible.");
      }
      this.mBulkUploadLimit = paramBundle.getInt("com.mixpanel.android.MPConfig.BulkUploadLimit", 40);
      this.mFlushInterval = paramBundle.getInt("com.mixpanel.android.MPConfig.FlushInterval", 60000);
      this.mDebugFlushInterval = paramBundle.getInt("com.mixpanel.android.MPConfig.DebugFlushInterval", 1000);
      this.mDataExpiration = paramBundle.getInt("com.mixpanel.android.MPConfig.DataExpiration", 432000000);
      this.mMinimumDatabaseLimit = paramBundle.getInt("com.mixpanel.android.MPConfig.MinimumDatabaseLimit", 20971520);
      this.mDisableFallback = paramBundle.getBoolean("com.mixpanel.android.MPConfig.DisableFallback", true);
      this.mResourcePackageName = paramBundle.getString("com.mixpanel.android.MPConfig.ResourcePackageName");
      this.mDisableGestureBindingUI = paramBundle.getBoolean("com.mixpanel.android.MPConfig.DisableGestureBindingUI", false);
      this.mDisableEmulatorBindingUI = paramBundle.getBoolean("com.mixpanel.android.MPConfig.DisableEmulatorBindingUI", false);
      this.mDisableAppOpenEvent = paramBundle.getBoolean("com.mixpanel.android.MPConfig.DisableAppOpenEvent", true);
      bool1 = paramBundle.getBoolean("com.mixpanel.android.MPConfig.AutoCheckForSurveys", true);
      boolean bool2 = paramBundle.getBoolean("com.mixpanel.android.MPConfig.AutoShowMixpanelUpdates", true);
      if ((bool1) && (bool2))
      {
        bool1 = true;
        this.mAutoShowMixpanelUpdates = bool1;
        this.mTestMode = paramBundle.getBoolean("com.mixpanel.android.MPConfig.TestMode", false);
        String str = paramBundle.getString("com.mixpanel.android.MPConfig.EventsEndpoint");
        localObject1 = str;
        if (str == null) {
          localObject1 = "https://api.mixpanel.com/track?ip=1";
        }
        this.mEventsEndpoint = ((String)localObject1);
        str = paramBundle.getString("com.mixpanel.android.MPConfig.EventsFallbackEndpoint");
        localObject1 = str;
        if (str == null) {
          localObject1 = "http://api.mixpanel.com/track?ip=1";
        }
        this.mEventsFallbackEndpoint = ((String)localObject1);
        str = paramBundle.getString("com.mixpanel.android.MPConfig.PeopleEndpoint");
        localObject1 = str;
        if (str == null) {
          localObject1 = "https://api.mixpanel.com/engage";
        }
        this.mPeopleEndpoint = ((String)localObject1);
        str = paramBundle.getString("com.mixpanel.android.MPConfig.PeopleFallbackEndpoint");
        localObject1 = str;
        if (str == null) {
          localObject1 = "http://api.mixpanel.com/engage";
        }
        this.mPeopleFallbackEndpoint = ((String)localObject1);
        str = paramBundle.getString("com.mixpanel.android.MPConfig.DecideEndpoint");
        localObject1 = str;
        if (str == null) {
          localObject1 = "https://decide.mixpanel.com/decide";
        }
        this.mDecideEndpoint = ((String)localObject1);
        str = paramBundle.getString("com.mixpanel.android.MPConfig.DecideFallbackEndpoint");
        localObject1 = str;
        if (str == null) {
          localObject1 = "http://decide.mixpanel.com/decide";
        }
        this.mDecideFallbackEndpoint = ((String)localObject1);
        localObject1 = paramBundle.getString("com.mixpanel.android.MPConfig.EditorUrl");
        paramBundle = (Bundle)localObject1;
        if (localObject1 == null) {
          paramBundle = "wss://switchboard.mixpanel.com/connect/";
        }
        this.mEditorUrl = paramBundle;
        if (DEBUG) {
          Log.v("MixpanelAPI.Conf", "Mixpanel configured with:\n    AutoShowMixpanelUpdates " + getAutoShowMixpanelUpdates() + "\n" + "    BulkUploadLimit " + getBulkUploadLimit() + "\n" + "    FlushInterval " + getFlushInterval(paramContext) + "\n" + "    DataExpiration " + getDataExpiration() + "\n" + "    MinimumDatabaseLimit " + getMinimumDatabaseLimit() + "\n" + "    DisableFallback " + getDisableFallback() + "\n" + "    DisableAppOpenEvent " + getDisableAppOpenEvent() + "\n" + "    DisableDeviceUIBinding " + getDisableGestureBindingUI() + "\n" + "    DisableEmulatorUIBinding " + getDisableEmulatorBindingUI() + "\n" + "    EnableDebugLogging " + DEBUG + "\n" + "    TestMode " + getTestMode() + "\n" + "    EventsEndpoint " + getEventsEndpoint() + "\n" + "    PeopleEndpoint " + getPeopleEndpoint() + "\n" + "    DecideEndpoint " + getDecideEndpoint() + "\n" + "    EventsFallbackEndpoint " + getEventsFallbackEndpoint() + "\n" + "    PeopleFallbackEndpoint " + getPeopleFallbackEndpoint() + "\n" + "    DecideFallbackEndpoint " + getDecideFallbackEndpoint() + "\n" + "    EditorUrl " + getEditorUrl() + "\n");
        }
        return;
      }
    }
    catch (GeneralSecurityException localGeneralSecurityException)
    {
      for (;;)
      {
        Log.i("MixpanelAPI.Conf", "System has no SSL support. Built-in events editor will not be available", localGeneralSecurityException);
        Object localObject2 = null;
        continue;
        boolean bool1 = false;
      }
    }
  }
  
  public static MPConfig getInstance(Context paramContext)
  {
    synchronized (sInstanceLock)
    {
      if (sInstance == null) {
        sInstance = readConfig(paramContext.getApplicationContext());
      }
      return sInstance;
    }
  }
  
  static MPConfig readConfig(Context paramContext)
  {
    String str = paramContext.getPackageName();
    try
    {
      Bundle localBundle2 = paramContext.getPackageManager().getApplicationInfo(str, 128).metaData;
      Bundle localBundle1 = localBundle2;
      if (localBundle2 == null) {
        localBundle1 = new Bundle();
      }
      paramContext = new MPConfig(localBundle1, paramContext);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw new RuntimeException("Can't configure Mixpanel with package name " + str, paramContext);
    }
  }
  
  public boolean getAutoShowMixpanelUpdates()
  {
    return this.mAutoShowMixpanelUpdates;
  }
  
  public int getBulkUploadLimit()
  {
    return this.mBulkUploadLimit;
  }
  
  public int getDataExpiration()
  {
    return this.mDataExpiration;
  }
  
  public String getDecideEndpoint()
  {
    return this.mDecideEndpoint;
  }
  
  public String getDecideFallbackEndpoint()
  {
    return this.mDecideFallbackEndpoint;
  }
  
  public boolean getDisableAppOpenEvent()
  {
    return this.mDisableAppOpenEvent;
  }
  
  public boolean getDisableEmulatorBindingUI()
  {
    return this.mDisableEmulatorBindingUI;
  }
  
  public boolean getDisableFallback()
  {
    return this.mDisableFallback;
  }
  
  public boolean getDisableGestureBindingUI()
  {
    return this.mDisableGestureBindingUI;
  }
  
  public String getEditorUrl()
  {
    return this.mEditorUrl;
  }
  
  public String getEventsEndpoint()
  {
    return this.mEventsEndpoint;
  }
  
  public String getEventsFallbackEndpoint()
  {
    return this.mEventsFallbackEndpoint;
  }
  
  public int getFlushInterval()
  {
    return getFlushInterval(null);
  }
  
  public int getFlushInterval(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = paramContext.getApplicationInfo();
      i = paramContext.flags & 0x2;
      paramContext.flags = i;
      if (i == 0) {}
    }
    for (int i = 1; i != 0; i = 0) {
      return this.mDebugFlushInterval;
    }
    return this.mFlushInterval;
  }
  
  public int getMinimumDatabaseLimit()
  {
    return this.mMinimumDatabaseLimit;
  }
  
  public String getPeopleEndpoint()
  {
    return this.mPeopleEndpoint;
  }
  
  public String getPeopleFallbackEndpoint()
  {
    return this.mPeopleFallbackEndpoint;
  }
  
  public String getResourcePackageName()
  {
    return this.mResourcePackageName;
  }
  
  public SSLSocketFactory getSSLSocketFactory()
  {
    try
    {
      SSLSocketFactory localSSLSocketFactory = this.mSSLSocketFactory;
      return localSSLSocketFactory;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean getTestMode()
  {
    return this.mTestMode;
  }
  
  public void setSSLSocketFactory(SSLSocketFactory paramSSLSocketFactory)
  {
    try
    {
      this.mSSLSocketFactory = paramSSLSocketFactory;
      return;
    }
    finally
    {
      paramSSLSocketFactory = finally;
      throw paramSSLSocketFactory;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/mixpanel/android/mpmetrics/MPConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */