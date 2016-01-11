package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import java.util.Iterator;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.type.JavaType;

@JacksonStdImpl
public class IterableSerializer
  extends AsArraySerializerBase<Iterable<?>>
{
  public IterableSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty)
  {
    super(Iterable.class, paramJavaType, paramBoolean, paramTypeSerializer, paramBeanProperty, null);
  }
  
  public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new IterableSerializer(this._elementType, this._staticTyping, paramTypeSerializer, this._property);
  }
  
  public void serializeContents(Iterable<?> paramIterable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
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
      Object localObject3 = localIterator.next();
      if (localObject3 == null) {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      }
      while (!localIterator.hasNext())
      {
        return;
        Class localClass = localObject3.getClass();
        Object localObject2;
        if (localClass == localObject1) {
          localObject2 = paramIterable;
        }
        for (;;)
        {
          if (localTypeSerializer != null) {
            break label115;
          }
          ((JsonSerializer)localObject2).serialize(localObject3, paramJsonGenerator, paramSerializerProvider);
          break;
          localObject2 = paramSerializerProvider.findValueSerializer(localClass, this._property);
          paramIterable = (Iterable<?>)localObject2;
          localObject1 = localClass;
        }
        label115:
        ((JsonSerializer)localObject2).serializeWithType(localObject3, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/ser/std/IterableSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */