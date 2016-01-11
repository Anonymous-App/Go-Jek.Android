package com.gojek.gotix.network.model;

public class Booking
{
  public static final int STATUS_BOOKING_BID = 1;
  public static final int STATUS_BOOKING_CONFIRM_BID = 2;
  public static final int STATUS_BOOKING_DONE = 4;
  public static final int STATUS_BOOKING_NO_DRIVER = 5;
  public static final int STATUS_BOOKING_PICK_UP_DONE = 7;
  public static final int STATUS_BOOKING_SEARCH_DRIVER = 6;
  private Integer booking_created_timestamp;
  private Integer booking_id;
  private String booking_reference;
  private Integer booking_status;
  private MapPoint destination;
  private Integer distance;
  private Driver driver;
  private String item;
  private Integer order_id;
  private MapPoint origin;
  private String routePolyline;
  
  public Booking(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString1, String paramString2, String paramString3, Integer paramInteger4, Driver paramDriver, MapPoint paramMapPoint1, MapPoint paramMapPoint2, Integer paramInteger5)
  {
    this.booking_id = paramInteger1;
    this.booking_created_timestamp = paramInteger2;
    this.booking_status = paramInteger3;
    this.booking_reference = paramString1;
    this.item = paramString2;
    this.routePolyline = paramString3;
    this.distance = paramInteger4;
    this.driver = paramDriver;
    this.destination = paramMapPoint1;
    this.origin = paramMapPoint2;
    this.order_id = paramInteger5;
  }
  
  public Integer getBooking_created_timestamp()
  {
    return this.booking_created_timestamp;
  }
  
  public Integer getBooking_id()
  {
    return this.booking_id;
  }
  
  public String getBooking_reference()
  {
    return this.booking_reference;
  }
  
  public Integer getBooking_status()
  {
    return this.booking_status;
  }
  
  public MapPoint getDestination()
  {
    return this.destination;
  }
  
  public Integer getDistance()
  {
    return this.distance;
  }
  
  public Driver getDriver()
  {
    return this.driver;
  }
  
  public String getItem()
  {
    return this.item;
  }
  
  public Integer getOrder_id()
  {
    return this.order_id;
  }
  
  public MapPoint getOrigin()
  {
    return this.origin;
  }
  
  public String getRoutePolyline()
  {
    return this.routePolyline;
  }
  
  public void setBooking_created_timestamp(Integer paramInteger)
  {
    this.booking_created_timestamp = paramInteger;
  }
  
  public void setBooking_id(Integer paramInteger)
  {
    this.booking_id = paramInteger;
  }
  
  public void setBooking_reference(String paramString)
  {
    this.booking_reference = paramString;
  }
  
  public void setBooking_status(Integer paramInteger)
  {
    this.booking_status = paramInteger;
  }
  
  public void setDestination(MapPoint paramMapPoint)
  {
    this.destination = paramMapPoint;
  }
  
  public void setDistance(Integer paramInteger)
  {
    this.distance = paramInteger;
  }
  
  public void setDriver(Driver paramDriver)
  {
    this.driver = paramDriver;
  }
  
  public void setItem(String paramString)
  {
    this.item = paramString;
  }
  
  public void setOrder_id(Integer paramInteger)
  {
    this.order_id = paramInteger;
  }
  
  public void setOrigin(MapPoint paramMapPoint)
  {
    this.origin = paramMapPoint;
  }
  
  public void setRoutePolyline(String paramString)
  {
    this.routePolyline = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/Booking.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */