package com.kartuku.digitalwallet.common.entity;

import java.security.Timestamp;

public class NotificationReceiver
{
  private Long ˊ;
  private String ˋ;
  private String ˎ;
  private Timestamp ˏ;
  
  public NotificationReceiver() {}
  
  public NotificationReceiver(Long paramLong, String paramString1, String paramString2)
  {
    this.ˊ = paramLong;
    this.ˋ = paramString1;
    this.ˎ = paramString2;
  }
  
  public NotificationReceiver(String paramString1, String paramString2)
  {
    this.ˋ = paramString1;
    this.ˎ = paramString2;
  }
  
  public Timestamp getCreatedDate()
  {
    return this.ˏ;
  }
  
  public String getReceiverId()
  {
    return this.ˋ;
  }
  
  public String getReceiverType()
  {
    return this.ˎ;
  }
  
  public Long getRid()
  {
    return this.ˊ;
  }
  
  public void setCreatedDate(Timestamp paramTimestamp)
  {
    this.ˏ = paramTimestamp;
  }
  
  public void setReceiverId(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setReceiverType(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setRid(Long paramLong)
  {
    this.ˊ = paramLong;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/entity/NotificationReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */