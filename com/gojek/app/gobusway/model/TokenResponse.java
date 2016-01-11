package com.gojek.app.gobusway.model;

public class TokenResponse
{
  private String accessToken;
  private String refreshToken;
  
  public String getAccessToken()
  {
    return this.accessToken;
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/model/TokenResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */