package com.nostratech.gojek.driver.common;

import java.io.Serializable;

public class EncryptedRequestDto
  implements Serializable
{
  private String ˊ;
  private String ˋ;
  private String ˎ;
  
  public EncryptedRequestDto() {}
  
  public EncryptedRequestDto(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public String getEncryptedText()
  {
    return this.ˊ;
  }
  
  public String getMerchantUserId()
  {
    return this.ˎ;
  }
  
  public String getUrl()
  {
    return this.ˋ;
  }
  
  public void setEncryptedText(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setMerchantUserId(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setUrl(String paramString)
  {
    this.ˋ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/nostratech/gojek/driver/common/EncryptedRequestDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */