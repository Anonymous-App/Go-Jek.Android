package org.codehaus.jackson.map.annotate;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.codehaus.jackson.annotate.JacksonAnnotation;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonDeserializer.None;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.KeyDeserializer.None;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.PARAMETER})
@JacksonAnnotation
public @interface JsonDeserialize
{
  Class<?> as() default NoClass.class;
  
  Class<?> contentAs() default NoClass.class;
  
  Class<? extends JsonDeserializer<?>> contentUsing() default JsonDeserializer.None.class;
  
  Class<?> keyAs() default NoClass.class;
  
  Class<? extends KeyDeserializer> keyUsing() default KeyDeserializer.None.class;
  
  Class<? extends JsonDeserializer<?>> using() default JsonDeserializer.None.class;
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/annotate/JsonDeserialize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */