package com.kartuku.digitalwallet.common.dto.iam;

public class UserBaseDto
  extends IdentityDto
{
  protected String email;
  protected String fullname;
  protected String pin;
  public String uid;
  
  public UserBaseDto() {}
  
  public UserBaseDto(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public String getFullname()
  {
    return this.fullname;
  }
  
  public String getPin()
  {
    return this.pin;
  }
  
  public String getUid()
  {
    return this.uid;
  }
  
  public void setEmail(String paramString)
  {
    this.email = paramString;
  }
  
  public void setFullname(String paramString)
  {
    this.fullname = paramString;
  }
  
  public void setPin(String paramString)
  {
    this.pin = paramString;
  }
  
  public void setUid(String paramString)
  {
    this.uid = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/iam/UserBaseDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */