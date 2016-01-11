package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import java.io.IOException;
import java.util.Iterator;

@JacksonStdImpl
public class IteratorSerializer
  extends AsArraySerializerBase<Iterator<?>>
{
  public IteratorSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer)
  {
    super(Iterator.class, paramJavaType, paramBoolean, paramTypeSerializer, null);
  }
  
  public IteratorSerializer(IteratorSerializer paramIteratorSerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    super(paramIteratorSerializer, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new IteratorSerializer(this, this._property, paramTypeSerializer, this._elementSerializer, this._unwrapSingle);
  }
  
  public boolean hasSingleElement(Iterator<?> paramIterator)
  {
    return false;
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, Iterator<?> paramIterator)
  {
    return (paramIterator == null) || (!paramIterator.hasNext());
  }
  
  public final void serialize(Iterator<?> paramIterator, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (((this._unwrapSingle == null) && (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED))) || ((this._unwrapSingle == Boolean.TRUE) && (hasSingleElement(paramIterator))))
    {
      serializeContents(paramIterator, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    paramJsonGenerator.writeStartArray();
    serializeContents(paramIterator, paramJsonGenerator, paramSerializerProvider);
    paramJsonGenerator.writeEndArray();
  }
  
  public void serializeContents(Iterator<?> paramIterator, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    TypeSerializer localTypeSerializer;
    Object localObject1;
    Object localObject2;
    if (paramIterator.hasNext())
    {
      localTypeSerializer = this._valueTypeSerializer;
      localObject1 = null;
      localObject2 = null;
    }
    for (;;)
    {
      Object localObject6 = paramIterator.next();
      if (localObject6 == null) {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      }
      while (!paramIterator.hasNext())
      {
        return;
        JsonSerializer localJsonSerializer = this._elementSerializer;
        Object localObject3 = localJsonSerializer;
        Object localObject4 = localObject2;
        Object localObject5 = localObject1;
        if (localJsonSerializer == null)
        {
          localObject4 = localObject6.getClass();
          if (localObject4 != localObject2) {
            break label123;
          }
          localObject3 = localObject1;
          localObject5 = localObject1;
          localObject4 = localObject2;
        }
        for (;;)
        {
          if (localTypeSerializer != null) {
            break label142;
          }
          ((JsonSerializer)localObject3).serialize(localObject6, paramJsonGenerator, paramSerializerProvider);
          localObject2 = localObject4;
          localObject1 = localObject5;
          break;
          label123:
          localObject3 = paramSerializerProvider.findValueSerializer((Class)localObject4, this._property);
          localObject5 = localObject3;
        }
        label142:
        ((JsonSerializer)localObject3).serializeWithType(localObject6, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        localObject2 = localObject4;
        localObject1 = localObject5;
      }
    }
  }
  
  public IteratorSerializer withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    return new IteratorSerializer(this, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ser/impl/IteratorSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */