package com.kartuku.digitalwallet.common.client.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;

public class RestParserHelper
{
  public static Map<String, String> convertJSONObjectToMap(String paramString, JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(paramString, String.valueOf(paramJSONObject.get(paramString)));
    return localHashMap;
  }
  
  public static Map<String, String> convertJSONObjectToMap(List<String> paramList, JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      localHashMap.put(str, String.valueOf(paramJSONObject.get(str)));
    }
    return localHashMap;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/client/util/RestParserHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */