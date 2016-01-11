package com.gojek.app.model;

public class RestResponse
{
  private Integer statusCode;
  private String statusMessage;
  
  public Integer getStatusCode()
  {
    return this.statusCode;
  }
  
  public String getStatusMessage()
  {
    return this.statusMessage;
  }
  
  public void setStatusCode(Integer paramInteger)
  {
    this.statusCode = paramInteger;
  }
  
  public void setStatusMessage(String paramString)
  {
    this.statusMessage = paramString;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("RestResponse{");
    localStringBuffer.append("statusCode=").append(this.statusCode);
    localStringBuffer.append(", statusMessage='").append(this.statusMessage).append('\'');
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/RestResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */