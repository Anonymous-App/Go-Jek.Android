package com.kartuku.digitalwallet.common.dto.cardlink;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kartuku.digitalwallet.common.dto.controller.ResponseStatusDto;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GenerateCardResponseDto
  extends ResponseStatusDto
{
  private String ˊ;
  private String ˋ;
  
  public GenerateCardResponseDto() {}
  
  public GenerateCardResponseDto(String paramString)
  {
    super(paramString);
  }
  
  public String getPrintNumber()
  {
    return this.ˋ;
  }
  
  public String getsCode()
  {
    return this.ˊ;
  }
  
  public void setPrintNumber(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setsCode(String paramString)
  {
    this.ˊ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/cardlink/GenerateCardResponseDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */