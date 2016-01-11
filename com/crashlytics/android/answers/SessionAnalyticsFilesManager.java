package com.crashlytics.android.answers;

import android.content.Context;
import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import io.fabric.sdk.android.services.events.EventsStorage;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;
import java.io.IOException;
import java.util.UUID;

class SessionAnalyticsFilesManager
  extends EventsFilesManager<SessionEvent>
{
  private static final String SESSION_ANALYTICS_TO_SEND_FILE_EXTENSION = ".tap";
  private static final String SESSION_ANALYTICS_TO_SEND_FILE_PREFIX = "sa";
  private AnalyticsSettingsData analyticsSettingsData;
  
  SessionAnalyticsFilesManager(Context paramContext, SessionEventTransform paramSessionEventTransform, CurrentTimeProvider paramCurrentTimeProvider, EventsStorage paramEventsStorage)
    throws IOException
  {
    super(paramContext, paramSessionEventTransform, paramCurrentTimeProvider, paramEventsStorage, 100);
  }
  
  protected String generateUniqueRollOverFileName()
  {
    UUID localUUID = UUID.randomUUID();
    return "sa" + "_" + localUUID.toString() + "_" + this.currentTimeProvider.getCurrentTimeMillis() + ".tap";
  }
  
  protected int getMaxByteSizePerFile()
  {
    if (this.analyticsSettingsData == null) {
      return super.getMaxByteSizePerFile();
    }
    return this.analyticsSettingsData.maxByteSizePerFile;
  }
  
  protected int getMaxFilesToKeep()
  {
    if (this.analyticsSettingsData == null) {
      return super.getMaxFilesToKeep();
    }
    return this.analyticsSettingsData.maxPendingSendFileCount;
  }
  
  void setAnalyticsSettingsData(AnalyticsSettingsData paramAnalyticsSettingsData)
  {
    this.analyticsSettingsData = paramAnalyticsSettingsData;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/SessionAnalyticsFilesManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */