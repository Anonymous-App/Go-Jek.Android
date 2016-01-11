package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import org.codehaus.jackson.map.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

@JacksonStdImpl
public class MapSerializer
  extends ContainerSerializerBase<Map<?, ?>>
  implements ResolvableSerializer
{
  protected static final JavaType UNSPECIFIED_TYPE = ;
  protected PropertySerializerMap _dynamicValueSerializers;
  protected final HashSet<String> _ignoredEntries;
  protected JsonSerializer<Object> _keySerializer;
  protected final JavaType _keyType;
  protected final BeanProperty _property;
  protected JsonSerializer<Object> _valueSerializer;
  protected final JavaType _valueType;
  protected final boolean _valueTypeIsStatic;
  protected final TypeSerializer _valueTypeSerializer;
  
  protected MapSerializer()
  {
    this((HashSet)null, null, null, false, null, null, null, null);
  }
  
  protected MapSerializer(HashSet<String> paramHashSet, JavaType paramJavaType1, JavaType paramJavaType2, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer1, JsonSerializer<Object> paramJsonSerializer2, BeanProperty paramBeanProperty)
  {
    super(Map.class, false);
    this._property = paramBeanProperty;
    this._ignoredEntries = paramHashSet;
    this._keyType = paramJavaType1;
    this._valueType = paramJavaType2;
    this._valueTypeIsStatic = paramBoolean;
    this._valueTypeSerializer = paramTypeSerializer;
    this._keySerializer = paramJsonSerializer1;
    this._valueSerializer = paramJsonSerializer2;
    this._dynamicValueSerializers = PropertySerializerMap.emptyMap();
  }
  
  @Deprecated
  public static MapSerializer construct(String[] paramArrayOfString, JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty)
  {
    return construct(paramArrayOfString, paramJavaType, paramBoolean, paramTypeSerializer, paramBeanProperty, null, null);
  }
  
  public static MapSerializer construct(String[] paramArrayOfString, JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty, JsonSerializer<Object> paramJsonSerializer1, JsonSerializer<Object> paramJsonSerializer2)
  {
    HashSet localHashSet = toSet(paramArrayOfString);
    if (paramJavaType == null)
    {
      paramJavaType = UNSPECIFIED_TYPE;
      paramArrayOfString = paramJavaType;
      bool = paramBoolean;
      if (!paramBoolean) {
        if ((paramJavaType == null) || (!paramJavaType.isFinal())) {
          break label71;
        }
      }
    }
    label71:
    for (boolean bool = true;; bool = false)
    {
      return new MapSerializer(localHashSet, paramArrayOfString, paramJavaType, bool, paramTypeSerializer, paramJsonSerializer1, paramJsonSerializer2, paramBeanProperty);
      paramArrayOfString = paramJavaType.getKeyType();
      paramJavaType = paramJavaType.getContentType();
      break;
    }
  }
  
  private static HashSet<String> toSet(String[] paramArrayOfString)
  {
    Object localObject;
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
    {
      localObject = null;
      return (HashSet<String>)localObject;
    }
    HashSet localHashSet = new HashSet(paramArrayOfString.length);
    int j = paramArrayOfString.length;
    int i = 0;
    for (;;)
    {
      localObject = localHashSet;
      if (i >= j) {
        break;
      }
      localHashSet.add(paramArrayOfString[i]);
      i += 1;
    }
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, Class<?> paramClass, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    paramClass = paramPropertySerializerMap.findAndAddSerializer(paramClass, paramSerializerProvider, this._property);
    if (paramPropertySerializerMap != paramClass.map) {
      this._dynamicValueSerializers = paramClass.map;
    }
    return paramClass.serializer;
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, JavaType paramJavaType, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    paramJavaType = paramPropertySerializerMap.findAndAddSerializer(paramJavaType, paramSerializerProvider, this._property);
    if (paramPropertySerializerMap != paramJavaType.map) {
      this._dynamicValueSerializers = paramJavaType.map;
    }
    return paramJavaType.serializer;
  }
  
  public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    paramTypeSerializer = new MapSerializer(this._ignoredEntries, this._keyType, this._valueType, this._valueTypeIsStatic, paramTypeSerializer, this._keySerializer, this._valueSerializer, this._property);
    if (this._valueSerializer != null) {
      paramTypeSerializer._valueSerializer = this._valueSerializer;
    }
    return paramTypeSerializer;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    return createSchemaNode("object", true);
  }
  
  public void resolve(SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    if ((this._valueTypeIsStatic) && (this._valueSerializer == null)) {
      this._valueSerializer = paramSerializerProvider.findValueSerializer(this._valueType, this._property);
    }
    if (this._keySerializer == null) {
      this._keySerializer = paramSerializerProvider.findKeySerializer(this._keyType, this._property);
    }
  }
  
  public void serialize(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    paramJsonGenerator.writeStartObject();
    if (!paramMap.isEmpty())
    {
      if (this._valueSerializer == null) {
        break label36;
      }
      serializeFieldsUsing(paramMap, paramJsonGenerator, paramSerializerProvider, this._valueSerializer);
    }
    for (;;)
    {
      paramJsonGenerator.writeEndObject();
      return;
      label36:
      serializeFields(paramMap, paramJsonGenerator, paramSerializerProvider);
    }
  }
  
  public void serializeFields(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    if (this._valueTypeSerializer != null)
    {
      serializeTypedFields(paramMap, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    JsonSerializer localJsonSerializer2 = this._keySerializer;
    HashSet localHashSet = this._ignoredEntries;
    int i;
    label59:
    Object localObject5;
    Object localObject6;
    if (!paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES))
    {
      i = 1;
      localObject1 = this._dynamicValueSerializers;
      Iterator localIterator = paramMap.entrySet().iterator();
      if (!localIterator.hasNext()) {
        break label290;
      }
      localObject3 = (Map.Entry)localIterator.next();
      localObject5 = ((Map.Entry)localObject3).getValue();
      localObject6 = ((Map.Entry)localObject3).getKey();
      if (localObject6 != null) {
        break label133;
      }
      paramSerializerProvider.getNullKeySerializer().serialize(null, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      if (localObject5 != null) {
        break label170;
      }
      paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      break label59;
      i = 0;
      break;
      label133:
      if (((i != 0) && (localObject5 == null)) || ((localHashSet != null) && (localHashSet.contains(localObject6)))) {
        break label59;
      }
      localJsonSerializer2.serialize(localObject6, paramJsonGenerator, paramSerializerProvider);
    }
    label170:
    Class localClass = localObject5.getClass();
    JsonSerializer localJsonSerializer1 = ((PropertySerializerMap)localObject1).serializerFor(localClass);
    Object localObject4 = localJsonSerializer1;
    Object localObject3 = localObject1;
    if (localJsonSerializer1 == null) {
      if (!this._valueType.hasGenericTypes()) {
        break label292;
      }
    }
    Object localObject2;
    label290:
    label292:
    for (Object localObject1 = _findAndAddDynamic((PropertySerializerMap)localObject1, paramSerializerProvider.constructSpecializedType(this._valueType, localClass), paramSerializerProvider);; localObject2 = _findAndAddDynamic((PropertySerializerMap)localObject2, localClass, paramSerializerProvider))
    {
      localObject3 = this._dynamicValueSerializers;
      localObject4 = localObject1;
      try
      {
        ((JsonSerializer)localObject4).serialize(localObject5, paramJsonGenerator, paramSerializerProvider);
        localObject1 = localObject3;
      }
      catch (Exception localException)
      {
        wrapAndThrow(paramSerializerProvider, localException, paramMap, "" + localObject6);
        localObject2 = localObject3;
      }
      break label59;
      break;
    }
  }
  
  protected void serializeFieldsUsing(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException, JsonGenerationException
  {
    JsonSerializer localJsonSerializer = this._keySerializer;
    HashSet localHashSet = this._ignoredEntries;
    TypeSerializer localTypeSerializer = this._valueTypeSerializer;
    int i;
    Iterator localIterator;
    if (!paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES))
    {
      i = 1;
      localIterator = paramMap.entrySet().iterator();
    }
    for (;;)
    {
      label44:
      if (!localIterator.hasNext()) {
        return;
      }
      Object localObject2 = (Map.Entry)localIterator.next();
      Object localObject1 = ((Map.Entry)localObject2).getValue();
      localObject2 = ((Map.Entry)localObject2).getKey();
      if (localObject2 == null) {
        paramSerializerProvider.getNullKeySerializer().serialize(null, paramJsonGenerator, paramSerializerProvider);
      }
      for (;;)
      {
        if (localObject1 != null) {
          break label155;
        }
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        break label44;
        i = 0;
        break;
        if (((i != 0) && (localObject1 == null)) || ((localHashSet != null) && (localHashSet.contains(localObject2)))) {
          break label44;
        }
        localJsonSerializer.serialize(localObject2, paramJsonGenerator, paramSerializerProvider);
      }
      label155:
      if (localTypeSerializer == null) {
        try
        {
          paramJsonSerializer.serialize(localObject1, paramJsonGenerator, paramSerializerProvider);
        }
        catch (Exception localException)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramMap, "" + localObject2);
        }
      } else {
        paramJsonSerializer.serializeWithType(localException, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
      }
    }
  }
  
  protected void serializeTypedFields(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    JsonSerializer localJsonSerializer2 = this._keySerializer;
    Object localObject1 = null;
    Object localObject2 = null;
    HashSet localHashSet = this._ignoredEntries;
    int i;
    label44:
    Object localObject3;
    Object localObject4;
    Object localObject5;
    if (!paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES))
    {
      i = 1;
      Iterator localIterator = paramMap.entrySet().iterator();
      if (!localIterator.hasNext()) {
        return;
      }
      localObject3 = (Map.Entry)localIterator.next();
      localObject4 = ((Map.Entry)localObject3).getValue();
      localObject5 = ((Map.Entry)localObject3).getKey();
      if (localObject5 != null) {
        break label118;
      }
      paramSerializerProvider.getNullKeySerializer().serialize(null, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      if (localObject4 != null) {
        break label155;
      }
      paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      break label44;
      i = 0;
      break;
      label118:
      if (((i != 0) && (localObject4 == null)) || ((localHashSet != null) && (localHashSet.contains(localObject5)))) {
        break label44;
      }
      localJsonSerializer2.serialize(localObject5, paramJsonGenerator, paramSerializerProvider);
    }
    label155:
    Class localClass = localObject4.getClass();
    if (localClass == localObject2) {
      localObject3 = localObject1;
    }
    for (;;)
    {
      try
      {
        ((JsonSerializer)localObject3).serializeWithType(localObject4, paramJsonGenerator, paramSerializerProvider, this._valueTypeSerializer);
      }
      catch (Exception localException)
      {
        wrapAndThrow(paramSerializerProvider, localException, paramMap, "" + localObject5);
      }
      break;
      JsonSerializer localJsonSerializer1 = paramSerializerProvider.findValueSerializer(localClass, this._property);
      localObject1 = localJsonSerializer1;
      localObject2 = localClass;
    }
  }
  
  public void serializeWithType(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonGenerationException
  {
    paramTypeSerializer.writeTypePrefixForObject(paramMap, paramJsonGenerator);
    if (!paramMap.isEmpty())
    {
      if (this._valueSerializer == null) {
        break label42;
      }
      serializeFieldsUsing(paramMap, paramJsonGenerator, paramSerializerProvider, this._valueSerializer);
    }
    for (;;)
    {
      paramTypeSerializer.writeTypeSuffixForObject(paramMap, paramJsonGenerator);
      return;
      label42:
      serializeFields(paramMap, paramJsonGenerator, paramSerializerProvider);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/ser/std/MapSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */