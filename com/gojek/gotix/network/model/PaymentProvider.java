package com.gojek.gotix.network.model;

public class PaymentProvider
{
  private String payment_provider_name;
  private int payment_provider_type;
  
  public PaymentProvider() {}
  
  public PaymentProvider(String paramString, int paramInt)
  {
    this.payment_provider_name = paramString;
    this.payment_provider_type = paramInt;
  }
  
  public String getPayment_provider_name()
  {
    return this.payment_provider_name;
  }
  
  public int getPayment_provider_type()
  {
    return this.payment_provider_type;
  }
  
  public void setPayment_provider_name(String paramString)
  {
    this.payment_provider_name = paramString;
  }
  
  public void setPayment_provider_type(int paramInt)
  {
    this.payment_provider_type = paramInt;
  }
  
  public String toString()
  {
    return getPayment_provider_name();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/PaymentProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */