package com.kartuku.digitalwallet.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RequestReportDto
{
  private Double ʻ;
  private Double ʼ;
  private String ʽ;
  private String ˊ;
  private Integer ˋ;
  private String ˎ;
  private String ˏ;
  private int ͺ;
  private String ᐝ;
  private int ι;
  
  public RequestReportDto() {}
  
  public RequestReportDto(String paramString1, Integer paramInteger, String paramString2, String paramString3, String paramString4, Double paramDouble1, Double paramDouble2, String paramString5, int paramInt1, int paramInt2)
  {
    this.ˊ = paramString1;
    this.ˋ = paramInteger;
    this.ˎ = paramString2;
    this.ˏ = paramString3;
    this.ᐝ = paramString4;
    this.ʻ = paramDouble1;
    this.ʼ = paramDouble2;
    this.ʽ = paramString5;
    this.ͺ = paramInt1;
    this.ι = paramInt2;
  }
  
  public Double getAmountFrom()
  {
    return this.ʻ;
  }
  
  public Double getAmountTo()
  {
    return this.ʼ;
  }
  
  public String getDateTrxFrom()
  {
    return this.ˎ;
  }
  
  public String getDateTrxTo()
  {
    return this.ˏ;
  }
  
  public String getMid()
  {
    return this.ˊ;
  }
  
  public int getPage()
  {
    return this.ͺ;
  }
  
  public String getPan()
  {
    return this.ᐝ;
  }
  
  public int getSize()
  {
    return this.ι;
  }
  
  public String getSort()
  {
    return this.ʽ;
  }
  
  public Integer getTxnType()
  {
    return this.ˋ;
  }
  
  public void setAmountFrom(Double paramDouble)
  {
    this.ʻ = paramDouble;
  }
  
  public void setAmountTo(Double paramDouble)
  {
    this.ʼ = paramDouble;
  }
  
  public void setDateTrxFrom(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setDateTrxTo(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setMid(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setPage(int paramInt)
  {
    this.ͺ = paramInt;
  }
  
  public void setPan(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setSize(int paramInt)
  {
    this.ι = paramInt;
  }
  
  public void setSort(String paramString)
  {
    this.ʽ = paramString;
  }
  
  public void setTxnType(Integer paramInteger)
  {
    this.ˋ = paramInteger;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/dto/RequestReportDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */