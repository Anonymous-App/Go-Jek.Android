package com.crashlytics.android.answers;

import io.fabric.sdk.android.services.events.EventsStrategy;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;

abstract interface SessionAnalyticsManagerStrategy<T>
  extends EventsStrategy<T>
{
  public abstract void processEvent(SessionEvent.Builder paramBuilder);
  
  public abstract void setAnalyticsSettingsData(AnalyticsSettingsData paramAnalyticsSettingsData, String paramString);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/SessionAnalyticsManagerStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */