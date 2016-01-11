package com.gojek.app.persistence.domain;

import io.realm.RealmObject;
import io.realm.annotations.Index;

public class BookingRate
  extends RealmObject
{
  @Index
  private String bookingJson;
  private int customerId;
  private String driverId;
  private String driverName;
  private String feedback;
  private int id;
  @Index
  private String orderNo;
  private int rate;
  private int serviceType;
  private int statusBooking;
  
  public String getBookingJson()
  {
    return this.bookingJson;
  }
  
  public int getCustomerId()
  {
    return this.customerId;
  }
  
  public String getDriverId()
  {
    return this.driverId;
  }
  
  public String getDriverName()
  {
    return this.driverName;
  }
  
  public String getFeedback()
  {
    return this.feedback;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getOrderNo()
  {
    return this.orderNo;
  }
  
  public int getRate()
  {
    return this.rate;
  }
  
  public int getServiceType()
  {
    return this.serviceType;
  }
  
  public int getStatusBooking()
  {
    return this.statusBooking;
  }
  
  public void setBookingJson(String paramString)
  {
    this.bookingJson = paramString;
  }
  
  public void setCustomerId(int paramInt)
  {
    this.customerId = paramInt;
  }
  
  public void setDriverId(String paramString)
  {
    this.driverId = paramString;
  }
  
  public void setDriverName(String paramString)
  {
    this.driverName = paramString;
  }
  
  public void setFeedback(String paramString)
  {
    this.feedback = paramString;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setOrderNo(String paramString)
  {
    this.orderNo = paramString;
  }
  
  public void setRate(int paramInt)
  {
    this.rate = paramInt;
  }
  
  public void setServiceType(int paramInt)
  {
    this.serviceType = paramInt;
  }
  
  public void setStatusBooking(int paramInt)
  {
    this.statusBooking = paramInt;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/persistence/domain/BookingRate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */