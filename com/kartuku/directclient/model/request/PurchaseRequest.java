package com.kartuku.directclient.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kartuku.directclient.model.Request;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseRequest
  extends Request
{
  private String ʻ;
  private String ʼ;
  private String ʽ;
  private Boolean ʾ;
  private String ʿ;
  private String ˈ;
  private String ˉ;
  private String ˊ;
  private String ˋ = "";
  private String ˌ;
  private String ˎ;
  private String ˏ;
  private String ͺ;
  private String ᐝ;
  private String ι;
  
  public String getCardCVV()
  {
    return this.ˈ;
  }
  
  public String getCardToken()
  {
    return this.ʿ;
  }
  
  public String getFilterBin()
  {
    return this.ˉ;
  }
  
  public String getFilterCard()
  {
    return this.ˌ;
  }
  
  public String getIpgGateway()
  {
    return this.ˋ;
  }
  
  public String getMerchantToken()
  {
    return this.merchantToken;
  }
  
  public String getMerchantUserCode()
  {
    return this.ˊ;
  }
  
  public String getTxnAmount()
  {
    return this.ʻ;
  }
  
  public String getTxnCurrency()
  {
    return this.ʼ;
  }
  
  public String getTxnCustom1()
  {
    return this.ʽ;
  }
  
  public String getTxnCustom2()
  {
    return this.ͺ;
  }
  
  public String getTxnCustom3()
  {
    return this.ι;
  }
  
  public String getTxnReference()
  {
    return this.ᐝ;
  }
  
  public String getTxnStoreCode()
  {
    return this.ˏ;
  }
  
  public String getTxnTradingDate()
  {
    return this.ˎ;
  }
  
  public Boolean isCardTokenize()
  {
    return this.ʾ;
  }
  
  public void setCardCVV(String paramString)
  {
    this.ˈ = paramString;
  }
  
  public void setCardToken(String paramString)
  {
    this.ʿ = paramString;
  }
  
  public void setCardTokenize(Boolean paramBoolean)
  {
    this.ʾ = paramBoolean;
  }
  
  public void setFilterBin(String paramString)
  {
    this.ˉ = paramString;
  }
  
  public void setFilterCard(String paramString)
  {
    this.ˌ = paramString;
  }
  
  public void setIpgGateway(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setMerchantToken(String paramString)
  {
    this.merchantToken = paramString;
  }
  
  public void setMerchantUserCode(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setTxnAmount(String paramString)
  {
    this.ʻ = paramString;
  }
  
  public void setTxnCurrency(String paramString)
  {
    this.ʼ = paramString;
  }
  
  public void setTxnCustom1(String paramString)
  {
    this.ʽ = paramString;
  }
  
  public void setTxnCustom2(String paramString)
  {
    this.ͺ = paramString;
  }
  
  public void setTxnCustom3(String paramString)
  {
    this.ι = paramString;
  }
  
  public void setTxnReference(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setTxnStoreCode(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setTxnTradingDate(String paramString)
  {
    this.ˎ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/directclient/model/request/PurchaseRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */