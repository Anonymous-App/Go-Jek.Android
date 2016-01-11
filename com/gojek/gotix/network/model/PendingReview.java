package com.gojek.gotix.network.model;

import com.google.gson.annotations.SerializedName;

public class PendingReview
{
  @SerializedName("booking_id")
  private int bookingId;
  private Driver driver;
  @SerializedName("order_id")
  private int orderId;
  
  public int getBookingId()
  {
    return this.bookingId;
  }
  
  public Driver getDriver()
  {
    return this.driver;
  }
  
  public int getOrderId()
  {
    return this.orderId;
  }
  
  public void setBookingId(int paramInt)
  {
    this.bookingId = paramInt;
  }
  
  public void setDriver(Driver paramDriver)
  {
    this.driver = paramDriver;
  }
  
  public void setOrderId(int paramInt)
  {
    this.orderId = paramInt;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/PendingReview.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */