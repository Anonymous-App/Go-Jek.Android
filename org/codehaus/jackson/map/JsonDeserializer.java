package org.codehaus.jackson.map;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;

public abstract class JsonDeserializer<T>
{
  public abstract T deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException;
  
  public T deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, T paramT)
    throws IOException, JsonProcessingException
  {
    throw new UnsupportedOperationException("Can not update object of type " + paramT.getClass().getName() + " (by deserializer of type " + getClass().getName() + ")");
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
  }
  
  public T getEmptyValue()
  {
    return (T)getNullValue();
  }
  
  public T getNullValue()
  {
    return null;
  }
  
  public JsonDeserializer<T> unwrappingDeserializer()
  {
    return this;
  }
  
  public static abstract class None
    extends JsonDeserializer<Object>
  {}
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/JsonDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */