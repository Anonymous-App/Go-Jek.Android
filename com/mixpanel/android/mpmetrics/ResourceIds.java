package com.mixpanel.android.mpmetrics;

public abstract interface ResourceIds
{
  public abstract int idFromName(String paramString);
  
  public abstract boolean knownIdName(String paramString);
  
  public abstract String nameForId(int paramInt);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/mixpanel/android/mpmetrics/ResourceIds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */