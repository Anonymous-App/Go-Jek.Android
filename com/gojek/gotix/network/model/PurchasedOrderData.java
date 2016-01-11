package com.gojek.gotix.network.model;

import com.google.gson.annotations.SerializedName;

public class PurchasedOrderData
{
  @SerializedName("dob")
  private String dateOfBirth;
  private int gender;
  @SerializedName("data")
  private PaymentData[] paymentData;
  @SerializedName("payment_type")
  private int paymentType;
  
  public PurchasedOrderData(int paramInt1, PaymentData[] paramArrayOfPaymentData, String paramString, int paramInt2)
  {
    this.paymentType = paramInt1;
    this.paymentData = paramArrayOfPaymentData;
    this.dateOfBirth = paramString;
    this.gender = paramInt2;
  }
  
  public String getDateOfBirth()
  {
    return this.dateOfBirth;
  }
  
  public int getGender()
  {
    return this.gender;
  }
  
  public PaymentData[] getPaymentData()
  {
    return this.paymentData;
  }
  
  public int getPaymentType()
  {
    return this.paymentType;
  }
  
  public void setDateOfBirth(String paramString)
  {
    this.dateOfBirth = paramString;
  }
  
  public void setGender(int paramInt)
  {
    this.gender = paramInt;
  }
  
  public void setPaymentData(PaymentData[] paramArrayOfPaymentData)
  {
    this.paymentData = paramArrayOfPaymentData;
  }
  
  public void setPaymentType(int paramInt)
  {
    this.paymentType = paramInt;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/PurchasedOrderData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */