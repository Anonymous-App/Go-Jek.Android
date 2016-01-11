package com.gojek.app.gobusway.util;

import android.content.Context;

public class GoBusWayPreferences
{
  private static final String ACCESS_TOKEN_KEY = "access token";
  private static final String REFRESH_TOKEN_KEY = "refresh token";
  private Context mContext;
  
  public GoBusWayPreferences(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  public String getBearerAccessToken()
  {
    return PreferencesHelper.getString(this.mContext, "access token", "");
  }
  
  public String getRefreshToken()
  {
    return PreferencesHelper.getString(this.mContext, "refresh token", "");
  }
  
  public void saveAccessToken(String paramString)
  {
    PreferencesHelper.putString(this.mContext, "access token", paramString);
  }
  
  public void saveRefreshToken(String paramString)
  {
    PreferencesHelper.putString(this.mContext, "refresh token", paramString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/util/GoBusWayPreferences.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */