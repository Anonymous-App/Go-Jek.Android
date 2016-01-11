package com.crashlytics.android.beta;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build.VERSION;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.cache.MemoryValueCache;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.DeviceIdentifierProvider;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.common.IdManager.DeviceIdentifierType;
import io.fabric.sdk.android.services.common.SystemCurrentTimeProvider;
import io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import io.fabric.sdk.android.services.settings.BetaSettingsData;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Beta
  extends Kit<Boolean>
  implements DeviceIdentifierProvider
{
  private static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
  private static final String CRASHLYTICS_BUILD_PROPERTIES = "crashlytics-build.properties";
  static final String NO_DEVICE_TOKEN = "";
  public static final String TAG = "Beta";
  private final MemoryValueCache<String> deviceTokenCache = new MemoryValueCache();
  private final DeviceTokenLoader deviceTokenLoader = new DeviceTokenLoader();
  private UpdatesController updatesController;
  
  private String getBetaDeviceToken(Context paramContext, String paramString)
  {
    if (isAppPossiblyInstalledByBeta(paramString, Build.VERSION.SDK_INT))
    {
      Fabric.getLogger().d("Beta", "App was possibly installed by Beta. Getting device token");
      try
      {
        paramContext = (String)this.deviceTokenCache.get(paramContext, this.deviceTokenLoader);
        boolean bool = "".equals(paramContext);
        if (bool) {
          return null;
        }
        return paramContext;
      }
      catch (Exception paramContext)
      {
        Fabric.getLogger().e("Beta", "Failed to load the Beta device token", paramContext);
        return null;
      }
    }
    Fabric.getLogger().d("Beta", "App was not installed by Beta. Skipping device token");
    return null;
  }
  
  private BetaSettingsData getBetaSettingsData()
  {
    SettingsData localSettingsData = Settings.getInstance().awaitSettingsData();
    if (localSettingsData != null) {
      return localSettingsData.betaSettingsData;
    }
    return null;
  }
  
  public static Beta getInstance()
  {
    return (Beta)Fabric.getKit(Beta.class);
  }
  
  private BuildProperties loadBuildProperties(Context paramContext)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    Object localObject4 = null;
    Object localObject5 = null;
    Object localObject1 = localObject4;
    for (;;)
    {
      try
      {
        localInputStream = paramContext.getAssets().open("crashlytics-build.properties");
        paramContext = (Context)localObject5;
        if (localInputStream != null)
        {
          localObject1 = localObject4;
          localObject2 = localInputStream;
          localObject3 = localInputStream;
          paramContext = BuildProperties.fromPropertiesStream(localInputStream);
          localObject1 = paramContext;
          localObject2 = localInputStream;
          localObject3 = localInputStream;
          Fabric.getLogger().d("Beta", paramContext.packageName + " build properties: " + paramContext.versionName + " (" + paramContext.versionCode + ")" + " - " + paramContext.buildId);
        }
        localObject3 = paramContext;
      }
      catch (Exception paramContext)
      {
        InputStream localInputStream;
        localObject3 = localObject2;
        Fabric.getLogger().e("Beta", "Error reading Beta build properties", paramContext);
        localObject3 = localIOException1;
        if (localObject2 == null) {
          continue;
        }
        try
        {
          ((InputStream)localObject2).close();
          return localIOException1;
        }
        catch (IOException paramContext)
        {
          Fabric.getLogger().e("Beta", "Error closing Beta build properties asset", paramContext);
          return localIOException1;
        }
      }
      finally
      {
        if (localObject3 == null) {
          break label218;
        }
      }
      try
      {
        localInputStream.close();
        localObject3 = paramContext;
        return (BuildProperties)localObject3;
      }
      catch (IOException localIOException1)
      {
        Fabric.getLogger().e("Beta", "Error closing Beta build properties asset", localIOException1);
        return paramContext;
      }
    }
    try
    {
      ((InputStream)localObject3).close();
      label218:
      throw paramContext;
    }
    catch (IOException localIOException2)
    {
      for (;;)
      {
        Fabric.getLogger().e("Beta", "Error closing Beta build properties asset", localIOException2);
      }
    }
  }
  
  boolean canCheckForUpdates(BetaSettingsData paramBetaSettingsData, BuildProperties paramBuildProperties)
  {
    return (paramBetaSettingsData != null) && (!TextUtils.isEmpty(paramBetaSettingsData.updateUrl)) && (paramBuildProperties != null);
  }
  
  @TargetApi(14)
  UpdatesController createUpdatesController(int paramInt, Application paramApplication)
  {
    if (paramInt >= 14) {
      return new ActivityLifecycleCheckForUpdatesController(getFabric().getActivityLifecycleManager(), getFabric().getExecutorService());
    }
    return new ImmediateCheckForUpdatesController();
  }
  
  protected Boolean doInBackground()
  {
    Fabric.getLogger().d("Beta", "Beta kit initializing...");
    Context localContext = getContext();
    IdManager localIdManager = getIdManager();
    if (TextUtils.isEmpty(getBetaDeviceToken(localContext, localIdManager.getInstallerPackageName())))
    {
      Fabric.getLogger().d("Beta", "A Beta device token was not found for this app");
      return Boolean.valueOf(false);
    }
    Fabric.getLogger().d("Beta", "Beta device token is present, checking for app updates.");
    BetaSettingsData localBetaSettingsData = getBetaSettingsData();
    BuildProperties localBuildProperties = loadBuildProperties(localContext);
    if (canCheckForUpdates(localBetaSettingsData, localBuildProperties)) {
      this.updatesController.initialize(localContext, this, localIdManager, localBetaSettingsData, localBuildProperties, new PreferenceStoreImpl(this), new SystemCurrentTimeProvider(), new DefaultHttpRequestFactory(Fabric.getLogger()));
    }
    return Boolean.valueOf(true);
  }
  
  public Map<IdManager.DeviceIdentifierType, String> getDeviceIdentifiers()
  {
    String str = getIdManager().getInstallerPackageName();
    str = getBetaDeviceToken(getContext(), str);
    HashMap localHashMap = new HashMap();
    if (!TextUtils.isEmpty(str)) {
      localHashMap.put(IdManager.DeviceIdentifierType.FONT_TOKEN, str);
    }
    return localHashMap;
  }
  
  public String getIdentifier()
  {
    return "com.crashlytics.sdk.android:beta";
  }
  
  String getOverridenSpiEndpoint()
  {
    return CommonUtils.getStringsFileValue(getContext(), "com.crashlytics.ApiEndpoint");
  }
  
  public String getVersion()
  {
    return "1.1.3.61";
  }
  
  @TargetApi(11)
  boolean isAppPossiblyInstalledByBeta(String paramString, int paramInt)
  {
    if (paramInt < 11) {
      return paramString == null;
    }
    return "io.crash.air".equals(paramString);
  }
  
  @TargetApi(14)
  protected boolean onPreExecute()
  {
    Application localApplication = (Application)getContext().getApplicationContext();
    this.updatesController = createUpdatesController(Build.VERSION.SDK_INT, localApplication);
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/beta/Beta.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */