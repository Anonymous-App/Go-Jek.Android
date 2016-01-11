package com.newrelic.agent.android.instrumentation;

import java.lang.annotation.Annotation;

public @interface ReplaceCallSite
{
  boolean isStatic() default false;
  
  String scope() default "";
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/newrelic/agent/android/instrumentation/ReplaceCallSite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */