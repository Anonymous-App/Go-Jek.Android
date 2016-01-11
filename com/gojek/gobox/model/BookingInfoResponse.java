package com.gojek.gobox.model;

public class BookingInfoResponse
{
  private Location[] destinations;
  private Driver driver;
  private BookingItem item;
  private Order order;
  private Location origin;
  private Payment payment;
  private Vehicle vehicle;
  
  public BookingInfoResponse(BookingItem paramBookingItem, Payment paramPayment, Driver paramDriver, Vehicle paramVehicle, Order paramOrder, Location paramLocation, Location[] paramArrayOfLocation)
  {
    this.item = paramBookingItem;
    this.payment = paramPayment;
    this.driver = paramDriver;
    this.vehicle = paramVehicle;
    this.order = paramOrder;
    this.origin = paramLocation;
    this.destinations = paramArrayOfLocation;
  }
  
  public Location[] getDestinations()
  {
    return this.destinations;
  }
  
  public Driver getDriver()
  {
    return this.driver;
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
  
  public Vehicle getVehicle()
  {
    return this.vehicle;
  }
  
  public void setDestinations(Location[] paramArrayOfLocation)
  {
    this.destinations = paramArrayOfLocation;
  }
  
  public void setDriver(Driver paramDriver)
  {
    this.driver = paramDriver;
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
  
  public void setVehicle(Vehicle paramVehicle)
  {
    this.vehicle = paramVehicle;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/BookingInfoResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */