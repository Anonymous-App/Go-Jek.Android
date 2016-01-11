package org.parceler.converter;

import android.os.Parcel;
import java.util.Collection;
import java.util.Iterator;
import org.parceler.TypeRangeParcelConverter;

public abstract class CollectionParcelConverter<T, C extends Collection<T>>
  implements TypeRangeParcelConverter<Collection<T>, C>
{
  private static final int NULL = -1;
  
  public abstract C createCollection();
  
  public C fromParcel(Parcel paramParcel)
  {
    int j = paramParcel.readInt();
    Object localObject;
    if (j == -1)
    {
      localObject = null;
      return (C)localObject;
    }
    Collection localCollection = createCollection();
    int i = 0;
    for (;;)
    {
      localObject = localCollection;
      if (i >= j) {
        break;
      }
      localCollection.add(itemFromParcel(paramParcel));
      i += 1;
    }
  }
  
  public abstract T itemFromParcel(Parcel paramParcel);
  
  public abstract void itemToParcel(T paramT, Parcel paramParcel);
  
  public void toParcel(Collection<T> paramCollection, Parcel paramParcel)
  {
    if (paramCollection == null) {
      paramParcel.writeInt(-1);
    }
    for (;;)
    {
      return;
      paramParcel.writeInt(paramCollection.size());
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext()) {
        itemToParcel(paramCollection.next(), paramParcel);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/converter/CollectionParcelConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */