package com.gojek.app.model;

public class SignUpResponse
{
  private int customerId;
  private String status;
  
  public int getCustomerId()
  {
    return this.customerId;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setCustomerId(int paramInt)
  {
    this.customerId = paramInt;
  }
  
  public void setStatus(String paramString)
  {
    this.status = paramString;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("SignUpResponse{");
    localStringBuffer.append("customerId=").append(this.customerId);
    localStringBuffer.append(", status='").append(this.status).append('\'');
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/SignUpResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */