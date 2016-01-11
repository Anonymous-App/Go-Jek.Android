package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class RegisterGcmRequestBody
{
  @SerializedName("device_token")
  private String deviceToken;
  
  public RegisterGcmRequestBody(String paramString)
  {
    this.deviceToken = paramString;
  }
  
  public String getDeviceToken()
  {
    return this.deviceToken;
  }
  
  public void setDeviceToken(String paramString)
  {
    this.deviceToken = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/RegisterGcmRequestBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */