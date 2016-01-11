package com.gojek.app.gobusway.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class BusWayResponse
{
  @SerializedName("bus")
  private ArrayList<BusWay> mBusWays;
  
  public ArrayList<BusWay> getBusWays()
  {
    return this.mBusWays;
  }
  
  public void setBusWays(ArrayList<BusWay> paramArrayList)
  {
    this.mBusWays = paramArrayList;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/model/BusWayResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */