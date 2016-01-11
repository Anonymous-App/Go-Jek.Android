package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializableWithType;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;

@JacksonStdImpl
public class SerializableWithTypeSerializer
  extends SerializerBase<JsonSerializableWithType>
{
  public static final SerializableWithTypeSerializer instance = new SerializableWithTypeSerializer();
  
  protected SerializableWithTypeSerializer()
  {
    super(JsonSerializableWithType.class);
  }
  
  /* Error */
  public org.codehaus.jackson.JsonNode getSchema(SerializerProvider paramSerializerProvider, java.lang.reflect.Type paramType)
    throws org.codehaus.jackson.map.JsonMappingException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 31	org/codehaus/jackson/map/ser/std/SerializableWithTypeSerializer:createObjectNode	()Lorg/codehaus/jackson/node/ObjectNode;
    //   4: astore 9
    //   6: ldc 33
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
    //   34: invokestatic 39	org/codehaus/jackson/map/type/TypeFactory:rawClass	(Ljava/lang/reflect/Type;)Ljava/lang/Class;
    //   37: astore_2
    //   38: aload 6
    //   40: astore 5
    //   42: aload 8
    //   44: astore_3
    //   45: aload 7
    //   47: astore 4
    //   49: aload_2
    //   50: ldc 41
    //   52: invokevirtual 47	java/lang/Class:isAnnotationPresent	(Ljava/lang/Class;)Z
    //   55: ifeq +83 -> 138
    //   58: aload_2
    //   59: ldc 41
    //   61: invokevirtual 51	java/lang/Class:getAnnotation	(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
    //   64: checkcast 41	org/codehaus/jackson/schema/JsonSerializableSchema
    //   67: astore 7
    //   69: aload 7
    //   71: invokeinterface 55 1 0
    //   76: astore_2
    //   77: ldc 57
    //   79: aload 7
    //   81: invokeinterface 60 1 0
    //   86: invokevirtual 66	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   89: ifne +11 -> 100
    //   92: aload 7
    //   94: invokeinterface 60 1 0
    //   99: astore_1
    //   100: aload 6
    //   102: astore 5
    //   104: aload_1
    //   105: astore_3
    //   106: aload_2
    //   107: astore 4
    //   109: ldc 57
    //   111: aload 7
    //   113: invokeinterface 69 1 0
    //   118: invokevirtual 66	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   121: ifne +17 -> 138
    //   124: aload 7
    //   126: invokeinterface 69 1 0
    //   131: astore 5
    //   133: aload_2
    //   134: astore 4
    //   136: aload_1
    //   137: astore_3
    //   138: aload 9
    //   140: ldc 71
    //   142: aload 4
    //   144: invokevirtual 77	org/codehaus/jackson/node/ObjectNode:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   147: aload_3
    //   148: ifnull +27 -> 175
    //   151: aload 9
    //   153: ldc 79
    //   155: new 81	org/codehaus/jackson/map/ObjectMapper
    //   158: dup
    //   159: invokespecial 82	org/codehaus/jackson/map/ObjectMapper:<init>	()V
    //   162: aload_3
    //   163: ldc 84
    //   165: invokevirtual 88	org/codehaus/jackson/map/ObjectMapper:readValue	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   168: checkcast 84	org/codehaus/jackson/JsonNode
    //   171: invokevirtual 91	org/codehaus/jackson/node/ObjectNode:put	(Ljava/lang/String;Lorg/codehaus/jackson/JsonNode;)Lorg/codehaus/jackson/JsonNode;
    //   174: pop
    //   175: aload 5
    //   177: ifnull +28 -> 205
    //   180: aload 9
    //   182: ldc 93
    //   184: new 81	org/codehaus/jackson/map/ObjectMapper
    //   187: dup
    //   188: invokespecial 82	org/codehaus/jackson/map/ObjectMapper:<init>	()V
    //   191: aload 5
    //   193: ldc 84
    //   195: invokevirtual 88	org/codehaus/jackson/map/ObjectMapper:readValue	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   198: checkcast 84	org/codehaus/jackson/JsonNode
    //   201: invokevirtual 91	org/codehaus/jackson/node/ObjectNode:put	(Ljava/lang/String;Lorg/codehaus/jackson/JsonNode;)Lorg/codehaus/jackson/JsonNode;
    //   204: pop
    //   205: aload 9
    //   207: areturn
    //   208: astore_1
    //   209: new 95	java/lang/IllegalStateException
    //   212: dup
    //   213: aload_1
    //   214: invokespecial 98	java/lang/IllegalStateException:<init>	(Ljava/lang/Throwable;)V
    //   217: athrow
    //   218: astore_1
    //   219: new 95	java/lang/IllegalStateException
    //   222: dup
    //   223: aload_1
    //   224: invokespecial 98	java/lang/IllegalStateException:<init>	(Ljava/lang/Throwable;)V
    //   227: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	228	0	this	SerializableWithTypeSerializer
    //   0	228	1	paramSerializerProvider	SerializerProvider
    //   0	228	2	paramType	java.lang.reflect.Type
    //   24	139	3	localObject1	Object
    //   27	116	4	localObject2	Object
    //   20	172	5	localObject3	Object
    //   16	85	6	localObject4	Object
    //   8	117	7	localObject5	Object
    //   11	32	8	localObject6	Object
    //   4	202	9	localObjectNode	org.codehaus.jackson.node.ObjectNode
    // Exception table:
    //   from	to	target	type
    //   151	175	208	java/io/IOException
    //   180	205	218	java/io/IOException
  }
  
  public void serialize(JsonSerializableWithType paramJsonSerializableWithType, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    paramJsonSerializableWithType.serialize(paramJsonGenerator, paramSerializerProvider);
  }
  
  public final void serializeWithType(JsonSerializableWithType paramJsonSerializableWithType, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonGenerationException
  {
    paramJsonSerializableWithType.serializeWithType(paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/ser/std/SerializableWithTypeSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */