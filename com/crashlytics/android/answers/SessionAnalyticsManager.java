package com.crashlytics.android.answers;

import android.app.Activity;
import android.os.Looper;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;

class SessionAnalyticsManager
{
  static final String ON_CRASH_ERROR_MSG = "onCrash called from main thread!!!";
  final AnswersEventsHandler eventsHandler;
  
  public SessionAnalyticsManager(AnswersEventsHandler paramAnswersEventsHandler)
  {
    this.eventsHandler = paramAnswersEventsHandler;
  }
  
  public void disable()
  {
    this.eventsHandler.disable();
  }
  
  public void onCrash(String paramString)
  {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      throw new IllegalStateException("onCrash called from main thread!!!");
    }
    Fabric.getLogger().d("Answers", "Logged crash");
    this.eventsHandler.processEventSync(SessionEvent.crashEventBuilder(paramString));
  }
  
  public void onCustom(CustomEvent paramCustomEvent)
  {
    Fabric.getLogger().d("Answers", "Logged custom event: " + paramCustomEvent);
    this.eventsHandler.processEventAsync(SessionEvent.customEventBuilder(paramCustomEvent));
  }
  
  public void onError(String paramString)
  {
    Fabric.getLogger().d("Answers", "Logged error");
    this.eventsHandler.processEventAsync(SessionEvent.errorEventBuilder(paramString));
  }
  
  public void onInstall()
  {
    Fabric.getLogger().d("Answers", "Logged install");
    this.eventsHandler.processEventAsyncAndFlush(SessionEvent.installEventBuilder());
  }
  
  public void onLifecycle(Activity paramActivity, SessionEvent.Type paramType)
  {
    Fabric.getLogger().d("Answers", "Logged lifecycle event: " + paramType.name());
    this.eventsHandler.processEventAsync(SessionEvent.lifecycleEventBuilder(paramType, paramActivity));
  }
  
  public void onPredefined(PredefinedEvent paramPredefinedEvent)
  {
    Fabric.getLogger().d("Answers", "Logged predefined event: " + paramPredefinedEvent);
    this.eventsHandler.processEventAsync(SessionEvent.predefinedEventBuilder(paramPredefinedEvent));
  }
  
  public void setAnalyticsSettingsData(AnalyticsSettingsData paramAnalyticsSettingsData, String paramString)
  {
    this.eventsHandler.setAnalyticsSettingsData(paramAnalyticsSettingsData, paramString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/SessionAnalyticsManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */