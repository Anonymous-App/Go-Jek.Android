package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class u
  extends aj
{
  private static final String ID = a.C.toString();
  private static final String NAME = b.dB.toString();
  private static final String aoP = b.cr.toString();
  private final DataLayer aod;
  
  public u(DataLayer paramDataLayer)
  {
    super(ID, new String[] { NAME });
    this.aod = paramDataLayer;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    Object localObject = this.aod.get(di.j((d.a)paramMap.get(NAME)));
    if (localObject == null)
    {
      paramMap = (d.a)paramMap.get(aoP);
      if (paramMap != null) {
        return paramMap;
      }
      return di.pK();
    }
    return di.u(localObject);
  }
  
  public boolean nN()
  {
    return false;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/u.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */