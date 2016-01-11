package com.fasterxml.jackson.core.util;

import java.util.concurrent.ConcurrentHashMap;

public final class InternCache
  extends ConcurrentHashMap<String, String>
{
  private static final int MAX_ENTRIES = 180;
  public static final InternCache instance = new InternCache();
  private final Object lock = new Object();
  
  private InternCache()
  {
    super(180, 0.8F, 4);
  }
  
  public String intern(String paramString)
  {
    ??? = (String)get(paramString);
    if (??? != null) {
      return (String)???;
    }
    if (size() >= 180) {}
    synchronized (this.lock)
    {
      if (size() >= 180) {
        clear();
      }
      paramString = paramString.intern();
      put(paramString, paramString);
      return paramString;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/util/InternCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */