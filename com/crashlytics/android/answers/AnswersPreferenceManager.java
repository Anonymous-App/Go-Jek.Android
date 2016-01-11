package com.crashlytics.android.answers;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import io.fabric.sdk.android.services.persistence.PreferenceStore;

class AnswersPreferenceManager
{
  static final String PREFKEY_ANALYTICS_LAUNCHED = "analytics_launched";
  private final PreferenceStore prefStore;
  
  public AnswersPreferenceManager(PreferenceStore paramPreferenceStore)
  {
    this.prefStore = paramPreferenceStore;
  }
  
  @SuppressLint({"CommitPrefEdits"})
  public boolean hasAnalyticsLaunched()
  {
    return this.prefStore.get().getBoolean("analytics_launched", false);
  }
  
  @SuppressLint({"CommitPrefEdits"})
  public void setAnalyticsLaunched()
  {
    this.prefStore.save(this.prefStore.edit().putBoolean("analytics_launched", true));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/AnswersPreferenceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */