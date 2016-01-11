package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class s
  extends aj
{
  private static final String ID = a.L.toString();
  private static final String anV = b.bl.toString();
  private static final String aoE = b.cV.toString();
  private final a aoF;
  
  public s(a parama)
  {
    super(ID, new String[] { aoE });
    this.aoF = parama;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    String str = di.j((d.a)paramMap.get(aoE));
    HashMap localHashMap = new HashMap();
    paramMap = (d.a)paramMap.get(anV);
    if (paramMap != null)
    {
      paramMap = di.o(paramMap);
      if (!(paramMap instanceof Map))
      {
        bh.W("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
        return di.pK();
      }
      paramMap = ((Map)paramMap).entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        localHashMap.put(localEntry.getKey().toString(), localEntry.getValue());
      }
    }
    try
    {
      paramMap = di.u(this.aoF.b(str, localHashMap));
      return paramMap;
    }
    catch (Exception paramMap)
    {
      bh.W("Custom macro/tag " + str + " threw exception " + paramMap.getMessage());
    }
    return di.pK();
  }
  
  public boolean nN()
  {
    return false;
  }
  
  public static abstract interface a
  {
    public abstract Object b(String paramString, Map<String, Object> paramMap);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/s.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */