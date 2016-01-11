package org.parceler.converter;

import java.util.TreeSet;

public abstract class TreeSetParcelConverter<T>
  extends CollectionParcelConverter<T, TreeSet<T>>
{
  public TreeSet<T> createCollection()
  {
    return new TreeSet();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/converter/TreeSetParcelConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */