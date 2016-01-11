package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.lang.reflect.Type;
import java.util.Collection;

public abstract class StaticListSerializerBase<T extends Collection<?>>
  extends StdSerializer<T>
  implements ContextualSerializer
{
  protected final JsonSerializer<String> _serializer;
  protected final Boolean _unwrapSingle;
  
  protected StaticListSerializerBase(StaticListSerializerBase<?> paramStaticListSerializerBase, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    super(paramStaticListSerializerBase);
    this._serializer = paramJsonSerializer;
    this._unwrapSingle = paramBoolean;
  }
  
  protected StaticListSerializerBase(Class<?> paramClass)
  {
    super(paramClass, false);
    this._serializer = null;
    this._unwrapSingle = null;
  }
  
  public abstract JsonSerializer<?> _withResolved(BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean);
  
  protected abstract void acceptContentVisitor(JsonArrayFormatVisitor paramJsonArrayFormatVisitor)
    throws JsonMappingException;
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    acceptContentVisitor(paramJsonFormatVisitorWrapper.expectArrayFormat(paramJavaType));
  }
  
  protected abstract JsonNode contentSchema();
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject2 = null;
    JsonFormat.Value localValue = null;
    Object localObject4 = null;
    Object localObject3 = localObject4;
    if (paramBeanProperty != null)
    {
      localObject2 = paramSerializerProvider.getAnnotationIntrospector();
      localObject3 = paramBeanProperty.getMember();
      localObject1 = localValue;
      if (localObject3 != null)
      {
        Object localObject5 = ((AnnotationIntrospector)localObject2).findContentSerializer((Annotated)localObject3);
        localObject1 = localValue;
        if (localObject5 != null) {
          localObject1 = paramSerializerProvider.serializerInstance((Annotated)localObject3, localObject5);
        }
      }
      localValue = paramBeanProperty.findFormatOverrides((AnnotationIntrospector)localObject2);
      localObject2 = localObject1;
      localObject3 = localObject4;
      if (localValue != null)
      {
        localObject3 = localValue.getFeature(JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
        localObject2 = localObject1;
      }
    }
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = this._serializer;
    }
    localObject1 = findConvertingContentSerializer(paramSerializerProvider, paramBeanProperty, (JsonSerializer)localObject1);
    if (localObject1 == null) {}
    for (paramSerializerProvider = paramSerializerProvider.findValueSerializer(String.class, paramBeanProperty);; paramSerializerProvider = paramSerializerProvider.handleSecondaryContextualization((JsonSerializer)localObject1, paramBeanProperty))
    {
      localObject1 = paramSerializerProvider;
      if (isDefaultSerializer(paramSerializerProvider)) {
        localObject1 = null;
      }
      if ((localObject1 != this._serializer) || (localObject3 != this._unwrapSingle)) {
        break;
      }
      return this;
    }
    return _withResolved(paramBeanProperty, (JsonSerializer)localObject1, (Boolean)localObject3);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    return createSchemaNode("array", true).set("items", contentSchema());
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, T paramT)
  {
    return (paramT == null) || (paramT.size() == 0);
  }
  
  @Deprecated
  public boolean isEmpty(T paramT)
  {
    return isEmpty(null, paramT);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ser/std/StaticListSerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */