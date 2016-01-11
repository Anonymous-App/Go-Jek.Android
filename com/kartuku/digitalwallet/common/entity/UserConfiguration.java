package com.kartuku.digitalwallet.common.entity;

import com.kartuku.digitalwallet.common.dto.controller.UserConfigurationDto;
import java.io.Serializable;
import java.sql.Timestamp;

public class UserConfiguration
  implements Serializable
{
  private Long ˊ;
  private User ˋ;
  private String ˎ;
  private String ˏ;
  private Timestamp ᐝ;
  
  public long getCid()
  {
    return this.ˊ.longValue();
  }
  
  public Timestamp getDateUpdate()
  {
    return this.ᐝ;
  }
  
  public String getEnablePin()
  {
    return this.ˎ;
  }
  
  public String getPrimaryCC()
  {
    return this.ˏ;
  }
  
  public User getUser()
  {
    return this.ˋ;
  }
  
  public void setCid(long paramLong)
  {
    this.ˊ = Long.valueOf(paramLong);
  }
  
  public void setDateUpdate(Timestamp paramTimestamp)
  {
    this.ᐝ = paramTimestamp;
  }
  
  public void setEnablePin(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setEntity(UserConfigurationDto paramUserConfigurationDto)
  {
    this.ˎ = paramUserConfigurationDto.getEnablePin();
    this.ˏ = paramUserConfigurationDto.getPrimaryCC();
  }
  
  public void setPrimaryCC(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setUser(User paramUser)
  {
    this.ˋ = paramUser;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/entity/UserConfiguration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */