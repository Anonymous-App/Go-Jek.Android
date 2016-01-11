package com.gojek.app.model;

import com.google.android.gms.maps.model.LatLng;

public class NearDriverLocation
{
  public String driverLatLong;
  public LatLng latlng;
  
  public void convertToLatLng()
  {
    String[] arrayOfString = this.driverLatLong.split(",");
    this.latlng = new LatLng(Double.parseDouble(arrayOfString[0]), Double.parseDouble(arrayOfString[1]));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/NearDriverLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */