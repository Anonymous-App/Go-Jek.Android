package org.parceler;

import android.os.Parcel;

public abstract interface TypeRangeParcelConverter<L, U extends L>
{
  public abstract U fromParcel(Parcel paramParcel);
  
  public abstract void toParcel(L paramL, Parcel paramParcel);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/TypeRangeParcelConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */