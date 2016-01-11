package com.gojek.app.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class GojekPreference
{
  private static SharedPreferences sharedPreference;
  private static GojekPreference sinPref;
  
  public static GojekPreference getInstance(Context paramContext)
  {
    if (sinPref == null) {
      sinPref = new GojekPreference();
    }
    sharedPreference = paramContext.getSharedPreferences("GojekPref", 0);
    return sinPref;
  }
  
  public boolean getBoolean(String paramString, boolean paramBoolean)
  {
    return sharedPreference.getBoolean(paramString, paramBoolean);
  }
  
  public int getInt(String paramString, int paramInt)
  {
    return sharedPreference.getInt(paramString, paramInt);
  }
  
  public long getLong(String paramString, long paramLong)
  {
    return sharedPreference.getLong(paramString, paramLong);
  }
  
  public String getString(String paramString1, String paramString2)
  {
    return sharedPreference.getString(paramString1, paramString2);
  }
  
  public void setBoolean(String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = sharedPreference.edit();
    localEditor.putBoolean(paramString, paramBoolean);
    localEditor.commit();
  }
  
  public void setInt(String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = sharedPreference.edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }
  
  public void setLong(String paramString, long paramLong)
  {
    SharedPreferences.Editor localEditor = sharedPreference.edit();
    localEditor.putLong(paramString, paramLong);
    localEditor.commit();
  }
  
  public String setString(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = sharedPreference.edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
    return paramString1;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/util/GojekPreference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */