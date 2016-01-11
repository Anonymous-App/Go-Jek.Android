package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class VehicleTypeResponse
{
  @SerializedName("vehicles")
  private ArrayList<VehicleType> mArrayVehicle;
  
  public VehicleTypeResponse(ArrayList<VehicleType> paramArrayList)
  {
    this.mArrayVehicle = paramArrayList;
  }
  
  public ArrayList<VehicleType> getArrayVehicle()
  {
    return this.mArrayVehicle;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/VehicleTypeResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */