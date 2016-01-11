package com.crashlytics.android.beta;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.settings.BetaSettingsData;
import java.util.concurrent.atomic.AtomicBoolean;

abstract class AbstractCheckForUpdatesController
  implements UpdatesController
{
  static final long LAST_UPDATE_CHECK_DEFAULT = 0L;
  static final String LAST_UPDATE_CHECK_KEY = "last_update_check";
  private static final long MILLIS_PER_SECOND = 1000L;
  private Beta beta;
  private BetaSettingsData betaSettings;
  private BuildProperties buildProps;
  private Context context;
  private CurrentTimeProvider currentTimeProvider;
  private final AtomicBoolean externallyReady;
  private HttpRequestFactory httpRequestFactory;
  private IdManager idManager;
  private final AtomicBoolean initialized = new AtomicBoolean();
  private long lastCheckTimeMillis = 0L;
  private PreferenceStore preferenceStore;
  
  public AbstractCheckForUpdatesController()
  {
    this(false);
  }
  
  public AbstractCheckForUpdatesController(boolean paramBoolean)
  {
    this.externallyReady = new AtomicBoolean(paramBoolean);
  }
  
  private void performUpdateCheck()
  {
    Fabric.getLogger().d("Beta", "Performing update check");
    String str1 = new ApiKey().getValue(this.context);
    String str2 = this.idManager.createIdHeaderValue(str1, this.buildProps.packageName);
    new CheckForUpdatesRequest(this.beta, this.beta.getOverridenSpiEndpoint(), this.betaSettings.updateUrl, this.httpRequestFactory, new CheckForUpdatesResponseTransform()).invoke(str1, str2, this.buildProps);
  }
  
  @SuppressLint({"CommitPrefEdits"})
  protected void checkForUpdates()
  {
    long l1;
    synchronized (this.preferenceStore)
    {
      if (this.preferenceStore.get().contains("last_update_check")) {
        this.preferenceStore.save(this.preferenceStore.edit().remove("last_update_check"));
      }
      l1 = this.currentTimeProvider.getCurrentTimeMillis();
      long l2 = this.betaSettings.updateSuspendDurationSeconds * 1000L;
      Fabric.getLogger().d("Beta", "Check for updates delay: " + l2);
      Fabric.getLogger().d("Beta", "Check for updates last check time: " + getLastCheckTimeMillis());
      l2 = getLastCheckTimeMillis() + l2;
      Fabric.getLogger().d("Beta", "Check for updates current time: " + l1 + ", next check time: " + l2);
      if (l1 < l2) {}
    }
    Fabric.getLogger().d("Beta", "Check for updates next check time was not passed");
  }
  
  long getLastCheckTimeMillis()
  {
    return this.lastCheckTimeMillis;
  }
  
  public void initialize(Context paramContext, Beta paramBeta, IdManager paramIdManager, BetaSettingsData paramBetaSettingsData, BuildProperties paramBuildProperties, PreferenceStore paramPreferenceStore, CurrentTimeProvider paramCurrentTimeProvider, HttpRequestFactory paramHttpRequestFactory)
  {
    this.context = paramContext;
    this.beta = paramBeta;
    this.idManager = paramIdManager;
    this.betaSettings = paramBetaSettingsData;
    this.buildProps = paramBuildProperties;
    this.preferenceStore = paramPreferenceStore;
    this.currentTimeProvider = paramCurrentTimeProvider;
    this.httpRequestFactory = paramHttpRequestFactory;
    if (signalInitialized()) {
      checkForUpdates();
    }
  }
  
  void setLastCheckTimeMillis(long paramLong)
  {
    this.lastCheckTimeMillis = paramLong;
  }
  
  protected boolean signalExternallyReady()
  {
    this.externallyReady.set(true);
    return this.initialized.get();
  }
  
  boolean signalInitialized()
  {
    this.initialized.set(true);
    return this.externallyReady.get();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/beta/AbstractCheckForUpdatesController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */