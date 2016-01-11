package org.parceler.converter;

import java.util.TreeMap;

public abstract class TreeMapParcelConverter<K, V>
  extends MapParcelConverter<K, V, TreeMap<K, V>>
{
  public TreeMap<K, V> createMap()
  {
    return new TreeMap();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/converter/TreeMapParcelConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */