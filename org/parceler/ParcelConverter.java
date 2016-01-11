package org.parceler;

import android.os.Parcel;

public abstract interface ParcelConverter<T>
  extends TypeRangeParcelConverter<T, T>
{
  public static final String CONVERT_FROM_PARCEL = "fromParcel";
  public static final String CONVERT_TO_PARCEL = "toParcel";
  
  public static class EmptyConverter
    implements ParcelConverter<Object>
  {
    public Object fromParcel(Parcel paramParcel)
    {
      throw new ParcelerRuntimeException("Empty Converter should not be used.");
    }
    
    public void toParcel(Object paramObject, Parcel paramParcel)
    {
      throw new ParcelerRuntimeException("Empty Converter should not be used.");
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/ParcelConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */