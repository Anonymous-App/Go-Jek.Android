package com.kartuku.digitalwallet.common.dto.controller;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Card
{
  private String ʻ;
  private String ʼ;
  private String ʽ;
  private String ˊ;
  private String ˋ;
  private String ˎ;
  private String ˏ;
  private Double ͺ;
  private String ᐝ;
  private String ι;
  
  public Card() {}
  
  public Card(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.ˊ = paramString1;
    this.ˋ = paramString2;
    this.ˏ = paramString3;
    this.ʻ = paramString4;
  }
  
  public Card(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.ˊ = paramString1;
    this.ˋ = paramString2;
    this.ˏ = paramString3;
    this.ʻ = paramString4;
    this.ʼ = paramString5;
  }
  
  public Double getAmount()
  {
    return this.ͺ;
  }
  
  public String getCompanyCode()
  {
    return this.ˏ;
  }
  
  public String getExpireDate()
  {
    return this.ʼ;
  }
  
  public String getLabel()
  {
    return this.ʽ;
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
  
  public String getStatus()
  {
    return this.ι;
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
  
  public void setCompanyCode(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setExpireDate(String paramString)
  {
    this.ʼ = paramString;
  }
  
  public void setLabel(String paramString)
  {
    this.ʽ = paramString;
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
  
  public void setStatus(String paramString)
  {
    this.ι = paramString;
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/controller/Card.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */