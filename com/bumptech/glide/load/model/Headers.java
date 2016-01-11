package com.bumptech.glide.load.model;

import java.util.Map;

public abstract interface Headers
{
  public static final Headers DEFAULT = new LazyHeaders.Builder().build();
  @Deprecated
  public static final Headers NONE = new Headers.1();
  
  public abstract Map<String, String> getHeaders();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/bumptech/glide/load/model/Headers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */