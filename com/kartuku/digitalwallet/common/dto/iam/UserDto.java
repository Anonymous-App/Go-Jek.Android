package com.kartuku.digitalwallet.common.dto.iam;

import java.io.Serializable;
import java.util.Date;

public class UserDto
  implements Serializable
{
  private Date ʻ;
  private Long ʼ;
  private Long ˊ;
  private String ˋ;
  private String ˎ;
  private Date ˏ;
  private String ᐝ;
  
  public Date getAddedDate()
  {
    return this.ʻ;
  }
  
  public Long getId()
  {
    return this.ˊ;
  }
  
  public String getLastIp()
  {
    return this.ˎ;
  }
  
  public Date getLastLogin()
  {
    return this.ˏ;
  }
  
  public String getName()
  {
    return this.ˋ;
  }
  
  public String getPassword()
  {
    return this.ᐝ;
  }
  
  public Long getStatus()
  {
    return this.ʼ;
  }
  
  public void setAddedDate(Date paramDate)
  {
    this.ʻ = paramDate;
  }
  
  public void setId(Long paramLong)
  {
    this.ˊ = paramLong;
  }
  
  public void setLastIp(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setLastLogin(Date paramDate)
  {
    this.ˏ = paramDate;
  }
  
  public void setName(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setPassword(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setStatus(Long paramLong)
  {
    this.ʼ = paramLong;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/iam/UserDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */