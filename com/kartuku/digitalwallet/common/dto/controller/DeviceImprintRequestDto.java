package com.kartuku.digitalwallet.common.dto.controller;

import com.kartuku.digitalwallet.common.entity.DeviceImprint;
import com.kartuku.digitalwallet.common.entity.User;
import java.sql.Date;

public class DeviceImprintRequestDto
{
  private String ˊ;
  private String ˋ;
  private Date ˎ;
  private String ˏ;
  private Long ᐝ;
  
  public Date getExpiredDate()
  {
    return this.ˎ;
  }
  
  public String getImei()
  {
    return this.ˊ;
  }
  
  public Long getStatus()
  {
    return this.ᐝ;
  }
  
  public String getTokenAccess()
  {
    return this.ˋ;
  }
  
  public String getUid()
  {
    return this.ˏ;
  }
  
  public void setExpiredDate(Date paramDate)
  {
    this.ˎ = paramDate;
  }
  
  public void setImei(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setStatus(Long paramLong)
  {
    this.ᐝ = paramLong;
  }
  
  public void setTokenAccess(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setUid(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public final DeviceImprint toEntity(User paramUser)
  {
    DeviceImprint localDeviceImprint = new DeviceImprint();
    localDeviceImprint.setUser(paramUser);
    localDeviceImprint.setImei(this.ˊ);
    localDeviceImprint.setExpiredDate(this.ˎ);
    localDeviceImprint.setTokenAccess(this.ˋ);
    return localDeviceImprint;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/controller/DeviceImprintRequestDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */