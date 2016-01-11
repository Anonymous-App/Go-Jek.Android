package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class ConfirmationRemainingTimeResponse
{
  @SerializedName("expired_time")
  private long expiredTime;
  
  public ConfirmationRemainingTimeResponse(long paramLong)
  {
    this.expiredTime = paramLong;
  }
  
  public long getExpiredTime()
  {
    return this.expiredTime;
  }
  
  public void setExpiredTime(long paramLong)
  {
    this.expiredTime = paramLong;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/ConfirmationRemainingTimeResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */