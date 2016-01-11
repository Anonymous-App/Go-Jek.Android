package com.gojek.app.model;

public class CustomerVerificationResponse
  extends Response
{
  private Integer customerId;
  
  public Integer getCustomerId()
  {
    return this.customerId;
  }
  
  public void setCustomerId(Integer paramInteger)
  {
    this.customerId = paramInteger;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("CustomerVerificationResponse{");
    localStringBuffer.append("customerId=").append(this.customerId);
    localStringBuffer.append(", status=").append(this.status);
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/CustomerVerificationResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */