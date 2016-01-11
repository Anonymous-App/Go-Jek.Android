package org.parceler;

public abstract interface ParcelWrapper<T>
{
  public static final String GET_PARCEL = "getParcel";
  
  public abstract T getParcel();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/ParcelWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */