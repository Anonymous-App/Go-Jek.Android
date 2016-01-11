package com.kartuku.directclient.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kartuku.directclient.model.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoidAuthorizeResponse
  extends Response
{
  private String ʻ;
  private String ˊ;
  private String ˋ;
  private String ˎ;
  private String ˏ;
  private String ᐝ;
  
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
  
  public String getRespMessage()
  {
    return this.respMessage;
  }
  
  public String getTxnAmount()
  {
    return this.ˊ;
  }
  
  public String getTxnApprovalCode()
  {
    return this.ˋ;
  }
  
  public String getTxnCurrency()
  {
    return this.ˎ;
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
    return this.ˏ;
  }
  
  public String getTxnReference()
  {
    return this.ᐝ;
  }
  
  public String getTxnResponseCode()
  {
    return this.ʻ;
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
  
  public void setRespMessage(String paramString)
  {
    this.respMessage = paramString;
  }
  
  public void setTxnAmount(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setTxnApprovalCode(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setTxnCurrency(String paramString)
  {
    this.ˎ = paramString;
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
    this.ˏ = paramString;
  }
  
  public void setTxnReference(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setTxnResponseCode(String paramString)
  {
    this.ʻ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/directclient/model/response/VoidAuthorizeResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */