package com.mixpanel.android.viewcrawler;

import com.mixpanel.android.mpmetrics.Tweaks;
import org.json.JSONArray;

public abstract interface UpdatesFromMixpanel
{
  public abstract Tweaks getTweaks();
  
  public abstract void setEventBindings(JSONArray paramJSONArray);
  
  public abstract void setVariants(JSONArray paramJSONArray);
  
  public abstract void startUpdates();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/mixpanel/android/viewcrawler/UpdatesFromMixpanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */