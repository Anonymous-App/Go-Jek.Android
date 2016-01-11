package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse
{
  private String error;
  @SerializedName("error_description")
  private String errorDescription;
  
  public ErrorResponse(String paramString1, String paramString2)
  {
    this.error = paramString1;
    this.errorDescription = paramString2;
  }
  
  public String getError()
  {
    return this.error;
  }
  
  public String getErrorDescription()
  {
    return this.errorDescription;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/ErrorResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */