package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class m
  extends aj
{
  private static final String ID = a.A.toString();
  private static final String VALUE = b.ff.toString();
  
  public m()
  {
    super(ID, new String[] { VALUE });
  }
  
  public static String nQ()
  {
    return ID;
  }
  
  public static String nR()
  {
    return VALUE;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    return (d.a)paramMap.get(VALUE);
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */