package com.crashlytics.android.answers;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class AnswersAttributes
{
  final Map<String, Object> attributes = new HashMap();
  final AnswersEventValidator validator;
  
  public AnswersAttributes(AnswersEventValidator paramAnswersEventValidator)
  {
    this.validator = paramAnswersEventValidator;
  }
  
  void put(String paramString, Number paramNumber)
  {
    if ((this.validator.isNull(paramString, "key")) || (this.validator.isNull(paramNumber, "value"))) {
      return;
    }
    putAttribute(this.validator.limitStringLength(paramString), paramNumber);
  }
  
  void put(String paramString1, String paramString2)
  {
    if ((this.validator.isNull(paramString1, "key")) || (this.validator.isNull(paramString2, "value"))) {
      return;
    }
    putAttribute(this.validator.limitStringLength(paramString1), this.validator.limitStringLength(paramString2));
  }
  
  void putAttribute(String paramString, Object paramObject)
  {
    if (!this.validator.isFullMap(this.attributes, paramString)) {
      this.attributes.put(paramString, paramObject);
    }
  }
  
  public String toString()
  {
    JSONObject localJSONObject = new JSONObject(this.attributes);
    if (!(localJSONObject instanceof JSONObject)) {
      return localJSONObject.toString();
    }
    return JSONObjectInstrumentation.toString((JSONObject)localJSONObject);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/AnswersAttributes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */