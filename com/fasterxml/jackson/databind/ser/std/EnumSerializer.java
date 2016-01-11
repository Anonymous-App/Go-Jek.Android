package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.EnumValues;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@JacksonStdImpl
public class EnumSerializer
  extends StdScalarSerializer<Enum<?>>
  implements ContextualSerializer
{
  private static final long serialVersionUID = 1L;
  protected final Boolean _serializeAsIndex;
  protected final EnumValues _values;
  
  @Deprecated
  public EnumSerializer(EnumValues paramEnumValues)
  {
    this(paramEnumValues, null);
  }
  
  public EnumSerializer(EnumValues paramEnumValues, Boolean paramBoolean)
  {
    super(paramEnumValues.getEnumClass(), false);
    this._values = paramEnumValues;
    this._serializeAsIndex = paramBoolean;
  }
  
  protected static Boolean _isShapeWrittenUsingIndex(Class<?> paramClass, JsonFormat.Value paramValue, boolean paramBoolean)
  {
    if (paramValue == null)
    {
      paramValue = null;
      if (paramValue != null) {
        break label20;
      }
    }
    label20:
    while ((paramValue == JsonFormat.Shape.ANY) || (paramValue == JsonFormat.Shape.SCALAR))
    {
      return null;
      paramValue = paramValue.getShape();
      break;
    }
    if (paramValue == JsonFormat.Shape.STRING) {
      return Boolean.FALSE;
    }
    if ((paramValue.isNumeric()) || (paramValue == JsonFormat.Shape.ARRAY)) {
      return Boolean.TRUE;
    }
    paramValue = new StringBuilder().append("Unsupported serialization shape (").append(paramValue).append(") for Enum ").append(paramClass.getName()).append(", not supported as ");
    if (paramBoolean) {}
    for (paramClass = "class";; paramClass = "property") {
      throw new IllegalArgumentException(paramClass + " annotation");
    }
  }
  
  public static EnumSerializer construct(Class<?> paramClass, SerializationConfig paramSerializationConfig, BeanDescription paramBeanDescription, JsonFormat.Value paramValue)
  {
    return new EnumSerializer(EnumValues.constructFromName(paramSerializationConfig, paramClass), _isShapeWrittenUsingIndex(paramClass, paramValue, true));
  }
  
  protected final boolean _serializeAsIndex(SerializerProvider paramSerializerProvider)
  {
    if (this._serializeAsIndex != null) {
      return this._serializeAsIndex.booleanValue();
    }
    return paramSerializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    Object localObject = paramJsonFormatVisitorWrapper.getProvider();
    if (_serializeAsIndex((SerializerProvider)localObject))
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectIntegerFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null) {
        paramJsonFormatVisitorWrapper.numberType(JsonParser.NumberType.INT);
      }
    }
    do
    {
      return;
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectStringFormat(paramJavaType);
    } while (paramJsonFormatVisitorWrapper == null);
    paramJavaType = new LinkedHashSet();
    if ((localObject != null) && (((SerializerProvider)localObject).isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING))) {
      localObject = this._values.enums().iterator();
    }
    while (((Iterator)localObject).hasNext())
    {
      paramJavaType.add(((Enum)((Iterator)localObject).next()).toString());
      continue;
      localObject = this._values.values().iterator();
      while (((Iterator)localObject).hasNext()) {
        paramJavaType.add(((SerializableString)((Iterator)localObject).next()).getValue());
      }
    }
    paramJsonFormatVisitorWrapper.enumTypes(paramJavaType);
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    EnumSerializer localEnumSerializer = this;
    if (paramBeanProperty != null)
    {
      paramSerializerProvider = paramSerializerProvider.getAnnotationIntrospector().findFormat(paramBeanProperty.getMember());
      localEnumSerializer = this;
      if (paramSerializerProvider != null)
      {
        paramSerializerProvider = _isShapeWrittenUsingIndex(paramBeanProperty.getType().getRawClass(), paramSerializerProvider, false);
        localEnumSerializer = this;
        if (paramSerializerProvider != this._serializeAsIndex) {
          localEnumSerializer = new EnumSerializer(this._values, paramSerializerProvider);
        }
      }
    }
    return localEnumSerializer;
  }
  
  public EnumValues getEnumValues()
  {
    return this._values;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    Object localObject;
    if (_serializeAsIndex(paramSerializerProvider)) {
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
      paramSerializerProvider.add(((SerializableString)paramType.next()).getValue());
    }
  }
  
  public final void serialize(Enum<?> paramEnum, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (_serializeAsIndex(paramSerializerProvider))
    {
      paramJsonGenerator.writeNumber(paramEnum.ordinal());
      return;
    }
    if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING))
    {
      paramJsonGenerator.writeString(paramEnum.toString());
      return;
    }
    paramJsonGenerator.writeString(this._values.serializedValueFor(paramEnum));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ser/std/EnumSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */