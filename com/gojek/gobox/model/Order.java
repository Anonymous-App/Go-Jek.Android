package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class Order
{
  @SerializedName("num_of_shippers")
  int numberOfShipper;
  @SerializedName("pick_up_later")
  boolean pickUpLater;
  @SerializedName("pick_up_now")
  boolean pickUpNow;
  long time;
  @SerializedName("total_price")
  double totalPrice;
  
  public Order() {}
  
  public Order(boolean paramBoolean1, boolean paramBoolean2, long paramLong, double paramDouble, int paramInt)
  {
    this.pickUpNow = paramBoolean1;
    this.pickUpLater = paramBoolean2;
    this.time = paramLong;
    this.totalPrice = paramDouble;
    this.numberOfShipper = paramInt;
  }
  
  public int getNumberOfShipper()
  {
    return this.numberOfShipper;
  }
  
  public long getTime()
  {
    return this.time;
  }
  
  public double getTotalPrice()
  {
    return this.totalPrice;
  }
  
  public boolean isPickUpLater()
  {
    return this.pickUpLater;
  }
  
  public boolean isPickUpNow()
  {
    return this.pickUpNow;
  }
  
  public void setNumberOfShipper(int paramInt)
  {
    this.numberOfShipper = paramInt;
  }
  
  public void setPickUpLater(boolean paramBoolean)
  {
    this.pickUpLater = paramBoolean;
  }
  
  public void setPickUpNow(boolean paramBoolean)
  {
    this.pickUpNow = paramBoolean;
  }
  
  public void setTime(long paramLong)
  {
    this.time = paramLong;
  }
  
  public void setTotalPrice(double paramDouble)
  {
    this.totalPrice = paramDouble;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/Order.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */