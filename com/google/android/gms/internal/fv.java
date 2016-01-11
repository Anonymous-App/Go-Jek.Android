package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@ez
class fv
{
  private int tc;
  private final List<String> uJ;
  private final List<String> uK;
  private final String uL;
  private final String uM;
  private final String uN;
  private final String uO;
  private final boolean uP;
  private final int uQ;
  private String uR;
  
  public fv(int paramInt, Map<String, String> paramMap)
  {
    this.uR = ((String)paramMap.get("url"));
    this.uM = ((String)paramMap.get("base_uri"));
    this.uN = ((String)paramMap.get("post_parameters"));
    this.uP = parseBoolean((String)paramMap.get("drt_include"));
    this.uL = ((String)paramMap.get("activation_overlay_url"));
    this.uK = J((String)paramMap.get("check_packages"));
    this.uQ = parseInt((String)paramMap.get("request_id"));
    this.uO = ((String)paramMap.get("type"));
    this.uJ = J((String)paramMap.get("errors"));
    this.tc = paramInt;
  }
  
  private List<String> J(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Arrays.asList(paramString.split(","));
  }
  
  private static boolean parseBoolean(String paramString)
  {
    return (paramString != null) && ((paramString.equals("1")) || (paramString.equals("true")));
  }
  
  private int parseInt(String paramString)
  {
    if (paramString == null) {
      return 0;
    }
    return Integer.parseInt(paramString);
  }
  
  public List<String> cL()
  {
    return this.uJ;
  }
  
  public String cM()
  {
    return this.uN;
  }
  
  public boolean cN()
  {
    return this.uP;
  }
  
  public int getErrorCode()
  {
    return this.tc;
  }
  
  public String getType()
  {
    return this.uO;
  }
  
  public String getUrl()
  {
    return this.uR;
  }
  
  public void setUrl(String paramString)
  {
    this.uR = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/fv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */