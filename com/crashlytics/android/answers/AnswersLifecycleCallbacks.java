package com.crashlytics.android.answers;

import android.app.Activity;
import android.os.Bundle;
import io.fabric.sdk.android.ActivityLifecycleManager.Callbacks;

class AnswersLifecycleCallbacks
  extends ActivityLifecycleManager.Callbacks
{
  private final SessionAnalyticsManager analyticsManager;
  
  public AnswersLifecycleCallbacks(SessionAnalyticsManager paramSessionAnalyticsManager)
  {
    this.analyticsManager = paramSessionAnalyticsManager;
  }
  
  public void onActivityCreated(Activity paramActivity, Bundle paramBundle)
  {
    this.analyticsManager.onLifecycle(paramActivity, SessionEvent.Type.CREATE);
  }
  
  public void onActivityDestroyed(Activity paramActivity)
  {
    this.analyticsManager.onLifecycle(paramActivity, SessionEvent.Type.DESTROY);
  }
  
  public void onActivityPaused(Activity paramActivity)
  {
    this.analyticsManager.onLifecycle(paramActivity, SessionEvent.Type.PAUSE);
  }
  
  public void onActivityResumed(Activity paramActivity)
  {
    this.analyticsManager.onLifecycle(paramActivity, SessionEvent.Type.RESUME);
  }
  
  public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle)
  {
    this.analyticsManager.onLifecycle(paramActivity, SessionEvent.Type.SAVE_INSTANCE_STATE);
  }
  
  public void onActivityStarted(Activity paramActivity)
  {
    this.analyticsManager.onLifecycle(paramActivity, SessionEvent.Type.START);
  }
  
  public void onActivityStopped(Activity paramActivity)
  {
    this.analyticsManager.onLifecycle(paramActivity, SessionEvent.Type.STOP);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/AnswersLifecycleCallbacks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */