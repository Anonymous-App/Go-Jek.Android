package com.kartuku.directclient.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kartuku.directclient.model.Request;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CaptureRequest
  extends Request
{
  private String ʻ;
  private String ˊ;
  private String ˋ;
  private String ˎ;
  private String ˏ;
  private String ᐝ;
  
  public String getAuthIpgTxnReference()
  {
    return this.ʻ;
  }
  
  public String getAuthTxnReference()
  {
    return this.ᐝ;
  }
  
  public String getIpgGateway()
  {
    return this.ˊ;
  }
  
  public String getMerchantToken()
  {
    return this.merchantToken;
  }
  
  public String getTxnAmount()
  {
    return this.ˎ;
  }
  
  public String getTxnCurrency()
  {
    return this.ˏ;
  }
  
  public String getTxnReference()
  {
    return this.ˋ;
  }
  
  public void setAuthIpgTxnReference(String paramString)
  {
    this.ʻ = paramString;
  }
  
  public void setAuthTxnReference(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setIpgGateway(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setMerchantToken(String paramString)
  {
    this.merchantToken = paramString;
  }
  
  public void setTxnAmount(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setTxnCurrency(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setTxnReference(String paramString)
  {
    this.ˋ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/directclient/model/request/CaptureRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */