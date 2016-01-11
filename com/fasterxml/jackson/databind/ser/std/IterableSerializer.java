package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.util.Iterator;

@JacksonStdImpl
public class IterableSerializer
  extends AsArraySerializerBase<Iterable<?>>
{
  public IterableSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer)
  {
    super(Iterable.class, paramJavaType, paramBoolean, paramTypeSerializer, null);
  }
  
  public IterableSerializer(IterableSerializer paramIterableSerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    super(paramIterableSerializer, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new IterableSerializer(this, this._property, paramTypeSerializer, this._elementSerializer, this._unwrapSingle);
  }
  
  public boolean hasSingleElement(Iterable<?> paramIterable)
  {
    if (paramIterable != null)
    {
      paramIterable = paramIterable.iterator();
      if (paramIterable.hasNext())
      {
        paramIterable.next();
        if (!paramIterable.hasNext()) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, Iterable<?> paramIterable)
  {
    return (paramIterable == null) || (!paramIterable.iterator().hasNext());
  }
  
  public final void serialize(Iterable<?> paramIterable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (((this._unwrapSingle == null) && (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED))) || ((this._unwrapSingle == Boolean.TRUE) && (hasSingleElement(paramIterable))))
    {
      serializeContents(paramIterable, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    paramJsonGenerator.writeStartArray();
    serializeContents(paramIterable, paramJsonGenerator, paramSerializerProvider);
    paramJsonGenerator.writeEndArray();
  }
  
  public void serializeContents(Iterable<?> paramIterable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    Iterator localIterator = paramIterable.iterator();
    TypeSerializer localTypeSerializer;
    Object localObject1;
    if (localIterator.hasNext())
    {
      localTypeSerializer = this._valueTypeSerializer;
      paramIterable = null;
      localObject1 = null;
    }
    for (;;)
    {
      Object localObject5 = localIterator.next();
      if (localObject5 == null) {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      }
      while (!localIterator.hasNext())
      {
        return;
        JsonSerializer localJsonSerializer = this._elementSerializer;
        Object localObject2 = localJsonSerializer;
        Object localObject3 = localObject1;
        Object localObject4 = paramIterable;
        if (localJsonSerializer == null)
        {
          localObject3 = localObject5.getClass();
          if (localObject3 != localObject1) {
            break label129;
          }
          localObject2 = paramIterable;
          localObject4 = paramIterable;
          localObject3 = localObject1;
        }
        for (;;)
        {
          if (localTypeSerializer != null) {
            break label148;
          }
          ((JsonSerializer)localObject2).serialize(localObject5, paramJsonGenerator, paramSerializerProvider);
          localObject1 = localObject3;
          paramIterable = (Iterable<?>)localObject4;
          break;
          label129:
          localObject2 = paramSerializerProvider.findValueSerializer((Class)localObject3, this._property);
          localObject4 = localObject2;
        }
        label148:
        ((JsonSerializer)localObject2).serializeWithType(localObject5, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        localObject1 = localObject3;
        paramIterable = (Iterable<?>)localObject4;
      }
    }
  }
  
  public IterableSerializer withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    return new IterableSerializer(this, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ser/std/IterableSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */