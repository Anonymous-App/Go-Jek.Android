package com.kartuku.digitalwallet.common.dto.cardlink;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GenerateCardRequestDto
{
  private String ˊ;
  private String ˋ;
  private String ˎ;
  private String ˏ;
  private boolean ᐝ;
  
  public GenerateCardRequestDto() {}
  
  public GenerateCardRequestDto(String paramString1, String paramString2, String paramString3)
  {
    this.ˊ = paramString1;
    this.ˋ = paramString2;
    this.ˏ = paramString3;
  }
  
  public String getCardType()
  {
    return this.ˏ;
  }
  
  public String getName()
  {
    return this.ˊ;
  }
  
  public String getPrintNumber()
  {
    return this.ˋ;
  }
  
  public String getPrintToken()
  {
    return this.ˎ;
  }
  
  public boolean isActive()
  {
    return this.ᐝ;
  }
  
  public void setActive(boolean paramBoolean)
  {
    this.ᐝ = paramBoolean;
  }
  
  public void setCardType(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setName(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setPrintNumber(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setPrintToken(String paramString)
  {
    this.ˎ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/cardlink/GenerateCardRequestDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */