package com.newrelic.com.google.gson.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface Expose
{
  boolean deserialize() default true;
  
  boolean serialize() default true;
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/newrelic/com/google/gson/annotations/Expose.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */