package com.fasterxml.jackson.databind.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.TYPE})
public @interface JsonPOJOBuilder
{
  String buildMethodName() default "build";
  
  String withPrefix() default "with";
  
  public static class Value
  {
    public final String buildMethodName;
    public final String withPrefix;
    
    public Value(JsonPOJOBuilder paramJsonPOJOBuilder)
    {
      this.buildMethodName = paramJsonPOJOBuilder.buildMethodName();
      this.withPrefix = paramJsonPOJOBuilder.withPrefix();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/annotation/JsonPOJOBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */