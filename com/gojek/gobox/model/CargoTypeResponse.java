package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class CargoTypeResponse
{
  @SerializedName("boxes")
  private ArrayList<CargoType> mCargoTypes;
  
  public ArrayList<CargoType> getmCargoTypes()
  {
    return this.mCargoTypes;
  }
  
  public void setmCargoTypes(ArrayList<CargoType> paramArrayList)
  {
    this.mCargoTypes = paramArrayList;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/CargoTypeResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */