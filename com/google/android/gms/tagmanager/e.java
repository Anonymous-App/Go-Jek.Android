package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class e
  extends aj
{
  private static final String ID = a.Y.toString();
  private static final String anT = b.bW.toString();
  private static final String anU = b.bZ.toString();
  private final Context lB;
  
  public e(Context paramContext)
  {
    super(ID, new String[] { anU });
    this.lB = paramContext;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    Object localObject = (d.a)paramMap.get(anU);
    if (localObject == null) {
      return di.pK();
    }
    localObject = di.j((d.a)localObject);
    paramMap = (d.a)paramMap.get(anT);
    if (paramMap != null) {}
    for (paramMap = di.j(paramMap);; paramMap = null)
    {
      paramMap = ay.f(this.lB, (String)localObject, paramMap);
      if (paramMap == null) {
        break;
      }
      return di.u(paramMap);
    }
    return di.pK();
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */