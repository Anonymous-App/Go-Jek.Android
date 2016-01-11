package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.Collection;

@JacksonStdImpl
public final class StringCollectionDeserializer
  extends ContainerDeserializerBase<Collection<String>>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = 1L;
  protected final JavaType _collectionType;
  protected final JsonDeserializer<Object> _delegateDeserializer;
  protected final JsonDeserializer<String> _valueDeserializer;
  protected final ValueInstantiator _valueInstantiator;
  
  public StringCollectionDeserializer(JavaType paramJavaType, JsonDeserializer<?> paramJsonDeserializer, ValueInstantiator paramValueInstantiator)
  {
    this(paramJavaType, paramValueInstantiator, null, paramJsonDeserializer);
  }
  
  protected StringCollectionDeserializer(JavaType paramJavaType, ValueInstantiator paramValueInstantiator, JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2)
  {
    super(paramJavaType);
    this._collectionType = paramJavaType;
    this._valueDeserializer = paramJsonDeserializer2;
    this._valueInstantiator = paramValueInstantiator;
    this._delegateDeserializer = paramJsonDeserializer1;
  }
  
  private Collection<String> deserializeUsingCustom(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<String> paramCollection, JsonDeserializer<String> paramJsonDeserializer)
    throws IOException
  {
    Object localObject;
    if (paramJsonParser.nextTextValue() == null)
    {
      localObject = paramJsonParser.getCurrentToken();
      if (localObject == JsonToken.END_ARRAY) {
        return paramCollection;
      }
      if (localObject == JsonToken.VALUE_NULL) {
        localObject = (String)paramJsonDeserializer.getNullValue(paramDeserializationContext);
      }
    }
    for (;;)
    {
      paramCollection.add(localObject);
      break;
      localObject = (String)paramJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      continue;
      localObject = (String)paramJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
    }
  }
  
  private final Collection<String> handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<String> paramCollection)
    throws IOException
  {
    if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
      throw paramDeserializationContext.mappingException(this._collectionType.getRawClass());
    }
    JsonDeserializer localJsonDeserializer = this._valueDeserializer;
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL)
    {
      if (localJsonDeserializer == null) {}
      for (paramJsonParser = null;; paramJsonParser = (String)localJsonDeserializer.getNullValue(paramDeserializationContext))
      {
        paramCollection.add(paramJsonParser);
        return paramCollection;
      }
    }
    if (localJsonDeserializer == null) {}
    for (paramJsonParser = _parseString(paramJsonParser, paramDeserializationContext);; paramJsonParser = (String)localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext)) {
      break;
    }
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject1 = null;
    Object localObject2 = localObject1;
    if (this._valueInstantiator != null)
    {
      localObject2 = localObject1;
      if (this._valueInstantiator.getDelegateCreator() != null) {
        localObject2 = findDeserializer(paramDeserializationContext, this._valueInstantiator.getDelegateType(paramDeserializationContext.getConfig()), paramBeanProperty);
      }
    }
    localObject1 = this._valueDeserializer;
    JavaType localJavaType = this._collectionType.getContentType();
    if (localObject1 == null)
    {
      JsonDeserializer localJsonDeserializer = findConvertingContentDeserializer(paramDeserializationContext, paramBeanProperty, (JsonDeserializer)localObject1);
      localObject1 = localJsonDeserializer;
      if (localJsonDeserializer != null) {}
    }
    for (localObject1 = paramDeserializationContext.findContextualValueDeserializer(localJavaType, paramBeanProperty);; localObject1 = paramDeserializationContext.handleSecondaryContextualization((JsonDeserializer)localObject1, paramBeanProperty, localJavaType))
    {
      paramDeserializationContext = (DeserializationContext)localObject1;
      if (isDefaultDeserializer((JsonDeserializer)localObject1)) {
        paramDeserializationContext = null;
      }
      return withResolved((JsonDeserializer)localObject2, paramDeserializationContext);
    }
  }
  
  public Collection<String> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (this._delegateDeserializer != null) {
      return (Collection)this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
    }
    return deserialize(paramJsonParser, paramDeserializationContext, (Collection)this._valueInstantiator.createUsingDefault(paramDeserializationContext));
  }
  
  public Collection<String> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<String> paramCollection)
    throws IOException
  {
    Object localObject;
    if (!paramJsonParser.isExpectedStartArrayToken())
    {
      localObject = handleNonArray(paramJsonParser, paramDeserializationContext, paramCollection);
      return (Collection<String>)localObject;
    }
    if (this._valueDeserializer != null) {
      return deserializeUsingCustom(paramJsonParser, paramDeserializationContext, paramCollection, this._valueDeserializer);
    }
    for (;;)
    {
      String str;
      try
      {
        str = paramJsonParser.nextTextValue();
        if (str != null)
        {
          paramCollection.add(str);
          continue;
        }
        localJsonToken = paramJsonParser.getCurrentToken();
      }
      catch (Exception paramJsonParser)
      {
        throw JsonMappingException.wrapWithPath(paramJsonParser, paramCollection, paramCollection.size());
      }
      JsonToken localJsonToken;
      localObject = paramCollection;
      if (localJsonToken == JsonToken.END_ARRAY) {
        break;
      }
      localObject = str;
      if (localJsonToken != JsonToken.VALUE_NULL) {
        localObject = _parseString(paramJsonParser, paramDeserializationContext);
      }
      paramCollection.add(localObject);
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    return paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonDeserializer<Object> getContentDeserializer()
  {
    return this._valueDeserializer;
  }
  
  public JavaType getContentType()
  {
    return this._collectionType.getContentType();
  }
  
  public boolean isCachable()
  {
    return (this._valueDeserializer == null) && (this._delegateDeserializer == null);
  }
  
  protected StringCollectionDeserializer withResolved(JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2)
  {
    if ((this._valueDeserializer == paramJsonDeserializer2) && (this._delegateDeserializer == paramJsonDeserializer1)) {
      return this;
    }
    return new StringCollectionDeserializer(this._collectionType, this._valueInstantiator, paramJsonDeserializer1, paramJsonDeserializer2);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/deser/std/StringCollectionDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */