package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
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
public class StringCollectionSerializer
  extends StaticListSerializerBase<Collection<String>>
  implements ResolvableSerializer
{
  protected JsonSerializer<String> _serializer;
  
  @Deprecated
  public StringCollectionSerializer(BeanProperty paramBeanProperty)
  {
    this(paramBeanProperty, null);
  }
  
  public StringCollectionSerializer(BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer)
  {
    super(Collection.class, paramBeanProperty);
    this._serializer = paramJsonSerializer;
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
  
  public void serialize(Collection<String> paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    paramJsonGenerator.writeStartArray();
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/ser/std/StringCollectionSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */