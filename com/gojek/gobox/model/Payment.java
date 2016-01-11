package com.gojek.gobox.model;

public class Payment
{
  int payment_type;
  
  public Payment() {}
  
  public Payment(int paramInt)
  {
    this.payment_type = paramInt;
  }
  
  public int getType()
  {
    return this.payment_type;
  }
  
  public void setType(int paramInt)
  {
    this.payment_type = paramInt;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/Payment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */