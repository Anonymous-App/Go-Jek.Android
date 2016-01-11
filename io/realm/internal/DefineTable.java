package io.realm.internal;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface DefineTable
{
  String query() default "";
  
  String row() default "";
  
  String table() default "";
  
  String view() default "";
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/realm/internal/DefineTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */