package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class CollectionSerializer
  extends AsArraySerializerBase<Collection<?>>
{
  private static final long serialVersionUID = 1L;
  
  @Deprecated
  public CollectionSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty, JsonSerializer<Object> paramJsonSerializer)
  {
    this(paramJavaType, paramBoolean, paramTypeSerializer, paramJsonSerializer);
  }
  
  public CollectionSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
  {
    super(Collection.class, paramJavaType, paramBoolean, paramTypeSerializer, paramJsonSerializer);
  }
  
  public CollectionSerializer(CollectionSerializer paramCollectionSerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    super(paramCollectionSerializer, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new CollectionSerializer(this, this._property, paramTypeSerializer, this._elementSerializer, this._unwrapSingle);
  }
  
  public boolean hasSingleElement(Collection<?> paramCollection)
  {
    paramCollection = paramCollection.iterator();
    if (!paramCollection.hasNext()) {}
    do
    {
      return false;
      paramCollection.next();
    } while (paramCollection.hasNext());
    return true;
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, Collection<?> paramCollection)
  {
    return (paramCollection == null) || (paramCollection.isEmpty());
  }
  
  public final void serialize(Collection<?> paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    int i = paramCollection.size();
    if ((i == 1) && (((this._unwrapSingle == null) && (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED))) || (this._unwrapSingle == Boolean.TRUE)))
    {
      serializeContents(paramCollection, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    paramJsonGenerator.writeStartArray(i);
    serializeContents(paramCollection, paramJsonGenerator, paramSerializerProvider);
    paramJsonGenerator.writeEndArray();
  }
  
  public void serializeContents(Collection<?> paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (this._elementSerializer != null) {
      serializeContentsUsing(paramCollection, paramJsonGenerator, paramSerializerProvider, this._elementSerializer);
    }
    Iterator localIterator;
    do
    {
      return;
      localIterator = paramCollection.iterator();
    } while (!localIterator.hasNext());
    Object localObject2 = this._dynamicSerializers;
    TypeSerializer localTypeSerializer = this._valueTypeSerializer;
    int i = 0;
    int j = i;
    for (;;)
    {
      Object localObject4;
      Class localClass;
      Object localObject3;
      try
      {
        localObject4 = localIterator.next();
        if (localObject4 == null)
        {
          j = i;
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          localObject1 = localObject2;
          j = i + 1;
          i = j;
          localObject2 = localObject1;
          if (localIterator.hasNext()) {
            break;
          }
          return;
        }
        j = i;
        localClass = localObject4.getClass();
        j = i;
        JsonSerializer localJsonSerializer = ((PropertySerializerMap)localObject2).serializerFor(localClass);
        localObject3 = localJsonSerializer;
        Object localObject1 = localObject2;
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
          if (localTypeSerializer != null) {
            break label246;
          }
          j = i;
          ((JsonSerializer)localObject3).serialize(localObject4, paramJsonGenerator, paramSerializerProvider);
          continue;
        }
        j = i;
      }
      catch (Exception paramJsonGenerator)
      {
        wrapAndThrow(paramSerializerProvider, paramJsonGenerator, paramCollection, j);
        return;
      }
      localObject2 = _findAndAddDynamic((PropertySerializerMap)localObject2, localClass, paramSerializerProvider);
      continue;
      label246:
      j = i;
      ((JsonSerializer)localObject3).serializeWithType(localObject4, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
    }
  }
  
  public void serializeContentsUsing(Collection<?> paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException, JsonGenerationException
  {
    Iterator localIterator = paramCollection.iterator();
    TypeSerializer localTypeSerializer;
    int i;
    if (localIterator.hasNext())
    {
      localTypeSerializer = this._valueTypeSerializer;
      i = 0;
    }
    for (;;)
    {
      Object localObject = localIterator.next();
      if (localObject == null) {}
      try
      {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        for (;;)
        {
          i += 1;
          if (localIterator.hasNext()) {
            break;
          }
          return;
          if (localTypeSerializer != null) {
            break label95;
          }
          paramJsonSerializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramCollection, i);
          continue;
          label95:
          paramJsonSerializer.serializeWithType(localException, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        }
      }
    }
  }
  
  public CollectionSerializer withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    return new CollectionSerializer(this, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ser/std/CollectionSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */