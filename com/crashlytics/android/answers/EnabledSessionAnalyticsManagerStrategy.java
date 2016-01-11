package com.crashlytics.android.answers;

import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.events.EnabledEventsStrategy;
import io.fabric.sdk.android.services.events.FilesSender;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;
import java.util.concurrent.ScheduledExecutorService;

class EnabledSessionAnalyticsManagerStrategy
  extends EnabledEventsStrategy<SessionEvent>
  implements SessionAnalyticsManagerStrategy<SessionEvent>
{
  ApiKey apiKey = new ApiKey();
  boolean customEventsEnabled = true;
  EventFilter eventFilter = new KeepAllEventFilter();
  FilesSender filesSender;
  private final HttpRequestFactory httpRequestFactory;
  private final Kit kit;
  final SessionEventMetadata metadata;
  boolean predefinedEventsEnabled = true;
  
  public EnabledSessionAnalyticsManagerStrategy(Kit paramKit, Context paramContext, ScheduledExecutorService paramScheduledExecutorService, SessionAnalyticsFilesManager paramSessionAnalyticsFilesManager, HttpRequestFactory paramHttpRequestFactory, SessionEventMetadata paramSessionEventMetadata)
  {
    super(paramContext, paramScheduledExecutorService, paramSessionAnalyticsFilesManager);
    this.kit = paramKit;
    this.httpRequestFactory = paramHttpRequestFactory;
    this.metadata = paramSessionEventMetadata;
  }
  
  public FilesSender getFilesSender()
  {
    return this.filesSender;
  }
  
  public void processEvent(SessionEvent.Builder paramBuilder)
  {
    paramBuilder = paramBuilder.build(this.metadata);
    if ((!this.customEventsEnabled) && (SessionEvent.Type.CUSTOM.equals(paramBuilder.type)))
    {
      Fabric.getLogger().d("Answers", "Custom events tracking disabled - skipping event: " + paramBuilder);
      return;
    }
    if ((!this.predefinedEventsEnabled) && (SessionEvent.Type.PREDEFINED.equals(paramBuilder.type)))
    {
      Fabric.getLogger().d("Answers", "Predefined events tracking disabled - skipping event: " + paramBuilder);
      return;
    }
    if (this.eventFilter.skipEvent(paramBuilder))
    {
      Fabric.getLogger().d("Answers", "Skipping filtered event: " + paramBuilder);
      return;
    }
    recordEvent(paramBuilder);
  }
  
  public void setAnalyticsSettingsData(AnalyticsSettingsData paramAnalyticsSettingsData, String paramString)
  {
    this.filesSender = AnswersRetryFilesSender.build(new SessionAnalyticsFilesSender(this.kit, paramString, paramAnalyticsSettingsData.analyticsURL, this.httpRequestFactory, this.apiKey.getValue(this.context)));
    ((SessionAnalyticsFilesManager)this.filesManager).setAnalyticsSettingsData(paramAnalyticsSettingsData);
    this.customEventsEnabled = paramAnalyticsSettingsData.trackCustomEvents;
    Logger localLogger = Fabric.getLogger();
    StringBuilder localStringBuilder = new StringBuilder().append("Custom event tracking ");
    if (this.customEventsEnabled)
    {
      paramString = "enabled";
      localLogger.d("Answers", paramString);
      this.predefinedEventsEnabled = paramAnalyticsSettingsData.trackPredefinedEvents;
      localLogger = Fabric.getLogger();
      localStringBuilder = new StringBuilder().append("Predefined event tracking ");
      if (!this.predefinedEventsEnabled) {
        break label205;
      }
    }
    label205:
    for (paramString = "enabled";; paramString = "disabled")
    {
      localLogger.d("Answers", paramString);
      if (paramAnalyticsSettingsData.samplingRate > 1)
      {
        Fabric.getLogger().d("Answers", "Event sampling enabled");
        this.eventFilter = new SamplingEventFilter(paramAnalyticsSettingsData.samplingRate);
      }
      configureRollover(paramAnalyticsSettingsData.flushIntervalSeconds);
      return;
      paramString = "disabled";
      break;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/EnabledSessionAnalyticsManagerStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */