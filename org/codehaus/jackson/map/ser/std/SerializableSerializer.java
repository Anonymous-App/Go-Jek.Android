package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializable;
import org.codehaus.jackson.map.JsonSerializableWithType;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.ser.SerializerBase;

@JacksonStdImpl
public class SerializableSerializer
  extends SerializerBase<JsonSerializable>
{
  public static final SerializableSerializer instance = new SerializableSerializer();
  
  protected SerializableSerializer()
  {
    super(JsonSerializable.class);
  }
  
  /* Error */
  public org.codehaus.jackson.JsonNode getSchema(SerializerProvider paramSerializerProvider, java.lang.reflect.Type paramType)
    throws org.codehaus.jackson.map.JsonMappingException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 31	org/codehaus/jackson/map/ser/std/SerializableSerializer:createObjectNode	()Lorg/codehaus/jackson/node/ObjectNode;
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
    //   30: ifnull +111 -> 141
    //   33: aload_2
    //   34: invokestatic 39	org/codehaus/jackson/map/type/TypeFactory:type	(Ljava/lang/reflect/Type;)Lorg/codehaus/jackson/type/JavaType;
    //   37: invokevirtual 45	org/codehaus/jackson/type/JavaType:getRawClass	()Ljava/lang/Class;
    //   40: astore_2
    //   41: aload 6
    //   43: astore 5
    //   45: aload 8
    //   47: astore_3
    //   48: aload 7
    //   50: astore 4
    //   52: aload_2
    //   53: ldc 47
    //   55: invokevirtual 53	java/lang/Class:isAnnotationPresent	(Ljava/lang/Class;)Z
    //   58: ifeq +83 -> 141
    //   61: aload_2
    //   62: ldc 47
    //   64: invokevirtual 57	java/lang/Class:getAnnotation	(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
    //   67: checkcast 47	org/codehaus/jackson/schema/JsonSerializableSchema
    //   70: astore 7
    //   72: aload 7
    //   74: invokeinterface 61 1 0
    //   79: astore_2
    //   80: ldc 63
    //   82: aload 7
    //   84: invokeinterface 66 1 0
    //   89: invokevirtual 72	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   92: ifne +11 -> 103
    //   95: aload 7
    //   97: invokeinterface 66 1 0
    //   102: astore_1
    //   103: aload 6
    //   105: astore 5
    //   107: aload_1
    //   108: astore_3
    //   109: aload_2
    //   110: astore 4
    //   112: ldc 63
    //   114: aload 7
    //   116: invokeinterface 75 1 0
    //   121: invokevirtual 72	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   124: ifne +17 -> 141
    //   127: aload 7
    //   129: invokeinterface 75 1 0
    //   134: astore 5
    //   136: aload_2
    //   137: astore 4
    //   139: aload_1
    //   140: astore_3
    //   141: aload 9
    //   143: ldc 76
    //   145: aload 4
    //   147: invokevirtual 82	org/codehaus/jackson/node/ObjectNode:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   150: aload_3
    //   151: ifnull +27 -> 178
    //   154: aload 9
    //   156: ldc 84
    //   158: new 86	org/codehaus/jackson/map/ObjectMapper
    //   161: dup
    //   162: invokespecial 87	org/codehaus/jackson/map/ObjectMapper:<init>	()V
    //   165: aload_3
    //   166: ldc 89
    //   168: invokevirtual 93	org/codehaus/jackson/map/ObjectMapper:readValue	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   171: checkcast 89	org/codehaus/jackson/JsonNode
    //   174: invokevirtual 96	org/codehaus/jackson/node/ObjectNode:put	(Ljava/lang/String;Lorg/codehaus/jackson/JsonNode;)Lorg/codehaus/jackson/JsonNode;
    //   177: pop
    //   178: aload 5
    //   180: ifnull +28 -> 208
    //   183: aload 9
    //   185: ldc 98
    //   187: new 86	org/codehaus/jackson/map/ObjectMapper
    //   190: dup
    //   191: invokespecial 87	org/codehaus/jackson/map/ObjectMapper:<init>	()V
    //   194: aload 5
    //   196: ldc 89
    //   198: invokevirtual 93	org/codehaus/jackson/map/ObjectMapper:readValue	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   201: checkcast 89	org/codehaus/jackson/JsonNode
    //   204: invokevirtual 96	org/codehaus/jackson/node/ObjectNode:put	(Ljava/lang/String;Lorg/codehaus/jackson/JsonNode;)Lorg/codehaus/jackson/JsonNode;
    //   207: pop
    //   208: aload 9
    //   210: areturn
    //   211: astore_1
    //   212: new 100	java/lang/IllegalStateException
    //   215: dup
    //   216: aload_1
    //   217: invokespecial 103	java/lang/IllegalStateException:<init>	(Ljava/lang/Throwable;)V
    //   220: athrow
    //   221: astore_1
    //   222: new 100	java/lang/IllegalStateException
    //   225: dup
    //   226: aload_1
    //   227: invokespecial 103	java/lang/IllegalStateException:<init>	(Ljava/lang/Throwable;)V
    //   230: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	231	0	this	SerializableSerializer
    //   0	231	1	paramSerializerProvider	SerializerProvider
    //   0	231	2	paramType	java.lang.reflect.Type
    //   24	142	3	localObject1	Object
    //   27	119	4	localObject2	Object
    //   20	175	5	localObject3	Object
    //   16	88	6	localObject4	Object
    //   8	120	7	localObject5	Object
    //   11	35	8	localObject6	Object
    //   4	205	9	localObjectNode	org.codehaus.jackson.node.ObjectNode
    // Exception table:
    //   from	to	target	type
    //   154	178	211	java/io/IOException
    //   183	208	221	java/io/IOException
  }
  
  public void serialize(JsonSerializable paramJsonSerializable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    paramJsonSerializable.serialize(paramJsonGenerator, paramSerializerProvider);
  }
  
  public final void serializeWithType(JsonSerializable paramJsonSerializable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonGenerationException
  {
    if ((paramJsonSerializable instanceof JsonSerializableWithType))
    {
      ((JsonSerializableWithType)paramJsonSerializable).serializeWithType(paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
      return;
    }
    serialize(paramJsonSerializable, paramJsonGenerator, paramSerializerProvider);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/ser/std/SerializableSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */