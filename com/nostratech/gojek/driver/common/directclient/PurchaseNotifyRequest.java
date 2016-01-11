package com.nostratech.gojek.driver.common.directclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kartuku.directclient.model.request.PurchaseRequest;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseNotifyRequest
  extends PurchaseRequest
{
  private String ʻ;
  private String ˊ;
  private String ˋ;
  private String ˎ;
  private boolean ˏ;
  private String ᐝ;
  
  public PurchaseNotifyRequest() {}
  
  public PurchaseNotifyRequest(boolean paramBoolean, String paramString)
  {
    this.ˏ = paramBoolean;
    this.ᐝ = paramString;
  }
  
  public String getCardExpiry()
  {
    return this.ˊ;
  }
  
  public String getCardNumber()
  {
    return this.ˋ;
  }
  
  public String getProductType()
  {
    return this.ʻ;
  }
  
  public String getReceiverUrl()
  {
    return this.ᐝ;
  }
  
  public String getType()
  {
    return this.ˎ;
  }
  
  public boolean isWithNotification()
  {
    return this.ˏ;
  }
  
  public void setCardExpiry(String paramString)
  {
    this.ˊ = paramString;
  }
  
  public void setCardNumber(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setProductType(String paramString)
  {
    this.ʻ = paramString;
  }
  
  public void setReceiverUrl(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setType(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setWithNotification(boolean paramBoolean)
  {
    this.ˏ = paramBoolean;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/nostratech/gojek/driver/common/directclient/PurchaseNotifyRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */