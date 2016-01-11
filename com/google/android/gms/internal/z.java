package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

@ez
class z
  implements ac
{
  private gv mi;
  
  public z(gv paramgv)
  {
    this.mi = paramgv;
  }
  
  public void a(af paramaf, boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    if (paramBoolean) {}
    for (paramaf = "1";; paramaf = "0")
    {
      localHashMap.put("isVisible", paramaf);
      this.mi.a("onAdVisibilityChanged", localHashMap);
      return;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/z.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */