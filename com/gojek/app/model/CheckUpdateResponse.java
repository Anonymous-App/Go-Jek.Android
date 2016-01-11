package com.gojek.app.model;

public class CheckUpdateResponse
{
  private int code = 0;
  private String message = "";
  
  public int getCode()
  {
    return this.code;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public void setCode(int paramInt)
  {
    this.code = paramInt;
  }
  
  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
  
  public String toString()
  {
    return "CheckUpdateResponse{code=" + this.code + ", message='" + this.message + '\'' + '}';
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/CheckUpdateResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */