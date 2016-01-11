package org.parceler.converter;

import java.util.LinkedHashSet;

public abstract class LinkedHashSetParcelConverter<T>
  extends CollectionParcelConverter<T, LinkedHashSet<T>>
{
  public LinkedHashSet<T> createCollection()
  {
    return new LinkedHashSet();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/converter/LinkedHashSetParcelConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */