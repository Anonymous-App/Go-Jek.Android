package com.crashlytics.android.beta;

class ImmediateCheckForUpdatesController
  extends AbstractCheckForUpdatesController
{
  public ImmediateCheckForUpdatesController()
  {
    super(true);
  }
  
  public boolean isActivityLifecycleTriggered()
  {
    return false;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/beta/ImmediateCheckForUpdatesController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */