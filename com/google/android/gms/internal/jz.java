package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class jz
{
  private static final Pattern MT = Pattern.compile("\\\\.");
  private static final Pattern MU = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
  
  public static String bf(String paramString)
  {
    Matcher localMatcher;
    Object localObject1;
    if (!TextUtils.isEmpty(paramString))
    {
      localMatcher = MU.matcher(paramString);
      localObject1 = null;
      while (localMatcher.find())
      {
        Object localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new StringBuffer();
        }
        switch (localMatcher.group().charAt(0))
        {
        default: 
          localObject1 = localObject2;
          break;
        case '\b': 
          localMatcher.appendReplacement((StringBuffer)localObject2, "\\\\b");
          localObject1 = localObject2;
          break;
        case '"': 
          localMatcher.appendReplacement((StringBuffer)localObject2, "\\\\\\\"");
          localObject1 = localObject2;
          break;
        case '\\': 
          localMatcher.appendReplacement((StringBuffer)localObject2, "\\\\\\\\");
          localObject1 = localObject2;
          break;
        case '/': 
          localMatcher.appendReplacement((StringBuffer)localObject2, "\\\\/");
          localObject1 = localObject2;
          break;
        case '\f': 
          localMatcher.appendReplacement((StringBuffer)localObject2, "\\\\f");
          localObject1 = localObject2;
          break;
        case '\n': 
          localMatcher.appendReplacement((StringBuffer)localObject2, "\\\\n");
          localObject1 = localObject2;
          break;
        case '\r': 
          localMatcher.appendReplacement((StringBuffer)localObject2, "\\\\r");
          localObject1 = localObject2;
          break;
        case '\t': 
          localMatcher.appendReplacement((StringBuffer)localObject2, "\\\\t");
          localObject1 = localObject2;
        }
      }
      if (localObject1 != null) {}
    }
    else
    {
      return paramString;
    }
    localMatcher.appendTail((StringBuffer)localObject1);
    return ((StringBuffer)localObject1).toString();
  }
  
  public static boolean d(Object paramObject1, Object paramObject2)
  {
    if (((paramObject1 instanceof JSONObject)) && ((paramObject2 instanceof JSONObject)))
    {
      paramObject1 = (JSONObject)paramObject1;
      paramObject2 = (JSONObject)paramObject2;
      if (((JSONObject)paramObject1).length() == ((JSONObject)paramObject2).length()) {}
    }
    for (;;)
    {
      return false;
      Iterator localIterator = ((JSONObject)paramObject1).keys();
      for (;;)
      {
        String str;
        if (localIterator.hasNext())
        {
          str = (String)localIterator.next();
          if (!((JSONObject)paramObject2).has(str)) {
            break;
          }
        }
        boolean bool;
        int i;
        try
        {
          bool = d(((JSONObject)paramObject1).get(str), ((JSONObject)paramObject2).get(str));
          if (!bool) {
            return false;
          }
        }
        catch (JSONException paramObject1) {}
      }
      return true;
      if ((!(paramObject1 instanceof JSONArray)) || (!(paramObject2 instanceof JSONArray))) {
        break label170;
      }
      paramObject1 = (JSONArray)paramObject1;
      paramObject2 = (JSONArray)paramObject2;
      if (((JSONArray)paramObject1).length() == ((JSONArray)paramObject2).length())
      {
        i = 0;
        if (i >= ((JSONArray)paramObject1).length()) {}
      }
      try
      {
        bool = d(((JSONArray)paramObject1).get(i), ((JSONArray)paramObject2).get(i));
        if (bool) {
          i += 1;
        }
      }
      catch (JSONException paramObject1)
      {
        return false;
      }
    }
    return true;
    label170:
    return paramObject1.equals(paramObject2);
    return false;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/jz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */