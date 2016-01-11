package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;

@JacksonStdImpl
public final class IndexedStringListSerializer
  extends StaticListSerializerBase<List<String>>
  implements ResolvableSerializer
{
  protected JsonSerializer<String> _serializer;
  
  public IndexedStringListSerializer(BeanProperty paramBeanProperty)
  {
    this(paramBeanProperty, null);
  }
  
  public IndexedStringListSerializer(BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer)
  {
    super(List.class, paramBeanProperty);
    this._serializer = paramJsonSerializer;
  }
  
  private final void serializeContents(List<String> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    int j = 0;
    int i = 0;
    for (;;)
    {
      try
      {
        int k = paramList.size();
        if (i < k)
        {
          j = i;
          String str = (String)paramList.get(i);
          if (str == null)
          {
            j = i;
            paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          }
          else
          {
            j = i;
            paramJsonGenerator.writeString(str);
          }
        }
      }
      catch (Exception paramJsonGenerator)
      {
        wrapAndThrow(paramSerializerProvider, paramJsonGenerator, paramList, j);
      }
      return;
      i += 1;
    }
  }
  
  private final void serializeUsingCustom(List<String> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    int i = 0;
    int j = i;
    for (;;)
    {
      try
      {
        int k = paramList.size();
        j = i;
        JsonSerializer localJsonSerializer = this._serializer;
        i = 0;
        if (i < k)
        {
          j = i;
          String str = (String)paramList.get(i);
          if (str == null)
          {
            j = i;
            paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          }
          else
          {
            j = i;
            localJsonSerializer.serialize(str, paramJsonGenerator, paramSerializerProvider);
          }
        }
      }
      catch (Exception paramJsonGenerator)
      {
        wrapAndThrow(paramSerializerProvider, paramJsonGenerator, paramList, j);
      }
      return;
      i += 1;
    }
  }
  
  protected JsonNode contentSchema()
  {
    return createSchemaNode("string", true);
  }
  
  public void resolve(SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    if (this._serializer == null)
    {
      paramSerializerProvider = paramSerializerProvider.findValueSerializer(String.class, this._property);
      if (!isDefaultSerializer(paramSerializerProvider)) {
        this._serializer = paramSerializerProvider;
      }
    }
  }
  
  public void serialize(List<String> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    paramJsonGenerator.writeStartArray();
    if (this._serializer == null) {
      serializeContents(paramList, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      paramJsonGenerator.writeEndArray();
      return;
      serializeUsingCustom(paramList, paramJsonGenerator, paramSerializerProvider);
    }
  }
  
  public void serializeWithType(List<String> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonGenerationException
  {
    paramTypeSerializer.writeTypePrefixForArray(paramList, paramJsonGenerator);
    if (this._serializer == null) {
      serializeContents(paramList, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      paramTypeSerializer.writeTypeSuffixForArray(paramList, paramJsonGenerator);
      return;
      serializeUsingCustom(paramList, paramJsonGenerator, paramSerializerProvider);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/ser/std/IndexedStringListSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */