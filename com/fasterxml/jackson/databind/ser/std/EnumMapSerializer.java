package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.EnumValues;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

@JacksonStdImpl
@Deprecated
public class EnumMapSerializer
  extends ContainerSerializer<EnumMap<? extends Enum<?>, ?>>
  implements ContextualSerializer
{
  protected final EnumValues _keyEnums;
  protected final BeanProperty _property;
  protected final boolean _staticTyping;
  protected final JsonSerializer<Object> _valueSerializer;
  protected final JavaType _valueType;
  protected final TypeSerializer _valueTypeSerializer;
  
  public EnumMapSerializer(JavaType paramJavaType, boolean paramBoolean, EnumValues paramEnumValues, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
  {
    super(EnumMap.class, false);
    this._property = null;
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
    this._valueSerializer = paramJsonSerializer;
  }
  
  public EnumMapSerializer(EnumMapSerializer paramEnumMapSerializer, BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer)
  {
    super(paramEnumMapSerializer);
    this._property = paramBeanProperty;
    this._staticTyping = paramEnumMapSerializer._staticTyping;
    this._valueType = paramEnumMapSerializer._valueType;
    this._keyEnums = paramEnumMapSerializer._keyEnums;
    this._valueTypeSerializer = paramEnumMapSerializer._valueTypeSerializer;
    this._valueSerializer = paramJsonSerializer;
  }
  
  public EnumMapSerializer _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new EnumMapSerializer(this._valueType, this._staticTyping, this._keyEnums, paramTypeSerializer, this._valueSerializer);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper == null) {}
    for (;;)
    {
      return;
      JsonObjectFormatVisitor localJsonObjectFormatVisitor = paramJsonFormatVisitorWrapper.expectObjectFormat(paramJavaType);
      if (localJsonObjectFormatVisitor != null)
      {
        Object localObject3 = paramJavaType.containedType(1);
        Object localObject2 = this._valueSerializer;
        Object localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject1 = localObject2;
          if (localObject3 != null) {
            localObject1 = paramJsonFormatVisitorWrapper.getProvider().findValueSerializer((JavaType)localObject3, this._property);
          }
        }
        localObject2 = localObject3;
        if (localObject3 == null) {
          localObject2 = paramJsonFormatVisitorWrapper.getProvider().constructType(Object.class);
        }
        Object localObject4 = this._keyEnums;
        localObject3 = localObject4;
        if (localObject4 == null)
        {
          localObject3 = paramJavaType.containedType(0);
          if (localObject3 == null) {
            throw new IllegalStateException("Can not resolve Enum type of EnumMap: " + paramJavaType);
          }
          localObject3 = paramJsonFormatVisitorWrapper.getProvider().findValueSerializer((JavaType)localObject3, this._property);
          if (!(localObject3 instanceof EnumSerializer)) {
            throw new IllegalStateException("Can not resolve Enum type of EnumMap: " + paramJavaType);
          }
          localObject3 = ((EnumSerializer)localObject3).getEnumValues();
        }
        localObject3 = ((EnumValues)localObject3).internalMap().entrySet().iterator();
        while (((Iterator)localObject3).hasNext())
        {
          Map.Entry localEntry = (Map.Entry)((Iterator)localObject3).next();
          localObject4 = ((SerializableString)localEntry.getValue()).getValue();
          paramJavaType = (JavaType)localObject1;
          if (localObject1 == null) {
            paramJavaType = paramJsonFormatVisitorWrapper.getProvider().findValueSerializer(localEntry.getKey().getClass(), this._property);
          }
          localJsonObjectFormatVisitor.property((String)localObject4, paramJavaType, (JavaType)localObject2);
          localObject1 = paramJavaType;
        }
      }
    }
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramBeanProperty != null)
    {
      AnnotatedMember localAnnotatedMember = paramBeanProperty.getMember();
      localObject1 = localObject2;
      if (localAnnotatedMember != null)
      {
        Object localObject3 = paramSerializerProvider.getAnnotationIntrospector().findContentSerializer(localAnnotatedMember);
        localObject1 = localObject2;
        if (localObject3 != null) {
          localObject1 = paramSerializerProvider.serializerInstance(localAnnotatedMember, localObject3);
        }
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = this._valueSerializer;
    }
    localObject1 = findConvertingContentSerializer(paramSerializerProvider, paramBeanProperty, (JsonSerializer)localObject2);
    if (localObject1 == null)
    {
      if (!this._staticTyping) {
        break label111;
      }
      paramSerializerProvider = withValueSerializer(paramBeanProperty, paramSerializerProvider.findValueSerializer(this._valueType, paramBeanProperty));
    }
    label111:
    do
    {
      return paramSerializerProvider;
      localObject1 = paramSerializerProvider.handleSecondaryContextualization((JsonSerializer)localObject1, paramBeanProperty);
      paramSerializerProvider = this;
    } while (localObject1 == this._valueSerializer);
    return withValueSerializer(paramBeanProperty, (JsonSerializer)localObject1);
  }
  
  public JsonSerializer<?> getContentSerializer()
  {
    return this._valueSerializer;
  }
  
  public JavaType getContentType()
  {
    return this._valueType;
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
            localObjectNode2.set(paramSerializerProvider.getConfig().getAnnotationIntrospector().findEnumValue(localEnum), paramType);
            i += 1;
            break;
          }
        }
        localObjectNode1.set("properties", localObjectNode2);
      }
    }
    return localObjectNode1;
  }
  
  public boolean hasSingleElement(EnumMap<? extends Enum<?>, ?> paramEnumMap)
  {
    return paramEnumMap.size() == 1;
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, EnumMap<? extends Enum<?>, ?> paramEnumMap)
  {
    return (paramEnumMap == null) || (paramEnumMap.isEmpty());
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
    if (this._valueSerializer != null) {
      serializeContentsUsing(paramEnumMap, paramJsonGenerator, paramSerializerProvider, this._valueSerializer);
    }
    label70:
    label207:
    label291:
    label303:
    for (;;)
    {
      return;
      Object localObject1 = null;
      Object localObject4 = null;
      Object localObject2 = this._keyEnums;
      int i;
      boolean bool;
      TypeSerializer localTypeSerializer;
      Iterator localIterator;
      if (!paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES))
      {
        i = 1;
        bool = paramSerializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        localTypeSerializer = this._valueTypeSerializer;
        localIterator = paramEnumMap.entrySet().iterator();
      }
      for (;;)
      {
        if (!localIterator.hasNext()) {
          break label303;
        }
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        Object localObject6 = localEntry.getValue();
        if ((i == 0) || (localObject6 != null))
        {
          Object localObject5 = (Enum)localEntry.getKey();
          if (bool) {
            paramJsonGenerator.writeFieldName(((Enum)localObject5).toString());
          }
          Object localObject3;
          for (;;)
          {
            if (localObject6 != null) {
              break label207;
            }
            paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
            break label70;
            i = 0;
            break;
            localObject3 = localObject2;
            if (localObject2 == null) {
              localObject3 = ((EnumSerializer)paramSerializerProvider.findValueSerializer(((Enum)localObject5).getDeclaringClass(), this._property)).getEnumValues();
            }
            paramJsonGenerator.writeFieldName(((EnumValues)localObject3).serializedValueFor((Enum)localObject5));
            localObject2 = localObject3;
          }
          localObject5 = localObject6.getClass();
          if (localObject5 == localObject4) {
            localObject3 = localObject1;
          }
          JsonSerializer localJsonSerializer;
          for (;;)
          {
            if (localTypeSerializer != null) {
              break label291;
            }
            try
            {
              ((JsonSerializer)localObject3).serialize(localObject6, paramJsonGenerator, paramSerializerProvider);
            }
            catch (Exception localException)
            {
              wrapAndThrow(paramSerializerProvider, localException, paramEnumMap, ((Enum)localEntry.getKey()).name());
            }
            break;
            localJsonSerializer = paramSerializerProvider.findValueSerializer((Class)localObject5, this._property);
            localObject1 = localJsonSerializer;
            localObject4 = localObject5;
          }
          localJsonSerializer.serializeWithType(localObject6, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        }
      }
    }
  }
  
  protected void serializeContentsUsing(EnumMap<? extends Enum<?>, ?> paramEnumMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException, JsonGenerationException
  {
    Object localObject1 = this._keyEnums;
    int i;
    boolean bool;
    TypeSerializer localTypeSerializer;
    Iterator localIterator;
    if (!paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES))
    {
      i = 1;
      bool = paramSerializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
      localTypeSerializer = this._valueTypeSerializer;
      localIterator = paramEnumMap.entrySet().iterator();
    }
    for (;;)
    {
      label45:
      if (!localIterator.hasNext()) {
        return;
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject3 = localEntry.getValue();
      if ((i == 0) || (localObject3 != null))
      {
        Enum localEnum = (Enum)localEntry.getKey();
        if (bool) {
          paramJsonGenerator.writeFieldName(localEnum.toString());
        }
        for (;;)
        {
          if (localObject3 != null) {
            break label182;
          }
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          break label45;
          i = 0;
          break;
          Object localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = ((EnumSerializer)paramSerializerProvider.findValueSerializer(localEnum.getDeclaringClass(), this._property)).getEnumValues();
          }
          paramJsonGenerator.writeFieldName(((EnumValues)localObject2).serializedValueFor(localEnum));
          localObject1 = localObject2;
        }
        label182:
        if (localTypeSerializer == null) {
          try
          {
            paramJsonSerializer.serialize(localObject3, paramJsonGenerator, paramSerializerProvider);
          }
          catch (Exception localException)
          {
            wrapAndThrow(paramSerializerProvider, localException, paramEnumMap, ((Enum)localEntry.getKey()).name());
          }
        } else {
          paramJsonSerializer.serializeWithType(localObject3, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
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
  
  public EnumMapSerializer withValueSerializer(BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer)
  {
    if ((this._property == paramBeanProperty) && (paramJsonSerializer == this._valueSerializer)) {
      return this;
    }
    return new EnumMapSerializer(this, paramBeanProperty, paramJsonSerializer);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ser/std/EnumMapSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */