package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.lang.reflect.Array;

@JacksonStdImpl
public class ObjectArrayDeserializer
  extends ContainerDeserializerBase<Object[]>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = 1L;
  protected final ArrayType _arrayType;
  protected final Class<?> _elementClass;
  protected JsonDeserializer<Object> _elementDeserializer;
  protected final TypeDeserializer _elementTypeDeserializer;
  protected final boolean _untyped;
  
  public ObjectArrayDeserializer(ArrayType paramArrayType, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    super(paramArrayType);
    this._arrayType = paramArrayType;
    this._elementClass = paramArrayType.getContentType().getRawClass();
    if (this._elementClass == Object.class) {}
    for (boolean bool = true;; bool = false)
    {
      this._untyped = bool;
      this._elementDeserializer = paramJsonDeserializer;
      this._elementTypeDeserializer = paramTypeDeserializer;
      return;
    }
  }
  
  private final Object[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
      return null;
    }
    if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
    {
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (this._elementClass == Byte.class)) {
        return deserializeFromBase64(paramJsonParser, paramDeserializationContext);
      }
      throw paramDeserializationContext.mappingException(this._arrayType.getRawClass());
    }
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL)
    {
      paramJsonParser = this._elementDeserializer.getNullValue(paramDeserializationContext);
      if (!this._untyped) {
        break label154;
      }
    }
    label154:
    for (paramDeserializationContext = new Object[1];; paramDeserializationContext = (Object[])Array.newInstance(this._elementClass, 1))
    {
      paramDeserializationContext[0] = paramJsonParser;
      return paramDeserializationContext;
      if (this._elementTypeDeserializer == null)
      {
        paramJsonParser = this._elementDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
        break;
      }
      paramJsonParser = this._elementDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, this._elementTypeDeserializer);
      break;
    }
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject1 = findConvertingContentDeserializer(paramDeserializationContext, paramBeanProperty, this._elementDeserializer);
    Object localObject2 = this._arrayType.getContentType();
    if (localObject1 == null) {}
    for (paramDeserializationContext = paramDeserializationContext.findContextualValueDeserializer((JavaType)localObject2, paramBeanProperty);; paramDeserializationContext = paramDeserializationContext.handleSecondaryContextualization((JsonDeserializer)localObject1, paramBeanProperty, (JavaType)localObject2))
    {
      localObject2 = this._elementTypeDeserializer;
      localObject1 = localObject2;
      if (localObject2 != null) {
        localObject1 = ((TypeDeserializer)localObject2).forProperty(paramBeanProperty);
      }
      return withDeserializer((TypeDeserializer)localObject1, paramDeserializationContext);
    }
  }
  
  public Object[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (!paramJsonParser.isExpectedStartArrayToken()) {
      return handleNonArray(paramJsonParser, paramDeserializationContext);
    }
    ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object localObject2 = localObjectBuffer.resetAndStart();
    int i = 0;
    TypeDeserializer localTypeDeserializer = this._elementTypeDeserializer;
    label140:
    label156:
    label196:
    for (;;)
    {
      try
      {
        localObject1 = paramJsonParser.nextToken();
        if (localObject1 == JsonToken.END_ARRAY) {
          break label156;
        }
        if (localObject1 == JsonToken.VALUE_NULL)
        {
          localObject1 = this._elementDeserializer.getNullValue(paramDeserializationContext);
          if (i < localObject2.length) {
            break label196;
          }
          Object[] arrayOfObject = localObjectBuffer.appendCompletedChunk((Object[])localObject2);
          i = 0;
          localObject2 = arrayOfObject;
          int j = i + 1;
          localObject2[i] = localObject1;
          i = j;
          continue;
        }
        if (localTypeDeserializer != null) {
          break label140;
        }
      }
      catch (Exception paramJsonParser)
      {
        throw JsonMappingException.wrapWithPath(paramJsonParser, localObject2, localObjectBuffer.bufferedSize() + i);
      }
      Object localObject1 = this._elementDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      continue;
      localObject1 = this._elementDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
      continue;
      if (this._untyped) {}
      for (paramJsonParser = localObjectBuffer.completeAndClearBuffer((Object[])localObject2, i);; paramJsonParser = localObjectBuffer.completeAndClearBuffer((Object[])localObject2, i, this._elementClass))
      {
        paramDeserializationContext.returnObjectBuffer(localObjectBuffer);
        return paramJsonParser;
      }
    }
  }
  
  protected Byte[] deserializeFromBase64(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    paramJsonParser = paramJsonParser.getBinaryValue(paramDeserializationContext.getBase64Variant());
    paramDeserializationContext = new Byte[paramJsonParser.length];
    int i = 0;
    int j = paramJsonParser.length;
    while (i < j)
    {
      paramDeserializationContext[i] = Byte.valueOf(paramJsonParser[i]);
      i += 1;
    }
    return paramDeserializationContext;
  }
  
  public Object[] deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return (Object[])paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonDeserializer<Object> getContentDeserializer()
  {
    return this._elementDeserializer;
  }
  
  public JavaType getContentType()
  {
    return this._arrayType.getContentType();
  }
  
  public boolean isCachable()
  {
    return (this._elementDeserializer == null) && (this._elementTypeDeserializer == null);
  }
  
  public ObjectArrayDeserializer withDeserializer(TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
  {
    if ((paramJsonDeserializer == this._elementDeserializer) && (paramTypeDeserializer == this._elementTypeDeserializer)) {
      return this;
    }
    return new ObjectArrayDeserializer(this._arrayType, paramJsonDeserializer, paramTypeDeserializer);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/deser/std/ObjectArrayDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */