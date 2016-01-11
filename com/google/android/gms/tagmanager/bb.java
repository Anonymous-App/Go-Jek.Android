package com.google.android.gms.tagmanager;

import android.util.LruCache;

class bb<K, V>
  implements k<K, V>
{
  private LruCache<K, V> apI;
  
  bb(int paramInt, final l.a<K, V> parama)
  {
    this.apI = new LruCache(paramInt)
    {
      protected int sizeOf(K paramAnonymousK, V paramAnonymousV)
      {
        return parama.sizeOf(paramAnonymousK, paramAnonymousV);
      }
    };
  }
  
  public void e(K paramK, V paramV)
  {
    this.apI.put(paramK, paramV);
  }
  
  public V get(K paramK)
  {
    return (V)this.apI.get(paramK);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/bb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */