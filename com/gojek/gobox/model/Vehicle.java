package com.gojek.gobox.model;

public class Vehicle
{
  private String name;
  private String number;
  
  public Vehicle(String paramString1, String paramString2)
  {
    this.number = paramString1;
    this.name = paramString2;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getNumber()
  {
    return this.number;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setNumber(String paramString)
  {
    this.number = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/Vehicle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */