package com.crashlytics.android.answers;

import io.fabric.sdk.android.services.concurrency.internal.RetryState;

class RetryManager
{
  private static final long NANOSECONDS_IN_MS = 1000000L;
  long lastRetry;
  private RetryState retryState;
  
  public RetryManager(RetryState paramRetryState)
  {
    if (paramRetryState == null) {
      throw new NullPointerException("retryState must not be null");
    }
    this.retryState = paramRetryState;
  }
  
  public boolean canRetry(long paramLong)
  {
    long l = this.retryState.getRetryDelay();
    return paramLong - this.lastRetry >= 1000000L * l;
  }
  
  public void recordRetry(long paramLong)
  {
    this.lastRetry = paramLong;
    this.retryState = this.retryState.nextRetryState();
  }
  
  public void reset()
  {
    this.lastRetry = 0L;
    this.retryState = this.retryState.initialRetryState();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/RetryManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */