package com.gojek.app.model;

import android.content.Context;
import com.gojek.app.util.GojekPreference;

public class Adult
{
  public static final String isAdult_ = "is_adult";
  public boolean isAdult = false;
  private GojekPreference mPref;
  
  public Adult(Context paramContext)
  {
    this.mPref = GojekPreference.getInstance(paramContext);
    this.isAdult = Boolean.valueOf(this.mPref.getString("is_adult", null)).booleanValue();
  }
  
  public void clearData()
  {
    this.mPref.setString("is_adult", null);
  }
  
  public boolean isAdult()
  {
    this.isAdult = Boolean.valueOf(this.mPref.getString("is_adult", "")).booleanValue();
    return this.isAdult;
  }
  
  public void saveData(boolean paramBoolean)
  {
    this.mPref.setString("is_adult", Boolean.toString(paramBoolean));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/Adult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */