package com.kartuku.digitalwallet.common.dto.iam;

import java.io.Serializable;

public class RegistrationForm
  implements Serializable
{
  private String ˊ;
  private String ˋ;
  private String ˎ;
  private boolean ˏ;
  
  public RegistrationForm() {}
  
  public RegistrationForm(String paramString1, String paramString2, String paramString3)
  {
    setName(paramString1);
    setPassword(paramString2);
    setRePassword(paramString3);
  }
  
  public String getName()
  {
    return this.ˊ;
  }
  
  public String getPassword()
  {
    return this.ˋ;
  }
  
  public String getRePassword()
  {
    return this.ˎ;
  }
  
  public boolean isValid()
  {
    return this.ˏ;
  }
  
  public void setName(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setPassword(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setRePassword(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setValid(boolean paramBoolean)
  {
    this.ˏ = paramBoolean;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/iam/RegistrationForm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */