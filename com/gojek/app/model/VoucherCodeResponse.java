package com.gojek.app.model;

public class VoucherCodeResponse
{
  public int creditBalance;
  public String message;
  
  public int getCreditBalance()
  {
    return this.creditBalance;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public void setCreditBalance(int paramInt)
  {
    this.creditBalance = paramInt;
  }
  
  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("VoucherCodeResponse{");
    localStringBuffer.append("creditBalance=").append(this.creditBalance);
    localStringBuffer.append(", message='").append(this.message).append('\'');
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/VoucherCodeResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */