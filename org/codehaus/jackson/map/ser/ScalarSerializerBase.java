package org.codehaus.jackson.map.ser;

import org.codehaus.jackson.map.ser.std.SerializerBase;

@Deprecated
public abstract class ScalarSerializerBase<T>
  extends SerializerBase<T>
{
  protected ScalarSerializerBase(Class<T> paramClass)
  {
    super(paramClass);
  }
  
  protected ScalarSerializerBase(Class<?> paramClass, boolean paramBoolean)
  {
    super(paramClass);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/ser/ScalarSerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */