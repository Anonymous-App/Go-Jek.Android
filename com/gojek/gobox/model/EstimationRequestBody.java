package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class EstimationRequestBody
{
  private ArrayList<Location> destinations;
  @SerializedName("num_of_shippers")
  private int numberOfShippers;
  private Location origin;
  
  public EstimationRequestBody(int paramInt, Location paramLocation, ArrayList<Location> paramArrayList)
  {
    this.numberOfShippers = paramInt;
    this.origin = paramLocation;
    this.destinations = paramArrayList;
  }
  
  public ArrayList<Location> getDestinations()
  {
    return this.destinations;
  }
  
  public int getNumberOfShippers()
  {
    return this.numberOfShippers;
  }
  
  public Location getOrigin()
  {
    return this.origin;
  }
  
  public void setDestinations(ArrayList<Location> paramArrayList)
  {
    this.destinations = paramArrayList;
  }
  
  public void setNumberOfShippers(int paramInt)
  {
    this.numberOfShippers = paramInt;
  }
  
  public void setOrigin(Location paramLocation)
  {
    this.origin = paramLocation;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/EstimationRequestBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */