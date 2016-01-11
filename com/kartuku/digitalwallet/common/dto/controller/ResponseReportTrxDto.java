package com.kartuku.digitalwallet.common.dto.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ResponseReportTrxDto
  extends ResponseStatusDto
  implements Serializable
{
  private String ʻ;
  private Double ʼ;
  private String ʽ;
  private BigDecimal ˊ;
  private String ˋ;
  private String ˎ;
  private String ˏ;
  private String ͺ;
  private String ᐝ;
  
  public ResponseReportTrxDto() {}
  
  public ResponseReportTrxDto(BigDecimal paramBigDecimal, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, Double paramDouble, String paramString6, String paramString7)
  {
    this.ˊ = paramBigDecimal;
    this.ˋ = paramString1;
    this.ˎ = paramString2;
    this.ˏ = paramString3;
    this.ᐝ = paramString4;
    this.ʻ = paramString5;
    this.ʼ = paramDouble;
    this.ʽ = paramString6;
    this.ͺ = paramString7;
  }
  
  public Double getAmount()
  {
    return this.ʼ;
  }
  
  public String getCardTypeDesc()
  {
    return this.ʽ;
  }
  
  public BigDecimal getId()
  {
    return this.ˊ;
  }
  
  public String getMid()
  {
    return this.ˋ;
  }
  
  public String getPan()
  {
    return this.ᐝ;
  }
  
  public String getPrintNumber()
  {
    return this.ʻ;
  }
  
  public String getStoreName()
  {
    return this.ͺ;
  }
  
  public String getTxnResTime()
  {
    return this.ˏ;
  }
  
  public String getTxnType()
  {
    return this.ˎ;
  }
  
  public void setAmount(Double paramDouble)
  {
    this.ʼ = paramDouble;
  }
  
  public void setCardTypeDesc(String paramString)
  {
    this.ʽ = paramString;
  }
  
  public void setId(BigDecimal paramBigDecimal)
  {
    this.ˊ = paramBigDecimal;
  }
  
  public void setMid(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setPan(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setPrintNumber(String paramString)
  {
    this.ʻ = paramString;
  }
  
  public void setStoreName(String paramString)
  {
    this.ͺ = paramString;
  }
  
  public void setTxnResTime(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setTxnType(String paramString)
  {
    this.ˎ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/controller/ResponseReportTrxDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */