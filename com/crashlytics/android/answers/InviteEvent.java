package com.crashlytics.android.answers;

public class InviteEvent
  extends PredefinedEvent<InviteEvent>
{
  static final String METHOD_ATTRIBUTE = "method";
  static final String TYPE = "invite";
  
  String getPredefinedType()
  {
    return "invite";
  }
  
  public InviteEvent putMethod(String paramString)
  {
    this.predefinedAttributes.put("method", paramString);
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/InviteEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */