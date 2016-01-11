package org.codehaus.jackson.map.deser;

import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.deser.std.ObjectArrayDeserializer;
import org.codehaus.jackson.map.type.ArrayType;

@Deprecated
public class ArrayDeserializer
  extends ObjectArrayDeserializer
{
  @Deprecated
  public ArrayDeserializer(ArrayType paramArrayType, JsonDeserializer<Object> paramJsonDeserializer)
  {
    this(paramArrayType, paramJsonDeserializer, null);
  }
  
  public ArrayDeserializer(ArrayType paramArrayType, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    super(paramArrayType, paramJsonDeserializer, paramTypeDeserializer);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/deser/ArrayDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */