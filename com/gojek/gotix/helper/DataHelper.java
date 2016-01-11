package com.gojek.gotix.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class DataHelper
{
  private static final String GOTIX_PREFERENCES = "GotixPref";
  private static final String TAG = DataHelper.class.getSimpleName();
  private static SharedPreferences.Editor editor;
  private static SharedPreferences sharedPreferences;
  
  public static void clearData()
  {
    sharedPreferences.edit().clear();
  }
  
  public static void clearData(String paramString)
  {
    sharedPreferences.edit().remove(paramString);
  }
  
  public static boolean getBoolean(String paramString)
  {
    return sharedPreferences.getBoolean(paramString, false);
  }
  
  public static int getInt(String paramString)
  {
    return sharedPreferences.getInt(paramString, 0);
  }
  
  public static long getLong(String paramString)
  {
    return sharedPreferences.getLong(paramString, 0L);
  }
  
  public static String getString(String paramString)
  {
    return sharedPreferences.getString(paramString, null);
  }
  
  public static void init(Context paramContext)
  {
    sharedPreferences = paramContext.getSharedPreferences("GotixPref", 0);
    editor = sharedPreferences.edit();
  }
  
  public static void saveData(String paramString, int paramInt)
  {
    editor.putInt(paramString, paramInt);
    editor.commit();
  }
  
  public static void saveData(String paramString, long paramLong)
  {
    editor.putLong(paramString, paramLong);
    editor.commit();
  }
  
  public static void saveData(String paramString1, String paramString2)
  {
    editor.putString(paramString1, paramString2);
    editor.commit();
  }
  
  public static void saveData(String paramString, boolean paramBoolean)
  {
    editor.putBoolean(paramString, paramBoolean);
    editor.commit();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/helper/DataHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */