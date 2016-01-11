package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public final class ed
{
  public static String D(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    try
    {
      paramString = JSONObjectInstrumentation.init(paramString).getString("developerPayload");
      return paramString;
    }
    catch (JSONException paramString)
    {
      gs.W("Fail to parse purchase data");
    }
    return null;
  }
  
  public static String E(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    try
    {
      paramString = JSONObjectInstrumentation.init(paramString).getString("purchaseToken");
      return paramString;
    }
    catch (JSONException paramString)
    {
      gs.W("Fail to parse purchase data");
    }
    return null;
  }
  
  public static int b(Bundle paramBundle)
  {
    paramBundle = paramBundle.get("RESPONSE_CODE");
    if (paramBundle == null)
    {
      gs.W("Bundle with null response code, assuming OK (known issue)");
      return 0;
    }
    if ((paramBundle instanceof Integer)) {
      return ((Integer)paramBundle).intValue();
    }
    if ((paramBundle instanceof Long)) {
      return (int)((Long)paramBundle).longValue();
    }
    gs.W("Unexpected type for intent response code. " + paramBundle.getClass().getName());
    return 5;
  }
  
  public static int d(Intent paramIntent)
  {
    paramIntent = paramIntent.getExtras().get("RESPONSE_CODE");
    if (paramIntent == null)
    {
      gs.W("Intent with no response code, assuming OK (known issue)");
      return 0;
    }
    if ((paramIntent instanceof Integer)) {
      return ((Integer)paramIntent).intValue();
    }
    if ((paramIntent instanceof Long)) {
      return (int)((Long)paramIntent).longValue();
    }
    gs.W("Unexpected type for intent response code. " + paramIntent.getClass().getName());
    return 5;
  }
  
  public static String e(Intent paramIntent)
  {
    if (paramIntent == null) {
      return null;
    }
    return paramIntent.getStringExtra("INAPP_PURCHASE_DATA");
  }
  
  public static String f(Intent paramIntent)
  {
    if (paramIntent == null) {
      return null;
    }
    return paramIntent.getStringExtra("INAPP_DATA_SIGNATURE");
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */