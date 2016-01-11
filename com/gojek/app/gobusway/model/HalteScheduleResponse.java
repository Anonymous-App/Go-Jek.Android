package com.gojek.app.gobusway.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class HalteScheduleResponse
{
  @SerializedName("halte_koridor")
  private ArrayList<HalteSchedule> halteSchedules;
  
  public ArrayList<HalteSchedule> getHalteSchedules()
  {
    return this.halteSchedules;
  }
  
  public void setHalteSchedules(ArrayList<HalteSchedule> paramArrayList)
  {
    this.halteSchedules = paramArrayList;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/model/HalteScheduleResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */