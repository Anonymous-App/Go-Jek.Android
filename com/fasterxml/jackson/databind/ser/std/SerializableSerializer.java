package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializable.Base;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@JacksonStdImpl
public class SerializableSerializer
  extends StdSerializer<JsonSerializable>
{
  private static final AtomicReference<ObjectMapper> _mapperReference = new AtomicReference();
  public static final SerializableSerializer instance = new SerializableSerializer();
  
  protected SerializableSerializer()
  {
    super(JsonSerializable.class);
  }
  
  private static final ObjectMapper _getObjectMapper()
  {
    try
    {
      ObjectMapper localObjectMapper2 = (ObjectMapper)_mapperReference.get();
      ObjectMapper localObjectMapper1 = localObjectMapper2;
      if (localObjectMapper2 == null)
      {
        localObjectMapper1 = new ObjectMapper();
        _mapperReference.set(localObjectMapper1);
      }
      return localObjectMapper1;
    }
    finally {}
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    paramJsonFormatVisitorWrapper.expectAnyFormat(paramJavaType);
  }
  
  /* Error */
  public com.fasterxml.jackson.databind.JsonNode getSchema(SerializerProvider paramSerializerProvider, java.lang.reflect.Type paramType)
    throws JsonMappingException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 61	com/fasterxml/jackson/databind/ser/std/SerializableSerializer:createObjectNode	()Lcom/fasterxml/jackson/databind/node/ObjectNode;
    //   4: astore 9
    //   6: ldc 63
    //   8: astore 7
    //   10: aconst_null
    //   11: astore 8
    //   13: aconst_null
    //   14: astore_1
    //   15: aconst_null
    //   16: astore 6
    //   18: aload 6
    //   20: astore 5
    //   22: aload 8
    //   24: astore_3
    //   25: aload 7
    //   27: astore 4
    //   29: aload_2
    //   30: ifnull +108 -> 138
    //   33: aload_2
    //   34: invokestatic 69	com/fasterxml/jackson/databind/type/TypeFactory:rawClass	(Ljava/lang/reflect/Type;)Ljava/lang/Class;
    //   37: astore_2
    //   38: aload 6
    //   40: astore 5
    //   42: aload 8
    //   44: astore_3
    //   45: aload 7
    //   47: astore 4
    //   49: aload_2
    //   50: ldc 71
    //   52: invokevirtual 77	java/lang/Class:isAnnotationPresent	(Ljava/lang/Class;)Z
    //   55: ifeq +83 -> 138
    //   58: aload_2
    //   59: ldc 71
    //   61: invokevirtual 81	java/lang/Class:getAnnotation	(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
    //   64: checkcast 71	com/fasterxml/jackson/databind/jsonschema/JsonSerializableSchema
    //   67: astore 7
    //   69: aload 7
    //   71: invokeinterface 85 1 0
    //   76: astore_2
    //   77: ldc 87
    //   79: aload 7
    //   81: invokeinterface 90 1 0
    //   86: invokevirtual 96	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   89: ifne +11 -> 100
    //   92: aload 7
    //   94: invokeinterface 90 1 0
    //   99: astore_1
    //   100: aload 6
    //   102: astore 5
    //   104: aload_1
    //   105: astore_3
    //   106: aload_2
    //   107: astore 4
    //   109: ldc 87
    //   111: aload 7
    //   113: invokeinterface 99 1 0
    //   118: invokevirtual 96	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   121: ifne +17 -> 138
    //   124: aload 7
    //   126: invokeinterface 99 1 0
    //   131: astore 5
    //   133: aload_2
    //   134: astore 4
    //   136: aload_1
    //   137: astore_3
    //   138: aload 9
    //   140: ldc 101
    //   142: aload 4
    //   144: invokevirtual 107	com/fasterxml/jackson/databind/node/ObjectNode:put	(Ljava/lang/String;Ljava/lang/String;)Lcom/fasterxml/jackson/databind/node/ObjectNode;
    //   147: pop
    //   148: aload_3
    //   149: ifnull +18 -> 167
    //   152: aload 9
    //   154: ldc 109
    //   156: invokestatic 111	com/fasterxml/jackson/databind/ser/std/SerializableSerializer:_getObjectMapper	()Lcom/fasterxml/jackson/databind/ObjectMapper;
    //   159: aload_3
    //   160: invokevirtual 115	com/fasterxml/jackson/databind/ObjectMapper:readTree	(Ljava/lang/String;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   163: invokevirtual 118	com/fasterxml/jackson/databind/node/ObjectNode:set	(Ljava/lang/String;Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   166: pop
    //   167: aload 5
    //   169: ifnull +19 -> 188
    //   172: aload 9
    //   174: ldc 120
    //   176: invokestatic 111	com/fasterxml/jackson/databind/ser/std/SerializableSerializer:_getObjectMapper	()Lcom/fasterxml/jackson/databind/ObjectMapper;
    //   179: aload 5
    //   181: invokevirtual 115	com/fasterxml/jackson/databind/ObjectMapper:readTree	(Ljava/lang/String;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   184: invokevirtual 118	com/fasterxml/jackson/databind/node/ObjectNode:set	(Ljava/lang/String;Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   187: pop
    //   188: aload 9
    //   190: areturn
    //   191: astore_1
    //   192: new 46	com/fasterxml/jackson/databind/JsonMappingException
    //   195: dup
    //   196: ldc 122
    //   198: invokespecial 125	com/fasterxml/jackson/databind/JsonMappingException:<init>	(Ljava/lang/String;)V
    //   201: athrow
    //   202: astore_1
    //   203: new 46	com/fasterxml/jackson/databind/JsonMappingException
    //   206: dup
    //   207: ldc 127
    //   209: invokespecial 125	com/fasterxml/jackson/databind/JsonMappingException:<init>	(Ljava/lang/String;)V
    //   212: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	213	0	this	SerializableSerializer
    //   0	213	1	paramSerializerProvider	SerializerProvider
    //   0	213	2	paramType	java.lang.reflect.Type
    //   24	136	3	localObject1	Object
    //   27	116	4	localObject2	Object
    //   20	160	5	localObject3	Object
    //   16	85	6	localObject4	Object
    //   8	117	7	localObject5	Object
    //   11	32	8	localObject6	Object
    //   4	185	9	localObjectNode	com.fasterxml.jackson.databind.node.ObjectNode
    // Exception table:
    //   from	to	target	type
    //   152	167	191	java/io/IOException
    //   172	188	202	java/io/IOException
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, JsonSerializable paramJsonSerializable)
  {
    if ((paramJsonSerializable instanceof JsonSerializable.Base)) {
      return ((JsonSerializable.Base)paramJsonSerializable).isEmpty(paramSerializerProvider);
    }
    return false;
  }
  
  public void serialize(JsonSerializable paramJsonSerializable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    paramJsonSerializable.serialize(paramJsonGenerator, paramSerializerProvider);
  }
  
  public final void serializeWithType(JsonSerializable paramJsonSerializable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    paramJsonSerializable.serializeWithType(paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ser/std/SerializableSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */