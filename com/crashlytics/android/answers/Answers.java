package com.crashlytics.android.answers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import io.fabric.sdk.android.ActivityLifecycleManager;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.Crash.FatalException;
import io.fabric.sdk.android.services.common.Crash.LoggedException;
import io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import io.fabric.sdk.android.services.persistence.FileStoreImpl;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import io.fabric.sdk.android.services.settings.FeaturesSettingsData;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.io.File;

public class Answers
  extends Kit<Boolean>
{
  static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
  static final long FIRST_LAUNCH_INTERVAL_IN_MS = 3600000L;
  static final String PREF_STORE_NAME = "settings";
  public static final String TAG = "Answers";
  SessionAnalyticsManager analyticsManager;
  private long installedAt;
  ActivityLifecycleManager lifecycleManager;
  AnswersPreferenceManager preferenceManager;
  private String versionCode;
  private String versionName;
  
  public static Answers getInstance()
  {
    return (Answers)Fabric.getKit(Answers.class);
  }
  
  private void initializeSessionAnalytics(Context paramContext)
  {
    try
    {
      SessionMetadataCollector localSessionMetadataCollector = new SessionMetadataCollector(paramContext, getIdManager(), this.versionCode, this.versionName);
      paramContext = new AnswersEventsHandler(this, paramContext, new AnswersFilesManagerProvider(paramContext, new FileStoreImpl(this)), localSessionMetadataCollector, new DefaultHttpRequestFactory(Fabric.getLogger()));
      paramContext.enable();
      this.analyticsManager = new SessionAnalyticsManager(paramContext);
      this.lifecycleManager.registerCallbacks(new AnswersLifecycleCallbacks(this.analyticsManager));
      if (isFirstLaunch(this.installedAt))
      {
        Fabric.getLogger().d("Answers", "New app install detected");
        this.analyticsManager.onInstall();
        this.preferenceManager.setAnalyticsLaunched();
      }
      return;
    }
    catch (Exception paramContext)
    {
      Fabric.getLogger().e("Answers", "Failed to initialize", paramContext);
    }
  }
  
  protected Boolean doInBackground()
  {
    try
    {
      SettingsData localSettingsData = Settings.getInstance().awaitSettingsData();
      if (localSettingsData == null)
      {
        Fabric.getLogger().e("Answers", "Failed to retrieve settings");
        return Boolean.valueOf(false);
      }
      if (localSettingsData.featuresData.collectAnalytics)
      {
        Fabric.getLogger().d("Answers", "Analytics collection enabled");
        this.analyticsManager.setAnalyticsSettingsData(localSettingsData.analyticsSettingsData, getOverridenSpiEndpoint());
        return Boolean.valueOf(true);
      }
      Fabric.getLogger().d("Answers", "Analytics collection disabled");
      this.lifecycleManager.resetCallbacks();
      this.analyticsManager.disable();
      return Boolean.valueOf(false);
    }
    catch (Exception localException)
    {
      Fabric.getLogger().e("Answers", "Error dealing with settings", localException);
    }
    return Boolean.valueOf(false);
  }
  
  public String getIdentifier()
  {
    return "com.crashlytics.sdk.android:answers";
  }
  
  String getOverridenSpiEndpoint()
  {
    return CommonUtils.getStringsFileValue(getContext(), "com.crashlytics.ApiEndpoint");
  }
  
  public String getVersion()
  {
    return "1.3.2.79";
  }
  
  boolean installedRecently(long paramLong)
  {
    return System.currentTimeMillis() - paramLong < 3600000L;
  }
  
  boolean isFirstLaunch(long paramLong)
  {
    return (!this.preferenceManager.hasAnalyticsLaunched()) && (installedRecently(paramLong));
  }
  
  public void logAddToCart(AddToCartEvent paramAddToCartEvent)
  {
    if (paramAddToCartEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramAddToCartEvent);
    }
  }
  
  public void logContentView(ContentViewEvent paramContentViewEvent)
  {
    if (paramContentViewEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramContentViewEvent);
    }
  }
  
  public void logCustom(CustomEvent paramCustomEvent)
  {
    if (paramCustomEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onCustom(paramCustomEvent);
    }
  }
  
  public void logInvite(InviteEvent paramInviteEvent)
  {
    if (paramInviteEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramInviteEvent);
    }
  }
  
  public void logLevelEnd(LevelEndEvent paramLevelEndEvent)
  {
    if (paramLevelEndEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramLevelEndEvent);
    }
  }
  
  public void logLevelStart(LevelStartEvent paramLevelStartEvent)
  {
    if (paramLevelStartEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramLevelStartEvent);
    }
  }
  
  public void logLogin(LoginEvent paramLoginEvent)
  {
    if (paramLoginEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramLoginEvent);
    }
  }
  
  public void logPurchase(PurchaseEvent paramPurchaseEvent)
  {
    if (paramPurchaseEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramPurchaseEvent);
    }
  }
  
  public void logRating(RatingEvent paramRatingEvent)
  {
    if (paramRatingEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramRatingEvent);
    }
  }
  
  public void logSearch(SearchEvent paramSearchEvent)
  {
    if (paramSearchEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramSearchEvent);
    }
  }
  
  public void logShare(ShareEvent paramShareEvent)
  {
    if (paramShareEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramShareEvent);
    }
  }
  
  public void logSignUp(SignUpEvent paramSignUpEvent)
  {
    if (paramSignUpEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramSignUpEvent);
    }
  }
  
  public void logStartCheckout(StartCheckoutEvent paramStartCheckoutEvent)
  {
    if (paramStartCheckoutEvent == null) {
      throw new NullPointerException("event must not be null");
    }
    if (this.analyticsManager != null) {
      this.analyticsManager.onPredefined(paramStartCheckoutEvent);
    }
  }
  
  public void onException(Crash.FatalException paramFatalException)
  {
    if (this.analyticsManager != null) {
      this.analyticsManager.onCrash(paramFatalException.getSessionId());
    }
  }
  
  public void onException(Crash.LoggedException paramLoggedException)
  {
    if (this.analyticsManager != null) {
      this.analyticsManager.onError(paramLoggedException.getSessionId());
    }
  }
  
  @SuppressLint({"NewApi"})
  protected boolean onPreExecute()
  {
    try
    {
      Context localContext = getContext();
      this.preferenceManager = new AnswersPreferenceManager(new PreferenceStoreImpl(localContext, "settings"));
      this.lifecycleManager = new ActivityLifecycleManager(localContext);
      PackageInfo localPackageInfo = localContext.getPackageManager().getPackageInfo(localContext.getPackageName(), 0);
      this.versionCode = Integer.toString(localPackageInfo.versionCode);
      String str;
      if (localPackageInfo.versionName == null)
      {
        str = "0.0";
        this.versionName = str;
        if (Build.VERSION.SDK_INT < 9) {
          break label109;
        }
      }
      label109:
      for (this.installedAt = localPackageInfo.firstInstallTime;; this.installedAt = new File(localContext.getPackageManager().getApplicationInfo(localContext.getPackageName(), 0).sourceDir).lastModified())
      {
        initializeSessionAnalytics(localContext);
        return true;
        str = localPackageInfo.versionName;
        break;
      }
      return false;
    }
    catch (Exception localException)
    {
      Fabric.getLogger().e("Answers", "Error retrieving app properties", localException);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/Answers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */