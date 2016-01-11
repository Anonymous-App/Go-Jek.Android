package org.parceler.converter;

import java.util.ArrayList;

public abstract class ArrayListParcelConverter<T>
  extends CollectionParcelConverter<T, ArrayList<T>>
{
  public ArrayList<T> createCollection()
  {
    return new ArrayList();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/converter/ArrayListParcelConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */