package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.lang.reflect.Type;

public class StdDelegatingSerializer
  extends StdSerializer<Object>
  implements ContextualSerializer, ResolvableSerializer, JsonFormatVisitable, SchemaAware
{
  protected final Converter<Object, ?> _converter;
  protected final JsonSerializer<Object> _delegateSerializer;
  protected final JavaType _delegateType;
  
  public StdDelegatingSerializer(Converter<?, ?> paramConverter)
  {
    super(Object.class);
    this._converter = paramConverter;
    this._delegateType = null;
    this._delegateSerializer = null;
  }
  
  public StdDelegatingSerializer(Converter<Object, ?> paramConverter, JavaType paramJavaType, JsonSerializer<?> paramJsonSerializer)
  {
    super(paramJavaType);
    this._converter = paramConverter;
    this._delegateType = paramJavaType;
    this._delegateSerializer = paramJsonSerializer;
  }
  
  public <T> StdDelegatingSerializer(Class<T> paramClass, Converter<T, ?> paramConverter)
  {
    super(paramClass, false);
    this._converter = paramConverter;
    this._delegateType = null;
    this._delegateSerializer = null;
  }
  
  protected JsonSerializer<Object> _findSerializer(Object paramObject, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    return paramSerializerProvider.findValueSerializer(paramObject.getClass());
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (this._delegateSerializer != null) {
      this._delegateSerializer.acceptJsonFormatVisitor(paramJsonFormatVisitorWrapper, paramJavaType);
    }
  }
  
  protected Object convertValue(Object paramObject)
  {
    return this._converter.convert(paramObject);
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer2 = this._delegateSerializer;
    JavaType localJavaType = this._delegateType;
    JsonSerializer localJsonSerializer1 = localJsonSerializer2;
    Object localObject2 = localJavaType;
    if (localJsonSerializer2 == null)
    {
      localObject1 = localJavaType;
      if (localJavaType == null) {
        localObject1 = this._converter.getOutputType(paramSerializerProvider.getTypeFactory());
      }
      localJsonSerializer1 = localJsonSerializer2;
      localObject2 = localObject1;
      if (!((JavaType)localObject1).isJavaLangObject())
      {
        localJsonSerializer1 = paramSerializerProvider.findValueSerializer((JavaType)localObject1);
        localObject2 = localObject1;
      }
    }
    Object localObject1 = localJsonSerializer1;
    if ((localJsonSerializer1 instanceof ContextualSerializer)) {
      localObject1 = paramSerializerProvider.handleSecondaryContextualization(localJsonSerializer1, paramBeanProperty);
    }
    if ((localObject1 == this._delegateSerializer) && (localObject2 == this._delegateType)) {
      return this;
    }
    return withDelegate(this._converter, (JavaType)localObject2, (JsonSerializer)localObject1);
  }
  
  protected Converter<Object, ?> getConverter()
  {
    return this._converter;
  }
  
  public JsonSerializer<?> getDelegatee()
  {
    return this._delegateSerializer;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    throws JsonMappingException
  {
    if ((this._delegateSerializer instanceof SchemaAware)) {
      return ((SchemaAware)this._delegateSerializer).getSchema(paramSerializerProvider, paramType);
    }
    return super.getSchema(paramSerializerProvider, paramType);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType, boolean paramBoolean)
    throws JsonMappingException
  {
    if ((this._delegateSerializer instanceof SchemaAware)) {
      return ((SchemaAware)this._delegateSerializer).getSchema(paramSerializerProvider, paramType, paramBoolean);
    }
    return super.getSchema(paramSerializerProvider, paramType);
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, Object paramObject)
  {
    Object localObject = convertValue(paramObject);
    if (this._delegateSerializer == null) {
      return paramObject == null;
    }
    return this._delegateSerializer.isEmpty(paramSerializerProvider, localObject);
  }
  
  @Deprecated
  public boolean isEmpty(Object paramObject)
  {
    return isEmpty(null, paramObject);
  }
  
  public void resolve(SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    if ((this._delegateSerializer != null) && ((this._delegateSerializer instanceof ResolvableSerializer))) {
      ((ResolvableSerializer)this._delegateSerializer).resolve(paramSerializerProvider);
    }
  }
  
  public void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    Object localObject = convertValue(paramObject);
    if (localObject == null)
    {
      paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      return;
    }
    JsonSerializer localJsonSerializer = this._delegateSerializer;
    paramObject = localJsonSerializer;
    if (localJsonSerializer == null) {
      paramObject = _findSerializer(localObject, paramSerializerProvider);
    }
    ((JsonSerializer)paramObject).serialize(localObject, paramJsonGenerator, paramSerializerProvider);
  }
  
  public void serializeWithType(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    Object localObject = convertValue(paramObject);
    JsonSerializer localJsonSerializer2 = this._delegateSerializer;
    JsonSerializer localJsonSerializer1 = localJsonSerializer2;
    if (localJsonSerializer2 == null) {
      localJsonSerializer1 = _findSerializer(paramObject, paramSerializerProvider);
    }
    localJsonSerializer1.serializeWithType(localObject, paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
  }
  
  protected StdDelegatingSerializer withDelegate(Converter<Object, ?> paramConverter, JavaType paramJavaType, JsonSerializer<?> paramJsonSerializer)
  {
    if (getClass() != StdDelegatingSerializer.class) {
      throw new IllegalStateException("Sub-class " + getClass().getName() + " must override 'withDelegate'");
    }
    return new StdDelegatingSerializer(paramConverter, paramJavaType, paramJsonSerializer);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ser/std/StdDelegatingSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */