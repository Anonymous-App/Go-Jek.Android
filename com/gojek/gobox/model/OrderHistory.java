package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class OrderHistory
{
  private int id;
  private String origin;
  @SerializedName("service_type_id")
  private int serviceType;
  @SerializedName("estimated_start_time")
  private long startTime;
  private int status;
  
  public OrderHistory(int paramInt1, long paramLong, String paramString, int paramInt2, int paramInt3)
  {
    this.id = paramInt1;
    this.startTime = paramLong;
    this.origin = paramString;
    this.status = paramInt2;
    this.serviceType = paramInt3;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getOrigin()
  {
    return this.origin;
  }
  
  public int getServiceType()
  {
    return this.serviceType;
  }
  
  public long getStartTime()
  {
    return this.startTime;
  }
  
  public int getStatus()
  {
    return this.status;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setOrigin(String paramString)
  {
    this.origin = paramString;
  }
  
  public void setServiceType(int paramInt)
  {
    this.serviceType = paramInt;
  }
  
  public void setStartTime(long paramLong)
  {
    this.startTime = paramLong;
  }
  
  public void setStatus(int paramInt)
  {
    this.status = paramInt;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/OrderHistory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */