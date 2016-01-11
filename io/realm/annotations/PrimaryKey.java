package io.realm.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface PrimaryKey {}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/realm/annotations/PrimaryKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */