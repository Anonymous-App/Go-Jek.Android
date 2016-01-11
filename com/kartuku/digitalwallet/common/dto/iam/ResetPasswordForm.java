package com.kartuku.digitalwallet.common.dto.iam;

public class ResetPasswordForm
{
  private UserDto ˊ;
  private String ˋ;
  
  public ResetPasswordForm() {}
  
  public ResetPasswordForm(UserDto paramUserDto, String paramString)
  {
    this.ˊ = paramUserDto;
    this.ˋ = paramString;
  }
  
  public String getNewPassword()
  {
    return this.ˋ;
  }
  
  public UserDto getUser()
  {
    return this.ˊ;
  }
  
  public void setNewPassword(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setUser(UserDto paramUserDto)
  {
    this.ˊ = paramUserDto;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/iam/ResetPasswordForm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */