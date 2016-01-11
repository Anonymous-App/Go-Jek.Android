package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class BookingRequestBody
{
  @SerializedName("consumer_name")
  String consumerName;
  @SerializedName("consumer_phone")
  String consumerPhone;
  ArrayList<Location> destinations;
  BookingItem item;
  Order order;
  Location origin;
  Payment payment;
  
  public BookingRequestBody() {}
  
  public BookingRequestBody(Order paramOrder, Location paramLocation, BookingItem paramBookingItem, ArrayList<Location> paramArrayList, Payment paramPayment)
  {
    this.order = paramOrder;
    this.origin = paramLocation;
    this.item = paramBookingItem;
    this.destinations = paramArrayList;
    this.payment = paramPayment;
  }
  
  public String getConsumerName()
  {
    return this.consumerName;
  }
  
  public String getConsumerPhone()
  {
    return this.consumerPhone;
  }
  
  public ArrayList<Location> getDestinations()
  {
    return this.destinations;
  }
  
  public BookingItem getItem()
  {
    return this.item;
  }
  
  public Order getOrder()
  {
    return this.order;
  }
  
  public Location getOrigin()
  {
    return this.origin;
  }
  
  public Payment getPayment()
  {
    return this.payment;
  }
  
  public void setConsumerName(String paramString)
  {
    this.consumerName = paramString;
  }
  
  public void setConsumerPhone(String paramString)
  {
    this.consumerPhone = paramString;
  }
  
  public void setDestinations(ArrayList<Location> paramArrayList)
  {
    this.destinations = paramArrayList;
  }
  
  public void setItem(BookingItem paramBookingItem)
  {
    this.item = paramBookingItem;
  }
  
  public void setOrder(Order paramOrder)
  {
    this.order = paramOrder;
  }
  
  public void setOrigin(Location paramLocation)
  {
    this.origin = paramLocation;
  }
  
  public void setPayment(Payment paramPayment)
  {
    this.payment = paramPayment;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/BookingRequestBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */