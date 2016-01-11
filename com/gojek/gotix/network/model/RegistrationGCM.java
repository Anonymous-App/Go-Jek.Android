package com.gojek.gotix.network.model;

import com.google.gson.annotations.SerializedName;

public class RegistrationGCM
{
  @SerializedName("device_token")
  private String deviceToken;
  
  public RegistrationGCM(String paramString)
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/RegistrationGCM.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */