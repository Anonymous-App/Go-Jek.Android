package org.parceler.converter;

import android.os.Parcel;
import org.parceler.ParcelConverter;

public class CharArrayParcelConverter
  implements ParcelConverter<char[]>
{
  private static final int NULL = -1;
  
  public char[] fromParcel(Parcel paramParcel)
  {
    int i = paramParcel.readInt();
    if (i == -1) {
      return null;
    }
    char[] arrayOfChar = new char[i];
    paramParcel.readCharArray(arrayOfChar);
    return arrayOfChar;
  }
  
  public void toParcel(char[] paramArrayOfChar, Parcel paramParcel)
  {
    if (paramArrayOfChar == null)
    {
      paramParcel.writeInt(-1);
      return;
    }
    paramParcel.writeInt(paramArrayOfChar.length);
    paramParcel.writeCharArray(paramArrayOfChar);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/converter/CharArrayParcelConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */