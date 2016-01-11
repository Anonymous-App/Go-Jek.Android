package com.google.android.gms.analytics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class aa
{
  private final Map<String, Integer> AU = new HashMap();
  private final Map<String, String> AV = new HashMap();
  private final boolean AW;
  private final String AX;
  
  aa(String paramString, boolean paramBoolean)
  {
    this.AW = paramBoolean;
    this.AX = paramString;
  }
  
  void e(String paramString, int paramInt)
  {
    if (!this.AW) {
      return;
    }
    Integer localInteger2 = (Integer)this.AU.get(paramString);
    Integer localInteger1 = localInteger2;
    if (localInteger2 == null) {
      localInteger1 = Integer.valueOf(0);
    }
    this.AU.put(paramString, Integer.valueOf(localInteger1.intValue() + paramInt));
  }
  
  String eL()
  {
    if (!this.AW) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.AX);
    Iterator localIterator = this.AU.keySet().iterator();
    String str;
    while (localIterator.hasNext())
    {
      str = (String)localIterator.next();
      localStringBuilder.append("&").append(str).append("=").append(this.AU.get(str));
    }
    localIterator = this.AV.keySet().iterator();
    while (localIterator.hasNext())
    {
      str = (String)localIterator.next();
      localStringBuilder.append("&").append(str).append("=").append((String)this.AV.get(str));
    }
    return localStringBuilder.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/aa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */