package com.kartuku.directclient.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kartuku.directclient.model.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenRemoveResponse
  extends Response
{
  private String ˊ;
  
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
    return this.ˊ;
  }
  
  public String getRespMessage()
  {
    return this.respMessage;
  }
  
  public String getTxnIpAddress()
  {
    return this.txnIpAddress;
  }
  
  public String getTxnMID()
  {
    return this.txnMID;
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
    this.ˊ = paramString;
  }
  
  public void setRespMessage(String paramString)
  {
    this.respMessage = paramString;
  }
  
  public void setTxnIpAddress(String paramString)
  {
    this.txnIpAddress = paramString;
  }
  
  public void setTxnMID(String paramString)
  {
    this.txnMID = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/directclient/model/response/TokenRemoveResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */