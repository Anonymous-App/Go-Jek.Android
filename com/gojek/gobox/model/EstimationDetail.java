package com.gojek.gobox.model;

public class EstimationDetail
{
  private String name;
  private double price;
  
  public EstimationDetail(String paramString, double paramDouble)
  {
    this.name = paramString;
    this.price = paramDouble;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public double getPrice()
  {
    return this.price;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setPrice(double paramDouble)
  {
    this.price = paramDouble;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/EstimationDetail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */