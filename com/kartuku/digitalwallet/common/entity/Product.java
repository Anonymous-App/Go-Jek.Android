package com.kartuku.digitalwallet.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Product
  implements Serializable
{
  private String ʻ;
  private String ʼ;
  private boolean ʽ;
  private Long ˊ;
  private String ˋ;
  private Timestamp ˎ;
  private PaymentType ˏ;
  private AccessToken ᐝ;
  
  public Product() {}
  
  public Product(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public AccessToken getAccess()
  {
    return this.ᐝ;
  }
  
  public Timestamp getDateUpdate()
  {
    return this.ˎ;
  }
  
  public String getMerchantToken()
  {
    return this.ʻ;
  }
  
  public PaymentType getPaymentType()
  {
    return this.ˏ;
  }
  
  public Long getProductId()
  {
    return this.ˊ;
  }
  
  public String getProductType()
  {
    return this.ˋ;
  }
  
  public String getSecretKey()
  {
    return this.ʼ;
  }
  
  public boolean isWithCVV()
  {
    return this.ʽ;
  }
  
  public void setAccess(AccessToken paramAccessToken)
  {
    this.ᐝ = paramAccessToken;
  }
  
  public void setDateUpdate(Timestamp paramTimestamp)
  {
    this.ˎ = paramTimestamp;
  }
  
  public void setMerchantToken(String paramString)
  {
    this.ʻ = paramString;
  }
  
  public void setPaymentType(PaymentType paramPaymentType)
  {
    this.ˏ = paramPaymentType;
  }
  
  public void setProductId(Long paramLong)
  {
    this.ˊ = paramLong;
  }
  
  public void setProductType(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setSecretKey(String paramString)
  {
    this.ʼ = paramString;
  }
  
  public void setWithCVV(boolean paramBoolean)
  {
    this.ʽ = paramBoolean;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/entity/Product.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */