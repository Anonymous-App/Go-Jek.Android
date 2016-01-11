package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.io.SerializedString;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.util.EnumValues;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.type.JavaType;

@JacksonStdImpl
public class EnumSerializer
  extends ScalarSerializerBase<Enum<?>>
{
  protected final EnumValues _values;
  
  public EnumSerializer(EnumValues paramEnumValues)
  {
    super(Enum.class, false);
    this._values = paramEnumValues;
  }
  
  public static EnumSerializer construct(Class<Enum<?>> paramClass, SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription)
  {
    paramBasicBeanDescription = paramSerializationConfig.getAnnotationIntrospector();
    if (paramSerializationConfig.isEnabled(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING)) {}
    for (paramClass = EnumValues.constructFromToString(paramClass, paramBasicBeanDescription);; paramClass = EnumValues.constructFromName(paramClass, paramBasicBeanDescription)) {
      return new EnumSerializer(paramClass);
    }
  }
  
  public EnumValues getEnumValues()
  {
    return this._values;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    Object localObject;
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_ENUMS_USING_INDEX)) {
      localObject = createSchemaNode("integer", true);
    }
    ObjectNode localObjectNode;
    do
    {
      do
      {
        return (JsonNode)localObject;
        localObjectNode = createSchemaNode("string", true);
        localObject = localObjectNode;
      } while (paramType == null);
      localObject = localObjectNode;
    } while (!paramSerializerProvider.constructType(paramType).isEnumType());
    paramSerializerProvider = localObjectNode.putArray("enum");
    paramType = this._values.values().iterator();
    for (;;)
    {
      localObject = localObjectNode;
      if (!paramType.hasNext()) {
        break;
      }
      paramSerializerProvider.add(((SerializedString)paramType.next()).getValue());
    }
  }
  
  public final void serialize(Enum<?> paramEnum, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_ENUMS_USING_INDEX))
    {
      paramJsonGenerator.writeNumber(paramEnum.ordinal());
      return;
    }
    paramJsonGenerator.writeString(this._values.serializedValueFor(paramEnum));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/ser/std/EnumSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */