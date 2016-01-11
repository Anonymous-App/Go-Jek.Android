package lib.gojek.base.networks.model;

import com.google.gson.annotations.SerializedName;

public class RegisteredToken
{
  @SerializedName("token")
  public String accessToken;
  @SerializedName("expires_in")
  public long expiresIn;
  
  public RegisteredToken(String paramString, long paramLong)
  {
    this.accessToken = paramString;
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
  
  public void setAccessToken(String paramString)
  {
    this.accessToken = paramString;
  }
  
  public void setExpiresIn(long paramLong)
  {
    this.expiresIn = paramLong;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/networks/model/RegisteredToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */