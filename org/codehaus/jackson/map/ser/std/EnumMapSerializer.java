package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.util.EnumValues;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

@JacksonStdImpl
public class EnumMapSerializer
  extends ContainerSerializerBase<EnumMap<? extends Enum<?>, ?>>
  implements ResolvableSerializer
{
  protected final EnumValues _keyEnums;
  protected final BeanProperty _property;
  protected final boolean _staticTyping;
  protected JsonSerializer<Object> _valueSerializer;
  protected final JavaType _valueType;
  protected final TypeSerializer _valueTypeSerializer;
  
  @Deprecated
  public EnumMapSerializer(JavaType paramJavaType, boolean paramBoolean, EnumValues paramEnumValues, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty)
  {
    this(paramJavaType, paramBoolean, paramEnumValues, paramTypeSerializer, paramBeanProperty, null);
  }
  
  public EnumMapSerializer(JavaType paramJavaType, boolean paramBoolean, EnumValues paramEnumValues, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty, JsonSerializer<Object> paramJsonSerializer)
  {
    super(EnumMap.class, false);
    if (!paramBoolean)
    {
      paramBoolean = bool;
      if (paramJavaType != null)
      {
        paramBoolean = bool;
        if (!paramJavaType.isFinal()) {}
      }
    }
    else
    {
      paramBoolean = true;
    }
    this._staticTyping = paramBoolean;
    this._valueType = paramJavaType;
    this._keyEnums = paramEnumValues;
    this._valueTypeSerializer = paramTypeSerializer;
    this._property = paramBeanProperty;
    this._valueSerializer = paramJsonSerializer;
  }
  
  public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new EnumMapSerializer(this._valueType, this._staticTyping, this._keyEnums, paramTypeSerializer, this._property, this._valueSerializer);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    throws JsonMappingException
  {
    ObjectNode localObjectNode1 = createSchemaNode("object", true);
    if ((paramType instanceof ParameterizedType))
    {
      Object localObject = ((ParameterizedType)paramType).getActualTypeArguments();
      if (localObject.length == 2)
      {
        paramType = paramSerializerProvider.constructType(localObject[0]);
        localObject = paramSerializerProvider.constructType(localObject[1]);
        ObjectNode localObjectNode2 = JsonNodeFactory.instance.objectNode();
        Enum[] arrayOfEnum = (Enum[])paramType.getRawClass().getEnumConstants();
        int j = arrayOfEnum.length;
        int i = 0;
        if (i < j)
        {
          Enum localEnum = arrayOfEnum[i];
          paramType = paramSerializerProvider.findValueSerializer(((JavaType)localObject).getRawClass(), this._property);
          if ((paramType instanceof SchemaAware)) {}
          for (paramType = ((SchemaAware)paramType).getSchema(paramSerializerProvider, null);; paramType = JsonSchema.getDefaultSchemaNode())
          {
            localObjectNode2.put(paramSerializerProvider.getConfig().getAnnotationIntrospector().findEnumValue(localEnum), paramType);
            i += 1;
            break;
          }
        }
        localObjectNode1.put("properties", localObjectNode2);
      }
    }
    return localObjectNode1;
  }
  
  public void resolve(SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    if ((this._staticTyping) && (this._valueSerializer == null)) {
      this._valueSerializer = paramSerializerProvider.findValueSerializer(this._valueType, this._property);
    }
  }
  
  public void serialize(EnumMap<? extends Enum<?>, ?> paramEnumMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    paramJsonGenerator.writeStartObject();
    if (!paramEnumMap.isEmpty()) {
      serializeContents(paramEnumMap, paramJsonGenerator, paramSerializerProvider);
    }
    paramJsonGenerator.writeEndObject();
  }
  
  protected void serializeContents(EnumMap<? extends Enum<?>, ?> paramEnumMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    if (this._valueSerializer != null)
    {
      serializeContentsUsing(paramEnumMap, paramJsonGenerator, paramSerializerProvider, this._valueSerializer);
      return;
    }
    Object localObject1 = null;
    Object localObject3 = null;
    Object localObject4 = this._keyEnums;
    Iterator localIterator = paramEnumMap.entrySet().iterator();
    label42:
    Map.Entry localEntry;
    Object localObject6;
    Object localObject2;
    Object localObject7;
    while (localIterator.hasNext())
    {
      localEntry = (Map.Entry)localIterator.next();
      localObject6 = (Enum)localEntry.getKey();
      localObject2 = localObject4;
      if (localObject4 == null) {
        localObject2 = ((EnumSerializer)paramSerializerProvider.findValueSerializer(((Enum)localObject6).getDeclaringClass(), this._property)).getEnumValues();
      }
      paramJsonGenerator.writeFieldName(((EnumValues)localObject2).serializedValueFor((Enum)localObject6));
      localObject7 = localEntry.getValue();
      if (localObject7 == null)
      {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        localObject4 = localObject2;
      }
      else
      {
        localObject6 = localObject7.getClass();
        if (localObject6 != localObject3) {
          break label210;
        }
        localObject4 = localObject1;
      }
    }
    for (;;)
    {
      try
      {
        ((JsonSerializer)localObject4).serialize(localObject7, paramJsonGenerator, paramSerializerProvider);
        localObject4 = localObject2;
      }
      catch (Exception localException)
      {
        wrapAndThrow(paramSerializerProvider, localException, paramEnumMap, ((Enum)localEntry.getKey()).name());
        localObject5 = localObject2;
      }
      break label42;
      break;
      label210:
      Object localObject5 = paramSerializerProvider.findValueSerializer((Class)localObject6, this._property);
      localObject1 = localObject5;
      localObject3 = localObject6;
    }
  }
  
  protected void serializeContentsUsing(EnumMap<? extends Enum<?>, ?> paramEnumMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException, JsonGenerationException
  {
    Object localObject2 = this._keyEnums;
    Iterator localIterator = paramEnumMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Enum localEnum = (Enum)localEntry.getKey();
      Object localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = ((EnumSerializer)paramSerializerProvider.findValueSerializer(localEnum.getDeclaringClass(), this._property)).getEnumValues();
      }
      paramJsonGenerator.writeFieldName(((EnumValues)localObject1).serializedValueFor(localEnum));
      localObject2 = localEntry.getValue();
      if (localObject2 == null)
      {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        localObject2 = localObject1;
      }
      else
      {
        try
        {
          paramJsonSerializer.serialize(localObject2, paramJsonGenerator, paramSerializerProvider);
          localObject2 = localObject1;
        }
        catch (Exception localException)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramEnumMap, ((Enum)localEntry.getKey()).name());
          Object localObject3 = localObject1;
        }
      }
    }
  }
  
  public void serializeWithType(EnumMap<? extends Enum<?>, ?> paramEnumMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonGenerationException
  {
    paramTypeSerializer.writeTypePrefixForObject(paramEnumMap, paramJsonGenerator);
    if (!paramEnumMap.isEmpty()) {
      serializeContents(paramEnumMap, paramJsonGenerator, paramSerializerProvider);
    }
    paramTypeSerializer.writeTypeSuffixForObject(paramEnumMap, paramJsonGenerator);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/ser/std/EnumMapSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */