package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

public class hp
{
  private static final String[] Cm;
  private static final Map<String, Integer> Cn;
  
  static
  {
    int i = 0;
    Cm = new String[] { "text1", "text2", "icon", "intent_action", "intent_data", "intent_data_id", "intent_extra_data", "suggest_large_icon", "intent_activity" };
    Cn = new HashMap(Cm.length);
    while (i < Cm.length)
    {
      Cn.put(Cm[i], Integer.valueOf(i));
      i += 1;
    }
  }
  
  public static String O(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= Cm.length)) {
      return null;
    }
    return Cm[paramInt];
  }
  
  public static int as(String paramString)
  {
    Integer localInteger = (Integer)Cn.get(paramString);
    if (localInteger == null) {
      throw new IllegalArgumentException("[" + paramString + "] is not a valid global search section name");
    }
    return localInteger.intValue();
  }
  
  public static int fl()
  {
    return Cm.length;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/hp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */