package com.kartuku.digitalwallet.common.entity;

import com.kartuku.digitalwallet.common.dto.iam.UserBaseDto;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class User
  extends UserBaseDto
  implements Serializable
{
  private Date ʻ;
  private Date ʼ;
  private Long ʽ = Long.valueOf(1L);
  private List<DeviceImprint> ʾ;
  private AccessToken ˊ;
  private String ˋ;
  private String ˎ;
  private String ˏ;
  private Timestamp ͺ;
  private String ᐝ;
  private UserConfiguration ι;
  
  public User() {}
  
  public User(String paramString1, String paramString2)
  {
    setUid(paramString1);
    setEmail(paramString2);
  }
  
  public void addDeviceImprint(DeviceImprint paramDeviceImprint)
  {
    this.ʾ.add(paramDeviceImprint);
    if (paramDeviceImprint.getUser() != this) {
      paramDeviceImprint.setUser(this);
    }
  }
  
  public Date getAddedDate()
  {
    return this.ʼ;
  }
  
  public String getCardLinkId()
  {
    return this.ˋ;
  }
  
  public Timestamp getDateUpdate()
  {
    return this.ͺ;
  }
  
  public List<DeviceImprint> getDeviceImprint()
  {
    return this.ʾ;
  }
  
  public String getJsessionId()
  {
    return this.ᐝ;
  }
  
  public String getLastIp()
  {
    return this.ˏ;
  }
  
  public Date getLastLogin()
  {
    return this.ʻ;
  }
  
  public AccessToken getMerchantOwner()
  {
    return this.ˊ;
  }
  
  public String getPhoneNumber()
  {
    return this.ˎ;
  }
  
  public Long getStatus()
  {
    return this.ʽ;
  }
  
  public String getUid()
  {
    return this.uid;
  }
  
  public UserConfiguration getUserConfiguration()
  {
    return this.ι;
  }
  
  public void setAddedDate(Date paramDate)
  {
    this.ʼ = paramDate;
  }
  
  public void setCardLinkId(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setDateUpdate(Timestamp paramTimestamp)
  {
    this.ͺ = paramTimestamp;
  }
  
  public void setDeviceImprint(List<DeviceImprint> paramList)
  {
    this.ʾ = paramList;
  }
  
  public void setJsessionId(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setLastIp(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setLastLogin(Date paramDate)
  {
    this.ʻ = paramDate;
  }
  
  public void setMerchantOwner(AccessToken paramAccessToken)
  {
    this.ˊ = paramAccessToken;
  }
  
  public void setPhoneNumber(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setStatus(Long paramLong)
  {
    this.ʽ = paramLong;
  }
  
  public void setUid(String paramString)
  {
    this.uid = paramString;
  }
  
  public void setUserConfiguration(UserConfiguration paramUserConfiguration)
  {
    this.ι = paramUserConfiguration;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/entity/User.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */