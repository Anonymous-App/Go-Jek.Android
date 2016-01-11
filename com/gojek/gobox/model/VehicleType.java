package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class VehicleType
{
  @SerializedName("box_id")
  private int box_id;
  @SerializedName("id")
  private int id;
  @SerializedName("lat")
  private double lat;
  @SerializedName("long")
  private double longi;
  @SerializedName("name")
  private String name;
  @SerializedName("number")
  private String number;
  
  public VehicleType(int paramInt1, int paramInt2, String paramString1, String paramString2, double paramDouble1, double paramDouble2)
  {
    this.id = paramInt1;
    this.box_id = paramInt2;
    this.name = paramString1;
    this.number = paramString2;
    this.lat = paramDouble1;
    this.longi = paramDouble2;
  }
  
  public int getBox_id()
  {
    return this.box_id;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public double getLat()
  {
    return this.lat;
  }
  
  public double getLongi()
  {
    return this.longi;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getNumber()
  {
    return this.number;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/VehicleType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */