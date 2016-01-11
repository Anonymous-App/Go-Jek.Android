package org.parceler;

import java.util.Map;

public abstract interface Repository<T>
{
  public abstract Map<Class, T> get();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/Repository.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */