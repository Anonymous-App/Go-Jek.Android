package com.crashlytics.android.answers;

import android.content.Context;
import android.os.Looper;
import io.fabric.sdk.android.services.common.SystemCurrentTimeProvider;
import io.fabric.sdk.android.services.events.EventsStorage;
import io.fabric.sdk.android.services.events.GZIPQueueFileEventStorage;
import io.fabric.sdk.android.services.persistence.FileStore;
import java.io.File;
import java.io.IOException;

class AnswersFilesManagerProvider
{
  static final String SESSION_ANALYTICS_FILE_NAME = "session_analytics.tap";
  static final String SESSION_ANALYTICS_TO_SEND_DIR = "session_analytics_to_send";
  final Context context;
  final FileStore fileStore;
  
  public AnswersFilesManagerProvider(Context paramContext, FileStore paramFileStore)
  {
    this.context = paramContext;
    this.fileStore = paramFileStore;
  }
  
  public SessionAnalyticsFilesManager getAnalyticsFilesManager()
    throws IOException
  {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      throw new IllegalStateException("AnswersFilesManagerProvider cannot be called on the main thread");
    }
    SessionEventTransform localSessionEventTransform = new SessionEventTransform();
    SystemCurrentTimeProvider localSystemCurrentTimeProvider = new SystemCurrentTimeProvider();
    Object localObject = this.fileStore.getFilesDir();
    localObject = new GZIPQueueFileEventStorage(this.context, (File)localObject, "session_analytics.tap", "session_analytics_to_send");
    return new SessionAnalyticsFilesManager(this.context, localSessionEventTransform, localSystemCurrentTimeProvider, (EventsStorage)localObject);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/AnswersFilesManagerProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */