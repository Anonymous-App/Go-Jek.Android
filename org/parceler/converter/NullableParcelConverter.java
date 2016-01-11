package org.parceler.converter;

import android.os.Parcel;
import org.parceler.ParcelConverter;

public abstract class NullableParcelConverter<T>
  implements ParcelConverter<T>
{
  private static final int NOT_NULL = 1;
  private static final int NULL = -1;
  
  public T fromParcel(Parcel paramParcel)
  {
    if (paramParcel.readInt() == -1) {
      return null;
    }
    return (T)nullSafeFromParcel(paramParcel);
  }
  
  public abstract T nullSafeFromParcel(Parcel paramParcel);
  
  public abstract void nullSafeToParcel(T paramT, Parcel paramParcel);
  
  public void toParcel(T paramT, Parcel paramParcel)
  {
    if (paramT == null)
    {
      paramParcel.writeInt(-1);
      return;
    }
    paramParcel.writeInt(1);
    nullSafeToParcel(paramT, paramParcel);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/converter/NullableParcelConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */