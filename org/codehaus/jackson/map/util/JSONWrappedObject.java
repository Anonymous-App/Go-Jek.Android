package org.codehaus.jackson.map.util;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializableWithType;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

public class JSONWrappedObject
  implements JsonSerializableWithType
{
  protected final String _prefix;
  protected final JavaType _serializationType;
  protected final String _suffix;
  protected final Object _value;
  
  public JSONWrappedObject(String paramString1, String paramString2, Object paramObject)
  {
    this(paramString1, paramString2, paramObject, (JavaType)null);
  }
  
  @Deprecated
  public JSONWrappedObject(String paramString1, String paramString2, Object paramObject, Class<?> paramClass)
  {
    this._prefix = paramString1;
    this._suffix = paramString2;
    this._value = paramObject;
    if (paramClass == null) {}
    for (paramString1 = null;; paramString1 = TypeFactory.defaultInstance().constructType(paramClass))
    {
      this._serializationType = paramString1;
      return;
    }
  }
  
  public JSONWrappedObject(String paramString1, String paramString2, Object paramObject, JavaType paramJavaType)
  {
    this._prefix = paramString1;
    this._suffix = paramString2;
    this._value = paramObject;
    this._serializationType = paramJavaType;
  }
  
  public String getPrefix()
  {
    return this._prefix;
  }
  
  public JavaType getSerializationType()
  {
    return this._serializationType;
  }
  
  public String getSuffix()
  {
    return this._suffix;
  }
  
  public Object getValue()
  {
    return this._value;
  }
  
  public void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonProcessingException
  {
    if (this._prefix != null) {
      paramJsonGenerator.writeRaw(this._prefix);
    }
    if (this._value == null) {
      paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
    }
    for (;;)
    {
      if (this._suffix != null) {
        paramJsonGenerator.writeRaw(this._suffix);
      }
      return;
      if (this._serializationType != null) {
        paramSerializerProvider.findTypedValueSerializer(this._serializationType, true, null).serialize(this._value, paramJsonGenerator, paramSerializerProvider);
      } else {
        paramSerializerProvider.findTypedValueSerializer(this._value.getClass(), true, null).serialize(this._value, paramJsonGenerator, paramSerializerProvider);
      }
    }
  }
  
  public void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonProcessingException
  {
    serialize(paramJsonGenerator, paramSerializerProvider);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/util/JSONWrappedObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */