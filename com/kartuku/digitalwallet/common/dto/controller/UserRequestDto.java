package com.kartuku.digitalwallet.common.dto.controller;

import com.kartuku.digitalwallet.common.dto.iam.UserBaseDto;

public class UserRequestDto
  extends UserBaseDto
{
  private String ʻ;
  private String ʼ;
  private String ˊ;
  private String ˋ;
  private Long ˎ;
  private String ˏ;
  private String ᐝ;
  
  public UserRequestDto() {}
  
  public UserRequestDto(String paramString1, String paramString2, String paramString3)
  {
    setUid(paramString1);
    setEmail(paramString2);
    setPassword(paramString3);
  }
  
  public String getCardLinkId()
  {
    return this.ˊ;
  }
  
  public String getCardType()
  {
    return this.ʼ;
  }
  
  public String getCompanyCode()
  {
    return this.ʻ;
  }
  
  public String getNewPassword()
  {
    return this.ᐝ;
  }
  
  public String getPassword()
  {
    return this.ˏ;
  }
  
  public String getPhoneNumber()
  {
    return this.ˋ;
  }
  
  public Long getStatus()
  {
    return this.ˎ;
  }
  
  public void setCardLinkId(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setCardType(String paramString)
  {
    this.ʼ = paramString;
  }
  
  public void setCompanyCode(String paramString)
  {
    this.ʻ = paramString;
  }
  
  public void setNewPassword(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setPassword(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setPhoneNumber(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setStatus(Long paramLong)
  {
    this.ˎ = paramLong;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/controller/UserRequestDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */