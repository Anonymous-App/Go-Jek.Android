package com.newrelic.agent.android.instrumentation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.METHOD})
public @interface WrapReturn
{
  String className();
  
  String methodDesc();
  
  String methodName();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/newrelic/agent/android/instrumentation/WrapReturn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */