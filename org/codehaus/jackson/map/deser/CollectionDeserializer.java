package org.codehaus.jackson.map.deser;

import java.lang.reflect.Constructor;
import java.util.Collection;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.type.JavaType;

@Deprecated
public class CollectionDeserializer
  extends org.codehaus.jackson.map.deser.std.CollectionDeserializer
{
  protected CollectionDeserializer(CollectionDeserializer paramCollectionDeserializer)
  {
    super(paramCollectionDeserializer);
  }
  
  @Deprecated
  public CollectionDeserializer(JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer, Constructor<Collection<Object>> paramConstructor)
  {
    super(paramJavaType, paramJsonDeserializer, paramTypeDeserializer, paramConstructor);
  }
  
  public CollectionDeserializer(JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer, ValueInstantiator paramValueInstantiator)
  {
    super(paramJavaType, paramJsonDeserializer, paramTypeDeserializer, paramValueInstantiator);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/deser/CollectionDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */