package com.crashlytics.android.answers;

import io.fabric.sdk.android.services.concurrency.internal.DefaultRetryPolicy;
import io.fabric.sdk.android.services.concurrency.internal.ExponentialBackoff;
import io.fabric.sdk.android.services.concurrency.internal.RetryState;
import io.fabric.sdk.android.services.events.FilesSender;
import java.io.File;
import java.util.List;

class AnswersRetryFilesSender
  implements FilesSender
{
  private static final int BACKOFF_MS = 1000;
  private static final int BACKOFF_POWER = 8;
  private static final double JITTER_PERCENT = 0.1D;
  private static final int MAX_RETRIES = 5;
  private final SessionAnalyticsFilesSender filesSender;
  private final RetryManager retryManager;
  
  AnswersRetryFilesSender(SessionAnalyticsFilesSender paramSessionAnalyticsFilesSender, RetryManager paramRetryManager)
  {
    this.filesSender = paramSessionAnalyticsFilesSender;
    this.retryManager = paramRetryManager;
  }
  
  public static AnswersRetryFilesSender build(SessionAnalyticsFilesSender paramSessionAnalyticsFilesSender)
  {
    return new AnswersRetryFilesSender(paramSessionAnalyticsFilesSender, new RetryManager(new RetryState(new RandomBackoff(new ExponentialBackoff(1000L, 8), 0.1D), new DefaultRetryPolicy(5))));
  }
  
  public boolean send(List<File> paramList)
  {
    boolean bool = false;
    long l = System.nanoTime();
    if (this.retryManager.canRetry(l))
    {
      if (this.filesSender.send(paramList))
      {
        this.retryManager.reset();
        bool = true;
      }
    }
    else {
      return bool;
    }
    this.retryManager.recordRetry(l);
    return false;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/AnswersRetryFilesSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */