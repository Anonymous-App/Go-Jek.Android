package com.crashlytics.android.answers;

public class SignUpEvent
  extends PredefinedEvent<SignUpEvent>
{
  static final String METHOD_ATTRIBUTE = "method";
  static final String SUCCESS_ATTRIBUTE = "success";
  static final String TYPE = "signUp";
  
  String getPredefinedType()
  {
    return "signUp";
  }
  
  public SignUpEvent putMethod(String paramString)
  {
    this.predefinedAttributes.put("method", paramString);
    return this;
  }
  
  public SignUpEvent putSuccess(boolean paramBoolean)
  {
    this.predefinedAttributes.put("success", Boolean.toString(paramBoolean));
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/SignUpEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */