package com.kartuku.digitalwallet.common.dto.controller;

import com.kartuku.digitalwallet.common.entity.User;
import com.kartuku.digitalwallet.common.entity.UserConfiguration;

public class UserConfigurationDto
{
  private String ˊ;
  private String ˋ;
  private String ˎ;
  
  public String getEnablePin()
  {
    return this.ˋ;
  }
  
  public String getPrimaryCC()
  {
    return this.ˎ;
  }
  
  public String getUid()
  {
    return this.ˊ;
  }
  
  public void setEnablePin(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setEntity(UserConfiguration paramUserConfiguration, User paramUser)
  {
    this.ˊ = paramUser.getUid();
    setEnablePin("");
    this.ˎ = "1234123412";
  }
  
  public void setPrimaryCC(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setUid(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public final UserConfiguration toEntity(User paramUser)
  {
    UserConfiguration localUserConfiguration = new UserConfiguration();
    localUserConfiguration.setPrimaryCC(this.ˎ);
    localUserConfiguration.setEnablePin(this.ˋ);
    localUserConfiguration.setUser(paramUser);
    return localUserConfiguration;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/controller/UserConfigurationDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */