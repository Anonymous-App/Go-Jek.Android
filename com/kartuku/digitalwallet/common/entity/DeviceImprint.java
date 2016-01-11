package com.kartuku.digitalwallet.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class DeviceImprint
  implements Serializable
{
  private Long ʻ;
  private Timestamp ʼ;
  private Long ˊ;
  private String ˋ;
  private String ˎ;
  private Date ˏ;
  private User ᐝ;
  
  public Timestamp getDateUpdate()
  {
    return this.ʼ;
  }
  
  public Date getExpiredDate()
  {
    return this.ˏ;
  }
  
  public Long getId()
  {
    return this.ˊ;
  }
  
  public String getImei()
  {
    return this.ˋ;
  }
  
  public Long getStatus()
  {
    return this.ʻ;
  }
  
  public String getTokenAccess()
  {
    return this.ˎ;
  }
  
  public User getUser()
  {
    return this.ᐝ;
  }
  
  public void setDateUpdate(Timestamp paramTimestamp)
  {
    this.ʼ = paramTimestamp;
  }
  
  public void setExpiredDate(Date paramDate)
  {
    this.ˏ = paramDate;
  }
  
  public void setId(Long paramLong)
  {
    this.ˊ = paramLong;
  }
  
  public void setImei(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setStatus(Long paramLong)
  {
    this.ʻ = paramLong;
  }
  
  public void setTokenAccess(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setUser(User paramUser)
  {
    this.ᐝ = paramUser;
    if (!paramUser.getDeviceImprint().contains(this)) {
      paramUser.getDeviceImprint().add(this);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/entity/DeviceImprint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */