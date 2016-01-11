package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonMapFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

@JacksonStdImpl
public class MapSerializer
  extends ContainerSerializer<Map<?, ?>>
  implements ContextualSerializer
{
  protected static final JavaType UNSPECIFIED_TYPE = ;
  private static final long serialVersionUID = 1L;
  protected PropertySerializerMap _dynamicValueSerializers;
  protected final Object _filterId;
  protected final HashSet<String> _ignoredEntries;
  protected JsonSerializer<Object> _keySerializer;
  protected final JavaType _keyType;
  protected final BeanProperty _property;
  protected final boolean _sortKeys;
  protected final Object _suppressableValue;
  protected JsonSerializer<Object> _valueSerializer;
  protected final JavaType _valueType;
  protected final boolean _valueTypeIsStatic;
  protected final TypeSerializer _valueTypeSerializer;
  
  protected MapSerializer(MapSerializer paramMapSerializer, BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer1, JsonSerializer<?> paramJsonSerializer2, HashSet<String> paramHashSet)
  {
    super(Map.class, false);
    this._ignoredEntries = paramHashSet;
    this._keyType = paramMapSerializer._keyType;
    this._valueType = paramMapSerializer._valueType;
    this._valueTypeIsStatic = paramMapSerializer._valueTypeIsStatic;
    this._valueTypeSerializer = paramMapSerializer._valueTypeSerializer;
    this._keySerializer = paramJsonSerializer1;
    this._valueSerializer = paramJsonSerializer2;
    this._dynamicValueSerializers = paramMapSerializer._dynamicValueSerializers;
    this._property = paramBeanProperty;
    this._filterId = paramMapSerializer._filterId;
    this._sortKeys = paramMapSerializer._sortKeys;
    this._suppressableValue = paramMapSerializer._suppressableValue;
  }
  
  @Deprecated
  protected MapSerializer(MapSerializer paramMapSerializer, TypeSerializer paramTypeSerializer)
  {
    this(paramMapSerializer, paramTypeSerializer, paramMapSerializer._suppressableValue);
  }
  
  protected MapSerializer(MapSerializer paramMapSerializer, TypeSerializer paramTypeSerializer, Object paramObject)
  {
    super(Map.class, false);
    this._ignoredEntries = paramMapSerializer._ignoredEntries;
    this._keyType = paramMapSerializer._keyType;
    this._valueType = paramMapSerializer._valueType;
    this._valueTypeIsStatic = paramMapSerializer._valueTypeIsStatic;
    this._valueTypeSerializer = paramTypeSerializer;
    this._keySerializer = paramMapSerializer._keySerializer;
    this._valueSerializer = paramMapSerializer._valueSerializer;
    this._dynamicValueSerializers = paramMapSerializer._dynamicValueSerializers;
    this._property = paramMapSerializer._property;
    this._filterId = paramMapSerializer._filterId;
    this._sortKeys = paramMapSerializer._sortKeys;
    paramMapSerializer = (MapSerializer)paramObject;
    if (paramObject == JsonInclude.Include.NON_ABSENT) {
      if (!this._valueType.isReferenceType()) {
        break label121;
      }
    }
    label121:
    for (paramMapSerializer = JsonInclude.Include.NON_EMPTY;; paramMapSerializer = JsonInclude.Include.NON_NULL)
    {
      this._suppressableValue = paramMapSerializer;
      return;
    }
  }
  
  protected MapSerializer(MapSerializer paramMapSerializer, Object paramObject, boolean paramBoolean)
  {
    super(Map.class, false);
    this._ignoredEntries = paramMapSerializer._ignoredEntries;
    this._keyType = paramMapSerializer._keyType;
    this._valueType = paramMapSerializer._valueType;
    this._valueTypeIsStatic = paramMapSerializer._valueTypeIsStatic;
    this._valueTypeSerializer = paramMapSerializer._valueTypeSerializer;
    this._keySerializer = paramMapSerializer._keySerializer;
    this._valueSerializer = paramMapSerializer._valueSerializer;
    this._dynamicValueSerializers = paramMapSerializer._dynamicValueSerializers;
    this._property = paramMapSerializer._property;
    this._filterId = paramObject;
    this._sortKeys = paramBoolean;
    this._suppressableValue = paramMapSerializer._suppressableValue;
  }
  
  protected MapSerializer(HashSet<String> paramHashSet, JavaType paramJavaType1, JavaType paramJavaType2, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer1, JsonSerializer<?> paramJsonSerializer2)
  {
    super(Map.class, false);
    this._ignoredEntries = paramHashSet;
    this._keyType = paramJavaType1;
    this._valueType = paramJavaType2;
    this._valueTypeIsStatic = paramBoolean;
    this._valueTypeSerializer = paramTypeSerializer;
    this._keySerializer = paramJsonSerializer1;
    this._valueSerializer = paramJsonSerializer2;
    this._dynamicValueSerializers = PropertySerializerMap.emptyForProperties();
    this._property = null;
    this._filterId = null;
    this._sortKeys = false;
    this._suppressableValue = null;
  }
  
  public static MapSerializer construct(String[] paramArrayOfString, JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer1, JsonSerializer<Object> paramJsonSerializer2, Object paramObject)
  {
    Object localObject;
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
    {
      paramArrayOfString = null;
      if (paramJavaType != null) {
        break label84;
      }
      localObject = UNSPECIFIED_TYPE;
      paramJavaType = (JavaType)localObject;
      label23:
      if (paramBoolean) {
        break label111;
      }
      if ((localObject == null) || (!((JavaType)localObject).isFinal())) {
        break label106;
      }
      paramBoolean = true;
    }
    for (;;)
    {
      paramJavaType = new MapSerializer(paramArrayOfString, paramJavaType, (JavaType)localObject, paramBoolean, paramTypeSerializer, paramJsonSerializer1, paramJsonSerializer2);
      paramArrayOfString = paramJavaType;
      if (paramObject != null) {
        paramArrayOfString = paramJavaType.withFilterId(paramObject);
      }
      return paramArrayOfString;
      paramArrayOfString = ArrayBuilders.arrayToSet(paramArrayOfString);
      break;
      label84:
      localObject = paramJavaType.getKeyType();
      JavaType localJavaType = paramJavaType.getContentType();
      paramJavaType = (JavaType)localObject;
      localObject = localJavaType;
      break label23;
      label106:
      paramBoolean = false;
      continue;
      label111:
      if (((JavaType)localObject).getRawClass() == Object.class) {
        paramBoolean = false;
      }
    }
  }
  
  protected void _ensureOverride()
  {
    if (getClass() != MapSerializer.class) {
      throw new IllegalStateException("Missing override in class " + getClass().getName());
    }
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, JavaType paramJavaType, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    paramJavaType = paramPropertySerializerMap.findAndAddSecondarySerializer(paramJavaType, paramSerializerProvider, this._property);
    if (paramPropertySerializerMap != paramJavaType.map) {
      this._dynamicValueSerializers = paramJavaType.map;
    }
    return paramJavaType.serializer;
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, Class<?> paramClass, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    paramClass = paramPropertySerializerMap.findAndAddSecondarySerializer(paramClass, paramSerializerProvider, this._property);
    if (paramPropertySerializerMap != paramClass.map) {
      this._dynamicValueSerializers = paramClass.map;
    }
    return paramClass.serializer;
  }
  
  protected Map<?, ?> _orderEntries(Map<?, ?> paramMap)
  {
    if ((paramMap instanceof SortedMap)) {
      return paramMap;
    }
    return new TreeMap(paramMap);
  }
  
  public MapSerializer _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    if (this._valueTypeSerializer == paramTypeSerializer) {
      return this;
    }
    _ensureOverride();
    return new MapSerializer(this, paramTypeSerializer, null);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper == null) {}
    for (paramJavaType = null;; paramJavaType = paramJsonFormatVisitorWrapper.expectMapFormat(paramJavaType))
    {
      if (paramJavaType != null)
      {
        paramJavaType.keyFormat(this._keySerializer, this._keyType);
        JsonSerializer localJsonSerializer2 = this._valueSerializer;
        JsonSerializer localJsonSerializer1 = localJsonSerializer2;
        if (localJsonSerializer2 == null) {
          localJsonSerializer1 = _findAndAddDynamic(this._dynamicValueSerializers, this._valueType, paramJsonFormatVisitorWrapper.getProvider());
        }
        paramJavaType.valueFormat(localJsonSerializer1, this._valueType);
      }
      return;
    }
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonInclude.Include localInclude = null;
    Object localObject4 = null;
    Object localObject8 = null;
    Object localObject2 = null;
    AnnotationIntrospector localAnnotationIntrospector = paramSerializerProvider.getAnnotationIntrospector();
    Object localObject5;
    Object localObject3;
    Object localObject6;
    if (paramBeanProperty == null)
    {
      localObject5 = null;
      Object localObject7 = this._suppressableValue;
      localObject1 = localObject8;
      localObject3 = localInclude;
      localObject6 = localObject7;
      if (localObject5 != null)
      {
        localObject1 = localObject8;
        localObject3 = localInclude;
        localObject6 = localObject7;
        if (localAnnotationIntrospector != null)
        {
          localObject1 = localAnnotationIntrospector.findKeySerializer((Annotated)localObject5);
          if (localObject1 != null) {
            localObject2 = paramSerializerProvider.serializerInstance((Annotated)localObject5, localObject1);
          }
          localObject1 = localAnnotationIntrospector.findContentSerializer((Annotated)localObject5);
          if (localObject1 != null) {
            localObject4 = paramSerializerProvider.serializerInstance((Annotated)localObject5, localObject1);
          }
          localInclude = localAnnotationIntrospector.findSerializationInclusionForContent((Annotated)localObject5, null);
          localObject1 = localObject2;
          localObject3 = localObject4;
          localObject6 = localObject7;
          if (localInclude != null)
          {
            localObject6 = localInclude;
            localObject3 = localObject4;
            localObject1 = localObject2;
          }
        }
      }
      localObject2 = localObject3;
      if (localObject3 == null) {
        localObject2 = this._valueSerializer;
      }
      localObject2 = findConvertingContentSerializer(paramSerializerProvider, paramBeanProperty, (JsonSerializer)localObject2);
      if (localObject2 != null) {
        break label361;
      }
      if (((this._valueTypeIsStatic) && (this._valueType.getRawClass() != Object.class)) || (hasContentTypeAnnotation(paramSerializerProvider, paramBeanProperty))) {
        localObject2 = paramSerializerProvider.findValueSerializer(this._valueType, paramBeanProperty);
      }
      label221:
      localObject3 = localObject1;
      if (localObject1 == null) {
        localObject3 = this._keySerializer;
      }
      if (localObject3 != null) {
        break label373;
      }
      localObject3 = paramSerializerProvider.findKeySerializer(this._keyType, paramBeanProperty);
      label252:
      localObject1 = this._ignoredEntries;
      boolean bool2 = false;
      paramSerializerProvider = (SerializerProvider)localObject1;
      bool1 = bool2;
      if (localAnnotationIntrospector == null) {
        break label424;
      }
      paramSerializerProvider = (SerializerProvider)localObject1;
      bool1 = bool2;
      if (localObject5 == null) {
        break label424;
      }
      localObject4 = localAnnotationIntrospector.findPropertiesToIgnore((Annotated)localObject5, true);
      paramSerializerProvider = (SerializerProvider)localObject1;
      if (localObject4 == null) {
        break label399;
      }
      if (localObject1 != null) {
        break label385;
      }
    }
    label361:
    label373:
    label385:
    for (Object localObject1 = new HashSet();; localObject1 = new HashSet((Collection)localObject1))
    {
      int j = localObject4.length;
      int i = 0;
      for (;;)
      {
        paramSerializerProvider = (SerializerProvider)localObject1;
        if (i >= j) {
          break;
        }
        ((HashSet)localObject1).add(localObject4[i]);
        i += 1;
      }
      localObject5 = paramBeanProperty.getMember();
      break;
      localObject2 = paramSerializerProvider.handleSecondaryContextualization((JsonSerializer)localObject2, paramBeanProperty);
      break label221;
      localObject3 = paramSerializerProvider.handleSecondaryContextualization((JsonSerializer)localObject3, paramBeanProperty);
      break label252;
    }
    label399:
    localObject1 = localAnnotationIntrospector.findSerializationSortAlphabetically((Annotated)localObject5);
    if ((localObject1 != null) && (((Boolean)localObject1).booleanValue())) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      label424:
      localObject1 = withResolved(paramBeanProperty, (JsonSerializer)localObject3, (JsonSerializer)localObject2, paramSerializerProvider, bool1);
      paramSerializerProvider = (SerializerProvider)localObject1;
      if (localObject6 != this._suppressableValue) {
        paramSerializerProvider = ((MapSerializer)localObject1).withContentInclusion(localObject6);
      }
      localObject1 = paramSerializerProvider;
      if (paramBeanProperty != null)
      {
        paramBeanProperty = paramBeanProperty.getMember();
        localObject1 = paramSerializerProvider;
        if (paramBeanProperty != null)
        {
          paramBeanProperty = localAnnotationIntrospector.findFilterId(paramBeanProperty);
          localObject1 = paramSerializerProvider;
          if (paramBeanProperty != null) {
            localObject1 = paramSerializerProvider.withFilterId(paramBeanProperty);
          }
        }
      }
      return (JsonSerializer<?>)localObject1;
    }
  }
  
  public JsonSerializer<?> getContentSerializer()
  {
    return this._valueSerializer;
  }
  
  public JavaType getContentType()
  {
    return this._valueType;
  }
  
  public JsonSerializer<?> getKeySerializer()
  {
    return this._keySerializer;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    return createSchemaNode("object", true);
  }
  
  public boolean hasSingleElement(Map<?, ?> paramMap)
  {
    return paramMap.size() == 1;
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, Map<?, ?> paramMap)
  {
    return (paramMap == null) || (paramMap.isEmpty());
  }
  
  public void serialize(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    paramJsonGenerator.writeStartObject();
    paramJsonGenerator.setCurrentValue(paramMap);
    Object localObject2;
    Object localObject1;
    if (!paramMap.isEmpty())
    {
      localObject2 = this._suppressableValue;
      if (localObject2 != JsonInclude.Include.ALWAYS) {
        break label95;
      }
      localObject1 = null;
      if (!this._sortKeys)
      {
        localObject2 = paramMap;
        if (!paramSerializerProvider.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)) {}
      }
      else
      {
        localObject2 = _orderEntries(paramMap);
      }
      if (this._filterId == null) {
        break label126;
      }
      serializeFilteredFields((Map)localObject2, paramJsonGenerator, paramSerializerProvider, findPropertyFilter(paramSerializerProvider, this._filterId, localObject2), localObject1);
    }
    for (;;)
    {
      paramJsonGenerator.writeEndObject();
      return;
      label95:
      localObject1 = localObject2;
      if (localObject2 != null) {
        break;
      }
      localObject1 = localObject2;
      if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES)) {
        break;
      }
      localObject1 = JsonInclude.Include.NON_NULL;
      break;
      label126:
      if (localObject1 != null) {
        serializeOptionalFields((Map)localObject2, paramJsonGenerator, paramSerializerProvider, localObject1);
      } else if (this._valueSerializer != null) {
        serializeFieldsUsing((Map)localObject2, paramJsonGenerator, paramSerializerProvider, this._valueSerializer);
      } else {
        serializeFields((Map)localObject2, paramJsonGenerator, paramSerializerProvider);
      }
    }
  }
  
  public void serializeFields(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (this._valueTypeSerializer != null)
    {
      serializeTypedFields(paramMap, paramJsonGenerator, paramSerializerProvider, null);
      return;
    }
    JsonSerializer localJsonSerializer2 = this._keySerializer;
    HashSet localHashSet = this._ignoredEntries;
    Object localObject1 = this._dynamicValueSerializers;
    Iterator localIterator = paramMap.entrySet().iterator();
    label47:
    Object localObject3;
    Object localObject5;
    Object localObject6;
    label150:
    Class localClass;
    Object localObject4;
    if (localIterator.hasNext())
    {
      localObject3 = (Map.Entry)localIterator.next();
      localObject5 = ((Map.Entry)localObject3).getValue();
      localObject6 = ((Map.Entry)localObject3).getKey();
      if (localObject6 == null) {
        paramSerializerProvider.findNullKeySerializer(this._keyType, this._property).serialize(null, paramJsonGenerator, paramSerializerProvider);
      }
      for (;;)
      {
        if (localObject5 != null) {
          break label150;
        }
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        break;
        if ((localHashSet != null) && (localHashSet.contains(localObject6))) {
          break;
        }
        localJsonSerializer2.serialize(localObject6, paramJsonGenerator, paramSerializerProvider);
      }
      localClass = localObject5.getClass();
      JsonSerializer localJsonSerializer1 = ((PropertySerializerMap)localObject1).serializerFor(localClass);
      localObject4 = localJsonSerializer1;
      localObject3 = localObject1;
      if (localJsonSerializer1 == null) {
        if (!this._valueType.hasGenericTypes()) {
          break label272;
        }
      }
    }
    Object localObject2;
    label272:
    for (localObject1 = _findAndAddDynamic((PropertySerializerMap)localObject1, paramSerializerProvider.constructSpecializedType(this._valueType, localClass), paramSerializerProvider);; localObject2 = _findAndAddDynamic((PropertySerializerMap)localObject2, localClass, paramSerializerProvider))
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
      break label47;
      break;
    }
  }
  
  protected void serializeFieldsUsing(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException
  {
    JsonSerializer localJsonSerializer = this._keySerializer;
    HashSet localHashSet = this._ignoredEntries;
    TypeSerializer localTypeSerializer = this._valueTypeSerializer;
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = (Map.Entry)localIterator.next();
      Object localObject1 = ((Map.Entry)localObject2).getKey();
      if ((localHashSet == null) || (!localHashSet.contains(localObject1)))
      {
        if (localObject1 == null) {
          paramSerializerProvider.findNullKeySerializer(this._keyType, this._property).serialize(null, paramJsonGenerator, paramSerializerProvider);
        }
        for (;;)
        {
          localObject2 = ((Map.Entry)localObject2).getValue();
          if (localObject2 != null) {
            break label134;
          }
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          break;
          localJsonSerializer.serialize(localObject1, paramJsonGenerator, paramSerializerProvider);
        }
        label134:
        if (localTypeSerializer == null) {
          try
          {
            paramJsonSerializer.serialize(localObject2, paramJsonGenerator, paramSerializerProvider);
          }
          catch (Exception localException)
          {
            wrapAndThrow(paramSerializerProvider, localException, paramMap, "" + localObject1);
          }
        } else {
          paramJsonSerializer.serializeWithType(localException, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        }
      }
    }
  }
  
  @Deprecated
  public void serializeFilteredFields(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, PropertyFilter paramPropertyFilter)
    throws IOException
  {
    if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES)) {}
    for (Object localObject = null;; localObject = JsonInclude.Include.NON_NULL)
    {
      serializeFilteredFields(paramMap, paramJsonGenerator, paramSerializerProvider, paramPropertyFilter, localObject);
      return;
    }
  }
  
  public void serializeFilteredFields(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, PropertyFilter paramPropertyFilter, Object paramObject)
    throws IOException
  {
    HashSet localHashSet = this._ignoredEntries;
    Object localObject2 = this._dynamicValueSerializers;
    MapProperty localMapProperty = new MapProperty(this._valueTypeSerializer, this._property);
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject5 = localEntry.getKey();
      if ((localHashSet == null) || (!localHashSet.contains(localObject5)))
      {
        if (localObject5 == null) {}
        Object localObject6;
        for (JsonSerializer localJsonSerializer = paramSerializerProvider.findNullKeySerializer(this._keyType, this._property);; localJsonSerializer = this._keySerializer)
        {
          localObject6 = localEntry.getValue();
          if (localObject6 != null) {
            break label202;
          }
          if (paramObject != null) {
            break;
          }
          localObject4 = paramSerializerProvider.getDefaultNullValueSerializer();
          label132:
          localMapProperty.reset(localObject5, localJsonSerializer, (JsonSerializer)localObject4);
          try
          {
            paramPropertyFilter.serializeAsField(localObject6, paramJsonGenerator, paramSerializerProvider, localMapProperty);
          }
          catch (Exception localException)
          {
            wrapAndThrow(paramSerializerProvider, localException, paramMap, "" + localObject5);
          }
          break;
        }
        label202:
        Object localObject4 = this._valueSerializer;
        Object localObject3 = localObject2;
        Object localObject1 = localObject4;
        Class localClass;
        if (localObject4 == null)
        {
          localClass = localObject6.getClass();
          localObject4 = ((PropertySerializerMap)localObject2).serializerFor(localClass);
          localObject3 = localObject2;
          localObject1 = localObject4;
          if (localObject4 == null) {
            if (!this._valueType.hasGenericTypes()) {
              break label327;
            }
          }
        }
        label327:
        for (localObject1 = _findAndAddDynamic((PropertySerializerMap)localObject2, paramSerializerProvider.constructSpecializedType(this._valueType, localClass), paramSerializerProvider);; localObject1 = _findAndAddDynamic((PropertySerializerMap)localObject2, localClass, paramSerializerProvider))
        {
          localObject3 = this._dynamicValueSerializers;
          localObject2 = localObject3;
          localObject4 = localObject1;
          if (paramObject != JsonInclude.Include.NON_EMPTY) {
            break label132;
          }
          localObject2 = localObject3;
          localObject4 = localObject1;
          if (!((JsonSerializer)localObject1).isEmpty(paramSerializerProvider, localObject6)) {
            break label132;
          }
          localObject2 = localObject3;
          break;
        }
      }
    }
  }
  
  public void serializeOptionalFields(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, Object paramObject)
    throws IOException
  {
    if (this._valueTypeSerializer != null)
    {
      serializeTypedFields(paramMap, paramJsonGenerator, paramSerializerProvider, paramObject);
      return;
    }
    HashSet localHashSet = this._ignoredEntries;
    Object localObject2 = this._dynamicValueSerializers;
    Iterator localIterator = paramMap.entrySet().iterator();
    label42:
    Object localObject6;
    Object localObject4;
    label117:
    label196:
    Object localObject3;
    Class localClass;
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject5 = localEntry.getKey();
      if (localObject5 == null) {}
      for (JsonSerializer localJsonSerializer = paramSerializerProvider.findNullKeySerializer(this._keyType, this._property);; localJsonSerializer = this._keySerializer)
      {
        localObject6 = localEntry.getValue();
        if (localObject6 != null) {
          break label196;
        }
        if (paramObject != null) {
          break;
        }
        localObject4 = paramSerializerProvider.getDefaultNullValueSerializer();
        try
        {
          localJsonSerializer.serialize(localObject5, paramJsonGenerator, paramSerializerProvider);
          ((JsonSerializer)localObject4).serialize(localObject6, paramJsonGenerator, paramSerializerProvider);
        }
        catch (Exception localException)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramMap, "" + localObject5);
        }
        break;
        if ((localHashSet != null) && (localHashSet.contains(localObject5))) {
          break;
        }
      }
      localObject4 = this._valueSerializer;
      localObject3 = localObject2;
      localObject1 = localObject4;
      if (localObject4 == null)
      {
        localClass = localObject6.getClass();
        localObject4 = ((PropertySerializerMap)localObject2).serializerFor(localClass);
        localObject3 = localObject2;
        localObject1 = localObject4;
        if (localObject4 == null) {
          if (!this._valueType.hasGenericTypes()) {
            break label321;
          }
        }
      }
    }
    label321:
    for (Object localObject1 = _findAndAddDynamic((PropertySerializerMap)localObject2, paramSerializerProvider.constructSpecializedType(this._valueType, localClass), paramSerializerProvider);; localObject1 = _findAndAddDynamic((PropertySerializerMap)localObject2, localClass, paramSerializerProvider))
    {
      localObject3 = this._dynamicValueSerializers;
      localObject2 = localObject3;
      localObject4 = localObject1;
      if (paramObject != JsonInclude.Include.NON_EMPTY) {
        break label117;
      }
      localObject2 = localObject3;
      localObject4 = localObject1;
      if (!((JsonSerializer)localObject1).isEmpty(paramSerializerProvider, localObject6)) {
        break label117;
      }
      localObject2 = localObject3;
      break label42;
      break;
    }
  }
  
  @Deprecated
  protected void serializeTypedFields(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES)) {}
    for (Object localObject = null;; localObject = JsonInclude.Include.NON_NULL)
    {
      serializeTypedFields(paramMap, paramJsonGenerator, paramSerializerProvider, localObject);
      return;
    }
  }
  
  protected void serializeTypedFields(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, Object paramObject)
    throws IOException
  {
    HashSet localHashSet = this._ignoredEntries;
    Object localObject1 = this._dynamicValueSerializers;
    Iterator localIterator = paramMap.entrySet().iterator();
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject5 = localEntry.getKey();
      if (localObject5 == null) {}
      Object localObject6;
      for (JsonSerializer localJsonSerializer = paramSerializerProvider.findNullKeySerializer(this._keyType, this._property);; localJsonSerializer = this._keySerializer)
      {
        localObject6 = localEntry.getValue();
        if (localObject6 != null) {
          break label183;
        }
        if (paramObject != null) {
          break;
        }
        localObject4 = paramSerializerProvider.getDefaultNullValueSerializer();
        label100:
        localJsonSerializer.serialize(localObject5, paramJsonGenerator, paramSerializerProvider);
        try
        {
          ((JsonSerializer)localObject4).serializeWithType(localObject6, paramJsonGenerator, paramSerializerProvider, this._valueTypeSerializer);
        }
        catch (Exception localException)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramMap, "" + localObject5);
        }
        break;
        if ((localHashSet != null) && (localHashSet.contains(localObject5))) {
          break;
        }
      }
      label183:
      Object localObject2 = this._valueSerializer;
      Class localClass = localObject6.getClass();
      Object localObject4 = ((PropertySerializerMap)localObject1).serializerFor(localClass);
      Object localObject3 = localObject1;
      localObject2 = localObject4;
      if (localObject4 == null) {
        if (!this._valueType.hasGenericTypes()) {
          break label295;
        }
      }
      label295:
      for (localObject2 = _findAndAddDynamic((PropertySerializerMap)localObject1, paramSerializerProvider.constructSpecializedType(this._valueType, localClass), paramSerializerProvider);; localObject2 = _findAndAddDynamic((PropertySerializerMap)localObject1, localClass, paramSerializerProvider))
      {
        localObject3 = this._dynamicValueSerializers;
        localObject1 = localObject3;
        localObject4 = localObject2;
        if (paramObject != JsonInclude.Include.NON_EMPTY) {
          break label100;
        }
        localObject1 = localObject3;
        localObject4 = localObject2;
        if (!((JsonSerializer)localObject2).isEmpty(paramSerializerProvider, localObject6)) {
          break label100;
        }
        localObject1 = localObject3;
        break;
      }
    }
  }
  
  public void serializeWithType(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    paramTypeSerializer.writeTypePrefixForObject(paramMap, paramJsonGenerator);
    paramJsonGenerator.setCurrentValue(paramMap);
    Object localObject2 = paramMap;
    Object localObject1;
    if (!paramMap.isEmpty())
    {
      localObject2 = this._suppressableValue;
      if (localObject2 != JsonInclude.Include.ALWAYS) {
        break label105;
      }
      localObject1 = null;
      if (!this._sortKeys)
      {
        localObject2 = paramMap;
        if (!paramSerializerProvider.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)) {}
      }
      else
      {
        localObject2 = _orderEntries(paramMap);
      }
      if (this._filterId == null) {
        break label136;
      }
      serializeFilteredFields((Map)localObject2, paramJsonGenerator, paramSerializerProvider, findPropertyFilter(paramSerializerProvider, this._filterId, localObject2), localObject1);
    }
    for (;;)
    {
      paramTypeSerializer.writeTypeSuffixForObject(localObject2, paramJsonGenerator);
      return;
      label105:
      localObject1 = localObject2;
      if (localObject2 != null) {
        break;
      }
      localObject1 = localObject2;
      if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES)) {
        break;
      }
      localObject1 = JsonInclude.Include.NON_NULL;
      break;
      label136:
      if (localObject1 != null) {
        serializeOptionalFields((Map)localObject2, paramJsonGenerator, paramSerializerProvider, localObject1);
      } else if (this._valueSerializer != null) {
        serializeFieldsUsing((Map)localObject2, paramJsonGenerator, paramSerializerProvider, this._valueSerializer);
      } else {
        serializeFields((Map)localObject2, paramJsonGenerator, paramSerializerProvider);
      }
    }
  }
  
  public MapSerializer withContentInclusion(Object paramObject)
  {
    if (paramObject == this._suppressableValue) {
      return this;
    }
    _ensureOverride();
    return new MapSerializer(this, this._valueTypeSerializer, paramObject);
  }
  
  public MapSerializer withFilterId(Object paramObject)
  {
    if (this._filterId == paramObject) {
      return this;
    }
    _ensureOverride();
    return new MapSerializer(this, paramObject, this._sortKeys);
  }
  
  public MapSerializer withResolved(BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer1, JsonSerializer<?> paramJsonSerializer2, HashSet<String> paramHashSet, boolean paramBoolean)
  {
    _ensureOverride();
    paramJsonSerializer1 = new MapSerializer(this, paramBeanProperty, paramJsonSerializer1, paramJsonSerializer2, paramHashSet);
    paramBeanProperty = paramJsonSerializer1;
    if (paramBoolean != paramJsonSerializer1._sortKeys) {
      paramBeanProperty = new MapSerializer(paramJsonSerializer1, this._filterId, paramBoolean);
    }
    return paramBeanProperty;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ser/std/MapSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */