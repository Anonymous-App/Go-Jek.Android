package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Collection;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty.Std;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ResolvableDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.deser.ValueInstantiator;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.type.JavaType;

@JacksonStdImpl
public class CollectionDeserializer
  extends ContainerDeserializerBase<Collection<Object>>
  implements ResolvableDeserializer
{
  protected final JavaType _collectionType;
  protected JsonDeserializer<Object> _delegateDeserializer;
  protected final JsonDeserializer<Object> _valueDeserializer;
  protected final ValueInstantiator _valueInstantiator;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  protected CollectionDeserializer(CollectionDeserializer paramCollectionDeserializer)
  {
    super(paramCollectionDeserializer._valueClass);
    this._collectionType = paramCollectionDeserializer._collectionType;
    this._valueDeserializer = paramCollectionDeserializer._valueDeserializer;
    this._valueTypeDeserializer = paramCollectionDeserializer._valueTypeDeserializer;
    this._valueInstantiator = paramCollectionDeserializer._valueInstantiator;
    this._delegateDeserializer = paramCollectionDeserializer._delegateDeserializer;
  }
  
  @Deprecated
  protected CollectionDeserializer(JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer, Constructor<Collection<Object>> paramConstructor)
  {
    super(paramJavaType.getRawClass());
    this._collectionType = paramJavaType;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
    paramJavaType = new StdValueInstantiator(null, paramJavaType);
    if (paramConstructor != null) {
      paramJavaType.configureFromObjectSettings(new AnnotatedConstructor(paramConstructor, null, null), null, null, null, null);
    }
    this._valueInstantiator = paramJavaType;
  }
  
  public CollectionDeserializer(JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer, ValueInstantiator paramValueInstantiator)
  {
    super(paramJavaType.getRawClass());
    this._collectionType = paramJavaType;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
    this._valueInstantiator = paramValueInstantiator;
  }
  
  private final Collection<Object> handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<Object> paramCollection)
    throws IOException, JsonProcessingException
  {
    if (!paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
      throw paramDeserializationContext.mappingException(this._collectionType.getRawClass());
    }
    JsonDeserializer localJsonDeserializer = this._valueDeserializer;
    TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
      paramJsonParser = null;
    }
    for (;;)
    {
      paramCollection.add(paramJsonParser);
      return paramCollection;
      if (localTypeDeserializer == null) {
        paramJsonParser = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      } else {
        paramJsonParser = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
      }
    }
  }
  
  public Collection<Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (this._delegateDeserializer != null) {
      return (Collection)this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
    }
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING)
    {
      String str = paramJsonParser.getText();
      if (str.length() == 0) {
        return (Collection)this._valueInstantiator.createFromString(str);
      }
    }
    return deserialize(paramJsonParser, paramDeserializationContext, (Collection)this._valueInstantiator.createUsingDefault());
  }
  
  public Collection<Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<Object> paramCollection)
    throws IOException, JsonProcessingException
  {
    Object localObject;
    if (!paramJsonParser.isExpectedStartArrayToken()) {
      localObject = handleNonArray(paramJsonParser, paramDeserializationContext, paramCollection);
    }
    JsonDeserializer localJsonDeserializer;
    TypeDeserializer localTypeDeserializer;
    JsonToken localJsonToken;
    do
    {
      return (Collection<Object>)localObject;
      localJsonDeserializer = this._valueDeserializer;
      localTypeDeserializer = this._valueTypeDeserializer;
      localJsonToken = paramJsonParser.nextToken();
      localObject = paramCollection;
    } while (localJsonToken == JsonToken.END_ARRAY);
    if (localJsonToken == JsonToken.VALUE_NULL) {
      localObject = null;
    }
    for (;;)
    {
      paramCollection.add(localObject);
      break;
      if (localTypeDeserializer == null) {
        localObject = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      } else {
        localObject = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
      }
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
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
  
  public void resolve(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider)
    throws JsonMappingException
  {
    if (this._valueInstantiator.canCreateUsingDelegate())
    {
      JavaType localJavaType = this._valueInstantiator.getDelegateType();
      if (localJavaType == null) {
        throw new IllegalArgumentException("Invalid delegate-creator definition for " + this._collectionType + ": value instantiator (" + this._valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
      }
      this._delegateDeserializer = findDeserializer(paramDeserializationConfig, paramDeserializerProvider, localJavaType, new BeanProperty.Std(null, localJavaType, null, this._valueInstantiator.getDelegateCreator()));
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/deser/std/CollectionDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */