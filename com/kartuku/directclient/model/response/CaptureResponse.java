package com.kartuku.directclient.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kartuku.directclient.model.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CaptureResponse
  extends Response
{
  private String ʻ;
  private String ʼ;
  private String ʽ;
  private String ʾ;
  private String ˊ;
  private String ˋ;
  private String ˎ;
  private String ˏ;
  private String ͺ;
  private String ᐝ;
  private String ι;
  
  public String getIpgCaptureNo()
  {
    return this.ˊ;
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
  
  public String getPromoOriginalAmount()
  {
    return this.ˋ;
  }
  
  public String getPromoPointsEarned()
  {
    return this.ˎ;
  }
  
  public String getPromoTag()
  {
    return this.ˏ;
  }
  
  public String getRespMessage()
  {
    return this.respMessage;
  }
  
  public String getTxnApprovalCode()
  {
    return this.ᐝ;
  }
  
  public String getTxnAuthAmount()
  {
    return this.ʻ;
  }
  
  public String getTxnCapAmount()
  {
    return this.ʼ;
  }
  
  public String getTxnCurrency()
  {
    return this.ʽ;
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
    return this.ͺ;
  }
  
  public String getTxnReference()
  {
    return this.ι;
  }
  
  public String getTxnResponseCode()
  {
    return this.ʾ;
  }
  
  public void setIpgCaptureNo(String paramString)
  {
    this.ˊ = paramString;
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
  
  public void setPromoOriginalAmount(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setPromoPointsEarned(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setPromoTag(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setRespMessage(String paramString)
  {
    this.respMessage = paramString;
  }
  
  public void setTxnApprovalCode(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setTxnAuthAmount(String paramString)
  {
    this.ʻ = paramString;
  }
  
  public void setTxnCapAmount(String paramString)
  {
    this.ʼ = paramString;
  }
  
  public void setTxnCurrency(String paramString)
  {
    this.ʽ = paramString;
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
    this.ͺ = paramString;
  }
  
  public void setTxnReference(String paramString)
  {
    this.ι = paramString;
  }
  
  public void setTxnResponseCode(String paramString)
  {
    this.ʾ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/directclient/model/response/CaptureResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */