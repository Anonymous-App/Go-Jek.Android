package com.kartuku.digitalwallet.common.dto.iam;

public class ChangePasswordForm
{
  private UserDto ˊ;
  private String ˋ;
  private String ˎ;
  
  public ChangePasswordForm(UserDto paramUserDto, String paramString1, String paramString2)
  {
    this.ˊ = paramUserDto;
    this.ˋ = paramString1;
    this.ˎ = paramString2;
  }
  
  public String getNewPassword()
  {
    return this.ˎ;
  }
  
  public String getOldPassword()
  {
    return this.ˋ;
  }
  
  public UserDto getUser()
  {
    return this.ˊ;
  }
  
  public void setNewPassword(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setOldPassword(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setUser(UserDto paramUserDto)
  {
    this.ˊ = paramUserDto;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/iam/ChangePasswordForm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */