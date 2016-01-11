package com.gojek.app.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Martlocation
  implements Parcelable
{
  public static final Parcelable.Creator<Martlocation> CREATOR = new Martlocation.1();
  public double lat;
  public double lon;
  
  public Martlocation() {}
  
  protected Martlocation(Parcel paramParcel)
  {
    this.lat = paramParcel.readDouble();
    this.lon = paramParcel.readDouble();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeDouble(this.lat);
    paramParcel.writeDouble(this.lon);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/Martlocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */