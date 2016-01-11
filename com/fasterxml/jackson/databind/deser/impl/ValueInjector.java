package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.BeanProperty.Std;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.util.Annotations;
import java.io.IOException;

public class ValueInjector
  extends BeanProperty.Std
{
  protected final Object _valueId;
  
  public ValueInjector(PropertyName paramPropertyName, JavaType paramJavaType, Annotations paramAnnotations, AnnotatedMember paramAnnotatedMember, Object paramObject)
  {
    super(paramPropertyName, paramJavaType, null, paramAnnotations, paramAnnotatedMember, PropertyMetadata.STD_OPTIONAL);
    this._valueId = paramObject;
  }
  
  @Deprecated
  public ValueInjector(String paramString, JavaType paramJavaType, Annotations paramAnnotations, AnnotatedMember paramAnnotatedMember, Object paramObject)
  {
    this(new PropertyName(paramString), paramJavaType, paramAnnotations, paramAnnotatedMember, paramObject);
  }
  
  public Object findValue(DeserializationContext paramDeserializationContext, Object paramObject)
  {
    return paramDeserializationContext.findInjectableValue(this._valueId, this, paramObject);
  }
  
  public void inject(DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    this._member.setValue(paramObject, findValue(paramDeserializationContext, paramObject));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/deser/impl/ValueInjector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */