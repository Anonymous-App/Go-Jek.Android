package com.crashlytics.android.answers;

import java.util.Map;

public abstract class PredefinedEvent<T extends PredefinedEvent>
  extends AnswersEvent<T>
{
  final AnswersAttributes predefinedAttributes = new AnswersAttributes(this.validator);
  
  Map<String, Object> getPredefinedAttributes()
  {
    return this.predefinedAttributes.attributes;
  }
  
  abstract String getPredefinedType();
  
  public String toString()
  {
    return "{type:\"" + getPredefinedType() + '"' + ", predefinedAttributes:" + this.predefinedAttributes + ", customAttributes:" + this.customAttributes + "}";
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/PredefinedEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */