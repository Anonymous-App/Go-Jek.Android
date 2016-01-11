package com.gojek.gotix.network.model;

import com.google.gson.annotations.SerializedName;

public class DeliveryBooking
{
  @SerializedName("booking_reference")
  private String bookingReference;
  private MapPoint destination;
  @SerializedName("device_token")
  private String deviceToken;
  private String item;
  @SerializedName("user_id")
  private int userId;
  
  public DeliveryBooking(int paramInt, String paramString1, String paramString2, String paramString3, MapPoint paramMapPoint)
  {
    this.userId = paramInt;
    this.deviceToken = paramString1;
    this.bookingReference = paramString2;
    this.item = paramString3;
    this.destination = paramMapPoint;
  }
  
  public String getBookingReference()
  {
    return this.bookingReference;
  }
  
  public MapPoint getDestination()
  {
    return this.destination;
  }
  
  public String getDeviceToken()
  {
    return this.deviceToken;
  }
  
  public String getItem()
  {
    return this.item;
  }
  
  public int getUserId()
  {
    return this.userId;
  }
  
  public void setBookingReference(String paramString)
  {
    this.bookingReference = paramString;
  }
  
  public void setDestination(MapPoint paramMapPoint)
  {
    this.destination = paramMapPoint;
  }
  
  public void setDeviceToken(String paramString)
  {
    this.deviceToken = paramString;
  }
  
  public void setItem(String paramString)
  {
    this.item = paramString;
  }
  
  public void setUserId(int paramInt)
  {
    this.userId = paramInt;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/DeliveryBooking.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */