package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class p
  extends aj
{
  private static final String ID = a.D.toString();
  private final String Sx;
  
  public p(String paramString)
  {
    super(ID, new String[0]);
    this.Sx = paramString;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    if (this.Sx == null) {
      return di.pK();
    }
    return di.u(this.Sx);
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */