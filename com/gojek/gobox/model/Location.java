package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class Location
{
  private String address;
  @SerializedName("contact_name")
  private String contactName;
  @SerializedName("contact_person")
  private String contactPerson;
  private String info;
  private String instruction;
  private Double lat;
  @SerializedName("long")
  private Double longitude;
  private int order;
  
  public Location() {}
  
  public Location(int paramInt, double paramDouble1, double paramDouble2, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.order = paramInt;
    this.lat = Double.valueOf(paramDouble1);
    this.longitude = Double.valueOf(paramDouble2);
    this.address = paramString1;
    this.info = paramString2;
    this.instruction = paramString3;
    this.contactPerson = paramString4;
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public String getContactName()
  {
    return this.contactName;
  }
  
  public String getContactPerson()
  {
    return this.contactPerson;
  }
  
  public String getInfo()
  {
    return this.info;
  }
  
  public String getInstruction()
  {
    return this.instruction;
  }
  
  public Double getLat()
  {
    return this.lat;
  }
  
  public Double getLongitude()
  {
    return this.longitude;
  }
  
  public int getOrder()
  {
    return this.order;
  }
  
  public void setAddress(String paramString)
  {
    this.address = paramString;
  }
  
  public void setContactName(String paramString)
  {
    this.contactName = paramString;
  }
  
  public void setContactPerson(String paramString)
  {
    this.contactPerson = paramString;
  }
  
  public void setInfo(String paramString)
  {
    this.info = paramString;
  }
  
  public void setInstruction(String paramString)
  {
    this.instruction = paramString;
  }
  
  public void setLatitude(double paramDouble)
  {
    this.lat = Double.valueOf(paramDouble);
  }
  
  public void setLongitude(double paramDouble)
  {
    this.longitude = Double.valueOf(paramDouble);
  }
  
  public void setOrder(int paramInt)
  {
    this.order = paramInt;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/Location.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */