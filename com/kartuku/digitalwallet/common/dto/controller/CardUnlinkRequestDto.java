package com.kartuku.digitalwallet.common.dto.controller;

public class CardUnlinkRequestDto
{
  private String ˊ;
  private String ˋ;
  private String ˎ;
  private String ˏ;
  
  public CardUnlinkRequestDto() {}
  
  public CardUnlinkRequestDto(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.ˊ = paramString1;
    this.ˋ = paramString2;
    this.ˎ = paramString3;
    this.ˏ = paramString4;
  }
  
  public String getCompanyCode()
  {
    return this.ˎ;
  }
  
  public String getPrintedNumber()
  {
    return this.ˊ;
  }
  
  public String getTokenNumber()
  {
    return this.ˋ;
  }
  
  public String getType()
  {
    return this.ˏ;
  }
  
  public void setCompanyCode(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setPrintedNumber(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setTokenNumber(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setType(String paramString)
  {
    this.ˏ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/controller/CardUnlinkRequestDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */