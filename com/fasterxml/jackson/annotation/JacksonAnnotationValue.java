package com.fasterxml.jackson.annotation;

import java.lang.annotation.Annotation;

public abstract interface JacksonAnnotationValue<A extends Annotation>
{
  public abstract Class<A> valueFor();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/annotation/JacksonAnnotationValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */