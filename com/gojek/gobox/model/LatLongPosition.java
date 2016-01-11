package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class LatLongPosition
{
  private double lat;
  @SerializedName("lng")
  private double lngitude;
  @SerializedName("long")
  private double longitude;
  
  public LatLongPosition(double paramDouble1, double paramDouble2)
  {
    this.lat = paramDouble1;
    this.longitude = paramDouble2;
  }
  
  public double getLat()
  {
    return this.lat;
  }
  
  public double getLongitude()
  {
    if (this.longitude == 0.0D) {
      return this.lngitude;
    }
    return this.longitude;
  }
  
  public void setLat(double paramDouble)
  {
    this.lat = paramDouble;
  }
  
  public void setLongitude(double paramDouble)
  {
    this.longitude = paramDouble;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/LatLongPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */