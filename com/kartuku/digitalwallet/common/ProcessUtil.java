package com.kartuku.digitalwallet.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ProcessUtil
{
  public static boolean getCvvStatus(Map<String, Object> paramMap)
  {
    return ((Boolean)paramMap.get("withCvv")).booleanValue();
  }
  
  public static String getProcessId(String paramString1, String paramString2)
  {
    String str = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    if ((paramString1 != null) && (!paramString1.isEmpty())) {
      return paramString1 + paramString2 + str;
    }
    return paramString2 + str;
  }
  
  public static String getProcessId(Map<String, Object> paramMap)
  {
    if (paramMap.containsKey("processId")) {
      return paramMap.get("processId").toString();
    }
    return "";
  }
  
  public static String getProcessIdArgs(String... paramVarArgs)
  {
    if ((paramVarArgs != null) && (paramVarArgs.length > 0)) {
      return paramVarArgs[0];
    }
    return "";
  }
  
  public static String getProductNameArgs(String... paramVarArgs)
  {
    if ((paramVarArgs != null) && (paramVarArgs.length > 1)) {
      return paramVarArgs[2];
    }
    return "";
  }
  
  public static String getProductType(Map<String, Object> paramMap)
  {
    paramMap = paramMap.get("productType");
    if (paramMap == null) {
      return "";
    }
    return paramMap.toString();
  }
  
  public static String getProductTypeArgs(String... paramVarArgs)
  {
    if ((paramVarArgs != null) && (paramVarArgs.length > 1)) {
      return paramVarArgs[1];
    }
    return "";
  }
  
  public static String getSecurityToken(Map<String, Object> paramMap)
  {
    paramMap = paramMap.get("securityToken").toString();
    if (paramMap == null) {
      return "";
    }
    return paramMap.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/ProcessUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */