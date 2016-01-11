package com.kartuku.digitalwallet.common.dto.prepaid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kartuku.digitalwallet.common.dto.controller.ResponseStatusDto;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PrepaidCardBalanceDto
  extends ResponseStatusDto
{
  private String ʻ;
  private String ʼ;
  private Double ʽ;
  private String ˊ;
  private String ˋ;
  private String ˎ;
  private String ˏ;
  private Double ᐝ;
  
  public PrepaidCardBalanceDto() {}
  
  public PrepaidCardBalanceDto(String paramString1, Double paramDouble, String paramString2)
  {
    super(paramString2);
    this.ˊ = paramString1;
    this.ʽ = paramDouble;
  }
  
  public PrepaidCardBalanceDto(String paramString1, Double paramDouble1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, Double paramDouble2, String paramString7)
  {
    super(paramString2, paramString3);
    this.ˋ = paramString4;
    this.ˎ = paramString5;
    this.ˏ = paramString6;
    this.ᐝ = this.ᐝ;
    this.ʼ = paramString7;
    this.ˊ = paramString1;
    this.ʽ = paramDouble1;
  }
  
  public PrepaidCardBalanceDto(String paramString1, String paramString2, Double paramDouble)
  {
    super(paramString2, "");
    this.ˊ = paramString1;
    this.ʽ = paramDouble;
  }
  
  public PrepaidCardBalanceDto(String paramString1, String paramString2, Double paramDouble, String paramString3)
  {
    super(paramString2, paramString3);
    this.ˊ = paramString1;
    this.ʽ = paramDouble;
  }
  
  public PrepaidCardBalanceDto(String paramString1, String paramString2, Double paramDouble, String paramString3, String paramString4)
  {
    super(paramString2, paramString3);
    this.ˊ = paramString1;
    this.ʽ = paramDouble;
    this.ʻ = paramString4;
  }
  
  public String getAmsRC()
  {
    return this.ʼ;
  }
  
  public String getAuthCode()
  {
    return this.ˎ;
  }
  
  public Double getBalance()
  {
    return this.ʽ;
  }
  
  public String getCustomerName()
  {
    return this.ʻ;
  }
  
  public String getExpiryDate()
  {
    return this.ˋ;
  }
  
  public Double getMaxBalance()
  {
    return this.ᐝ;
  }
  
  public String getPrintNumber()
  {
    return this.ˊ;
  }
  
  public String getReferenceNumber()
  {
    return this.ˏ;
  }
  
  public void setAmsRC(String paramString)
  {
    this.ʼ = paramString;
  }
  
  public void setAuthCode(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setBalance(Double paramDouble)
  {
    this.ʽ = paramDouble;
  }
  
  public void setCustomerName(String paramString)
  {
    this.ʻ = paramString;
  }
  
  public void setExpiryDate(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setMaxBalance(Double paramDouble)
  {
    this.ᐝ = paramDouble;
  }
  
  public void setPrintNumber(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setReferenceNumber(String paramString)
  {
    this.ˏ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/prepaid/PrepaidCardBalanceDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */