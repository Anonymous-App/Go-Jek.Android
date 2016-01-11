package com.kartuku.directclient.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kartuku.directclient.model.Request;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorizeRequest
  extends Request
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
  private String ˎ;
  private String ˏ;
  private String ͺ;
  private String ᐝ;
  private Boolean ι;
  
  public String getCardCVV()
  {
    return this.ʿ;
  }
  
  public String getCardToken()
  {
    return this.ʾ;
  }
  
  public String getFilterBin()
  {
    return this.ˈ;
  }
  
  public String getFilterCard()
  {
    return this.ˉ;
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
    return this.ᐝ;
  }
  
  public String getTxnCurrency()
  {
    return this.ʻ;
  }
  
  public String getTxnCustom1()
  {
    return this.ʼ;
  }
  
  public String getTxnCustom2()
  {
    return this.ʽ;
  }
  
  public String getTxnCustom3()
  {
    return this.ͺ;
  }
  
  public String getTxnReference()
  {
    return this.ˏ;
  }
  
  public String getTxnStoreCode()
  {
    return this.ˎ;
  }
  
  public String getTxnTradingDate()
  {
    return this.ˋ;
  }
  
  public boolean isCardTokenize()
  {
    return this.ι.booleanValue();
  }
  
  public void setCardCVV(String paramString)
  {
    this.ʿ = paramString;
  }
  
  public void setCardToken(String paramString)
  {
    this.ʾ = paramString;
  }
  
  public void setCardTokenize(Boolean paramBoolean)
  {
    this.ι = paramBoolean;
  }
  
  public void setFilterBin(String paramString)
  {
    this.ˈ = paramString;
  }
  
  public void setFilterCard(String paramString)
  {
    this.ˉ = paramString;
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
    this.ᐝ = paramString;
  }
  
  public void setTxnCurrency(String paramString)
  {
    this.ʻ = paramString;
  }
  
  public void setTxnCustom1(String paramString)
  {
    this.ʼ = paramString;
  }
  
  public void setTxnCustom2(String paramString)
  {
    this.ʽ = paramString;
  }
  
  public void setTxnCustom3(String paramString)
  {
    this.ͺ = paramString;
  }
  
  public void setTxnReference(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setTxnStoreCode(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setTxnTradingDate(String paramString)
  {
    this.ˋ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/directclient/model/request/AuthorizeRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */