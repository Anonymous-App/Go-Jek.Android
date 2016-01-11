package com.gojek.gotix.network.model;

import com.google.gson.annotations.SerializedName;

public class EventDistance
{
  private long distance;
  @SerializedName("is_within_delivery_area")
  private boolean isWithinDeliveryArea;
  
  public EventDistance(long paramLong)
  {
    this.distance = paramLong;
  }
  
  public long getDistance()
  {
    return this.distance;
  }
  
  public boolean isWithinDeliveryArea()
  {
    return this.isWithinDeliveryArea;
  }
  
  public void setDistance(long paramLong)
  {
    this.distance = paramLong;
  }
  
  public void setIsWithinDeliveryArea(boolean paramBoolean)
  {
    this.isWithinDeliveryArea = paramBoolean;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/EventDistance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */