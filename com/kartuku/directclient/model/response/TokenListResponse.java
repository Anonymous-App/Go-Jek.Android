package com.kartuku.directclient.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kartuku.directclient.model.CardToken;
import com.kartuku.directclient.model.Response;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenListResponse
  extends Response
{
  private List<CardToken> ˊ;
  private String ˋ;
  
  public List<CardToken> getCardTokens()
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
  
  public String getMerchantUserCode()
  {
    return this.ˋ;
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
  
  public void setCardToken(List<CardToken> paramList)
  {
    this.ˊ = paramList;
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
    this.ˋ = paramString;
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/directclient/model/response/TokenListResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */