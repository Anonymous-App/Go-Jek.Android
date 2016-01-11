package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class TrackingStep
{
  @SerializedName("end_location")
  private LatLongPosition endLocation;
  private TrackingPolyline polyline;
  @SerializedName("start_location")
  private LatLongPosition startLocation;
  
  public LatLongPosition getEndLocation()
  {
    return this.endLocation;
  }
  
  public TrackingPolyline getPolyline()
  {
    return this.polyline;
  }
  
  public LatLongPosition getStartLocation()
  {
    return this.startLocation;
  }
  
  public void setEndLocation(LatLongPosition paramLatLongPosition)
  {
    this.endLocation = paramLatLongPosition;
  }
  
  public void setPolyline(TrackingPolyline paramTrackingPolyline)
  {
    this.polyline = paramTrackingPolyline;
  }
  
  public void setStartLocation(LatLongPosition paramLatLongPosition)
  {
    this.startLocation = paramLatLongPosition;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/TrackingStep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */