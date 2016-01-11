package com.gojek.app.gobusway.model;

import com.google.gson.annotations.SerializedName;

public class BusWay
{
  @SerializedName("buscode")
  private String busCode;
  @SerializedName("course")
  private double course;
  @SerializedName("gpsdatetime")
  private String gpsDateTime;
  @SerializedName("koridor")
  private String koridor;
  @SerializedName("latitude")
  private double latitude;
  @SerializedName("longitude")
  private double longitude;
  @SerializedName("speed")
  private double speed;
  
  public BusWay(String paramString1, String paramString2, String paramString3, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    this.busCode = paramString1;
    this.gpsDateTime = paramString2;
    this.koridor = paramString3;
    this.longitude = paramDouble1;
    this.latitude = paramDouble2;
    this.speed = paramDouble3;
    this.course = paramDouble4;
  }
  
  public String getBusCode()
  {
    return this.busCode;
  }
  
  public double getCourse()
  {
    return this.course;
  }
  
  public String getGpsDateTime()
  {
    return this.gpsDateTime;
  }
  
  public String getKoridor()
  {
    return this.koridor;
  }
  
  public double getLatitude()
  {
    return this.latitude;
  }
  
  public double getLongitude()
  {
    return this.longitude;
  }
  
  public double getSpeed()
  {
    return this.speed;
  }
  
  public void setBusCode(String paramString)
  {
    this.busCode = paramString;
  }
  
  public void setCourse(double paramDouble)
  {
    this.course = paramDouble;
  }
  
  public void setGpsDateTime(String paramString)
  {
    this.gpsDateTime = paramString;
  }
  
  public void setKoridor(String paramString)
  {
    this.koridor = paramString;
  }
  
  public void setLatitude(double paramDouble)
  {
    this.latitude = paramDouble;
  }
  
  public void setLongitude(double paramDouble)
  {
    this.longitude = paramDouble;
  }
  
  public void setSpeed(double paramDouble)
  {
    this.speed = paramDouble;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/model/BusWay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */