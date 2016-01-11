package com.gojek.app.json;

import com.android.volley.VolleyError;

public abstract interface JsonCallback<T>
{
  public abstract void onError(VolleyError paramVolleyError);
  
  public abstract void onResponse(T paramT);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/json/JsonCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */