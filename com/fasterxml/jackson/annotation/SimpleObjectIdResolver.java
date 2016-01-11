package com.fasterxml.jackson.annotation;

import java.util.HashMap;
import java.util.Map;

public class SimpleObjectIdResolver
  implements ObjectIdResolver
{
  protected Map<ObjectIdGenerator.IdKey, Object> _items;
  
  public void bindItem(ObjectIdGenerator.IdKey paramIdKey, Object paramObject)
  {
    if (this._items == null) {
      this._items = new HashMap();
    }
    while (!this._items.containsKey(paramIdKey))
    {
      this._items.put(paramIdKey, paramObject);
      return;
    }
    throw new IllegalStateException("Already had POJO for id (" + paramIdKey.key.getClass().getName() + ") [" + paramIdKey + "]");
  }
  
  public boolean canUseFor(ObjectIdResolver paramObjectIdResolver)
  {
    return paramObjectIdResolver.getClass() == getClass();
  }
  
  public ObjectIdResolver newForDeserialization(Object paramObject)
  {
    return new SimpleObjectIdResolver();
  }
  
  public Object resolveId(ObjectIdGenerator.IdKey paramIdKey)
  {
    if (this._items == null) {
      return null;
    }
    return this._items.get(paramIdKey);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/annotation/SimpleObjectIdResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */