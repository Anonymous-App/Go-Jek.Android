package com.crashlytics.android.answers;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import java.util.Locale;
import java.util.Map;

class AnswersEventValidator
{
  boolean failFast;
  final int maxNumAttributes;
  final int maxStringLength;
  
  public AnswersEventValidator(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this.maxNumAttributes = paramInt1;
    this.maxStringLength = paramInt2;
    this.failFast = paramBoolean;
  }
  
  private void logOrThrowException(RuntimeException paramRuntimeException)
  {
    if (this.failFast) {
      throw paramRuntimeException;
    }
    Fabric.getLogger().e("Answers", "Invalid user input detected", paramRuntimeException);
  }
  
  public boolean isFullMap(Map<String, Object> paramMap, String paramString)
  {
    if ((paramMap.size() >= this.maxNumAttributes) && (!paramMap.containsKey(paramString)))
    {
      logOrThrowException(new IllegalArgumentException(String.format(Locale.US, "Limit of %d attributes reached, skipping attribute", new Object[] { Integer.valueOf(this.maxNumAttributes) })));
      return true;
    }
    return false;
  }
  
  public boolean isNull(Object paramObject, String paramString)
  {
    if (paramObject == null)
    {
      logOrThrowException(new NullPointerException(paramString + " must not be null"));
      return true;
    }
    return false;
  }
  
  public String limitStringLength(String paramString)
  {
    String str = paramString;
    if (paramString.length() > this.maxStringLength)
    {
      logOrThrowException(new IllegalArgumentException(String.format(Locale.US, "String is too long, truncating to %d characters", new Object[] { Integer.valueOf(this.maxStringLength) })));
      str = paramString.substring(0, this.maxStringLength);
    }
    return str;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/AnswersEventValidator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */