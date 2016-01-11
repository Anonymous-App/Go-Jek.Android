package com.android.volley;

public abstract interface Network
{
  public abstract NetworkResponse performRequest(Request<?> paramRequest)
    throws VolleyError;
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/android/volley/Network.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */