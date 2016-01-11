package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.filter.FilteringParserDelegate;
import com.fasterxml.jackson.core.filter.JsonPointerBasedFilter;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectReader
  extends ObjectCodec
  implements Versioned, Serializable
{
  private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
  private static final long serialVersionUID = 1L;
  protected final DeserializationConfig _config;
  protected final DefaultDeserializationContext _context;
  protected final DataFormatReaders _dataFormatReaders;
  private final TokenFilter _filter;
  protected final InjectableValues _injectableValues;
  protected final JsonFactory _parserFactory;
  protected final JsonDeserializer<Object> _rootDeserializer;
  protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
  protected final FormatSchema _schema;
  protected final boolean _unwrapRoot;
  protected final Object _valueToUpdate;
  protected final JavaType _valueType;
  
  protected ObjectReader(ObjectMapper paramObjectMapper, DeserializationConfig paramDeserializationConfig)
  {
    this(paramObjectMapper, paramDeserializationConfig, null, null, null, null);
  }
  
  protected ObjectReader(ObjectMapper paramObjectMapper, DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, Object paramObject, FormatSchema paramFormatSchema, InjectableValues paramInjectableValues)
  {
    this._config = paramDeserializationConfig;
    this._context = paramObjectMapper._deserializationContext;
    this._rootDeserializers = paramObjectMapper._rootDeserializers;
    this._parserFactory = paramObjectMapper._jsonFactory;
    this._valueType = paramJavaType;
    this._valueToUpdate = paramObject;
    if ((paramObject != null) && (paramJavaType.isArrayType())) {
      throw new IllegalArgumentException("Can not update an array value");
    }
    this._schema = paramFormatSchema;
    this._injectableValues = paramInjectableValues;
    this._unwrapRoot = paramDeserializationConfig.useRootWrapping();
    this._rootDeserializer = _prefetchRootDeserializer(paramJavaType);
    this._dataFormatReaders = null;
    this._filter = null;
  }
  
  protected ObjectReader(ObjectReader paramObjectReader, JsonFactory paramJsonFactory)
  {
    this._config = paramObjectReader._config.with(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, paramJsonFactory.requiresPropertyOrdering());
    this._context = paramObjectReader._context;
    this._rootDeserializers = paramObjectReader._rootDeserializers;
    this._parserFactory = paramJsonFactory;
    this._valueType = paramObjectReader._valueType;
    this._rootDeserializer = paramObjectReader._rootDeserializer;
    this._valueToUpdate = paramObjectReader._valueToUpdate;
    this._schema = paramObjectReader._schema;
    this._injectableValues = paramObjectReader._injectableValues;
    this._unwrapRoot = paramObjectReader._unwrapRoot;
    this._dataFormatReaders = paramObjectReader._dataFormatReaders;
    this._filter = paramObjectReader._filter;
  }
  
  protected ObjectReader(ObjectReader paramObjectReader, TokenFilter paramTokenFilter)
  {
    this._config = paramObjectReader._config;
    this._context = paramObjectReader._context;
    this._rootDeserializers = paramObjectReader._rootDeserializers;
    this._parserFactory = paramObjectReader._parserFactory;
    this._valueType = paramObjectReader._valueType;
    this._rootDeserializer = paramObjectReader._rootDeserializer;
    this._valueToUpdate = paramObjectReader._valueToUpdate;
    this._schema = paramObjectReader._schema;
    this._injectableValues = paramObjectReader._injectableValues;
    this._unwrapRoot = paramObjectReader._unwrapRoot;
    this._dataFormatReaders = paramObjectReader._dataFormatReaders;
    this._filter = paramTokenFilter;
  }
  
  protected ObjectReader(ObjectReader paramObjectReader, DeserializationConfig paramDeserializationConfig)
  {
    this._config = paramDeserializationConfig;
    this._context = paramObjectReader._context;
    this._rootDeserializers = paramObjectReader._rootDeserializers;
    this._parserFactory = paramObjectReader._parserFactory;
    this._valueType = paramObjectReader._valueType;
    this._rootDeserializer = paramObjectReader._rootDeserializer;
    this._valueToUpdate = paramObjectReader._valueToUpdate;
    this._schema = paramObjectReader._schema;
    this._injectableValues = paramObjectReader._injectableValues;
    this._unwrapRoot = paramDeserializationConfig.useRootWrapping();
    this._dataFormatReaders = paramObjectReader._dataFormatReaders;
    this._filter = paramObjectReader._filter;
  }
  
  protected ObjectReader(ObjectReader paramObjectReader, DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer, Object paramObject, FormatSchema paramFormatSchema, InjectableValues paramInjectableValues, DataFormatReaders paramDataFormatReaders)
  {
    this._config = paramDeserializationConfig;
    this._context = paramObjectReader._context;
    this._rootDeserializers = paramObjectReader._rootDeserializers;
    this._parserFactory = paramObjectReader._parserFactory;
    this._valueType = paramJavaType;
    this._rootDeserializer = paramJsonDeserializer;
    this._valueToUpdate = paramObject;
    if ((paramObject != null) && (paramJavaType.isArrayType())) {
      throw new IllegalArgumentException("Can not update an array value");
    }
    this._schema = paramFormatSchema;
    this._injectableValues = paramInjectableValues;
    this._unwrapRoot = paramDeserializationConfig.useRootWrapping();
    this._dataFormatReaders = paramDataFormatReaders;
    this._filter = paramObjectReader._filter;
  }
  
  protected Object _bind(JsonParser paramJsonParser, Object paramObject)
    throws IOException
  {
    Object localObject = _initForReading(paramJsonParser);
    if (localObject == JsonToken.VALUE_NULL) {
      if (paramObject == null)
      {
        paramObject = createDeserializationContext(paramJsonParser);
        paramObject = _findRootDeserializer((DeserializationContext)paramObject).getNullValue((DeserializationContext)paramObject);
      }
    }
    for (;;)
    {
      paramJsonParser.clearCurrentToken();
      return paramObject;
      continue;
      if ((localObject == JsonToken.END_ARRAY) || (localObject != JsonToken.END_OBJECT))
      {
        localObject = createDeserializationContext(paramJsonParser);
        JsonDeserializer localJsonDeserializer = _findRootDeserializer((DeserializationContext)localObject);
        if (this._unwrapRoot) {
          paramObject = _unwrapAndDeserialize(paramJsonParser, (DeserializationContext)localObject, this._valueType, localJsonDeserializer);
        } else if (paramObject == null) {
          paramObject = localJsonDeserializer.deserialize(paramJsonParser, (DeserializationContext)localObject);
        } else {
          localJsonDeserializer.deserialize(paramJsonParser, (DeserializationContext)localObject, paramObject);
        }
      }
    }
  }
  
  /* Error */
  protected Object _bindAndClose(JsonParser paramJsonParser)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 143	com/fasterxml/jackson/databind/ObjectReader:_initForReading	(Lcom/fasterxml/jackson/core/JsonParser;)Lcom/fasterxml/jackson/core/JsonToken;
    //   5: astore_2
    //   6: aload_2
    //   7: getstatic 149	com/fasterxml/jackson/core/JsonToken:VALUE_NULL	Lcom/fasterxml/jackson/core/JsonToken;
    //   10: if_acmpne +40 -> 50
    //   13: aload_0
    //   14: getfield 80	com/fasterxml/jackson/databind/ObjectReader:_valueToUpdate	Ljava/lang/Object;
    //   17: ifnonnull +25 -> 42
    //   20: aload_0
    //   21: aload_1
    //   22: invokevirtual 153	com/fasterxml/jackson/databind/ObjectReader:createDeserializationContext	(Lcom/fasterxml/jackson/core/JsonParser;)Lcom/fasterxml/jackson/databind/deser/DefaultDeserializationContext;
    //   25: astore_2
    //   26: aload_0
    //   27: aload_2
    //   28: invokevirtual 157	com/fasterxml/jackson/databind/ObjectReader:_findRootDeserializer	(Lcom/fasterxml/jackson/databind/DeserializationContext;)Lcom/fasterxml/jackson/databind/JsonDeserializer;
    //   31: aload_2
    //   32: invokevirtual 163	com/fasterxml/jackson/databind/JsonDeserializer:getNullValue	(Lcom/fasterxml/jackson/databind/DeserializationContext;)Ljava/lang/Object;
    //   35: astore_2
    //   36: aload_1
    //   37: invokevirtual 191	com/fasterxml/jackson/core/JsonParser:close	()V
    //   40: aload_2
    //   41: areturn
    //   42: aload_0
    //   43: getfield 80	com/fasterxml/jackson/databind/ObjectReader:_valueToUpdate	Ljava/lang/Object;
    //   46: astore_2
    //   47: goto -11 -> 36
    //   50: aload_2
    //   51: getstatic 171	com/fasterxml/jackson/core/JsonToken:END_ARRAY	Lcom/fasterxml/jackson/core/JsonToken;
    //   54: if_acmpeq +10 -> 64
    //   57: aload_2
    //   58: getstatic 174	com/fasterxml/jackson/core/JsonToken:END_OBJECT	Lcom/fasterxml/jackson/core/JsonToken;
    //   61: if_acmpne +11 -> 72
    //   64: aload_0
    //   65: getfield 80	com/fasterxml/jackson/databind/ObjectReader:_valueToUpdate	Ljava/lang/Object;
    //   68: astore_2
    //   69: goto -33 -> 36
    //   72: aload_0
    //   73: aload_1
    //   74: invokevirtual 153	com/fasterxml/jackson/databind/ObjectReader:createDeserializationContext	(Lcom/fasterxml/jackson/core/JsonParser;)Lcom/fasterxml/jackson/databind/deser/DefaultDeserializationContext;
    //   77: astore_2
    //   78: aload_0
    //   79: aload_2
    //   80: invokevirtual 157	com/fasterxml/jackson/databind/ObjectReader:_findRootDeserializer	(Lcom/fasterxml/jackson/databind/DeserializationContext;)Lcom/fasterxml/jackson/databind/JsonDeserializer;
    //   83: astore_3
    //   84: aload_0
    //   85: getfield 104	com/fasterxml/jackson/databind/ObjectReader:_unwrapRoot	Z
    //   88: ifeq +18 -> 106
    //   91: aload_0
    //   92: aload_1
    //   93: aload_2
    //   94: aload_0
    //   95: getfield 78	com/fasterxml/jackson/databind/ObjectReader:_valueType	Lcom/fasterxml/jackson/databind/JavaType;
    //   98: aload_3
    //   99: invokevirtual 178	com/fasterxml/jackson/databind/ObjectReader:_unwrapAndDeserialize	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;Lcom/fasterxml/jackson/databind/JavaType;Lcom/fasterxml/jackson/databind/JsonDeserializer;)Ljava/lang/Object;
    //   102: astore_2
    //   103: goto -67 -> 36
    //   106: aload_0
    //   107: getfield 80	com/fasterxml/jackson/databind/ObjectReader:_valueToUpdate	Ljava/lang/Object;
    //   110: ifnonnull +13 -> 123
    //   113: aload_3
    //   114: aload_1
    //   115: aload_2
    //   116: invokevirtual 182	com/fasterxml/jackson/databind/JsonDeserializer:deserialize	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;)Ljava/lang/Object;
    //   119: astore_2
    //   120: goto -84 -> 36
    //   123: aload_3
    //   124: aload_1
    //   125: aload_2
    //   126: aload_0
    //   127: getfield 80	com/fasterxml/jackson/databind/ObjectReader:_valueToUpdate	Ljava/lang/Object;
    //   130: invokevirtual 185	com/fasterxml/jackson/databind/JsonDeserializer:deserialize	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;Ljava/lang/Object;)Ljava/lang/Object;
    //   133: pop
    //   134: aload_0
    //   135: getfield 80	com/fasterxml/jackson/databind/ObjectReader:_valueToUpdate	Ljava/lang/Object;
    //   138: astore_2
    //   139: goto -103 -> 36
    //   142: astore_2
    //   143: aload_1
    //   144: invokevirtual 191	com/fasterxml/jackson/core/JsonParser:close	()V
    //   147: aload_2
    //   148: athrow
    //   149: astore_1
    //   150: aload_2
    //   151: areturn
    //   152: astore_1
    //   153: goto -6 -> 147
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	156	0	this	ObjectReader
    //   0	156	1	paramJsonParser	JsonParser
    //   5	134	2	localObject1	Object
    //   142	9	2	localObject2	Object
    //   83	41	3	localJsonDeserializer	JsonDeserializer
    // Exception table:
    //   from	to	target	type
    //   0	36	142	finally
    //   42	47	142	finally
    //   50	64	142	finally
    //   64	69	142	finally
    //   72	103	142	finally
    //   106	120	142	finally
    //   123	139	142	finally
    //   36	40	149	java/io/IOException
    //   143	147	152	java/io/IOException
  }
  
  /* Error */
  protected JsonNode _bindAndCloseAsTree(JsonParser paramJsonParser)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 196	com/fasterxml/jackson/databind/ObjectReader:_bindAsTree	(Lcom/fasterxml/jackson/core/JsonParser;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   5: astore_2
    //   6: aload_1
    //   7: invokevirtual 191	com/fasterxml/jackson/core/JsonParser:close	()V
    //   10: aload_2
    //   11: areturn
    //   12: astore_2
    //   13: aload_1
    //   14: invokevirtual 191	com/fasterxml/jackson/core/JsonParser:close	()V
    //   17: aload_2
    //   18: athrow
    //   19: astore_1
    //   20: aload_2
    //   21: areturn
    //   22: astore_1
    //   23: goto -6 -> 17
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	26	0	this	ObjectReader
    //   0	26	1	paramJsonParser	JsonParser
    //   5	6	2	localJsonNode1	JsonNode
    //   12	9	2	localJsonNode2	JsonNode
    // Exception table:
    //   from	to	target	type
    //   0	6	12	finally
    //   6	10	19	java/io/IOException
    //   13	17	22	java/io/IOException
  }
  
  protected <T> MappingIterator<T> _bindAndReadValues(JsonParser paramJsonParser)
    throws IOException
  {
    _initForMultiRead(paramJsonParser);
    paramJsonParser.nextToken();
    DefaultDeserializationContext localDefaultDeserializationContext = createDeserializationContext(paramJsonParser);
    return _newIterator(paramJsonParser, localDefaultDeserializationContext, _findRootDeserializer(localDefaultDeserializationContext), true);
  }
  
  protected JsonNode _bindAsTree(JsonParser paramJsonParser)
    throws IOException
  {
    Object localObject = _initForReading(paramJsonParser);
    if ((localObject == JsonToken.VALUE_NULL) || (localObject == JsonToken.END_ARRAY) || (localObject == JsonToken.END_OBJECT)) {
      localObject = NullNode.instance;
    }
    for (;;)
    {
      paramJsonParser.clearCurrentToken();
      return (JsonNode)localObject;
      localObject = createDeserializationContext(paramJsonParser);
      JsonDeserializer localJsonDeserializer = _findTreeDeserializer((DeserializationContext)localObject);
      if (this._unwrapRoot) {
        localObject = (JsonNode)_unwrapAndDeserialize(paramJsonParser, (DeserializationContext)localObject, JSON_NODE_TYPE, localJsonDeserializer);
      } else {
        localObject = (JsonNode)localJsonDeserializer.deserialize(paramJsonParser, (DeserializationContext)localObject);
      }
    }
  }
  
  protected JsonParser _considerFilter(JsonParser paramJsonParser)
  {
    if ((this._filter == null) || (FilteringParserDelegate.class.isInstance(paramJsonParser))) {
      return paramJsonParser;
    }
    return new FilteringParserDelegate(paramJsonParser, this._filter, false, false);
  }
  
  protected Object _detectBindAndClose(DataFormatReaders.Match paramMatch, boolean paramBoolean)
    throws IOException
  {
    if (!paramMatch.hasMatch()) {
      _reportUnkownFormat(this._dataFormatReaders, paramMatch);
    }
    JsonParser localJsonParser = paramMatch.createParserWithMatch();
    if (paramBoolean) {
      localJsonParser.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
    }
    return paramMatch.getReader()._bindAndClose(localJsonParser);
  }
  
  protected Object _detectBindAndClose(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    paramArrayOfByte = this._dataFormatReaders.findFormat(paramArrayOfByte, paramInt1, paramInt2);
    if (!paramArrayOfByte.hasMatch()) {
      _reportUnkownFormat(this._dataFormatReaders, paramArrayOfByte);
    }
    JsonParser localJsonParser = paramArrayOfByte.createParserWithMatch();
    return paramArrayOfByte.getReader()._bindAndClose(localJsonParser);
  }
  
  protected JsonNode _detectBindAndCloseAsTree(InputStream paramInputStream)
    throws IOException
  {
    paramInputStream = this._dataFormatReaders.findFormat(paramInputStream);
    if (!paramInputStream.hasMatch()) {
      _reportUnkownFormat(this._dataFormatReaders, paramInputStream);
    }
    JsonParser localJsonParser = paramInputStream.createParserWithMatch();
    localJsonParser.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
    return paramInputStream.getReader()._bindAndCloseAsTree(localJsonParser);
  }
  
  protected <T> MappingIterator<T> _detectBindAndReadValues(DataFormatReaders.Match paramMatch, boolean paramBoolean)
    throws IOException, JsonProcessingException
  {
    if (!paramMatch.hasMatch()) {
      _reportUnkownFormat(this._dataFormatReaders, paramMatch);
    }
    JsonParser localJsonParser = paramMatch.createParserWithMatch();
    if (paramBoolean) {
      localJsonParser.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
    }
    return paramMatch.getReader()._bindAndReadValues(localJsonParser);
  }
  
  protected JsonDeserializer<Object> _findRootDeserializer(DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    Object localObject;
    if (this._rootDeserializer != null) {
      localObject = this._rootDeserializer;
    }
    JavaType localJavaType;
    JsonDeserializer localJsonDeserializer;
    do
    {
      return (JsonDeserializer<Object>)localObject;
      localJavaType = this._valueType;
      if (localJavaType == null) {
        throw new JsonMappingException("No value type configured for ObjectReader");
      }
      localJsonDeserializer = (JsonDeserializer)this._rootDeserializers.get(localJavaType);
      localObject = localJsonDeserializer;
    } while (localJsonDeserializer != null);
    paramDeserializationContext = paramDeserializationContext.findRootValueDeserializer(localJavaType);
    if (paramDeserializationContext == null) {
      throw new JsonMappingException("Can not find a deserializer for type " + localJavaType);
    }
    this._rootDeserializers.put(localJavaType, paramDeserializationContext);
    return paramDeserializationContext;
  }
  
  protected JsonDeserializer<Object> _findTreeDeserializer(DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer2 = (JsonDeserializer)this._rootDeserializers.get(JSON_NODE_TYPE);
    JsonDeserializer localJsonDeserializer1 = localJsonDeserializer2;
    if (localJsonDeserializer2 == null)
    {
      localJsonDeserializer1 = paramDeserializationContext.findRootValueDeserializer(JSON_NODE_TYPE);
      if (localJsonDeserializer1 == null) {
        throw new JsonMappingException("Can not find a deserializer for type " + JSON_NODE_TYPE);
      }
      this._rootDeserializers.put(JSON_NODE_TYPE, localJsonDeserializer1);
    }
    return localJsonDeserializer1;
  }
  
  protected void _initForMultiRead(JsonParser paramJsonParser)
    throws IOException
  {
    if (this._schema != null) {
      paramJsonParser.setSchema(this._schema);
    }
    this._config.initialize(paramJsonParser);
  }
  
  protected JsonToken _initForReading(JsonParser paramJsonParser)
    throws IOException
  {
    if (this._schema != null) {
      paramJsonParser.setSchema(this._schema);
    }
    this._config.initialize(paramJsonParser);
    JsonToken localJsonToken2 = paramJsonParser.getCurrentToken();
    JsonToken localJsonToken1 = localJsonToken2;
    if (localJsonToken2 == null)
    {
      localJsonToken2 = paramJsonParser.nextToken();
      localJsonToken1 = localJsonToken2;
      if (localJsonToken2 == null) {
        throw JsonMappingException.from(paramJsonParser, "No content to map due to end-of-input");
      }
    }
    return localJsonToken1;
  }
  
  protected InputStream _inputStream(File paramFile)
    throws IOException
  {
    return new FileInputStream(paramFile);
  }
  
  protected InputStream _inputStream(URL paramURL)
    throws IOException
  {
    return paramURL.openStream();
  }
  
  protected ObjectReader _new(ObjectReader paramObjectReader, JsonFactory paramJsonFactory)
  {
    return new ObjectReader(paramObjectReader, paramJsonFactory);
  }
  
  protected ObjectReader _new(ObjectReader paramObjectReader, DeserializationConfig paramDeserializationConfig)
  {
    return new ObjectReader(paramObjectReader, paramDeserializationConfig);
  }
  
  protected ObjectReader _new(ObjectReader paramObjectReader, DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer, Object paramObject, FormatSchema paramFormatSchema, InjectableValues paramInjectableValues, DataFormatReaders paramDataFormatReaders)
  {
    return new ObjectReader(paramObjectReader, paramDeserializationConfig, paramJavaType, paramJsonDeserializer, paramObject, paramFormatSchema, paramInjectableValues, paramDataFormatReaders);
  }
  
  protected <T> MappingIterator<T> _newIterator(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonDeserializer<?> paramJsonDeserializer, boolean paramBoolean)
  {
    return new MappingIterator(this._valueType, paramJsonParser, paramDeserializationContext, paramJsonDeserializer, paramBoolean, this._valueToUpdate);
  }
  
  protected JsonDeserializer<Object> _prefetchRootDeserializer(JavaType paramJavaType)
  {
    JsonDeserializer localJsonDeserializer2 = null;
    JsonDeserializer localJsonDeserializer1 = localJsonDeserializer2;
    if (paramJavaType != null)
    {
      if (this._config.isEnabled(DeserializationFeature.EAGER_DESERIALIZER_FETCH)) {
        break label25;
      }
      localJsonDeserializer1 = localJsonDeserializer2;
    }
    for (;;)
    {
      return localJsonDeserializer1;
      label25:
      localJsonDeserializer2 = (JsonDeserializer)this._rootDeserializers.get(paramJavaType);
      localJsonDeserializer1 = localJsonDeserializer2;
      if (localJsonDeserializer2 == null)
      {
        localJsonDeserializer1 = localJsonDeserializer2;
        try
        {
          localJsonDeserializer2 = createDeserializationContext(null).findRootValueDeserializer(paramJavaType);
          localJsonDeserializer1 = localJsonDeserializer2;
          if (localJsonDeserializer2 != null)
          {
            localJsonDeserializer1 = localJsonDeserializer2;
            this._rootDeserializers.put(paramJavaType, localJsonDeserializer2);
            return localJsonDeserializer2;
          }
        }
        catch (JsonProcessingException paramJavaType) {}
      }
    }
    return localJsonDeserializer1;
  }
  
  protected void _reportUndetectableSource(Object paramObject)
    throws JsonProcessingException
  {
    throw new JsonParseException("Can not use source of type " + paramObject.getClass().getName() + " with format auto-detection: must be byte- not char-based", JsonLocation.NA);
  }
  
  protected void _reportUnkownFormat(DataFormatReaders paramDataFormatReaders, DataFormatReaders.Match paramMatch)
    throws JsonProcessingException
  {
    throw new JsonParseException("Can not detect format from input, does not look like any of detectable formats " + paramDataFormatReaders.toString(), JsonLocation.NA);
  }
  
  protected Object _unwrapAndDeserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer)
    throws IOException
  {
    String str1 = this._config.findRootName(paramJavaType).getSimpleName();
    if (paramJsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      throw JsonMappingException.from(paramJsonParser, "Current token not START_OBJECT (needed to unwrap root name '" + str1 + "'), but " + paramJsonParser.getCurrentToken());
    }
    if (paramJsonParser.nextToken() != JsonToken.FIELD_NAME) {
      throw JsonMappingException.from(paramJsonParser, "Current token not FIELD_NAME (to contain expected root name '" + str1 + "'), but " + paramJsonParser.getCurrentToken());
    }
    String str2 = paramJsonParser.getCurrentName();
    if (!str1.equals(str2)) {
      throw JsonMappingException.from(paramJsonParser, "Root name '" + str2 + "' does not match expected ('" + str1 + "') for type " + paramJavaType);
    }
    paramJsonParser.nextToken();
    if (this._valueToUpdate == null) {}
    for (paramDeserializationContext = paramJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext); paramJsonParser.nextToken() != JsonToken.END_OBJECT; paramDeserializationContext = this._valueToUpdate)
    {
      throw JsonMappingException.from(paramJsonParser, "Current token not END_OBJECT (to match wrapper object with root name '" + str1 + "'), but " + paramJsonParser.getCurrentToken());
      paramJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext, this._valueToUpdate);
    }
    return paramDeserializationContext;
  }
  
  protected void _verifySchemaType(FormatSchema paramFormatSchema)
  {
    if ((paramFormatSchema != null) && (!this._parserFactory.canUseSchema(paramFormatSchema))) {
      throw new IllegalArgumentException("Can not use FormatSchema of type " + paramFormatSchema.getClass().getName() + " for format " + this._parserFactory.getFormatName());
    }
  }
  
  protected ObjectReader _with(DeserializationConfig paramDeserializationConfig)
  {
    if (paramDeserializationConfig == this._config) {
      return this;
    }
    ObjectReader localObjectReader2 = _new(this, paramDeserializationConfig);
    ObjectReader localObjectReader1 = localObjectReader2;
    if (this._dataFormatReaders != null) {
      localObjectReader1 = localObjectReader2.withFormatDetection(this._dataFormatReaders.with(paramDeserializationConfig));
    }
    return localObjectReader1;
  }
  
  public ObjectReader at(JsonPointer paramJsonPointer)
  {
    return new ObjectReader(this, new JsonPointerBasedFilter(paramJsonPointer));
  }
  
  public ObjectReader at(String paramString)
  {
    return new ObjectReader(this, new JsonPointerBasedFilter(paramString));
  }
  
  public JsonNode createArrayNode()
  {
    return this._config.getNodeFactory().arrayNode();
  }
  
  protected DefaultDeserializationContext createDeserializationContext(JsonParser paramJsonParser)
  {
    return this._context.createInstance(this._config, paramJsonParser, this._injectableValues);
  }
  
  public JsonNode createObjectNode()
  {
    return this._config.getNodeFactory().objectNode();
  }
  
  public ObjectReader forType(TypeReference<?> paramTypeReference)
  {
    return forType(this._config.getTypeFactory().constructType(paramTypeReference.getType()));
  }
  
  public ObjectReader forType(JavaType paramJavaType)
  {
    if ((paramJavaType != null) && (paramJavaType.equals(this._valueType))) {
      return this;
    }
    JsonDeserializer localJsonDeserializer = _prefetchRootDeserializer(paramJavaType);
    DataFormatReaders localDataFormatReaders2 = this._dataFormatReaders;
    DataFormatReaders localDataFormatReaders1 = localDataFormatReaders2;
    if (localDataFormatReaders2 != null) {
      localDataFormatReaders1 = localDataFormatReaders2.withType(paramJavaType);
    }
    return _new(this, this._config, paramJavaType, localJsonDeserializer, this._valueToUpdate, this._schema, this._injectableValues, localDataFormatReaders1);
  }
  
  public ObjectReader forType(Class<?> paramClass)
  {
    return forType(this._config.constructType(paramClass));
  }
  
  public ContextAttributes getAttributes()
  {
    return this._config.getAttributes();
  }
  
  public DeserializationConfig getConfig()
  {
    return this._config;
  }
  
  public JsonFactory getFactory()
  {
    return this._parserFactory;
  }
  
  public InjectableValues getInjectableValues()
  {
    return this._injectableValues;
  }
  
  public TypeFactory getTypeFactory()
  {
    return this._config.getTypeFactory();
  }
  
  public boolean isEnabled(JsonParser.Feature paramFeature)
  {
    return this._parserFactory.isEnabled(paramFeature);
  }
  
  public boolean isEnabled(DeserializationFeature paramDeserializationFeature)
  {
    return this._config.isEnabled(paramDeserializationFeature);
  }
  
  public boolean isEnabled(MapperFeature paramMapperFeature)
  {
    return this._config.isEnabled(paramMapperFeature);
  }
  
  public <T extends TreeNode> T readTree(JsonParser paramJsonParser)
    throws IOException, JsonProcessingException
  {
    return _bindAsTree(paramJsonParser);
  }
  
  public JsonNode readTree(InputStream paramInputStream)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      return _detectBindAndCloseAsTree(paramInputStream);
    }
    return _bindAndCloseAsTree(_considerFilter(this._parserFactory.createParser(paramInputStream)));
  }
  
  public JsonNode readTree(Reader paramReader)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      _reportUndetectableSource(paramReader);
    }
    return _bindAndCloseAsTree(_considerFilter(this._parserFactory.createParser(paramReader)));
  }
  
  public JsonNode readTree(String paramString)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      _reportUndetectableSource(paramString);
    }
    return _bindAndCloseAsTree(_considerFilter(this._parserFactory.createParser(paramString)));
  }
  
  public <T> T readValue(JsonParser paramJsonParser)
    throws IOException, JsonProcessingException
  {
    return (T)_bind(paramJsonParser, this._valueToUpdate);
  }
  
  public <T> T readValue(JsonParser paramJsonParser, ResolvedType paramResolvedType)
    throws IOException, JsonProcessingException
  {
    return (T)forType((JavaType)paramResolvedType).readValue(paramJsonParser);
  }
  
  public <T> T readValue(JsonParser paramJsonParser, TypeReference<?> paramTypeReference)
    throws IOException, JsonProcessingException
  {
    return (T)forType(paramTypeReference).readValue(paramJsonParser);
  }
  
  public <T> T readValue(JsonParser paramJsonParser, JavaType paramJavaType)
    throws IOException, JsonProcessingException
  {
    return (T)forType(paramJavaType).readValue(paramJsonParser);
  }
  
  public <T> T readValue(JsonParser paramJsonParser, Class<T> paramClass)
    throws IOException, JsonProcessingException
  {
    return (T)forType(paramClass).readValue(paramJsonParser);
  }
  
  public <T> T readValue(JsonNode paramJsonNode)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      _reportUndetectableSource(paramJsonNode);
    }
    return (T)_bindAndClose(_considerFilter(treeAsTokens(paramJsonNode)));
  }
  
  public <T> T readValue(File paramFile)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      return (T)_detectBindAndClose(this._dataFormatReaders.findFormat(_inputStream(paramFile)), true);
    }
    return (T)_bindAndClose(_considerFilter(this._parserFactory.createParser(paramFile)));
  }
  
  public <T> T readValue(InputStream paramInputStream)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      return (T)_detectBindAndClose(this._dataFormatReaders.findFormat(paramInputStream), false);
    }
    return (T)_bindAndClose(_considerFilter(this._parserFactory.createParser(paramInputStream)));
  }
  
  public <T> T readValue(Reader paramReader)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      _reportUndetectableSource(paramReader);
    }
    return (T)_bindAndClose(_considerFilter(this._parserFactory.createParser(paramReader)));
  }
  
  public <T> T readValue(String paramString)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      _reportUndetectableSource(paramString);
    }
    return (T)_bindAndClose(_considerFilter(this._parserFactory.createParser(paramString)));
  }
  
  public <T> T readValue(URL paramURL)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      return (T)_detectBindAndClose(this._dataFormatReaders.findFormat(_inputStream(paramURL)), true);
    }
    return (T)_bindAndClose(_considerFilter(this._parserFactory.createParser(paramURL)));
  }
  
  public <T> T readValue(byte[] paramArrayOfByte)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      return (T)_detectBindAndClose(paramArrayOfByte, 0, paramArrayOfByte.length);
    }
    return (T)_bindAndClose(_considerFilter(this._parserFactory.createParser(paramArrayOfByte)));
  }
  
  public <T> T readValue(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      return (T)_detectBindAndClose(paramArrayOfByte, paramInt1, paramInt2);
    }
    return (T)_bindAndClose(_considerFilter(this._parserFactory.createParser(paramArrayOfByte, paramInt1, paramInt2)));
  }
  
  public <T> MappingIterator<T> readValues(JsonParser paramJsonParser)
    throws IOException, JsonProcessingException
  {
    DefaultDeserializationContext localDefaultDeserializationContext = createDeserializationContext(paramJsonParser);
    return _newIterator(paramJsonParser, localDefaultDeserializationContext, _findRootDeserializer(localDefaultDeserializationContext), false);
  }
  
  public <T> MappingIterator<T> readValues(File paramFile)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      return _detectBindAndReadValues(this._dataFormatReaders.findFormat(_inputStream(paramFile)), false);
    }
    return _bindAndReadValues(_considerFilter(this._parserFactory.createParser(paramFile)));
  }
  
  public <T> MappingIterator<T> readValues(InputStream paramInputStream)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      return _detectBindAndReadValues(this._dataFormatReaders.findFormat(paramInputStream), false);
    }
    return _bindAndReadValues(_considerFilter(this._parserFactory.createParser(paramInputStream)));
  }
  
  public <T> MappingIterator<T> readValues(Reader paramReader)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      _reportUndetectableSource(paramReader);
    }
    paramReader = _considerFilter(this._parserFactory.createParser(paramReader));
    _initForMultiRead(paramReader);
    paramReader.nextToken();
    DefaultDeserializationContext localDefaultDeserializationContext = createDeserializationContext(paramReader);
    return _newIterator(paramReader, localDefaultDeserializationContext, _findRootDeserializer(localDefaultDeserializationContext), true);
  }
  
  public <T> MappingIterator<T> readValues(String paramString)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      _reportUndetectableSource(paramString);
    }
    paramString = _considerFilter(this._parserFactory.createParser(paramString));
    _initForMultiRead(paramString);
    paramString.nextToken();
    DefaultDeserializationContext localDefaultDeserializationContext = createDeserializationContext(paramString);
    return _newIterator(paramString, localDefaultDeserializationContext, _findRootDeserializer(localDefaultDeserializationContext), true);
  }
  
  public <T> MappingIterator<T> readValues(URL paramURL)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      return _detectBindAndReadValues(this._dataFormatReaders.findFormat(_inputStream(paramURL)), true);
    }
    return _bindAndReadValues(_considerFilter(this._parserFactory.createParser(paramURL)));
  }
  
  public final <T> MappingIterator<T> readValues(byte[] paramArrayOfByte)
    throws IOException, JsonProcessingException
  {
    return readValues(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public <T> MappingIterator<T> readValues(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      return _detectBindAndReadValues(this._dataFormatReaders.findFormat(paramArrayOfByte, paramInt1, paramInt2), false);
    }
    return _bindAndReadValues(_considerFilter(this._parserFactory.createParser(paramArrayOfByte)));
  }
  
  public <T> Iterator<T> readValues(JsonParser paramJsonParser, ResolvedType paramResolvedType)
    throws IOException, JsonProcessingException
  {
    return readValues(paramJsonParser, (JavaType)paramResolvedType);
  }
  
  public <T> Iterator<T> readValues(JsonParser paramJsonParser, TypeReference<?> paramTypeReference)
    throws IOException, JsonProcessingException
  {
    return forType(paramTypeReference).readValues(paramJsonParser);
  }
  
  public <T> Iterator<T> readValues(JsonParser paramJsonParser, JavaType paramJavaType)
    throws IOException, JsonProcessingException
  {
    return forType(paramJavaType).readValues(paramJsonParser);
  }
  
  public <T> Iterator<T> readValues(JsonParser paramJsonParser, Class<T> paramClass)
    throws IOException, JsonProcessingException
  {
    return forType(paramClass).readValues(paramJsonParser);
  }
  
  public JsonParser treeAsTokens(TreeNode paramTreeNode)
  {
    return new TreeTraversingParser((JsonNode)paramTreeNode, this);
  }
  
  public <T> T treeToValue(TreeNode paramTreeNode, Class<T> paramClass)
    throws JsonProcessingException
  {
    try
    {
      paramTreeNode = readValue(treeAsTokens(paramTreeNode), paramClass);
      return paramTreeNode;
    }
    catch (JsonProcessingException paramTreeNode)
    {
      throw paramTreeNode;
    }
    catch (IOException paramTreeNode)
    {
      throw new IllegalArgumentException(paramTreeNode.getMessage(), paramTreeNode);
    }
  }
  
  public Version version()
  {
    return PackageVersion.VERSION;
  }
  
  public ObjectReader with(Base64Variant paramBase64Variant)
  {
    return _with(this._config.with(paramBase64Variant));
  }
  
  public ObjectReader with(FormatSchema paramFormatSchema)
  {
    if (this._schema == paramFormatSchema) {
      return this;
    }
    _verifySchemaType(paramFormatSchema);
    return _new(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, paramFormatSchema, this._injectableValues, this._dataFormatReaders);
  }
  
  public ObjectReader with(JsonFactory paramJsonFactory)
  {
    if (paramJsonFactory == this._parserFactory) {
      return this;
    }
    ObjectReader localObjectReader = _new(this, paramJsonFactory);
    if (paramJsonFactory.getCodec() == null) {
      paramJsonFactory.setCodec(localObjectReader);
    }
    return localObjectReader;
  }
  
  public ObjectReader with(JsonParser.Feature paramFeature)
  {
    return _with(this._config.with(paramFeature));
  }
  
  public ObjectReader with(DeserializationConfig paramDeserializationConfig)
  {
    return _with(paramDeserializationConfig);
  }
  
  public ObjectReader with(DeserializationFeature paramDeserializationFeature)
  {
    return _with(this._config.with(paramDeserializationFeature));
  }
  
  public ObjectReader with(DeserializationFeature paramDeserializationFeature, DeserializationFeature... paramVarArgs)
  {
    return _with(this._config.with(paramDeserializationFeature, paramVarArgs));
  }
  
  public ObjectReader with(InjectableValues paramInjectableValues)
  {
    if (this._injectableValues == paramInjectableValues) {
      return this;
    }
    return _new(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, this._schema, paramInjectableValues, this._dataFormatReaders);
  }
  
  public ObjectReader with(ContextAttributes paramContextAttributes)
  {
    return _with(this._config.with(paramContextAttributes));
  }
  
  public ObjectReader with(JsonNodeFactory paramJsonNodeFactory)
  {
    return _with(this._config.with(paramJsonNodeFactory));
  }
  
  public ObjectReader with(Locale paramLocale)
  {
    return _with(this._config.with(paramLocale));
  }
  
  public ObjectReader with(TimeZone paramTimeZone)
  {
    return _with(this._config.with(paramTimeZone));
  }
  
  public ObjectReader withAttribute(Object paramObject1, Object paramObject2)
  {
    return _with((DeserializationConfig)this._config.withAttribute(paramObject1, paramObject2));
  }
  
  public ObjectReader withAttributes(Map<Object, Object> paramMap)
  {
    return _with((DeserializationConfig)this._config.withAttributes(paramMap));
  }
  
  public ObjectReader withFeatures(JsonParser.Feature... paramVarArgs)
  {
    return _with(this._config.withFeatures(paramVarArgs));
  }
  
  public ObjectReader withFeatures(DeserializationFeature... paramVarArgs)
  {
    return _with(this._config.withFeatures(paramVarArgs));
  }
  
  public ObjectReader withFormatDetection(DataFormatReaders paramDataFormatReaders)
  {
    return _new(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, this._schema, this._injectableValues, paramDataFormatReaders);
  }
  
  public ObjectReader withFormatDetection(ObjectReader... paramVarArgs)
  {
    return withFormatDetection(new DataFormatReaders(paramVarArgs));
  }
  
  public ObjectReader withHandler(DeserializationProblemHandler paramDeserializationProblemHandler)
  {
    return _with(this._config.withHandler(paramDeserializationProblemHandler));
  }
  
  public ObjectReader withRootName(PropertyName paramPropertyName)
  {
    return _with(this._config.withRootName(paramPropertyName));
  }
  
  public ObjectReader withRootName(String paramString)
  {
    return _with((DeserializationConfig)this._config.withRootName(paramString));
  }
  
  @Deprecated
  public ObjectReader withType(TypeReference<?> paramTypeReference)
  {
    return forType(this._config.getTypeFactory().constructType(paramTypeReference.getType()));
  }
  
  @Deprecated
  public ObjectReader withType(JavaType paramJavaType)
  {
    return forType(paramJavaType);
  }
  
  @Deprecated
  public ObjectReader withType(Class<?> paramClass)
  {
    return forType(this._config.constructType(paramClass));
  }
  
  @Deprecated
  public ObjectReader withType(Type paramType)
  {
    return forType(this._config.getTypeFactory().constructType(paramType));
  }
  
  public ObjectReader withValueToUpdate(Object paramObject)
  {
    if (paramObject == this._valueToUpdate) {
      return this;
    }
    if (paramObject == null) {
      throw new IllegalArgumentException("cat not update null value");
    }
    if (this._valueType == null) {}
    for (JavaType localJavaType = this._config.constructType(paramObject.getClass());; localJavaType = this._valueType) {
      return _new(this, this._config, localJavaType, this._rootDeserializer, paramObject, this._schema, this._injectableValues, this._dataFormatReaders);
    }
  }
  
  public ObjectReader withView(Class<?> paramClass)
  {
    return _with(this._config.withView(paramClass));
  }
  
  public ObjectReader without(JsonParser.Feature paramFeature)
  {
    return _with(this._config.without(paramFeature));
  }
  
  public ObjectReader without(DeserializationFeature paramDeserializationFeature)
  {
    return _with(this._config.without(paramDeserializationFeature));
  }
  
  public ObjectReader without(DeserializationFeature paramDeserializationFeature, DeserializationFeature... paramVarArgs)
  {
    return _with(this._config.without(paramDeserializationFeature, paramVarArgs));
  }
  
  public ObjectReader withoutAttribute(Object paramObject)
  {
    return _with((DeserializationConfig)this._config.withoutAttribute(paramObject));
  }
  
  public ObjectReader withoutFeatures(JsonParser.Feature... paramVarArgs)
  {
    return _with(this._config.withoutFeatures(paramVarArgs));
  }
  
  public ObjectReader withoutFeatures(DeserializationFeature... paramVarArgs)
  {
    return _with(this._config.withoutFeatures(paramVarArgs));
  }
  
  public ObjectReader withoutRootName()
  {
    return _with(this._config.withRootName(PropertyName.NO_NAME));
  }
  
  public void writeTree(JsonGenerator paramJsonGenerator, TreeNode paramTreeNode)
  {
    throw new UnsupportedOperationException();
  }
  
  public void writeValue(JsonGenerator paramJsonGenerator, Object paramObject)
    throws IOException, JsonProcessingException
  {
    throw new UnsupportedOperationException("Not implemented for ObjectReader");
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ObjectReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */