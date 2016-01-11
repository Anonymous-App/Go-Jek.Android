package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Locale;
import java.util.Map;

class bc
  extends aj
{
  private static final String ID = a.N.toString();
  
  public bc()
  {
    super(ID, new String[0]);
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    paramMap = Locale.getDefault();
    if (paramMap == null) {
      return di.pK();
    }
    paramMap = paramMap.getLanguage();
    if (paramMap == null) {
      return di.pK();
    }
    return di.u(paramMap.toLowerCase());
  }
  
  public boolean nN()
  {
    return false;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/bc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */