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
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JacksonStdImpl
public class UntypedObjectDeserializer
  extends StdDeserializer<Object>
  implements ResolvableDeserializer, ContextualDeserializer
{
  protected static final Object[] NO_OBJECTS = new Object[0];
  @Deprecated
  public static final UntypedObjectDeserializer instance = new UntypedObjectDeserializer(null, null);
  private static final long serialVersionUID = 1L;
  protected JsonDeserializer<Object> _listDeserializer;
  protected JavaType _listType;
  protected JsonDeserializer<Object> _mapDeserializer;
  protected JavaType _mapType;
  protected JsonDeserializer<Object> _numberDeserializer;
  protected JsonDeserializer<Object> _stringDeserializer;
  
  @Deprecated
  public UntypedObjectDeserializer()
  {
    this(null, null);
  }
  
  public UntypedObjectDeserializer(JavaType paramJavaType1, JavaType paramJavaType2)
  {
    super(Object.class);
    this._listType = paramJavaType1;
    this._mapType = paramJavaType2;
  }
  
  public UntypedObjectDeserializer(UntypedObjectDeserializer paramUntypedObjectDeserializer, JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2, JsonDeserializer<?> paramJsonDeserializer3, JsonDeserializer<?> paramJsonDeserializer4)
  {
    super(Object.class);
    this._mapDeserializer = paramJsonDeserializer1;
    this._listDeserializer = paramJsonDeserializer2;
    this._stringDeserializer = paramJsonDeserializer3;
    this._numberDeserializer = paramJsonDeserializer4;
    this._listType = paramUntypedObjectDeserializer._listType;
    this._mapType = paramUntypedObjectDeserializer._mapType;
  }
  
  protected JsonDeserializer<Object> _clearIfStdImpl(JsonDeserializer<Object> paramJsonDeserializer)
  {
    JsonDeserializer<Object> localJsonDeserializer = paramJsonDeserializer;
    if (ClassUtil.isJacksonStdImpl(paramJsonDeserializer)) {
      localJsonDeserializer = null;
    }
    return localJsonDeserializer;
  }
  
  protected JsonDeserializer<Object> _findCustomDeser(DeserializationContext paramDeserializationContext, JavaType paramJavaType)
    throws JsonMappingException
  {
    return paramDeserializationContext.findNonContextualValueDeserializer(paramJavaType);
  }
  
  protected JsonDeserializer<?> _withResolved(JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2, JsonDeserializer<?> paramJsonDeserializer3, JsonDeserializer<?> paramJsonDeserializer4)
  {
    return new UntypedObjectDeserializer(this, paramJsonDeserializer1, paramJsonDeserializer2, paramJsonDeserializer3, paramJsonDeserializer4);
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    paramDeserializationContext = this;
    if (this._stringDeserializer == null)
    {
      paramDeserializationContext = this;
      if (this._numberDeserializer == null)
      {
        paramDeserializationContext = this;
        if (this._mapDeserializer == null)
        {
          paramDeserializationContext = this;
          if (this._listDeserializer == null)
          {
            paramDeserializationContext = this;
            if (getClass() == UntypedObjectDeserializer.class) {
              paramDeserializationContext = Vanilla.std;
            }
          }
        }
      }
    }
    return paramDeserializationContext;
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    switch (paramJsonParser.getCurrentTokenId())
    {
    case 2: 
    case 4: 
    default: 
      throw paramDeserializationContext.mappingException(Object.class);
    case 1: 
    case 5: 
      if (this._mapDeserializer != null) {
        return this._mapDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      return mapObject(paramJsonParser, paramDeserializationContext);
    case 3: 
      if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
        return mapArrayToArray(paramJsonParser, paramDeserializationContext);
      }
      if (this._listDeserializer != null) {
        return this._listDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      return mapArray(paramJsonParser, paramDeserializationContext);
    case 12: 
      return paramJsonParser.getEmbeddedObject();
    case 6: 
      if (this._stringDeserializer != null) {
        return this._stringDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      return paramJsonParser.getText();
    case 7: 
      if (this._numberDeserializer != null) {
        return this._numberDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      if (paramDeserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
        return _coerceIntegral(paramJsonParser, paramDeserializationContext);
      }
      return paramJsonParser.getNumberValue();
    case 8: 
      if (this._numberDeserializer != null) {
        return this._numberDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
        return paramJsonParser.getDecimalValue();
      }
      return Double.valueOf(paramJsonParser.getDoubleValue());
    case 9: 
      return Boolean.TRUE;
    case 10: 
      return Boolean.FALSE;
    }
    return null;
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    switch (paramJsonParser.getCurrentTokenId())
    {
    case 2: 
    case 4: 
    default: 
      throw paramDeserializationContext.mappingException(Object.class);
    case 1: 
    case 3: 
    case 5: 
      return paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
    case 12: 
      return paramJsonParser.getEmbeddedObject();
    case 6: 
      if (this._stringDeserializer != null) {
        return this._stringDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      return paramJsonParser.getText();
    case 7: 
      if (this._numberDeserializer != null) {
        return this._numberDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      if (paramDeserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
        return _coerceIntegral(paramJsonParser, paramDeserializationContext);
      }
      return paramJsonParser.getNumberValue();
    case 8: 
      if (this._numberDeserializer != null) {
        return this._numberDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
        return paramJsonParser.getDecimalValue();
      }
      return Double.valueOf(paramJsonParser.getDoubleValue());
    case 9: 
      return Boolean.TRUE;
    case 10: 
      return Boolean.FALSE;
    }
    return null;
  }
  
  public boolean isCachable()
  {
    return true;
  }
  
  protected Object mapArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {
      return new ArrayList(2);
    }
    Object localObject1 = deserialize(paramJsonParser, paramDeserializationContext);
    if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
    {
      paramJsonParser = new ArrayList(2);
      paramJsonParser.add(localObject1);
      return paramJsonParser;
    }
    Object localObject3 = deserialize(paramJsonParser, paramDeserializationContext);
    if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
    {
      paramJsonParser = new ArrayList(2);
      paramJsonParser.add(localObject1);
      paramJsonParser.add(localObject3);
      return paramJsonParser;
    }
    ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object localObject2 = localObjectBuffer.resetAndStart();
    int j = 0 + 1;
    localObject2[0] = localObject1;
    int i = j + 1;
    localObject2[j] = localObject3;
    j = i;
    for (;;)
    {
      localObject3 = deserialize(paramJsonParser, paramDeserializationContext);
      int k = j + 1;
      j = i;
      localObject1 = localObject2;
      if (i >= localObject2.length)
      {
        localObject1 = localObjectBuffer.appendCompletedChunk((Object[])localObject2);
        j = 0;
      }
      i = j + 1;
      localObject1[j] = localObject3;
      if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
      {
        paramJsonParser = new ArrayList(k);
        localObjectBuffer.completeAndClearBuffer((Object[])localObject1, i, paramJsonParser);
        return paramJsonParser;
      }
      j = k;
      localObject2 = localObject1;
    }
  }
  
  protected Object[] mapArrayToArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {
      return NO_OBJECTS;
    }
    ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object localObject1 = localObjectBuffer.resetAndStart();
    int i = 0;
    for (;;)
    {
      Object localObject3 = deserialize(paramJsonParser, paramDeserializationContext);
      int j = i;
      Object localObject2 = localObject1;
      if (i >= localObject1.length)
      {
        localObject2 = localObjectBuffer.appendCompletedChunk((Object[])localObject1);
        j = 0;
      }
      i = j + 1;
      localObject2[j] = localObject3;
      if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {
        return localObjectBuffer.completeAndClearBuffer((Object[])localObject2, i);
      }
      localObject1 = localObject2;
    }
  }
  
  protected Object mapObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject1 = paramJsonParser.getCurrentToken();
    if (localObject1 == JsonToken.START_OBJECT) {
      localObject1 = paramJsonParser.nextFieldName();
    }
    while (localObject1 == null)
    {
      return new LinkedHashMap(2);
      if (localObject1 == JsonToken.FIELD_NAME)
      {
        localObject1 = paramJsonParser.getCurrentName();
      }
      else
      {
        if (localObject1 != JsonToken.END_OBJECT) {
          throw paramDeserializationContext.mappingException(handledType(), paramJsonParser.getCurrentToken());
        }
        localObject1 = null;
      }
    }
    paramJsonParser.nextToken();
    Object localObject2 = deserialize(paramJsonParser, paramDeserializationContext);
    String str2 = paramJsonParser.nextFieldName();
    if (str2 == null)
    {
      paramJsonParser = new LinkedHashMap(2);
      paramJsonParser.put(localObject1, localObject2);
      return paramJsonParser;
    }
    paramJsonParser.nextToken();
    Object localObject3 = deserialize(paramJsonParser, paramDeserializationContext);
    String str1 = paramJsonParser.nextFieldName();
    if (str1 == null)
    {
      paramJsonParser = new LinkedHashMap(4);
      paramJsonParser.put(localObject1, localObject2);
      paramJsonParser.put(str2, localObject3);
      return paramJsonParser;
    }
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    localLinkedHashMap.put(localObject1, localObject2);
    localLinkedHashMap.put(str2, localObject3);
    localObject1 = str1;
    do
    {
      paramJsonParser.nextToken();
      localLinkedHashMap.put(localObject1, deserialize(paramJsonParser, paramDeserializationContext));
      str1 = paramJsonParser.nextFieldName();
      localObject1 = str1;
    } while (str1 != null);
    return localLinkedHashMap;
  }
  
  public void resolve(DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    JavaType localJavaType1 = paramDeserializationContext.constructType(Object.class);
    JavaType localJavaType2 = paramDeserializationContext.constructType(String.class);
    TypeFactory localTypeFactory = paramDeserializationContext.getTypeFactory();
    if (this._listType == null)
    {
      this._listDeserializer = _clearIfStdImpl(_findCustomDeser(paramDeserializationContext, localTypeFactory.constructCollectionType(List.class, localJavaType1)));
      if (this._mapType != null) {
        break label192;
      }
    }
    label192:
    for (this._mapDeserializer = _clearIfStdImpl(_findCustomDeser(paramDeserializationContext, localTypeFactory.constructMapType(Map.class, localJavaType2, localJavaType1)));; this._mapDeserializer = _findCustomDeser(paramDeserializationContext, this._mapType))
    {
      this._stringDeserializer = _clearIfStdImpl(_findCustomDeser(paramDeserializationContext, localJavaType2));
      this._numberDeserializer = _clearIfStdImpl(_findCustomDeser(paramDeserializationContext, localTypeFactory.constructType(Number.class)));
      localJavaType1 = TypeFactory.unknownType();
      this._mapDeserializer = paramDeserializationContext.handleSecondaryContextualization(this._mapDeserializer, null, localJavaType1);
      this._listDeserializer = paramDeserializationContext.handleSecondaryContextualization(this._listDeserializer, null, localJavaType1);
      this._stringDeserializer = paramDeserializationContext.handleSecondaryContextualization(this._stringDeserializer, null, localJavaType1);
      this._numberDeserializer = paramDeserializationContext.handleSecondaryContextualization(this._numberDeserializer, null, localJavaType1);
      return;
      this._listDeserializer = _findCustomDeser(paramDeserializationContext, this._listType);
      break;
    }
  }
  
  @JacksonStdImpl
  public static class Vanilla
    extends StdDeserializer<Object>
  {
    private static final long serialVersionUID = 1L;
    public static final Vanilla std = new Vanilla();
    
    public Vanilla()
    {
      super();
    }
    
    public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      switch (paramJsonParser.getCurrentTokenId())
      {
      case 2: 
      case 4: 
      default: 
        throw paramDeserializationContext.mappingException(Object.class);
      case 1: 
        if (paramJsonParser.nextToken() == JsonToken.END_OBJECT) {
          return new LinkedHashMap(2);
        }
      case 5: 
        return mapObject(paramJsonParser, paramDeserializationContext);
      case 3: 
        if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
        {
          if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
            return UntypedObjectDeserializer.NO_OBJECTS;
          }
          return new ArrayList(2);
        }
        if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
          return mapArrayToArray(paramJsonParser, paramDeserializationContext);
        }
        return mapArray(paramJsonParser, paramDeserializationContext);
      case 12: 
        return paramJsonParser.getEmbeddedObject();
      case 6: 
        return paramJsonParser.getText();
      case 7: 
        if (paramDeserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
          return _coerceIntegral(paramJsonParser, paramDeserializationContext);
        }
        return paramJsonParser.getNumberValue();
      case 8: 
        if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
          return paramJsonParser.getDecimalValue();
        }
        return Double.valueOf(paramJsonParser.getDoubleValue());
      case 9: 
        return Boolean.TRUE;
      case 10: 
        return Boolean.FALSE;
      }
      return null;
    }
    
    public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
      throws IOException
    {
      switch (paramJsonParser.getCurrentTokenId())
      {
      case 2: 
      case 4: 
      default: 
        throw paramDeserializationContext.mappingException(Object.class);
      case 1: 
      case 3: 
      case 5: 
        return paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
      case 6: 
        return paramJsonParser.getText();
      case 7: 
        if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
          return paramJsonParser.getBigIntegerValue();
        }
        return paramJsonParser.getNumberValue();
      case 8: 
        if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
          return paramJsonParser.getDecimalValue();
        }
        return Double.valueOf(paramJsonParser.getDoubleValue());
      case 9: 
        return Boolean.TRUE;
      case 10: 
        return Boolean.FALSE;
      case 12: 
        return paramJsonParser.getEmbeddedObject();
      }
      return null;
    }
    
    protected Object mapArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      Object localObject1 = deserialize(paramJsonParser, paramDeserializationContext);
      if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
      {
        paramJsonParser = new ArrayList(2);
        paramJsonParser.add(localObject1);
        return paramJsonParser;
      }
      Object localObject3 = deserialize(paramJsonParser, paramDeserializationContext);
      if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
      {
        paramJsonParser = new ArrayList(2);
        paramJsonParser.add(localObject1);
        paramJsonParser.add(localObject3);
        return paramJsonParser;
      }
      ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
      Object localObject2 = localObjectBuffer.resetAndStart();
      int j = 0 + 1;
      localObject2[0] = localObject1;
      int i = j + 1;
      localObject2[j] = localObject3;
      j = i;
      for (;;)
      {
        localObject3 = deserialize(paramJsonParser, paramDeserializationContext);
        int k = j + 1;
        j = i;
        localObject1 = localObject2;
        if (i >= localObject2.length)
        {
          localObject1 = localObjectBuffer.appendCompletedChunk((Object[])localObject2);
          j = 0;
        }
        i = j + 1;
        localObject1[j] = localObject3;
        if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
        {
          paramJsonParser = new ArrayList(k);
          localObjectBuffer.completeAndClearBuffer((Object[])localObject1, i, paramJsonParser);
          return paramJsonParser;
        }
        j = k;
        localObject2 = localObject1;
      }
    }
    
    protected Object[] mapArrayToArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
      Object localObject1 = localObjectBuffer.resetAndStart();
      int i = 0;
      for (;;)
      {
        Object localObject3 = deserialize(paramJsonParser, paramDeserializationContext);
        int j = i;
        Object localObject2 = localObject1;
        if (i >= localObject1.length)
        {
          localObject2 = localObjectBuffer.appendCompletedChunk((Object[])localObject1);
          j = 0;
        }
        i = j + 1;
        localObject2[j] = localObject3;
        if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {
          return localObjectBuffer.completeAndClearBuffer((Object[])localObject2, i);
        }
        localObject1 = localObject2;
      }
    }
    
    protected Object mapObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      String str2 = paramJsonParser.getText();
      paramJsonParser.nextToken();
      Object localObject1 = deserialize(paramJsonParser, paramDeserializationContext);
      String str3 = paramJsonParser.nextFieldName();
      if (str3 == null)
      {
        paramJsonParser = new LinkedHashMap(2);
        paramJsonParser.put(str2, localObject1);
        return paramJsonParser;
      }
      paramJsonParser.nextToken();
      Object localObject2 = deserialize(paramJsonParser, paramDeserializationContext);
      String str1 = paramJsonParser.nextFieldName();
      if (str1 == null)
      {
        paramJsonParser = new LinkedHashMap(4);
        paramJsonParser.put(str2, localObject1);
        paramJsonParser.put(str3, localObject2);
        return paramJsonParser;
      }
      LinkedHashMap localLinkedHashMap = new LinkedHashMap();
      localLinkedHashMap.put(str2, localObject1);
      localLinkedHashMap.put(str3, localObject2);
      do
      {
        paramJsonParser.nextToken();
        localLinkedHashMap.put(str1, deserialize(paramJsonParser, paramDeserializationContext));
        str2 = paramJsonParser.nextFieldName();
        str1 = str2;
      } while (str2 != null);
      return localLinkedHashMap;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/deser/std/UntypedObjectDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */