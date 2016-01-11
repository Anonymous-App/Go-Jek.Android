package com.gojek.app.model;

public class MakeBookingResponse
{
  private String errorMessage;
  private int id;
  private String orderNo;
  private boolean prebook;
  private String prebookMessage;
  
  public String getErrorMessage()
  {
    return this.errorMessage;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getOrderNo()
  {
    return this.orderNo;
  }
  
  public String getPrebookMessage()
  {
    return this.prebookMessage;
  }
  
  public boolean isPrebook()
  {
    return this.prebook;
  }
  
  public void setErrorMessage(String paramString)
  {
    this.errorMessage = paramString;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setOrderNo(String paramString)
  {
    this.orderNo = paramString;
  }
  
  public void setPrebook(boolean paramBoolean)
  {
    this.prebook = paramBoolean;
  }
  
  public void setPrebookMessage(String paramString)
  {
    this.prebookMessage = paramString;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("MakeBookingResponse{");
    localStringBuffer.append("id=").append(this.id);
    localStringBuffer.append(", orderNo='").append(this.orderNo).append('\'');
    localStringBuffer.append(", errorMessage='").append(this.errorMessage).append('\'');
    localStringBuffer.append(", prebook=").append(this.prebook);
    localStringBuffer.append(", prebookMessage='").append(this.prebookMessage).append('\'');
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/MakeBookingResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */