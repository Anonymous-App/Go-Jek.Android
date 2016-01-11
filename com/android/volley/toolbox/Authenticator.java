package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;

public abstract interface Authenticator
{
  public abstract String getAuthToken()
    throws AuthFailureError;
  
  public abstract void invalidateAuthToken(String paramString);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/android/volley/toolbox/Authenticator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */