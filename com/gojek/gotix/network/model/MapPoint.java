package com.gojek.gotix.network.model;

public class MapPoint
{
  private String address;
  private String contact_name;
  private String contact_phone;
  private Double latitude;
  private Double longitude;
  private String name;
  private String note;
  
  public MapPoint(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, Double paramDouble1, Double paramDouble2)
  {
    this.name = paramString1;
    this.note = paramString2;
    this.address = paramString3;
    this.contact_name = paramString4;
    this.contact_phone = paramString5;
    this.latitude = paramDouble1;
    this.longitude = paramDouble2;
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public String getContact_name()
  {
    return this.contact_name;
  }
  
  public String getContact_phone()
  {
    return this.contact_phone;
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
  
  public String getNote()
  {
    return this.note;
  }
  
  public void setAddress(String paramString)
  {
    this.address = paramString;
  }
  
  public void setContact_name(String paramString)
  {
    this.contact_name = paramString;
  }
  
  public void setContact_phone(String paramString)
  {
    this.contact_phone = paramString;
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
  
  public void setNote(String paramString)
  {
    this.note = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/MapPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */