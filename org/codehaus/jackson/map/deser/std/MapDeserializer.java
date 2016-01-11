package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty.Std;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.ResolvableDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.deser.SettableBeanProperty;
import org.codehaus.jackson.map.deser.ValueInstantiator;
import org.codehaus.jackson.map.deser.impl.PropertyBasedCreator;
import org.codehaus.jackson.map.deser.impl.PropertyValueBuffer;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.type.JavaType;

@JacksonStdImpl
public class MapDeserializer
  extends ContainerDeserializerBase<Map<Object, Object>>
  implements ResolvableDeserializer
{
  protected JsonDeserializer<Object> _delegateDeserializer;
  protected final boolean _hasDefaultCreator;
  protected HashSet<String> _ignorableProperties;
  protected final KeyDeserializer _keyDeserializer;
  protected final JavaType _mapType;
  protected PropertyBasedCreator _propertyBasedCreator;
  protected final JsonDeserializer<Object> _valueDeserializer;
  protected final ValueInstantiator _valueInstantiator;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  protected MapDeserializer(MapDeserializer paramMapDeserializer)
  {
    super(paramMapDeserializer._valueClass);
    this._mapType = paramMapDeserializer._mapType;
    this._keyDeserializer = paramMapDeserializer._keyDeserializer;
    this._valueDeserializer = paramMapDeserializer._valueDeserializer;
    this._valueTypeDeserializer = paramMapDeserializer._valueTypeDeserializer;
    this._valueInstantiator = paramMapDeserializer._valueInstantiator;
    this._propertyBasedCreator = paramMapDeserializer._propertyBasedCreator;
    this._delegateDeserializer = paramMapDeserializer._delegateDeserializer;
    this._hasDefaultCreator = paramMapDeserializer._hasDefaultCreator;
    this._ignorableProperties = paramMapDeserializer._ignorableProperties;
  }
  
  @Deprecated
  protected MapDeserializer(JavaType paramJavaType, Constructor<Map<Object, Object>> paramConstructor, KeyDeserializer paramKeyDeserializer, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    super(Map.class);
    this._mapType = paramJavaType;
    this._keyDeserializer = paramKeyDeserializer;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
    paramJavaType = new StdValueInstantiator(null, paramJavaType);
    if (paramConstructor != null) {
      paramJavaType.configureFromObjectSettings(new AnnotatedConstructor(paramConstructor, null, null), null, null, null, null);
    }
    if (paramConstructor != null) {}
    for (boolean bool = true;; bool = false)
    {
      this._hasDefaultCreator = bool;
      this._valueInstantiator = paramJavaType;
      return;
    }
  }
  
  public MapDeserializer(JavaType paramJavaType, ValueInstantiator paramValueInstantiator, KeyDeserializer paramKeyDeserializer, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    super(Map.class);
    this._mapType = paramJavaType;
    this._keyDeserializer = paramKeyDeserializer;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
    this._valueInstantiator = paramValueInstantiator;
    if (paramValueInstantiator.canCreateFromObjectWith()) {}
    for (this._propertyBasedCreator = new PropertyBasedCreator(paramValueInstantiator);; this._propertyBasedCreator = null)
    {
      this._hasDefaultCreator = paramValueInstantiator.canCreateUsingDefault();
      return;
    }
  }
  
  public Map<Object, Object> _deserializeUsingCreator(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    PropertyBasedCreator localPropertyBasedCreator = this._propertyBasedCreator;
    PropertyValueBuffer localPropertyValueBuffer = localPropertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext);
    Object localObject2 = paramJsonParser.getCurrentToken();
    Object localObject1 = localObject2;
    if (localObject2 == JsonToken.START_OBJECT) {
      localObject1 = paramJsonParser.nextToken();
    }
    localObject2 = this._valueDeserializer;
    TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
    if (localObject1 == JsonToken.FIELD_NAME)
    {
      Object localObject3 = paramJsonParser.getCurrentName();
      localObject1 = paramJsonParser.nextToken();
      if ((this._ignorableProperties != null) && (this._ignorableProperties.contains(localObject3))) {
        paramJsonParser.skipChildren();
      }
      do
      {
        localObject1 = paramJsonParser.nextToken();
        break;
        localObject3 = localPropertyBasedCreator.findCreatorProperty((String)localObject3);
        if (localObject3 == null) {
          break label175;
        }
        localObject1 = ((SettableBeanProperty)localObject3).deserialize(paramJsonParser, paramDeserializationContext);
      } while (!localPropertyValueBuffer.assignParameter(((SettableBeanProperty)localObject3).getPropertyIndex(), localObject1));
      paramJsonParser.nextToken();
      try
      {
        localObject1 = (Map)localPropertyBasedCreator.build(localPropertyValueBuffer);
        _readAndBind(paramJsonParser, paramDeserializationContext, (Map)localObject1);
        return (Map<Object, Object>)localObject1;
      }
      catch (Exception paramJsonParser)
      {
        wrapAndThrow(paramJsonParser, this._mapType.getRawClass());
        return null;
      }
      label175:
      localObject3 = paramJsonParser.getCurrentName();
      localObject3 = this._keyDeserializer.deserializeKey((String)localObject3, paramDeserializationContext);
      if (localObject1 == JsonToken.VALUE_NULL) {
        localObject1 = null;
      }
      for (;;)
      {
        localPropertyValueBuffer.bufferMapProperty(localObject3, localObject1);
        break;
        if (localTypeDeserializer == null) {
          localObject1 = ((JsonDeserializer)localObject2).deserialize(paramJsonParser, paramDeserializationContext);
        } else {
          localObject1 = ((JsonDeserializer)localObject2).deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
        }
      }
    }
    try
    {
      paramJsonParser = (Map)localPropertyBasedCreator.build(localPropertyValueBuffer);
      return paramJsonParser;
    }
    catch (Exception paramJsonParser)
    {
      wrapAndThrow(paramJsonParser, this._mapType.getRawClass());
    }
    return null;
  }
  
  protected final void _readAndBind(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Map<Object, Object> paramMap)
    throws IOException, JsonProcessingException
  {
    Object localObject2 = paramJsonParser.getCurrentToken();
    Object localObject1 = localObject2;
    if (localObject2 == JsonToken.START_OBJECT) {
      localObject1 = paramJsonParser.nextToken();
    }
    localObject2 = this._keyDeserializer;
    JsonDeserializer localJsonDeserializer = this._valueDeserializer;
    TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
    while (localObject1 == JsonToken.FIELD_NAME)
    {
      localObject1 = paramJsonParser.getCurrentName();
      Object localObject3 = ((KeyDeserializer)localObject2).deserializeKey((String)localObject1, paramDeserializationContext);
      JsonToken localJsonToken = paramJsonParser.nextToken();
      if ((this._ignorableProperties != null) && (this._ignorableProperties.contains(localObject1)))
      {
        paramJsonParser.skipChildren();
        localObject1 = paramJsonParser.nextToken();
      }
      else
      {
        if (localJsonToken == JsonToken.VALUE_NULL) {
          localObject1 = null;
        }
        for (;;)
        {
          paramMap.put(localObject3, localObject1);
          break;
          if (localTypeDeserializer == null) {
            localObject1 = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
          } else {
            localObject1 = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
          }
        }
      }
    }
  }
  
  public Map<Object, Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (this._propertyBasedCreator != null) {
      return _deserializeUsingCreator(paramJsonParser, paramDeserializationContext);
    }
    if (this._delegateDeserializer != null) {
      return (Map)this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
    }
    if (!this._hasDefaultCreator) {
      throw paramDeserializationContext.instantiationException(getMapClass(), "No default constructor found");
    }
    Object localObject = paramJsonParser.getCurrentToken();
    if ((localObject != JsonToken.START_OBJECT) && (localObject != JsonToken.FIELD_NAME) && (localObject != JsonToken.END_OBJECT))
    {
      if (localObject == JsonToken.VALUE_STRING) {
        return (Map)this._valueInstantiator.createFromString(paramJsonParser.getText());
      }
      throw paramDeserializationContext.mappingException(getMapClass());
    }
    localObject = (Map)this._valueInstantiator.createUsingDefault();
    _readAndBind(paramJsonParser, paramDeserializationContext, (Map)localObject);
    return (Map<Object, Object>)localObject;
  }
  
  public Map<Object, Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Map<Object, Object> paramMap)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if ((localJsonToken != JsonToken.START_OBJECT) && (localJsonToken != JsonToken.FIELD_NAME)) {
      throw paramDeserializationContext.mappingException(getMapClass());
    }
    _readAndBind(paramJsonParser, paramDeserializationContext, paramMap);
    return paramMap;
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return paramTypeDeserializer.deserializeTypedFromObject(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonDeserializer<Object> getContentDeserializer()
  {
    return this._valueDeserializer;
  }
  
  public JavaType getContentType()
  {
    return this._mapType.getContentType();
  }
  
  public final Class<?> getMapClass()
  {
    return this._mapType.getRawClass();
  }
  
  public JavaType getValueType()
  {
    return this._mapType;
  }
  
  public void resolve(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider)
    throws JsonMappingException
  {
    Object localObject;
    if (this._valueInstantiator.canCreateUsingDelegate())
    {
      localObject = this._valueInstantiator.getDelegateType();
      if (localObject == null) {
        throw new IllegalArgumentException("Invalid delegate-creator definition for " + this._mapType + ": value instantiator (" + this._valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
      }
      this._delegateDeserializer = findDeserializer(paramDeserializationConfig, paramDeserializerProvider, (JavaType)localObject, new BeanProperty.Std(null, (JavaType)localObject, null, this._valueInstantiator.getDelegateCreator()));
    }
    if (this._propertyBasedCreator != null)
    {
      localObject = this._propertyBasedCreator.getCreatorProperties().iterator();
      while (((Iterator)localObject).hasNext())
      {
        SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)((Iterator)localObject).next();
        if (!localSettableBeanProperty.hasValueDeserializer()) {
          this._propertyBasedCreator.assignDeserializer(localSettableBeanProperty, findDeserializer(paramDeserializationConfig, paramDeserializerProvider, localSettableBeanProperty.getType(), localSettableBeanProperty));
        }
      }
    }
  }
  
  public void setIgnorableProperties(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {}
    for (paramArrayOfString = null;; paramArrayOfString = ArrayBuilders.arrayToSet(paramArrayOfString))
    {
      this._ignorableProperties = paramArrayOfString;
      return;
    }
  }
  
  protected void wrapAndThrow(Throwable paramThrowable, Object paramObject)
    throws IOException
  {
    while (((paramThrowable instanceof InvocationTargetException)) && (paramThrowable.getCause() != null)) {
      paramThrowable = paramThrowable.getCause();
    }
    if ((paramThrowable instanceof Error)) {
      throw ((Error)paramThrowable);
    }
    if (((paramThrowable instanceof IOException)) && (!(paramThrowable instanceof JsonMappingException))) {
      throw ((IOException)paramThrowable);
    }
    throw JsonMappingException.wrapWithPath(paramThrowable, paramObject, null);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/deser/std/MapDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */