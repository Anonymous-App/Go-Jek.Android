package org.parceler.converter;

import android.os.Parcel;
import android.util.SparseArray;
import org.parceler.ParcelConverter;

public abstract class SparseArrayParcelConverter<T>
  implements ParcelConverter<SparseArray<T>>
{
  public SparseArray<T> fromParcel(Parcel paramParcel)
  {
    int j = paramParcel.readInt();
    Object localObject;
    if (j < 0)
    {
      localObject = null;
      return (SparseArray<T>)localObject;
    }
    SparseArray localSparseArray = new SparseArray(j);
    int i = 0;
    for (;;)
    {
      localObject = localSparseArray;
      if (i >= j) {
        break;
      }
      localSparseArray.append(paramParcel.readInt(), itemFromParcel(paramParcel));
      i += 1;
    }
  }
  
  public abstract T itemFromParcel(Parcel paramParcel);
  
  public abstract void itemToParcel(T paramT, Parcel paramParcel);
  
  public void toParcel(SparseArray<T> paramSparseArray, Parcel paramParcel)
  {
    if (paramSparseArray == null) {
      paramParcel.writeInt(-1);
    }
    for (;;)
    {
      return;
      paramParcel.writeInt(paramSparseArray.size());
      int i = 0;
      while (i < paramSparseArray.size())
      {
        paramParcel.writeInt(paramSparseArray.keyAt(i));
        itemToParcel(paramSparseArray.valueAt(i), paramParcel);
        i += 1;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/converter/SparseArrayParcelConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */