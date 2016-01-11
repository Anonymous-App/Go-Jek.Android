package com.nostra13.universalimageloader.cache.memory;

import java.util.Collection;

@Deprecated
public abstract interface MemoryCacheAware<K, V>
{
  public abstract void clear();
  
  public abstract V get(K paramK);
  
  public abstract Collection<K> keys();
  
  public abstract boolean put(K paramK, V paramV);
  
  public abstract V remove(K paramK);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/nostra13/universalimageloader/cache/memory/MemoryCacheAware.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */