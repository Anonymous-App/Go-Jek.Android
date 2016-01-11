package com.crashlytics.android.answers;

import io.fabric.sdk.android.Fabric;
import java.util.Map;

public abstract class AnswersEvent<T extends AnswersEvent>
{
  public static final int MAX_NUM_ATTRIBUTES = 20;
  public static final int MAX_STRING_LENGTH = 100;
  final AnswersAttributes customAttributes = new AnswersAttributes(this.validator);
  final AnswersEventValidator validator = new AnswersEventValidator(20, 100, Fabric.isDebuggable());
  
  Map<String, Object> getCustomAttributes()
  {
    return this.customAttributes.attributes;
  }
  
  public T putCustomAttribute(String paramString, Number paramNumber)
  {
    this.customAttributes.put(paramString, paramNumber);
    return this;
  }
  
  public T putCustomAttribute(String paramString1, String paramString2)
  {
    this.customAttributes.put(paramString1, paramString2);
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/AnswersEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */