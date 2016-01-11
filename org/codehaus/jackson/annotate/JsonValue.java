package org.codehaus.jackson.annotate;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
@JacksonAnnotation
public @interface JsonValue
{
  boolean value() default true;
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/annotate/JsonValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */