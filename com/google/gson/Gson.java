package com.google.gson;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import com.google.gson.internal.bind.TimeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class Gson
{
  static final boolean DEFAULT_JSON_NON_EXECUTABLE = false;
  private static final String JSON_NON_EXECUTABLE_PREFIX = ")]}'\n";
  private final ThreadLocal<Map<TypeToken<?>, FutureTypeAdapter<?>>> calls = new ThreadLocal();
  private final ConstructorConstructor constructorConstructor;
  final JsonDeserializationContext deserializationContext = new JsonDeserializationContext()
  {
    public <T> T deserialize(JsonElement paramAnonymousJsonElement, Type paramAnonymousType)
      throws JsonParseException
    {
      return (T)Gson.this.fromJson(paramAnonymousJsonElement, paramAnonymousType);
    }
  };
  private final List<TypeAdapterFactory> factories;
  private final boolean generateNonExecutableJson;
  private final boolean htmlSafe;
  private final boolean prettyPrinting;
  final JsonSerializationContext serializationContext = new JsonSerializationContext()
  {
    public JsonElement serialize(Object paramAnonymousObject)
    {
      return Gson.this.toJsonTree(paramAnonymousObject);
    }
    
    public JsonElement serialize(Object paramAnonymousObject, Type paramAnonymousType)
    {
      return Gson.this.toJsonTree(paramAnonymousObject, paramAnonymousType);
    }
  };
  private final boolean serializeNulls;
  private final Map<TypeToken<?>, TypeAdapter<?>> typeTokenCache = Collections.synchronizedMap(new HashMap());
  
  public Gson()
  {
    this(Excluder.DEFAULT, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
  }
  
  Gson(Excluder paramExcluder, FieldNamingStrategy paramFieldNamingStrategy, Map<Type, InstanceCreator<?>> paramMap, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, LongSerializationPolicy paramLongSerializationPolicy, List<TypeAdapterFactory> paramList)
  {
    this.constructorConstructor = new ConstructorConstructor(paramMap);
    this.serializeNulls = paramBoolean1;
    this.generateNonExecutableJson = paramBoolean3;
    this.htmlSafe = paramBoolean4;
    this.prettyPrinting = paramBoolean5;
    paramMap = new ArrayList();
    paramMap.add(TypeAdapters.JSON_ELEMENT_FACTORY);
    paramMap.add(ObjectTypeAdapter.FACTORY);
    paramMap.add(paramExcluder);
    paramMap.addAll(paramList);
    paramMap.add(TypeAdapters.STRING_FACTORY);
    paramMap.add(TypeAdapters.INTEGER_FACTORY);
    paramMap.add(TypeAdapters.BOOLEAN_FACTORY);
    paramMap.add(TypeAdapters.BYTE_FACTORY);
    paramMap.add(TypeAdapters.SHORT_FACTORY);
    paramMap.add(TypeAdapters.newFactory(Long.TYPE, Long.class, longAdapter(paramLongSerializationPolicy)));
    paramMap.add(TypeAdapters.newFactory(Double.TYPE, Double.class, doubleAdapter(paramBoolean6)));
    paramMap.add(TypeAdapters.newFactory(Float.TYPE, Float.class, floatAdapter(paramBoolean6)));
    paramMap.add(TypeAdapters.NUMBER_FACTORY);
    paramMap.add(TypeAdapters.CHARACTER_FACTORY);
    paramMap.add(TypeAdapters.STRING_BUILDER_FACTORY);
    paramMap.add(TypeAdapters.STRING_BUFFER_FACTORY);
    paramMap.add(TypeAdapters.newFactory(BigDecimal.class, TypeAdapters.BIG_DECIMAL));
    paramMap.add(TypeAdapters.newFactory(BigInteger.class, TypeAdapters.BIG_INTEGER));
    paramMap.add(TypeAdapters.URL_FACTORY);
    paramMap.add(TypeAdapters.URI_FACTORY);
    paramMap.add(TypeAdapters.UUID_FACTORY);
    paramMap.add(TypeAdapters.LOCALE_FACTORY);
    paramMap.add(TypeAdapters.INET_ADDRESS_FACTORY);
    paramMap.add(TypeAdapters.BIT_SET_FACTORY);
    paramMap.add(DateTypeAdapter.FACTORY);
    paramMap.add(TypeAdapters.CALENDAR_FACTORY);
    paramMap.add(TimeTypeAdapter.FACTORY);
    paramMap.add(SqlDateTypeAdapter.FACTORY);
    paramMap.add(TypeAdapters.TIMESTAMP_FACTORY);
    paramMap.add(ArrayTypeAdapter.FACTORY);
    paramMap.add(TypeAdapters.CLASS_FACTORY);
    paramMap.add(new CollectionTypeAdapterFactory(this.constructorConstructor));
    paramMap.add(new MapTypeAdapterFactory(this.constructorConstructor, paramBoolean2));
    paramMap.add(new JsonAdapterAnnotationTypeAdapterFactory(this.constructorConstructor));
    paramMap.add(TypeAdapters.ENUM_FACTORY);
    paramMap.add(new ReflectiveTypeAdapterFactory(this.constructorConstructor, paramFieldNamingStrategy, paramExcluder));
    this.factories = Collections.unmodifiableList(paramMap);
  }
  
  private static void assertFullConsumption(Object paramObject, JsonReader paramJsonReader)
  {
    if (paramObject != null) {
      try
      {
        if (paramJsonReader.peek() != JsonToken.END_DOCUMENT) {
          throw new JsonIOException("JSON document was not fully consumed.");
        }
      }
      catch (MalformedJsonException paramObject)
      {
        throw new JsonSyntaxException((Throwable)paramObject);
      }
      catch (IOException paramObject)
      {
        throw new JsonIOException((Throwable)paramObject);
      }
    }
  }
  
  private void checkValidFloatingPoint(double paramDouble)
  {
    if ((Double.isNaN(paramDouble)) || (Double.isInfinite(paramDouble))) {
      throw new IllegalArgumentException(paramDouble + " is not a valid double value as per JSON specification. To override this" + " behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
    }
  }
  
  private TypeAdapter<Number> doubleAdapter(boolean paramBoolean)
  {
    if (paramBoolean) {
      return TypeAdapters.DOUBLE;
    }
    new TypeAdapter()
    {
      public Double read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          return null;
        }
        return Double.valueOf(paramAnonymousJsonReader.nextDouble());
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, Number paramAnonymousNumber)
        throws IOException
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymousJsonWriter.nullValue();
          return;
        }
        double d = paramAnonymousNumber.doubleValue();
        Gson.this.checkValidFloatingPoint(d);
        paramAnonymousJsonWriter.value(paramAnonymousNumber);
      }
    };
  }
  
  private TypeAdapter<Number> floatAdapter(boolean paramBoolean)
  {
    if (paramBoolean) {
      return TypeAdapters.FLOAT;
    }
    new TypeAdapter()
    {
      public Float read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          return null;
        }
        return Float.valueOf((float)paramAnonymousJsonReader.nextDouble());
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, Number paramAnonymousNumber)
        throws IOException
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymousJsonWriter.nullValue();
          return;
        }
        float f = paramAnonymousNumber.floatValue();
        Gson.this.checkValidFloatingPoint(f);
        paramAnonymousJsonWriter.value(paramAnonymousNumber);
      }
    };
  }
  
  private TypeAdapter<Number> longAdapter(LongSerializationPolicy paramLongSerializationPolicy)
  {
    if (paramLongSerializationPolicy == LongSerializationPolicy.DEFAULT) {
      return TypeAdapters.LONG;
    }
    new TypeAdapter()
    {
      public Number read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          return null;
        }
        return Long.valueOf(paramAnonymousJsonReader.nextLong());
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, Number paramAnonymousNumber)
        throws IOException
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymousJsonWriter.nullValue();
          return;
        }
        paramAnonymousJsonWriter.value(paramAnonymousNumber.toString());
      }
    };
  }
  
  private JsonWriter newJsonWriter(Writer paramWriter)
    throws IOException
  {
    if (this.generateNonExecutableJson) {
      paramWriter.write(")]}'\n");
    }
    paramWriter = new JsonWriter(paramWriter);
    if (this.prettyPrinting) {
      paramWriter.setIndent("  ");
    }
    paramWriter.setSerializeNulls(this.serializeNulls);
    return paramWriter;
  }
  
  public <T> T fromJson(JsonElement paramJsonElement, Class<T> paramClass)
    throws JsonSyntaxException
  {
    paramJsonElement = fromJson(paramJsonElement, paramClass);
    return (T)Primitives.wrap(paramClass).cast(paramJsonElement);
  }
  
  public <T> T fromJson(JsonElement paramJsonElement, Type paramType)
    throws JsonSyntaxException
  {
    if (paramJsonElement == null) {
      return null;
    }
    return (T)fromJson(new JsonTreeReader(paramJsonElement), paramType);
  }
  
  /* Error */
  public <T> T fromJson(JsonReader paramJsonReader, Type paramType)
    throws JsonIOException, JsonSyntaxException
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aload_1
    //   3: invokevirtual 411	com/google/gson/stream/JsonReader:isLenient	()Z
    //   6: istore 4
    //   8: aload_1
    //   9: iconst_1
    //   10: invokevirtual 414	com/google/gson/stream/JsonReader:setLenient	(Z)V
    //   13: aload_1
    //   14: invokevirtual 293	com/google/gson/stream/JsonReader:peek	()Lcom/google/gson/stream/JsonToken;
    //   17: pop
    //   18: iconst_0
    //   19: istore_3
    //   20: aload_0
    //   21: aload_2
    //   22: invokestatic 420	com/google/gson/reflect/TypeToken:get	(Ljava/lang/reflect/Type;)Lcom/google/gson/reflect/TypeToken;
    //   25: invokevirtual 424	com/google/gson/Gson:getAdapter	(Lcom/google/gson/reflect/TypeToken;)Lcom/google/gson/TypeAdapter;
    //   28: aload_1
    //   29: invokevirtual 430	com/google/gson/TypeAdapter:read	(Lcom/google/gson/stream/JsonReader;)Ljava/lang/Object;
    //   32: astore_2
    //   33: aload_1
    //   34: iload 4
    //   36: invokevirtual 414	com/google/gson/stream/JsonReader:setLenient	(Z)V
    //   39: aload_2
    //   40: areturn
    //   41: astore_2
    //   42: iload_3
    //   43: ifeq +11 -> 54
    //   46: aload_1
    //   47: iload 4
    //   49: invokevirtual 414	com/google/gson/stream/JsonReader:setLenient	(Z)V
    //   52: aconst_null
    //   53: areturn
    //   54: new 308	com/google/gson/JsonSyntaxException
    //   57: dup
    //   58: aload_2
    //   59: invokespecial 311	com/google/gson/JsonSyntaxException:<init>	(Ljava/lang/Throwable;)V
    //   62: athrow
    //   63: astore_2
    //   64: aload_1
    //   65: iload 4
    //   67: invokevirtual 414	com/google/gson/stream/JsonReader:setLenient	(Z)V
    //   70: aload_2
    //   71: athrow
    //   72: astore_2
    //   73: new 308	com/google/gson/JsonSyntaxException
    //   76: dup
    //   77: aload_2
    //   78: invokespecial 311	com/google/gson/JsonSyntaxException:<init>	(Ljava/lang/Throwable;)V
    //   81: athrow
    //   82: astore_2
    //   83: new 308	com/google/gson/JsonSyntaxException
    //   86: dup
    //   87: aload_2
    //   88: invokespecial 311	com/google/gson/JsonSyntaxException:<init>	(Ljava/lang/Throwable;)V
    //   91: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	this	Gson
    //   0	92	1	paramJsonReader	JsonReader
    //   0	92	2	paramType	Type
    //   1	42	3	i	int
    //   6	60	4	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   13	18	41	java/io/EOFException
    //   20	33	41	java/io/EOFException
    //   13	18	63	finally
    //   20	33	63	finally
    //   54	63	63	finally
    //   73	82	63	finally
    //   83	92	63	finally
    //   13	18	72	java/lang/IllegalStateException
    //   20	33	72	java/lang/IllegalStateException
    //   13	18	82	java/io/IOException
    //   20	33	82	java/io/IOException
  }
  
  public <T> T fromJson(Reader paramReader, Class<T> paramClass)
    throws JsonSyntaxException, JsonIOException
  {
    paramReader = new JsonReader(paramReader);
    Object localObject = fromJson(paramReader, paramClass);
    assertFullConsumption(localObject, paramReader);
    return (T)Primitives.wrap(paramClass).cast(localObject);
  }
  
  public <T> T fromJson(Reader paramReader, Type paramType)
    throws JsonIOException, JsonSyntaxException
  {
    paramReader = new JsonReader(paramReader);
    paramType = fromJson(paramReader, paramType);
    assertFullConsumption(paramType, paramReader);
    return paramType;
  }
  
  public <T> T fromJson(String paramString, Class<T> paramClass)
    throws JsonSyntaxException
  {
    paramString = fromJson(paramString, paramClass);
    return (T)Primitives.wrap(paramClass).cast(paramString);
  }
  
  public <T> T fromJson(String paramString, Type paramType)
    throws JsonSyntaxException
  {
    if (paramString == null) {
      return null;
    }
    return (T)fromJson(new StringReader(paramString), paramType);
  }
  
  public <T> TypeAdapter<T> getAdapter(TypeToken<T> paramTypeToken)
  {
    Object localObject1 = (TypeAdapter)this.typeTokenCache.get(paramTypeToken);
    if (localObject1 != null) {
      return (TypeAdapter<T>)localObject1;
    }
    Object localObject2 = (Map)this.calls.get();
    int i = 0;
    localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = new HashMap();
      this.calls.set(localObject1);
      i = 1;
    }
    localObject2 = (FutureTypeAdapter)((Map)localObject1).get(paramTypeToken);
    if (localObject2 != null) {
      return (TypeAdapter<T>)localObject2;
    }
    try
    {
      localObject2 = new FutureTypeAdapter();
      ((Map)localObject1).put(paramTypeToken, localObject2);
      Iterator localIterator = this.factories.iterator();
      while (localIterator.hasNext())
      {
        TypeAdapter localTypeAdapter = ((TypeAdapterFactory)localIterator.next()).create(this, paramTypeToken);
        if (localTypeAdapter != null)
        {
          ((FutureTypeAdapter)localObject2).setDelegate(localTypeAdapter);
          this.typeTokenCache.put(paramTypeToken, localTypeAdapter);
          return localTypeAdapter;
        }
      }
      throw new IllegalArgumentException("GSON cannot handle " + paramTypeToken);
    }
    finally
    {
      ((Map)localObject1).remove(paramTypeToken);
      if (i != 0) {
        this.calls.remove();
      }
    }
  }
  
  public <T> TypeAdapter<T> getAdapter(Class<T> paramClass)
  {
    return getAdapter(TypeToken.get(paramClass));
  }
  
  public <T> TypeAdapter<T> getDelegateAdapter(TypeAdapterFactory paramTypeAdapterFactory, TypeToken<T> paramTypeToken)
  {
    int i = 0;
    if (!this.factories.contains(paramTypeAdapterFactory)) {
      i = 1;
    }
    Iterator localIterator = this.factories.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (TypeAdapterFactory)localIterator.next();
      if (i == 0)
      {
        if (localObject == paramTypeAdapterFactory) {
          i = 1;
        }
      }
      else
      {
        localObject = ((TypeAdapterFactory)localObject).create(this, paramTypeToken);
        if (localObject != null) {
          return (TypeAdapter<T>)localObject;
        }
      }
    }
    throw new IllegalArgumentException("GSON cannot serialize " + paramTypeToken);
  }
  
  public String toJson(JsonElement paramJsonElement)
  {
    StringWriter localStringWriter = new StringWriter();
    toJson(paramJsonElement, localStringWriter);
    return localStringWriter.toString();
  }
  
  public String toJson(Object paramObject)
  {
    if (paramObject == null) {
      return toJson(JsonNull.INSTANCE);
    }
    return toJson(paramObject, paramObject.getClass());
  }
  
  public String toJson(Object paramObject, Type paramType)
  {
    StringWriter localStringWriter = new StringWriter();
    toJson(paramObject, paramType, localStringWriter);
    return localStringWriter.toString();
  }
  
  public void toJson(JsonElement paramJsonElement, JsonWriter paramJsonWriter)
    throws JsonIOException
  {
    boolean bool1 = paramJsonWriter.isLenient();
    paramJsonWriter.setLenient(true);
    boolean bool2 = paramJsonWriter.isHtmlSafe();
    paramJsonWriter.setHtmlSafe(this.htmlSafe);
    boolean bool3 = paramJsonWriter.getSerializeNulls();
    paramJsonWriter.setSerializeNulls(this.serializeNulls);
    try
    {
      Streams.write(paramJsonElement, paramJsonWriter);
      return;
    }
    catch (IOException paramJsonElement)
    {
      throw new JsonIOException(paramJsonElement);
    }
    finally
    {
      paramJsonWriter.setLenient(bool1);
      paramJsonWriter.setHtmlSafe(bool2);
      paramJsonWriter.setSerializeNulls(bool3);
    }
  }
  
  public void toJson(JsonElement paramJsonElement, Appendable paramAppendable)
    throws JsonIOException
  {
    try
    {
      toJson(paramJsonElement, newJsonWriter(Streams.writerForAppendable(paramAppendable)));
      return;
    }
    catch (IOException paramJsonElement)
    {
      throw new RuntimeException(paramJsonElement);
    }
  }
  
  public void toJson(Object paramObject, Appendable paramAppendable)
    throws JsonIOException
  {
    if (paramObject != null)
    {
      toJson(paramObject, paramObject.getClass(), paramAppendable);
      return;
    }
    toJson(JsonNull.INSTANCE, paramAppendable);
  }
  
  public void toJson(Object paramObject, Type paramType, JsonWriter paramJsonWriter)
    throws JsonIOException
  {
    paramType = getAdapter(TypeToken.get(paramType));
    boolean bool1 = paramJsonWriter.isLenient();
    paramJsonWriter.setLenient(true);
    boolean bool2 = paramJsonWriter.isHtmlSafe();
    paramJsonWriter.setHtmlSafe(this.htmlSafe);
    boolean bool3 = paramJsonWriter.getSerializeNulls();
    paramJsonWriter.setSerializeNulls(this.serializeNulls);
    try
    {
      paramType.write(paramJsonWriter, paramObject);
      return;
    }
    catch (IOException paramObject)
    {
      throw new JsonIOException((Throwable)paramObject);
    }
    finally
    {
      paramJsonWriter.setLenient(bool1);
      paramJsonWriter.setHtmlSafe(bool2);
      paramJsonWriter.setSerializeNulls(bool3);
    }
  }
  
  public void toJson(Object paramObject, Type paramType, Appendable paramAppendable)
    throws JsonIOException
  {
    try
    {
      toJson(paramObject, paramType, newJsonWriter(Streams.writerForAppendable(paramAppendable)));
      return;
    }
    catch (IOException paramObject)
    {
      throw new JsonIOException((Throwable)paramObject);
    }
  }
  
  public JsonElement toJsonTree(Object paramObject)
  {
    if (paramObject == null) {
      return JsonNull.INSTANCE;
    }
    return toJsonTree(paramObject, paramObject.getClass());
  }
  
  public JsonElement toJsonTree(Object paramObject, Type paramType)
  {
    JsonTreeWriter localJsonTreeWriter = new JsonTreeWriter();
    toJson(paramObject, paramType, localJsonTreeWriter);
    return localJsonTreeWriter.get();
  }
  
  public String toString()
  {
    return "{serializeNulls:" + this.serializeNulls + "factories:" + this.factories + ",instanceCreators:" + this.constructorConstructor + "}";
  }
  
  static class FutureTypeAdapter<T>
    extends TypeAdapter<T>
  {
    private TypeAdapter<T> delegate;
    
    public T read(JsonReader paramJsonReader)
      throws IOException
    {
      if (this.delegate == null) {
        throw new IllegalStateException();
      }
      return (T)this.delegate.read(paramJsonReader);
    }
    
    public void setDelegate(TypeAdapter<T> paramTypeAdapter)
    {
      if (this.delegate != null) {
        throw new AssertionError();
      }
      this.delegate = paramTypeAdapter;
    }
    
    public void write(JsonWriter paramJsonWriter, T paramT)
      throws IOException
    {
      if (this.delegate == null) {
        throw new IllegalStateException();
      }
      this.delegate.write(paramJsonWriter, paramT);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/gson/Gson.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */