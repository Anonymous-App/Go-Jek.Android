package com.kartuku.digitalwallet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kartuku.digitalwallet.common.dto.prepaid.PrepaidCardBalanceDto;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PrepaidCardTopUpDto
  extends PrepaidCardBalanceDto
{
  private Double ˊ;
  
  public PrepaidCardTopUpDto() {}
  
  public PrepaidCardTopUpDto(String paramString1, String paramString2, String paramString3)
  {
    setCode(paramString2);
    setPrintNumber(paramString1);
    setDetail(paramString3);
  }
  
  public Double getAmount()
  {
    return this.ˊ;
  }
  
  public void setAmount(Double paramDouble)
  {
    this.ˊ = paramDouble;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/dto/PrepaidCardTopUpDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */