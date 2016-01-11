package org.codehaus.jackson.map.deser;

import org.codehaus.jackson.map.deser.std.StdDeserializer;

@Deprecated
public abstract class StdScalarDeserializer<T>
  extends StdDeserializer<T>
{
  protected StdScalarDeserializer(Class<?> paramClass)
  {
    super(paramClass);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/map/deser/StdScalarDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */