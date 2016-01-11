package com.gojek.gotix.network.model;

public class PaymentData
{
  private String key;
  private String value;
  
  public PaymentData(String paramString1, String paramString2)
  {
    this.key = paramString1;
    this.value = paramString2;
  }
  
  public String getKey()
  {
    return this.key;
  }
  
  public String getValue()
  {
    return this.value;
  }
  
  public void setKey(String paramString)
  {
    this.key = paramString;
  }
  
  public void setValue(String paramString)
  {
    this.value = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/PaymentData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */