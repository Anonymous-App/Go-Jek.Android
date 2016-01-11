package org.codehaus.jackson.map.jsontype.impl;

import java.io.IOException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.type.JavaType;

public class AsArrayTypeDeserializer
  extends TypeDeserializerBase
{
  @Deprecated
  public AsArrayTypeDeserializer(JavaType paramJavaType, TypeIdResolver paramTypeIdResolver, BeanProperty paramBeanProperty)
  {
    this(paramJavaType, paramTypeIdResolver, paramBeanProperty, null);
  }
  
  public AsArrayTypeDeserializer(JavaType paramJavaType, TypeIdResolver paramTypeIdResolver, BeanProperty paramBeanProperty, Class<?> paramClass)
  {
    super(paramJavaType, paramTypeIdResolver, paramBeanProperty, paramClass);
  }
  
  private final Object _deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    boolean bool = paramJsonParser.isExpectedStartArrayToken();
    Object localObject = _findDeserializer(paramDeserializationContext, _locateTypeId(paramJsonParser, paramDeserializationContext)).deserialize(paramJsonParser, paramDeserializationContext);
    if ((bool) && (paramJsonParser.nextToken() != JsonToken.END_ARRAY)) {
      throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "expected closing END_ARRAY after type information and deserialized value");
    }
    return localObject;
  }
  
  protected final String _locateTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (!paramJsonParser.isExpectedStartArrayToken())
    {
      if (((this._idResolver instanceof TypeIdResolverBase)) && (this._defaultImpl != null)) {
        return ((TypeIdResolverBase)this._idResolver).idFromBaseType();
      }
      throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.START_ARRAY, "need JSON Array to contain As.WRAPPER_ARRAY type information for class " + baseTypeName());
    }
    if (paramJsonParser.nextToken() != JsonToken.VALUE_STRING)
    {
      if (((this._idResolver instanceof TypeIdResolverBase)) && (this._defaultImpl != null)) {
        return ((TypeIdResolverBase)this._idResolver).idFromBaseType();
      }
      throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.VALUE_STRING, "need JSON String that contains type id (for subtype of " + baseTypeName() + ")");
    }
    paramDeserializationContext = paramJsonParser.getText();
    paramJsonParser.nextToken();
    return paramDeserializationContext;
  }
  
  public Object deserializeTypedFromAny(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    return _deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  public Object deserializeTypedFromArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    return _deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  public Object deserializeTypedFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    return _deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  public Object deserializeTypedFromScalar(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    return _deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonTypeInfo.As getTypeInclusion()
  {
    return JsonTypeInfo.As.WRAPPER_ARRAY;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/jsontype/impl/AsArrayTypeDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */