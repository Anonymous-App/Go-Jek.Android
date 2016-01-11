package com.gojek.gotix.network.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class LocationMap
  implements Parcelable
{
  public static final Parcelable.Creator<LocationMap> CREATOR = new LocationMap.1();
  private String latitude;
  private String longitude;
  private String nameLoc;
  
  public LocationMap() {}
  
  private LocationMap(Parcel paramParcel)
  {
    String[] arrayOfString = new String[3];
    paramParcel.readStringArray(arrayOfString);
    this.nameLoc = arrayOfString[0];
    this.latitude = arrayOfString[1];
    this.longitude = arrayOfString[2];
  }
  
  public LocationMap(String paramString1, String paramString2, String paramString3)
  {
    this.nameLoc = paramString1;
    this.latitude = paramString2;
    this.longitude = paramString3;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getLatitude()
  {
    return this.latitude;
  }
  
  public String getLongitude()
  {
    return this.longitude;
  }
  
  public String getNameLoc()
  {
    return this.nameLoc;
  }
  
  public void setLatitude(String paramString)
  {
    this.latitude = paramString;
  }
  
  public void setLongitude(String paramString)
  {
    this.longitude = paramString;
  }
  
  public void setNameLoc(String paramString)
  {
    this.nameLoc = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeStringArray(new String[] { this.nameLoc, this.latitude, this.longitude });
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/LocationMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */