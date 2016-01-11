package com.crashlytics.android.answers;

public class LevelStartEvent
  extends PredefinedEvent<LevelStartEvent>
{
  static final String LEVEL_NAME_ATTRIBUTE = "levelName";
  static final String TYPE = "levelStart";
  
  String getPredefinedType()
  {
    return "levelStart";
  }
  
  public LevelStartEvent putLevelName(String paramString)
  {
    this.predefinedAttributes.put("levelName", paramString);
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/LevelStartEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */