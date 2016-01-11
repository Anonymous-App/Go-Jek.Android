package com.kartuku.digitalwallet.common.dto.controller;

public class EncryptedRequestDto
{
  private String ˊ;
  private String ˋ;
  private String ˎ;
  
  public EncryptedRequestDto() {}
  
  public EncryptedRequestDto(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public String getAmount()
  {
    return this.ˎ;
  }
  
  public String getEncryptedText()
  {
    return this.ˊ;
  }
  
  public String getMerchantUserId()
  {
    return this.ˋ;
  }
  
  public void setAmount(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setEncryptedText(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setMerchantUserId(String paramString)
  {
    this.ˋ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/controller/EncryptedRequestDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */