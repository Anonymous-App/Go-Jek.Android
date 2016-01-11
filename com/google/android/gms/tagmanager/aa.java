package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class aa
  extends aj
{
  private static final String ID = a.F.toString();
  
  public aa()
  {
    super(ID, new String[0]);
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    String str2 = Build.MANUFACTURER;
    String str1 = Build.MODEL;
    paramMap = str1;
    if (!str1.startsWith(str2))
    {
      paramMap = str1;
      if (!str2.equals("unknown")) {
        paramMap = str2 + " " + str1;
      }
    }
    return di.u(paramMap);
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/aa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */