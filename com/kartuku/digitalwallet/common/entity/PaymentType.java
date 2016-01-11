package com.kartuku.digitalwallet.common.entity;

import java.io.Serializable;
import java.util.Set;

public class PaymentType
  implements Serializable
{
  private Long ˊ;
  private String ˋ;
  private Set<Product> ˎ;
  
  public PaymentType() {}
  
  public PaymentType(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public String getPaymentTypeDetail()
  {
    return this.ˋ;
  }
  
  public Long getPaymentTypeId()
  {
    return this.ˊ;
  }
  
  public Set<Product> getProduct()
  {
    return this.ˎ;
  }
  
  public void setPaymentTypeDetail(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void setPaymentTypeId(Long paramLong)
  {
    this.ˊ = paramLong;
  }
  
  public void setProduct(Set<Product> paramSet)
  {
    this.ˎ = paramSet;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/entity/PaymentType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */