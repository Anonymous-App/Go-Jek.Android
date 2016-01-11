package com.facebook;

public abstract interface FacebookCallback<RESULT>
{
  public abstract void onCancel();
  
  public abstract void onError(FacebookException paramFacebookException);
  
  public abstract void onSuccess(RESULT paramRESULT);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/facebook/FacebookCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */