package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ca
  extends aj
{
  private static final String ID = a.O.toString();
  
  public ca()
  {
    super(ID, new String[0]);
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    return di.u(Build.VERSION.RELEASE);
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/ca.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */