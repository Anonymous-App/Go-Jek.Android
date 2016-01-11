package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@JacksonStdImpl
public class StringCollectionSerializer
  extends StaticListSerializerBase<Collection<String>>
{
  public static final StringCollectionSerializer instance = new StringCollectionSerializer();
  
  protected StringCollectionSerializer()
  {
    super(Collection.class);
  }
  
  protected StringCollectionSerializer(StringCollectionSerializer paramStringCollectionSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    super(paramStringCollectionSerializer, paramJsonSerializer, paramBoolean);
  }
  
  private final void _serializeUnwrapped(Collection<String> paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (this._serializer == null)
    {
      serializeContents(paramCollection, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    serializeUsingCustom(paramCollection, paramJsonGenerator, paramSerializerProvider);
  }
  
  private final void serializeContents(Collection<String> paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    if (this._serializer != null) {
      serializeUsingCustom(paramCollection, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      return;
      int i = 0;
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (str == null) {}
        try
        {
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        }
        catch (Exception localException)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramCollection, i);
        }
        paramJsonGenerator.writeString(str);
        break label85;
        continue;
        label85:
        i += 1;
      }
    }
  }
  
  private void serializeUsingCustom(Collection<String> paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    JsonSerializer localJsonSerializer = this._serializer;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (str == null) {
        try
        {
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        }
        catch (Exception localException)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramCollection, 0);
        }
      } else {
        localJsonSerializer.serialize(localException, paramJsonGenerator, paramSerializerProvider);
      }
    }
  }
  
  public JsonSerializer<?> _withResolved(BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    return new StringCollectionSerializer(this, paramJsonSerializer, paramBoolean);
  }
  
  protected void acceptContentVisitor(JsonArrayFormatVisitor paramJsonArrayFormatVisitor)
    throws JsonMappingException
  {
    paramJsonArrayFormatVisitor.itemsFormat(JsonFormatTypes.STRING);
  }
  
  protected JsonNode contentSchema()
  {
    return createSchemaNode("string", true);
  }
  
  public void serialize(Collection<String> paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    int i = paramCollection.size();
    if ((i == 1) && (((this._unwrapSingle == null) && (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED))) || (this._unwrapSingle == Boolean.TRUE)))
    {
      _serializeUnwrapped(paramCollection, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    paramJsonGenerator.writeStartArray(i);
    if (this._serializer == null) {
      serializeContents(paramCollection, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      paramJsonGenerator.writeEndArray();
      return;
      serializeUsingCustom(paramCollection, paramJsonGenerator, paramSerializerProvider);
    }
  }
  
  public void serializeWithType(Collection<String> paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonGenerationException
  {
    paramTypeSerializer.writeTypePrefixForArray(paramCollection, paramJsonGenerator);
    if (this._serializer == null) {
      serializeContents(paramCollection, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      paramTypeSerializer.writeTypeSuffixForArray(paramCollection, paramJsonGenerator);
      return;
      serializeUsingCustom(paramCollection, paramJsonGenerator, paramSerializerProvider);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ser/impl/StringCollectionSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */