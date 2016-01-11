package com.fasterxml.jackson.databind.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.TYPE})
public @interface JsonNaming
{
  Class<? extends PropertyNamingStrategy> value() default PropertyNamingStrategy.class;
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/annotation/JsonNaming.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */