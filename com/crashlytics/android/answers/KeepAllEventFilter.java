package com.crashlytics.android.answers;

class KeepAllEventFilter
  implements EventFilter
{
  public boolean skipEvent(SessionEvent paramSessionEvent)
  {
    return false;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/KeepAllEventFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */