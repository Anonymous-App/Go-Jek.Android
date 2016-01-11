package io.realm.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface RealmClass {}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/realm/annotations/RealmClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */