package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class b
  extends aj
{
  private static final String ID = com.google.android.gms.internal.a.u.toString();
  private final a anS;
  
  public b(Context paramContext)
  {
    this(a.W(paramContext));
  }
  
  b(a parama)
  {
    super(ID, new String[0]);
    this.anS = parama;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    paramMap = this.anS.nJ();
    if (paramMap == null) {
      return di.pK();
    }
    return di.u(paramMap);
  }
  
  public boolean nN()
  {
    return false;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */