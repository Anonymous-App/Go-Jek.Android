package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.util.ClassUtil;

@JacksonStdImpl
public class ClassDeserializer
  extends StdScalarDeserializer<Class<?>>
{
  public ClassDeserializer()
  {
    super(Class.class);
  }
  
  public Class<?> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText();
      try
      {
        paramJsonParser = ClassUtil.findClass(paramJsonParser);
        return paramJsonParser;
      }
      catch (ClassNotFoundException paramJsonParser)
      {
        throw paramDeserializationContext.instantiationException(this._valueClass, paramJsonParser);
      }
    }
    throw paramDeserializationContext.mappingException(this._valueClass, localJsonToken);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/deser/std/ClassDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */