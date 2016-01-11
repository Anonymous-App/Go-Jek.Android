package com.gojek.app.model;

import com.google.gson.annotations.SerializedName;

public class OauthTokenResponse
{
  @SerializedName("access_token")
  private String accessToken;
  @SerializedName("expires_in")
  private long expiredIn;
  @SerializedName("refresh_token")
  private String refreshToken;
  @SerializedName("scope")
  private String scope;
  @SerializedName("token_type")
  private String tokenType;
  
  public String getAccessToken()
  {
    return this.accessToken;
  }
  
  public long getExpiredIn()
  {
    return this.expiredIn;
  }
  
  public String getRefreshToken()
  {
    return this.refreshToken;
  }
  
  public String getScope()
  {
    return this.scope;
  }
  
  public String getTokenType()
  {
    return this.tokenType;
  }
  
  public void setAccessToken(String paramString)
  {
    this.accessToken = paramString;
  }
  
  public void setExpiredIn(long paramLong)
  {
    this.expiredIn = paramLong;
  }
  
  public void setRefreshToken(String paramString)
  {
    this.refreshToken = paramString;
  }
  
  public void setScope(String paramString)
  {
    this.scope = paramString;
  }
  
  public void setTokenType(String paramString)
  {
    this.tokenType = paramString;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("OauthTokenResponse{");
    localStringBuffer.append("accessToken='").append(this.accessToken).append('\'');
    localStringBuffer.append(", tokenType='").append(this.tokenType).append('\'');
    localStringBuffer.append(", refreshToken='").append(this.refreshToken).append('\'');
    localStringBuffer.append(", expiredIn=").append(this.expiredIn);
    localStringBuffer.append(", scope='").append(this.scope).append('\'');
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/OauthTokenResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */