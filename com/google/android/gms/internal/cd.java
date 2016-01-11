package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@ez
public final class cd
  implements by
{
  private final bz pL;
  private final v pM;
  
  public cd(bz parambz, v paramv)
  {
    this.pL = parambz;
    this.pM = paramv;
  }
  
  private static boolean b(Map<String, String> paramMap)
  {
    return "1".equals(paramMap.get("custom_close"));
  }
  
  private static int c(Map<String, String> paramMap)
  {
    paramMap = (String)paramMap.get("o");
    if (paramMap != null)
    {
      if ("p".equalsIgnoreCase(paramMap)) {
        return gj.dm();
      }
      if ("l".equalsIgnoreCase(paramMap)) {
        return gj.dl();
      }
    }
    return -1;
  }
  
  public void a(gv paramgv, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("a");
    if (str == null) {
      gs.W("Action missing from an open GMSG.");
    }
    gw localgw;
    do
    {
      return;
      if ((this.pM != null) && (!this.pM.av()))
      {
        this.pM.d((String)paramMap.get("u"));
        return;
      }
      localgw = paramgv.du();
      if ("expand".equalsIgnoreCase(str))
      {
        if (paramgv.dy())
        {
          gs.W("Cannot expand WebView that is already expanded.");
          return;
        }
        localgw.a(b(paramMap), c(paramMap));
        return;
      }
      if ("webapp".equalsIgnoreCase(str))
      {
        paramgv = (String)paramMap.get("u");
        if (paramgv != null)
        {
          localgw.a(b(paramMap), c(paramMap), paramgv);
          return;
        }
        localgw.a(b(paramMap), c(paramMap), (String)paramMap.get("html"), (String)paramMap.get("baseurl"));
        return;
      }
      if (!"in_app_purchase".equalsIgnoreCase(str)) {
        break;
      }
      paramgv = (String)paramMap.get("product_id");
      paramMap = (String)paramMap.get("report_urls");
    } while (this.pL == null);
    if ((paramMap != null) && (!paramMap.isEmpty()))
    {
      paramMap = paramMap.split(" ");
      this.pL.a(paramgv, new ArrayList(Arrays.asList(paramMap)));
      return;
    }
    this.pL.a(paramgv, new ArrayList());
    return;
    localgw.a(new dj((String)paramMap.get("i"), (String)paramMap.get("u"), (String)paramMap.get("m"), (String)paramMap.get("p"), (String)paramMap.get("c"), (String)paramMap.get("f"), (String)paramMap.get("e")));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/cd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */