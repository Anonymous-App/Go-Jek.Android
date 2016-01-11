package org.parceler.converter;

import java.util.HashSet;

public abstract class HashSetParcelConverter<T>
  extends CollectionParcelConverter<T, HashSet<T>>
{
  public HashSet<T> createCollection()
  {
    return new HashSet();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/converter/HashSetParcelConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */