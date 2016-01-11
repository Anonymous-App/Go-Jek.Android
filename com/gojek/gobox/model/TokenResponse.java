package com.gojek.gobox.model;

import com.google.gson.annotations.SerializedName;

public class TokenResponse
{
  @SerializedName("access_token")
  private String accessToken;
  @SerializedName("expires_in")
  private long expiresIn;
  @SerializedName("refresh_token")
  private String refreshToken;
  
  public TokenResponse(String paramString1, String paramString2, long paramLong)
  {
    this.accessToken = paramString1;
    this.refreshToken = paramString2;
    this.expiresIn = paramLong;
  }
  
  public String getAccessToken()
  {
    return this.accessToken;
  }
  
  public long getExpiresIn()
  {
    return this.expiresIn;
  }
  
  public String getRefreshToken()
  {
    return this.refreshToken;
  }
  
  public void setAccessToken(String paramString)
  {
    this.accessToken = paramString;
  }
  
  public void setRefreshToken(String paramString)
  {
    this.refreshToken = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/TokenResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */