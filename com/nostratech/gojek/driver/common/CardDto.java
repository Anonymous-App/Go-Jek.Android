package com.nostratech.gojek.driver.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CardDto
{
  private String ʻ;
  private String ʼ;
  private String ʽ;
  private String ʾ;
  private String ˊ;
  private String ˋ;
  private String ˎ;
  private String ˏ;
  private Double ͺ;
  private String ᐝ;
  private String ι;
  
  public CardDto() {}
  
  public CardDto(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.ˊ = paramString1;
    this.ˎ = paramString2;
    this.ˏ = paramString3;
    this.ʻ = paramString4;
    this.ʼ = paramString5;
  }
  
  public Double getAmount()
  {
    return this.ͺ;
  }
  
  public String getCode()
  {
    return this.ι;
  }
  
  public String getCompanyCode()
  {
    return this.ˏ;
  }
  
  public String getDetail()
  {
    return this.ʾ;
  }
  
  public String getExpireDate()
  {
    return this.ʼ;
  }
  
  public String getLabel()
  {
    return this.ʽ;
  }
  
  public String getMaskedNumber()
  {
    return this.ˊ;
  }
  
  public String getPrintedNumber()
  {
    return this.ˊ;
  }
  
  public String getPrintedTokenNumber()
  {
    return this.ˋ;
  }
  
  public String getProductCode()
  {
    return this.ᐝ;
  }
  
  public String getTokenNumber()
  {
    return this.ˎ;
  }
  
  public String getType()
  {
    return this.ʻ;
  }
  
  public void setAmount(Double paramDouble)
  {
    this.ͺ = paramDouble;
  }
  
  public void setCode(String paramString)
  {
    this.ι = paramString;
  }
  
  public void setCompanyCode(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setDetail(String paramString)
  {
    this.ʾ = paramString;
  }
  
  public void setExpireDate(String paramString)
  {
    this.ʼ = paramString;
  }
  
  public void setLabel(String paramString)
  {
    this.ʽ = paramString;
  }
  
  public void setMaskedNumber(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setPrintedNumber(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setPrintedTokenNumber(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setProductCode(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setTokenNumber(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setType(String paramString)
  {
    this.ʻ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/nostratech/gojek/driver/common/CardDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */