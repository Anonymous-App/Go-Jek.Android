package com.kartuku.digitalwallet.common.dto.controller;

public class CardRequestDto
  extends Card
{
  private String ˊ;
  
  public CardRequestDto() {}
  
  public CardRequestDto(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    super(paramString1, paramString2, paramString3, paramString4);
    this.ˊ = paramString5;
  }
  
  public CardRequestDto(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    super(paramString1, paramString2, paramString3, paramString4, paramString5);
    this.ˊ = paramString6;
  }
  
  public String getCvv()
  {
    return this.ˊ;
  }
  
  public void setCvv(String paramString)
  {
    this.ˊ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/controller/CardRequestDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */