package com.gojek.gotix.network.model;

public class PaymentComponent
{
  private String payment_description;
  private String payment_title;
  private String payment_type;
  
  public PaymentComponent() {}
  
  public PaymentComponent(String paramString1, String paramString2, String paramString3)
  {
    this.payment_title = paramString1;
    this.payment_type = paramString2;
    this.payment_description = paramString3;
  }
  
  public String getPayment_description()
  {
    return this.payment_description;
  }
  
  public String getPayment_title()
  {
    return this.payment_title;
  }
  
  public String getPayment_type()
  {
    return this.payment_type;
  }
  
  public void setPayment_description(String paramString)
  {
    this.payment_description = paramString;
  }
  
  public void setPayment_title(String paramString)
  {
    this.payment_title = paramString;
  }
  
  public void setPayment_type(String paramString)
  {
    this.payment_type = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/PaymentComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */