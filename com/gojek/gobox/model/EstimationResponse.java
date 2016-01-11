package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class EstimationResponse
{
  @SerializedName("box_id")
  private long boxId;
  @SerializedName("estimated_distance")
  private double estimatedDistance;
  @SerializedName("estimated_price")
  private EstimatedPrice estimatedPrice;
  
  public long getBoxId()
  {
    return this.boxId;
  }
  
  public double getEstimatedDistance()
  {
    return this.estimatedDistance;
  }
  
  public EstimatedPrice getEstimatedPrice()
  {
    return this.estimatedPrice;
  }
  
  public void setBoxId(long paramLong)
  {
    this.boxId = paramLong;
  }
  
  public void setEstimatedDistance(double paramDouble)
  {
    this.estimatedDistance = paramDouble;
  }
  
  public void setEstimatedPrice(EstimatedPrice paramEstimatedPrice)
  {
    this.estimatedPrice = paramEstimatedPrice;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/EstimationResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */