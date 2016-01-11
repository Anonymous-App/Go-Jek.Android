package com.crashlytics.android.beta;

import android.content.Context;
import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.settings.BetaSettingsData;

abstract interface UpdatesController
{
  public abstract void initialize(Context paramContext, Beta paramBeta, IdManager paramIdManager, BetaSettingsData paramBetaSettingsData, BuildProperties paramBuildProperties, PreferenceStore paramPreferenceStore, CurrentTimeProvider paramCurrentTimeProvider, HttpRequestFactory paramHttpRequestFactory);
  
  public abstract boolean isActivityLifecycleTriggered();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/beta/UpdatesController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */