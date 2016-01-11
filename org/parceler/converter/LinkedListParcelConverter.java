package org.parceler.converter;

import java.util.LinkedList;

public abstract class LinkedListParcelConverter<T>
  extends CollectionParcelConverter<T, LinkedList<T>>
{
  public LinkedList<T> createCollection()
  {
    return new LinkedList();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/converter/LinkedListParcelConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */