package org.parceler.converter;

import java.util.HashMap;

public abstract class HashMapParcelConverter<K, V>
  extends MapParcelConverter<K, V, HashMap<K, V>>
{
  public HashMap<K, V> createMap()
  {
    return new HashMap();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/converter/HashMapParcelConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */