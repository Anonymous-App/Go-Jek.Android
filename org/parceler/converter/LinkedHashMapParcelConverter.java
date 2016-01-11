package org.parceler.converter;

import java.util.LinkedHashMap;

public abstract class LinkedHashMapParcelConverter<K, V>
  extends MapParcelConverter<K, V, LinkedHashMap<K, V>>
{
  public LinkedHashMap<K, V> createMap()
  {
    return new LinkedHashMap();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/converter/LinkedHashMapParcelConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */