package com.kartuku.digitalwallet.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionHistory
  implements Serializable
{
  private String ʻ;
  private String ʼ;
  private String ʽ;
  private String ʾ;
  private String ʿ;
  private String ˈ;
  private String ˉ;
  private Long ˊ;
  private String ˋ;
  private String ˌ;
  private String ˍ;
  private String ˎ;
  private String ˏ;
  private String ˑ;
  private String ͺ;
  private String ـ;
  private String ᐝ;
  private String ᐧ;
  private String ᐨ;
  private String ι;
  private Timestamp ﹳ;
  
  public String getAuthIpgTxnReference()
  {
    return this.ˑ;
  }
  
  public String getAuthTxnReference()
  {
    return this.ˍ;
  }
  
  public String getCardExpiry()
  {
    return this.ʼ;
  }
  
  public String getCardNumberMasked()
  {
    return this.ʽ;
  }
  
  public Timestamp getDateUpdate()
  {
    return this.ﹳ;
  }
  
  public Long getId()
  {
    return this.ˊ;
  }
  
  public String getIpgCaptureNo()
  {
    return this.ـ;
  }
  
  public String getIpgGateway()
  {
    return this.ͺ;
  }
  
  public String getIpgResponseCode()
  {
    return this.ˋ;
  }
  
  public String getIpgTxnReference()
  {
    return this.ˏ;
  }
  
  public String getMerchantToken()
  {
    return this.ʻ;
  }
  
  public String getMerchantUserCode()
  {
    return this.ᐝ;
  }
  
  public String getProductType()
  {
    return this.ˌ;
  }
  
  public String getTxnAmount()
  {
    return this.ʿ;
  }
  
  public String getTxnAuthAmount()
  {
    return this.ᐧ;
  }
  
  public String getTxnCapAmount()
  {
    return this.ᐨ;
  }
  
  public String getTxnReference()
  {
    return this.ˎ;
  }
  
  public String getTxnStatus()
  {
    return this.ˉ;
  }
  
  public String getTxnStoreCode()
  {
    return this.ʾ;
  }
  
  public String getTxnTradingDate()
  {
    return this.ι;
  }
  
  public String getTxnType()
  {
    return this.ˈ;
  }
  
  public void setAuthIpgTxnReference(String paramString)
  {
    this.ˑ = paramString;
  }
  
  public void setAuthTxnReference(String paramString)
  {
    this.ˍ = paramString;
  }
  
  public void setCardExpiry(String paramString)
  {
    this.ʼ = paramString;
  }
  
  public void setCardNumberMasked(String paramString)
  {
    this.ʽ = paramString;
  }
  
  public void setDateUpdate(Timestamp paramTimestamp)
  {
    this.ﹳ = paramTimestamp;
  }
  
  public void setId(Long paramLong)
  {
    this.ˊ = paramLong;
  }
  
  public void setIpgCaptureNo(String paramString)
  {
    this.ـ = paramString;
  }
  
  public void setIpgGateway(String paramString)
  {
    this.ͺ = paramString;
  }
  
  public void setIpgResponseCode(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setIpgTxnReference(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setMerchantToken(String paramString)
  {
    this.ʻ = paramString;
  }
  
  public void setMerchantUserCode(String paramString)
  {
    this.ᐝ = paramString;
  }
  
  public void setProductType(String paramString)
  {
    this.ˌ = paramString;
  }
  
  public void setTxnAmount(String paramString)
  {
    this.ʿ = paramString;
  }
  
  public void setTxnAuthAmount(String paramString)
  {
    this.ᐧ = paramString;
  }
  
  public void setTxnCapAmount(String paramString)
  {
    this.ᐨ = paramString;
  }
  
  public void setTxnReference(String paramString)
  {
    this.ˎ = paramString;
  }
  
  public void setTxnStatus(String paramString)
  {
    this.ˉ = paramString;
  }
  
  public void setTxnStoreCode(String paramString)
  {
    this.ʾ = paramString;
  }
  
  public void setTxnTradingDate(String paramString)
  {
    this.ι = paramString;
  }
  
  public void setTxnType(String paramString)
  {
    this.ˈ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/entity/TransactionHistory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */