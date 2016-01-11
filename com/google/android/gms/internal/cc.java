package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

@ez
public class cc
  implements by
{
  static final Map<String, Integer> pK = new HashMap();
  
  static
  {
    pK.put("resize", Integer.valueOf(1));
    pK.put("playVideo", Integer.valueOf(2));
    pK.put("storePicture", Integer.valueOf(3));
    pK.put("createCalendarEvent", Integer.valueOf(4));
  }
  
  public void a(gv paramgv, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("a");
    switch (((Integer)pK.get(str)).intValue())
    {
    case 2: 
    default: 
      gs.U("Unknown MRAID command called.");
      return;
    case 1: 
      new dd(paramgv, paramMap).execute();
      return;
    case 4: 
      new dc(paramgv, paramMap).execute();
      return;
    }
    new de(paramgv, paramMap).execute();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/cc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */