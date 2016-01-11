package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

@JacksonStdImpl
public class MapEntryDeserializer
  extends ContainerDeserializerBase<Map.Entry<Object, Object>>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = 1L;
  protected final KeyDeserializer _keyDeserializer;
  protected final JavaType _type;
  protected final JsonDeserializer<Object> _valueDeserializer;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  public MapEntryDeserializer(JavaType paramJavaType, KeyDeserializer paramKeyDeserializer, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    super(paramJavaType);
    if (paramJavaType.containedTypeCount() != 2) {
      throw new IllegalArgumentException("Missing generic type information for " + paramJavaType);
    }
    this._type = paramJavaType;
    this._keyDeserializer = paramKeyDeserializer;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
  }
  
  protected MapEntryDeserializer(MapEntryDeserializer paramMapEntryDeserializer)
  {
    super(paramMapEntryDeserializer._type);
    this._type = paramMapEntryDeserializer._type;
    this._keyDeserializer = paramMapEntryDeserializer._keyDeserializer;
    this._valueDeserializer = paramMapEntryDeserializer._valueDeserializer;
    this._valueTypeDeserializer = paramMapEntryDeserializer._valueTypeDeserializer;
  }
  
  protected MapEntryDeserializer(MapEntryDeserializer paramMapEntryDeserializer, KeyDeserializer paramKeyDeserializer, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    super(paramMapEntryDeserializer._type);
    this._type = paramMapEntryDeserializer._type;
    this._keyDeserializer = paramKeyDeserializer;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject2 = this._keyDeserializer;
    Object localObject1;
    Object localObject3;
    if (localObject2 == null)
    {
      localObject1 = paramDeserializationContext.findKeyDeserializer(this._type.containedType(0), paramBeanProperty);
      localObject2 = findConvertingContentDeserializer(paramDeserializationContext, paramBeanProperty, this._valueDeserializer);
      localObject3 = this._type.containedType(1);
      if (localObject2 != null) {
        break label119;
      }
    }
    label119:
    for (paramDeserializationContext = paramDeserializationContext.findContextualValueDeserializer((JavaType)localObject3, paramBeanProperty);; paramDeserializationContext = paramDeserializationContext.handleSecondaryContextualization((JsonDeserializer)localObject2, paramBeanProperty, (JavaType)localObject3))
    {
      localObject3 = this._valueTypeDeserializer;
      localObject2 = localObject3;
      if (localObject3 != null) {
        localObject2 = ((TypeDeserializer)localObject3).forProperty(paramBeanProperty);
      }
      return withResolved((KeyDeserializer)localObject1, (TypeDeserializer)localObject2, paramDeserializationContext);
      localObject1 = localObject2;
      if (!(localObject2 instanceof ContextualKeyDeserializer)) {
        break;
      }
      localObject1 = ((ContextualKeyDeserializer)localObject2).createContextual(paramDeserializationContext, paramBeanProperty);
      break;
    }
  }
  
  public Map.Entry<Object, Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject2 = paramJsonParser.getCurrentToken();
    if ((localObject2 != JsonToken.START_OBJECT) && (localObject2 != JsonToken.FIELD_NAME) && (localObject2 != JsonToken.END_OBJECT)) {
      return (Map.Entry)_deserializeFromEmpty(paramJsonParser, paramDeserializationContext);
    }
    Object localObject1 = localObject2;
    if (localObject2 == JsonToken.START_OBJECT) {
      localObject1 = paramJsonParser.nextToken();
    }
    if (localObject1 != JsonToken.FIELD_NAME)
    {
      if (localObject1 == JsonToken.END_OBJECT) {
        throw paramDeserializationContext.mappingException("Can not deserialize a Map.Entry out of empty JSON Object");
      }
      throw paramDeserializationContext.mappingException(handledType(), (JsonToken)localObject1);
    }
    localObject1 = this._keyDeserializer;
    localObject2 = this._valueDeserializer;
    TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
    String str = paramJsonParser.getCurrentName();
    Object localObject3 = ((KeyDeserializer)localObject1).deserializeKey(str, paramDeserializationContext);
    localObject1 = null;
    JsonToken localJsonToken = paramJsonParser.nextToken();
    for (;;)
    {
      try
      {
        if (localJsonToken != JsonToken.VALUE_NULL) {
          continue;
        }
        localObject2 = ((JsonDeserializer)localObject2).getNullValue(paramDeserializationContext);
        localObject1 = localObject2;
      }
      catch (Exception localException)
      {
        wrapAndThrow(localException, Map.Entry.class, str);
        continue;
        throw paramDeserializationContext.mappingException("Problem binding JSON into Map.Entry: unexpected content after JSON Object entry: " + localException);
      }
      localObject2 = paramJsonParser.nextToken();
      if (localObject2 == JsonToken.END_OBJECT) {
        break label277;
      }
      if (localObject2 != JsonToken.FIELD_NAME) {
        continue;
      }
      throw paramDeserializationContext.mappingException("Problem binding JSON into Map.Entry: more than one entry in JSON (second field: '" + paramJsonParser.getCurrentName() + "')");
      if (localTypeDeserializer == null)
      {
        localObject2 = ((JsonDeserializer)localObject2).deserialize(paramJsonParser, paramDeserializationContext);
        localObject1 = localObject2;
      }
      else
      {
        localObject2 = ((JsonDeserializer)localObject2).deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
        localObject1 = localObject2;
      }
    }
    label277:
    return new AbstractMap.SimpleEntry(localObject3, localObject1);
  }
  
  public Map.Entry<Object, Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Map.Entry<Object, Object> paramEntry)
    throws IOException
  {
    throw new IllegalStateException("Can not update Map.Entry values");
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
    return this._type.containedType(1);
  }
  
  public JavaType getValueType()
  {
    return this._type;
  }
  
  protected MapEntryDeserializer withResolved(KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
  {
    if ((this._keyDeserializer == paramKeyDeserializer) && (this._valueDeserializer == paramJsonDeserializer) && (this._valueTypeDeserializer == paramTypeDeserializer)) {
      return this;
    }
    return new MapEntryDeserializer(this, paramKeyDeserializer, paramJsonDeserializer, paramTypeDeserializer);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/deser/std/MapEntryDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */