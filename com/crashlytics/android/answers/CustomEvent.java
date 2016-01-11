package com.crashlytics.android.answers;

public class CustomEvent
  extends AnswersEvent<CustomEvent>
{
  private final String eventName;
  
  public CustomEvent(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException("eventName must not be null");
    }
    this.eventName = this.validator.limitStringLength(paramString);
  }
  
  String getCustomType()
  {
    return this.eventName;
  }
  
  public String toString()
  {
    return "{eventName:\"" + this.eventName + '"' + ", customAttributes:" + this.customAttributes + "}";
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/CustomEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */