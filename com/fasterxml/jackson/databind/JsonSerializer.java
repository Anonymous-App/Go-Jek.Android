package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.util.EmptyIterator;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.Iterator;

public abstract class JsonSerializer<T>
  implements JsonFormatVisitable
{
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper != null) {
      paramJsonFormatVisitorWrapper.expectAnyFormat(paramJavaType);
    }
  }
  
  public JsonSerializer<?> getDelegatee()
  {
    return null;
  }
  
  public Class<T> handledType()
  {
    return null;
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, T paramT)
  {
    return paramT == null;
  }
  
  @Deprecated
  public boolean isEmpty(T paramT)
  {
    return isEmpty(null, paramT);
  }
  
  public boolean isUnwrappingSerializer()
  {
    return false;
  }
  
  public Iterator<PropertyWriter> properties()
  {
    return EmptyIterator.instance();
  }
  
  public JsonSerializer<T> replaceDelegatee(JsonSerializer<?> paramJsonSerializer)
  {
    throw new UnsupportedOperationException();
  }
  
  public abstract void serialize(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonProcessingException;
  
  public void serializeWithType(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    paramTypeSerializer = handledType();
    paramJsonGenerator = paramTypeSerializer;
    if (paramTypeSerializer == null) {
      paramJsonGenerator = paramT.getClass();
    }
    throw paramSerializerProvider.mappingException("Type id handling not implemented for type %s (by serializer of type %s)", new Object[] { paramJsonGenerator.getName(), getClass().getName() });
  }
  
  public JsonSerializer<T> unwrappingSerializer(NameTransformer paramNameTransformer)
  {
    return this;
  }
  
  public boolean usesObjectId()
  {
    return false;
  }
  
  public JsonSerializer<?> withFilterId(Object paramObject)
  {
    return this;
  }
  
  public static abstract class None
    extends JsonSerializer<Object>
  {}
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/JsonSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */