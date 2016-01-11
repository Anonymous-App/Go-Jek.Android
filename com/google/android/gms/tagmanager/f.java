package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class f
  extends aj
{
  private static final String ID = a.w.toString();
  private final Context mContext;
  
  public f(Context paramContext)
  {
    super(ID, new String[0]);
    this.mContext = paramContext;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    return di.u(this.mContext.getPackageName());
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */