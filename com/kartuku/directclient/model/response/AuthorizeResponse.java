package com.kartuku.directclient.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kartuku.directclient.model.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorizeResponse
  extends Response
{
  private String ʻ;
  private String ʼ;
  private String ʽ;
  private String ʾ;
  private String ʿ;
  private String ˈ;
  private String ˉ;
  private String ˊ;
  private String ˋ;
  private String ˌ;
  private String ˍ;
  private String ˎ;
  private String ˏ;
  private String ˑ;
  private String ͺ;
  private String ᐝ;
  private String ι;
  
  public String getCardExpiry()
  {
    return this.ˊ;
  }
  
  public String getCardNumberMasked()
  {
    return this.ˋ;
  }
  
  public String getCardToken()
  {
    return this.ˎ;
  }
  
  public String getIpgGateway()
  {
    return this.ipgGateway;
  }
  
  public String getIpgResponseCode()
  {
    return this.ipgResponseCode;
  }
  
  public String getIpgTxnReference()
  {
    return this.ipgTxnReference;
  }
  
  public String getMerchantToken()
  {
    return this.merchantToken;
  }
  
  public String getMerchantUserCode()
  {
    return this.ˏ;
  }
  
  public String getPaymentBrand()
  {
    return this.ˑ;
  }
  
  public String getRedurectUrl()
  {
    return this.ᐝ;
  }
  
  public String getRespMessage()
  {
    return this.respMessage;
  }
  
  public String getTxnAmount()
  {
    return this.ʻ;
  }
  
  public String getTxnApprovalCode()
  {
    return this.ʼ;
  }
  
  public String getTxnCurrency()
  {
    return this.ʽ;
  }
  
  public String getTxnCustom1()
  {
    return this.ͺ;
  }
  
  public String getTxnCustom2()
  {
    return this.ι;
  }
  
  public String getTxnCustom3()
  {
    return this.ʾ;
  }
  
  public String getTxnIpAddress()
  {
    return this.txnIpAddress;
  }
  
  public String getTxnMID()
  {
    return this.txnMID;
  }
  
  public String getTxnRRN()
  {
    return this.ʿ;
  }
  
  public String getTxnReference()
  {
    return this.ˈ;
  }
  
  public String getTxnResponseCode()
  {
    return this.ˉ;
  }
  
  public String getTxnStoreCode()
  {
    return this.ˌ;
  }
  
  public String getTxnTradingDate()
  {
    return this.ˍ;
  }
  
  public void setCardExpiry(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setCardNumberMasked(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setCardToken(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setIpgGateway(String paramString)
  {
    this.ipgGateway = paramString;
  }
  
  public void setIpgResponseCode(String paramString)
  {
    this.ipgResponseCode = paramString;
  }
  
  public void setIpgTxnReference(String paramString)
  {
    this.ipgTxnReference = paramString;
  }
  
  public void setMerchantToken(String paramString)
  {
    this.merchantToken = paramString;
  }
  
  public void setMerchantUserCode(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setPaymentBrand(String paramString)
  {
    this.ˑ = paramString;
  }
  
  public void setRedurectUrl(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setRespMessage(String paramString)
  {
    this.respMessage = paramString;
  }
  
  public void setTxnAmount(String paramString)
  {
    this.ʻ = paramString;
  }
  
  public void setTxnApprovalCode(String paramString)
  {
    this.ʼ = paramString;
  }
  
  public void setTxnCurrency(String paramString)
  {
    this.ʽ = paramString;
  }
  
  public void setTxnCustom1(String paramString)
  {
    this.ͺ = paramString;
  }
  
  public void setTxnCustom2(String paramString)
  {
    this.ι = paramString;
  }
  
  public void setTxnCustom3(String paramString)
  {
    this.ʾ = paramString;
  }
  
  public void setTxnIpAddress(String paramString)
  {
    this.txnIpAddress = paramString;
  }
  
  public void setTxnMID(String paramString)
  {
    this.txnMID = paramString;
  }
  
  public void setTxnRRN(String paramString)
  {
    this.ʿ = paramString;
  }
  
  public void setTxnReference(String paramString)
  {
    this.ˈ = paramString;
  }
  
  public void setTxnResponseCode(String paramString)
  {
    this.ˉ = paramString;
  }
  
  public void setTxnStoreCode(String paramString)
  {
    this.ˌ = paramString;
  }
  
  public void setTxnTradingDate(String paramString)
  {
    this.ˍ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/directclient/model/response/AuthorizeResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */