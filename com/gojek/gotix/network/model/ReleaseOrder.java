package com.gojek.gotix.network.model;

public class ReleaseOrder
{
  private String status;
  
  public ReleaseOrder(String paramString)
  {
    this.status = paramString;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String paramString)
  {
    this.status = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/ReleaseOrder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */