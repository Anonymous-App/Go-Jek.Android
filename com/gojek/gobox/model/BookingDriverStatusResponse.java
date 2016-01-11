package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class BookingDriverStatusResponse
{
  private Location destinations;
  @SerializedName("estimation_time")
  private double estimationTime;
  @SerializedName("order_id")
  private long orderId;
  private int rating;
  private int state;
  private TrackingStep[] steps;
  @SerializedName("tracking_driver")
  private LatLongPosition trackingDriver;
  
  public BookingDriverStatusResponse(long paramLong, int paramInt1, int paramInt2, LatLongPosition paramLatLongPosition, Location paramLocation, double paramDouble, TrackingStep[] paramArrayOfTrackingStep)
  {
    this.orderId = paramLong;
    this.state = paramInt1;
    this.rating = paramInt2;
    this.trackingDriver = paramLatLongPosition;
    this.destinations = paramLocation;
    this.estimationTime = paramDouble;
    this.steps = paramArrayOfTrackingStep;
  }
  
  public Location getDestinations()
  {
    return this.destinations;
  }
  
  public double getEstimationTime()
  {
    return this.estimationTime;
  }
  
  public long getOrderId()
  {
    return this.orderId;
  }
  
  public int getRating()
  {
    return this.rating;
  }
  
  public int getState()
  {
    return this.state;
  }
  
  public TrackingStep[] getSteps()
  {
    return this.steps;
  }
  
  public LatLongPosition getTrackingDriver()
  {
    return this.trackingDriver;
  }
  
  public void setDestinations(Location paramLocation)
  {
    this.destinations = paramLocation;
  }
  
  public void setEstimationTime(double paramDouble)
  {
    this.estimationTime = paramDouble;
  }
  
  public void setOrderId(long paramLong)
  {
    this.orderId = paramLong;
  }
  
  public void setRating(int paramInt)
  {
    this.rating = paramInt;
  }
  
  public void setState(int paramInt)
  {
    this.state = paramInt;
  }
  
  public void setSteps(TrackingStep[] paramArrayOfTrackingStep)
  {
    this.steps = paramArrayOfTrackingStep;
  }
  
  public void setTrackingDriver(LatLongPosition paramLatLongPosition)
  {
    this.trackingDriver = paramLatLongPosition;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/BookingDriverStatusResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */