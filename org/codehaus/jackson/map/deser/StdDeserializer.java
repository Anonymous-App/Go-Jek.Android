package org.codehaus.jackson.map.deser;

import java.io.IOException;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.Base64Variants;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.deser.std.CalendarDeserializer;
import org.codehaus.jackson.map.deser.std.ClassDeserializer;
import org.codehaus.jackson.map.deser.std.StdScalarDeserializer;
import org.codehaus.jackson.type.JavaType;

@Deprecated
public abstract class StdDeserializer<T>
  extends org.codehaus.jackson.map.deser.std.StdDeserializer<T>
{
  protected StdDeserializer(Class<?> paramClass)
  {
    super(paramClass);
  }
  
  protected StdDeserializer(JavaType paramJavaType)
  {
    super(paramJavaType);
  }
  
  @Deprecated
  @JacksonStdImpl
  public class CalendarDeserializer
    extends CalendarDeserializer
  {
    public CalendarDeserializer() {}
  }
  
  @Deprecated
  @JacksonStdImpl
  public class ClassDeserializer
    extends ClassDeserializer
  {
    public ClassDeserializer() {}
  }
  
  @Deprecated
  @JacksonStdImpl
  public static final class StringDeserializer
    extends StdScalarDeserializer<String>
  {
    public StringDeserializer()
    {
      super();
    }
    
    public String deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      JsonToken localJsonToken = paramJsonParser.getCurrentToken();
      if (localJsonToken == JsonToken.VALUE_STRING) {
        return paramJsonParser.getText();
      }
      if (localJsonToken == JsonToken.VALUE_EMBEDDED_OBJECT)
      {
        paramJsonParser = paramJsonParser.getEmbeddedObject();
        if (paramJsonParser == null) {
          return null;
        }
        if ((paramJsonParser instanceof byte[])) {
          return Base64Variants.getDefaultVariant().encode((byte[])paramJsonParser, false);
        }
        return paramJsonParser.toString();
      }
      if (localJsonToken.isScalarValue()) {
        return paramJsonParser.getText();
      }
      throw paramDeserializationContext.mappingException(this._valueClass, localJsonToken);
    }
    
    public String deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
      throws IOException, JsonProcessingException
    {
      return deserialize(paramJsonParser, paramDeserializationContext);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/deser/StdDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */