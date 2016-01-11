package com.gojek.gotix.network.model;

public class Driver
{
  private String eta;
  private Double latitude;
  private Double longitude;
  private String name;
  private String phone;
  private String photo;
  private String type;
  
  public Driver(String paramString1, String paramString2, Double paramDouble1, Double paramDouble2, String paramString3, String paramString4, String paramString5)
  {
    this.name = paramString1;
    this.type = paramString2;
    this.latitude = paramDouble1;
    this.longitude = paramDouble2;
    this.phone = paramString3;
    this.eta = paramString4;
    this.photo = paramString5;
  }
  
  public String getEta()
  {
    return this.eta;
  }
  
  public Double getLatitude()
  {
    return this.latitude;
  }
  
  public Double getLongitude()
  {
    return this.longitude;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getPhone()
  {
    return this.phone;
  }
  
  public String getPhoto()
  {
    return this.photo;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setEta(String paramString)
  {
    this.eta = paramString;
  }
  
  public void setLatitude(Double paramDouble)
  {
    this.latitude = paramDouble;
  }
  
  public void setLongitude(Double paramDouble)
  {
    this.longitude = paramDouble;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setPhone(String paramString)
  {
    this.phone = paramString;
  }
  
  public void setPhoto(String paramString)
  {
    this.photo = paramString;
  }
  
  public void setType(String paramString)
  {
    this.type = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/Driver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */