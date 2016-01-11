package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

abstract class cd
  extends aj
{
  private static final String apf = b.bw.toString();
  private static final String aqb = b.bx.toString();
  
  public cd(String paramString)
  {
    super(paramString, new String[] { apf, aqb });
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    Object localObject = paramMap.values().iterator();
    while (((Iterator)localObject).hasNext()) {
      if ((d.a)((Iterator)localObject).next() == di.pK()) {
        return di.u(Boolean.valueOf(false));
      }
    }
    localObject = (d.a)paramMap.get(apf);
    d.a locala = (d.a)paramMap.get(aqb);
    if ((localObject == null) || (locala == null)) {}
    for (boolean bool = false;; bool = a((d.a)localObject, locala, paramMap)) {
      return di.u(Boolean.valueOf(bool));
    }
  }
  
  protected abstract boolean a(d.a parama1, d.a parama2, Map<String, d.a> paramMap);
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/cd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */