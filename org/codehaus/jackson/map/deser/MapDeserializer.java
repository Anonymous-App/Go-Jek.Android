package org.codehaus.jackson.map.deser;

import java.lang.reflect.Constructor;
import java.util.Map;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.type.JavaType;

@Deprecated
public class MapDeserializer
  extends org.codehaus.jackson.map.deser.std.MapDeserializer
{
  protected MapDeserializer(MapDeserializer paramMapDeserializer)
  {
    super(paramMapDeserializer);
  }
  
  @Deprecated
  public MapDeserializer(JavaType paramJavaType, Constructor<Map<Object, Object>> paramConstructor, KeyDeserializer paramKeyDeserializer, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    super(paramJavaType, paramConstructor, paramKeyDeserializer, paramJsonDeserializer, paramTypeDeserializer);
  }
  
  public MapDeserializer(JavaType paramJavaType, ValueInstantiator paramValueInstantiator, KeyDeserializer paramKeyDeserializer, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    super(paramJavaType, paramValueInstantiator, paramKeyDeserializer, paramJsonDeserializer, paramTypeDeserializer);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/deser/MapDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */