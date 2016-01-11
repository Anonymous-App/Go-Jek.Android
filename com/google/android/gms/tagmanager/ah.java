package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ah
  extends aj
{
  private static final String ID = a.K.toString();
  private final ct aoe;
  
  public ah(ct paramct)
  {
    super(ID, new String[0]);
    this.aoe = paramct;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    paramMap = this.aoe.pn();
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/ah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */