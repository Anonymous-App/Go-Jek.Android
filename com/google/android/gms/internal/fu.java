package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@ez
public final class fu
{
  private int mOrientation = -1;
  private String pn;
  private List<String> uA;
  private long uB = -1L;
  private boolean uC = false;
  private final long uD = -1L;
  private long uE = -1L;
  private boolean uF = false;
  private boolean uG = false;
  private boolean uH = false;
  private boolean uI = false;
  private List<String> ua;
  private String uv;
  private String uw;
  private List<String> ux;
  private String uy;
  private String uz;
  
  static String a(Map<String, List<String>> paramMap, String paramString)
  {
    paramMap = (List)paramMap.get(paramString);
    if ((paramMap != null) && (!paramMap.isEmpty())) {
      return (String)paramMap.get(0);
    }
    return null;
  }
  
  static long b(Map<String, List<String>> paramMap, String paramString)
  {
    paramMap = (List)paramMap.get(paramString);
    if ((paramMap != null) && (!paramMap.isEmpty()))
    {
      paramMap = (String)paramMap.get(0);
      try
      {
        float f = Float.parseFloat(paramMap);
        return (f * 1000.0F);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        gs.W("Could not parse float from " + paramString + " header: " + paramMap);
      }
    }
    return -1L;
  }
  
  static List<String> c(Map<String, List<String>> paramMap, String paramString)
  {
    paramMap = (List)paramMap.get(paramString);
    if ((paramMap != null) && (!paramMap.isEmpty()))
    {
      paramMap = (String)paramMap.get(0);
      if (paramMap != null) {
        return Arrays.asList(paramMap.trim().split("\\s+"));
      }
    }
    return null;
  }
  
  private boolean d(Map<String, List<String>> paramMap, String paramString)
  {
    paramMap = (List)paramMap.get(paramString);
    return (paramMap != null) && (!paramMap.isEmpty()) && (Boolean.valueOf((String)paramMap.get(0)).booleanValue());
  }
  
  private void f(Map<String, List<String>> paramMap)
  {
    this.uv = a(paramMap, "X-Afma-Ad-Size");
  }
  
  private void g(Map<String, List<String>> paramMap)
  {
    paramMap = c(paramMap, "X-Afma-Click-Tracking-Urls");
    if (paramMap != null) {
      this.ux = paramMap;
    }
  }
  
  private void h(Map<String, List<String>> paramMap)
  {
    paramMap = (List)paramMap.get("X-Afma-Debug-Dialog");
    if ((paramMap != null) && (!paramMap.isEmpty())) {
      this.uy = ((String)paramMap.get(0));
    }
  }
  
  private void i(Map<String, List<String>> paramMap)
  {
    paramMap = c(paramMap, "X-Afma-Tracking-Urls");
    if (paramMap != null) {
      this.uA = paramMap;
    }
  }
  
  private void j(Map<String, List<String>> paramMap)
  {
    long l = b(paramMap, "X-Afma-Interstitial-Timeout");
    if (l != -1L) {
      this.uB = l;
    }
  }
  
  private void k(Map<String, List<String>> paramMap)
  {
    this.uz = a(paramMap, "X-Afma-ActiveView");
  }
  
  private void l(Map<String, List<String>> paramMap)
  {
    this.uG |= d(paramMap, "X-Afma-Native");
  }
  
  private void m(Map<String, List<String>> paramMap)
  {
    this.uF |= d(paramMap, "X-Afma-Custom-Rendering-Allowed");
  }
  
  private void n(Map<String, List<String>> paramMap)
  {
    this.uC |= d(paramMap, "X-Afma-Mediation");
  }
  
  private void o(Map<String, List<String>> paramMap)
  {
    paramMap = c(paramMap, "X-Afma-Manual-Tracking-Urls");
    if (paramMap != null) {
      this.ua = paramMap;
    }
  }
  
  private void p(Map<String, List<String>> paramMap)
  {
    long l = b(paramMap, "X-Afma-Refresh-Rate");
    if (l != -1L) {
      this.uE = l;
    }
  }
  
  private void q(Map<String, List<String>> paramMap)
  {
    paramMap = (List)paramMap.get("X-Afma-Orientation");
    if ((paramMap != null) && (!paramMap.isEmpty()))
    {
      paramMap = (String)paramMap.get(0);
      if (!"portrait".equalsIgnoreCase(paramMap)) {
        break label53;
      }
      this.mOrientation = gj.dm();
    }
    label53:
    while (!"landscape".equalsIgnoreCase(paramMap)) {
      return;
    }
    this.mOrientation = gj.dl();
  }
  
  private void r(Map<String, List<String>> paramMap)
  {
    paramMap = (List)paramMap.get("X-Afma-Use-HTTPS");
    if ((paramMap != null) && (!paramMap.isEmpty())) {
      this.uH = Boolean.valueOf((String)paramMap.get(0)).booleanValue();
    }
  }
  
  private void s(Map<String, List<String>> paramMap)
  {
    paramMap = (List)paramMap.get("X-Afma-Content-Url-Opted-Out");
    if ((paramMap != null) && (!paramMap.isEmpty())) {
      this.uI = Boolean.valueOf((String)paramMap.get(0)).booleanValue();
    }
  }
  
  public void a(String paramString1, Map<String, List<String>> paramMap, String paramString2)
  {
    this.uw = paramString1;
    this.pn = paramString2;
    e(paramMap);
  }
  
  public void e(Map<String, List<String>> paramMap)
  {
    f(paramMap);
    g(paramMap);
    h(paramMap);
    i(paramMap);
    j(paramMap);
    n(paramMap);
    o(paramMap);
    p(paramMap);
    q(paramMap);
    k(paramMap);
    r(paramMap);
    m(paramMap);
    l(paramMap);
    s(paramMap);
  }
  
  public fk i(long paramLong)
  {
    return new fk(this.uw, this.pn, this.ux, this.uA, this.uB, this.uC, -1L, this.ua, this.uE, this.mOrientation, this.uv, paramLong, this.uy, this.uz, this.uF, this.uG, this.uH, this.uI);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/fu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */