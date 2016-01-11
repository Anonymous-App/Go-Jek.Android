package org.codehaus.jackson.map.ser;

import org.codehaus.jackson.type.JavaType;

@Deprecated
public abstract class SerializerBase<T>
  extends org.codehaus.jackson.map.ser.std.SerializerBase<T>
{
  protected SerializerBase(Class<T> paramClass)
  {
    super(paramClass);
  }
  
  protected SerializerBase(Class<?> paramClass, boolean paramBoolean)
  {
    super(paramClass, paramBoolean);
  }
  
  protected SerializerBase(JavaType paramJavaType)
  {
    super(paramJavaType);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/ser/SerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */