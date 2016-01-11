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
import java.util.List;

@JacksonStdImpl
public final class IndexedListSerializer
  extends AsArraySerializerBase<List<?>>
{
  private static final long serialVersionUID = 1L;
  
  public IndexedListSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
  {
    super(List.class, paramJavaType, paramBoolean, paramTypeSerializer, paramJsonSerializer);
  }
  
  public IndexedListSerializer(IndexedListSerializer paramIndexedListSerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    super(paramIndexedListSerializer, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new IndexedListSerializer(this, this._property, paramTypeSerializer, this._elementSerializer, this._unwrapSingle);
  }
  
  public boolean hasSingleElement(List<?> paramList)
  {
    return paramList.size() == 1;
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, List<?> paramList)
  {
    return (paramList == null) || (paramList.isEmpty());
  }
  
  public final void serialize(List<?> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    int i = paramList.size();
    if ((i == 1) && (((this._unwrapSingle == null) && (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED))) || (this._unwrapSingle == Boolean.TRUE)))
    {
      serializeContents(paramList, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    paramJsonGenerator.writeStartArray(i);
    serializeContents(paramList, paramJsonGenerator, paramSerializerProvider);
    paramJsonGenerator.writeEndArray();
  }
  
  public void serializeContents(List<?> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (this._elementSerializer != null) {
      serializeContentsUsing(paramList, paramJsonGenerator, paramSerializerProvider, this._elementSerializer);
    }
    int k;
    do
    {
      return;
      if (this._valueTypeSerializer != null)
      {
        serializeTypedContents(paramList, paramJsonGenerator, paramSerializerProvider);
        return;
      }
      k = paramList.size();
    } while (k == 0);
    int j = 0;
    int i = 0;
    for (;;)
    {
      Object localObject1;
      Class localClass;
      try
      {
        localObject2 = this._dynamicSerializers;
        if (i >= k) {
          break;
        }
        j = i;
        Object localObject4 = paramList.get(i);
        if (localObject4 == null)
        {
          j = i;
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          localObject1 = localObject2;
        }
        else
        {
          j = i;
          localClass = localObject4.getClass();
          j = i;
          JsonSerializer localJsonSerializer = ((PropertySerializerMap)localObject2).serializerFor(localClass);
          Object localObject3 = localJsonSerializer;
          localObject1 = localObject2;
          if (localJsonSerializer == null)
          {
            j = i;
            if (this._elementType.hasGenericTypes())
            {
              j = i;
              localObject2 = _findAndAddDynamic((PropertySerializerMap)localObject2, paramSerializerProvider.constructSpecializedType(this._elementType, localClass), paramSerializerProvider);
              j = i;
              localObject1 = this._dynamicSerializers;
              localObject3 = localObject2;
            }
          }
          else
          {
            j = i;
            ((JsonSerializer)localObject3).serialize(localObject4, paramJsonGenerator, paramSerializerProvider);
          }
        }
      }
      catch (Exception paramJsonGenerator)
      {
        wrapAndThrow(paramSerializerProvider, paramJsonGenerator, paramList, j);
        return;
      }
      j = i;
      Object localObject2 = _findAndAddDynamic((PropertySerializerMap)localObject2, localClass, paramSerializerProvider);
      continue;
      i += 1;
      localObject2 = localObject1;
    }
  }
  
  public void serializeContentsUsing(List<?> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException
  {
    int j = paramList.size();
    if (j == 0) {}
    for (;;)
    {
      return;
      TypeSerializer localTypeSerializer = this._valueTypeSerializer;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramList.get(i);
        if (localObject == null) {}
        try
        {
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        }
        catch (Exception localException)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramList, i);
        }
        if (localTypeSerializer == null) {
          paramJsonSerializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
        } else {
          paramJsonSerializer.serializeWithType(localException, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        }
        i += 1;
      }
    }
  }
  
  public void serializeTypedContents(List<?> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    int m = paramList.size();
    if (m == 0) {
      return;
    }
    int k = 0;
    int i = 0;
    int j = k;
    for (;;)
    {
      Object localObject1;
      Class localClass;
      try
      {
        TypeSerializer localTypeSerializer = this._valueTypeSerializer;
        j = k;
        localObject2 = this._dynamicSerializers;
        if (i >= m) {
          break;
        }
        j = i;
        Object localObject4 = paramList.get(i);
        if (localObject4 == null)
        {
          j = i;
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          localObject1 = localObject2;
        }
        else
        {
          j = i;
          localClass = localObject4.getClass();
          j = i;
          JsonSerializer localJsonSerializer = ((PropertySerializerMap)localObject2).serializerFor(localClass);
          Object localObject3 = localJsonSerializer;
          localObject1 = localObject2;
          if (localJsonSerializer == null)
          {
            j = i;
            if (this._elementType.hasGenericTypes())
            {
              j = i;
              localObject2 = _findAndAddDynamic((PropertySerializerMap)localObject2, paramSerializerProvider.constructSpecializedType(this._elementType, localClass), paramSerializerProvider);
              j = i;
              localObject1 = this._dynamicSerializers;
              localObject3 = localObject2;
            }
          }
          else
          {
            j = i;
            ((JsonSerializer)localObject3).serializeWithType(localObject4, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
          }
        }
      }
      catch (Exception paramJsonGenerator)
      {
        wrapAndThrow(paramSerializerProvider, paramJsonGenerator, paramList, j);
        return;
      }
      j = i;
      Object localObject2 = _findAndAddDynamic((PropertySerializerMap)localObject2, localClass, paramSerializerProvider);
      continue;
      i += 1;
      localObject2 = localObject1;
    }
  }
  
  public IndexedListSerializer withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    return new IndexedListSerializer(this, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ser/impl/IndexedListSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */