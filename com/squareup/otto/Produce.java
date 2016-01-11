package com.squareup.otto;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface Produce {}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/squareup/otto/Produce.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */