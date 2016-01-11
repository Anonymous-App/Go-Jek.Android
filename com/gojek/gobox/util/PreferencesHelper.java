package com.gojek.gobox.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class PreferencesHelper
{
  public static boolean getBoolean(Context paramContext, String paramString, boolean paramBoolean)
  {
    return getPreferences(paramContext).getBoolean(paramString, paramBoolean);
  }
  
  private static SharedPreferences.Editor getEditor(Context paramContext)
  {
    return getPreferences(paramContext).edit();
  }
  
  public static SharedPreferences.Editor getEditor(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences(paramString, 0).edit();
  }
  
  public static float getFloat(Context paramContext, String paramString, float paramFloat)
  {
    return getPreferences(paramContext).getFloat(paramString, paramFloat);
  }
  
  public static int getInt(Context paramContext, String paramString, int paramInt)
  {
    return getPreferences(paramContext).getInt(paramString, paramInt);
  }
  
  public static long getLong(Context paramContext, String paramString, long paramLong)
  {
    return getPreferences(paramContext).getLong(paramString, paramLong);
  }
  
  private static SharedPreferences getPreferences(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext);
  }
  
  public static SharedPreferences getPreferences(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences(paramString, 0);
  }
  
  public static String getString(Context paramContext, String paramString1, String paramString2)
  {
    return getPreferences(paramContext).getString(paramString1, paramString2);
  }
  
  public static void putBoolean(Context paramContext, String paramString, boolean paramBoolean)
  {
    getEditor(paramContext).putBoolean(paramString, paramBoolean).commit();
  }
  
  public static void putFloat(Context paramContext, String paramString, float paramFloat)
  {
    getEditor(paramContext).putFloat(paramString, paramFloat).commit();
  }
  
  public static void putInt(Context paramContext, String paramString, int paramInt)
  {
    getEditor(paramContext).putInt(paramString, paramInt).commit();
  }
  
  public static void putLong(Context paramContext, String paramString, long paramLong)
  {
    getEditor(paramContext).putLong(paramString, paramLong).commit();
  }
  
  public static void putString(Context paramContext, String paramString1, String paramString2)
  {
    getEditor(paramContext).putString(paramString1, paramString2).commit();
  }
  
  public static void removePreference(Context paramContext, String paramString)
  {
    getEditor(paramContext).remove(paramString).commit();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/util/PreferencesHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */