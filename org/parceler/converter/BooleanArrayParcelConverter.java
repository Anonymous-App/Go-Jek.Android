package org.parceler.converter;

import android.os.Parcel;
import org.parceler.ParcelConverter;

public class BooleanArrayParcelConverter
  implements ParcelConverter<boolean[]>
{
  private static final int NULL = -1;
  
  public boolean[] fromParcel(Parcel paramParcel)
  {
    int i = paramParcel.readInt();
    if (i == -1) {
      return null;
    }
    boolean[] arrayOfBoolean = new boolean[i];
    paramParcel.readBooleanArray(arrayOfBoolean);
    return arrayOfBoolean;
  }
  
  public void toParcel(boolean[] paramArrayOfBoolean, Parcel paramParcel)
  {
    if (paramArrayOfBoolean == null)
    {
      paramParcel.writeInt(-1);
      return;
    }
    paramParcel.writeInt(paramArrayOfBoolean.length);
    paramParcel.writeBooleanArray(paramArrayOfBoolean);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/converter/BooleanArrayParcelConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */