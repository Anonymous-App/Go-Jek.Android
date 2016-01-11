package com.gojek.gobox.model;

public class EstimatedPrice
{
  private EstimationDetail[] detail;
  private double total;
  
  public EstimationDetail[] getDetail()
  {
    return this.detail;
  }
  
  public double getTotal()
  {
    return this.total;
  }
  
  public void setDetail(EstimationDetail[] paramArrayOfEstimationDetail)
  {
    this.detail = paramArrayOfEstimationDetail;
  }
  
  public void setTotal(double paramDouble)
  {
    this.total = paramDouble;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/EstimatedPrice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */