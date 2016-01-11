package com.kartuku.digitalwallet.common.dto.ipg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentRequestDto
  extends IpgRequestDto
{
  private String ʻ;
  private String ʼ;
  private String ʽ;
  private String ʾ;
  private String ʿ;
  private String ˈ;
  private Boolean ˉ;
  private String ˊ;
  private String ˋ;
  private String ˌ;
  private String ˍ;
  private String ˎ;
  private String ˏ;
  private String ˑ;
  private String ͺ;
  private String ـ;
  private String ᐝ = "";
  private String ι;
  
  public String getCardCVV()
  {
    return this.ˍ;
  }
  
  public String getCardExpiry()
  {
    return this.ˊ;
  }
  
  public String getCardNumber()
  {
    return this.ˋ;
  }
  
  public String getCardToken()
  {
    return this.ˌ;
  }
  
  public Boolean getCardTokenize()
  {
    return this.ˉ;
  }
  
  public String getFilterBin()
  {
    return this.ˑ;
  }
  
  public String getFilterCard()
  {
    return this.ـ;
  }
  
  public String getIpgGateway()
  {
    return this.ᐝ;
  }
  
  public String getMerchantUserCode()
  {
    return this.ˏ;
  }
  
  public String getTxnAmount()
  {
    return this.ͺ;
  }
  
  public String getTxnCurrency()
  {
    return this.ι;
  }
  
  public String getTxnCustom1()
  {
    return this.ʾ;
  }
  
  public String getTxnCustom2()
  {
    return this.ʿ;
  }
  
  public String getTxnCustom3()
  {
    return this.ˈ;
  }
  
  public String getTxnReference()
  {
    return this.ʽ;
  }
  
  public String getTxnStoreCode()
  {
    return this.ʼ;
  }
  
  public String getTxnTradingDate()
  {
    return this.ʻ;
  }
  
  public String getType()
  {
    return this.ˎ;
  }
  
  public void setCardCVV(String paramString)
  {
    this.ˍ = paramString;
  }
  
  public void setCardExpiry(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setCardNumber(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setCardToken(String paramString)
  {
    this.ˌ = paramString;
  }
  
  public void setCardTokenize(Boolean paramBoolean)
  {
    this.ˉ = paramBoolean;
  }
  
  public void setFilterBin(String paramString)
  {
    this.ˑ = paramString;
  }
  
  public void setFilterCard(String paramString)
  {
    this.ـ = paramString;
  }
  
  public void setIpgGateway(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setMerchantUserCode(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setTxnAmount(String paramString)
  {
    this.ͺ = paramString;
  }
  
  public void setTxnCurrency(String paramString)
  {
    this.ι = paramString;
  }
  
  public void setTxnCustom1(String paramString)
  {
    this.ʾ = paramString;
  }
  
  public void setTxnCustom2(String paramString)
  {
    this.ʿ = paramString;
  }
  
  public void setTxnCustom3(String paramString)
  {
    this.ˈ = paramString;
  }
  
  public void setTxnReference(String paramString)
  {
    this.ʽ = paramString;
  }
  
  public void setTxnStoreCode(String paramString)
  {
    this.ʼ = paramString;
  }
  
  public void setTxnTradingDate(String paramString)
  {
    this.ʻ = paramString;
  }
  
  public void setType(String paramString)
  {
    this.ˎ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dto/ipg/PaymentRequestDto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */