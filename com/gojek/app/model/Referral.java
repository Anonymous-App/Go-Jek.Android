package com.gojek.app.model;

public class Referral
{
  public long creditTopupped;
  public int customerId;
  public String message;
  
  public long getCreditTopupped()
  {
    return this.creditTopupped;
  }
  
  public int getCustomerId()
  {
    return this.customerId;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public void setCreditTopupped(long paramLong)
  {
    this.creditTopupped = paramLong;
  }
  
  public void setCustomerId(int paramInt)
  {
    this.customerId = paramInt;
  }
  
  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("Referral{");
    localStringBuffer.append("customerId=").append(this.customerId);
    localStringBuffer.append(", creditTopupped=").append(this.creditTopupped);
    localStringBuffer.append(", message='").append(this.message).append('\'');
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/Referral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */