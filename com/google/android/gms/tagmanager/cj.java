package com.google.android.gms.tagmanager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class cj
  extends aj
{
  private static final String ID = a.S.toString();
  private final Context mContext;
  
  public cj(Context paramContext)
  {
    super(ID, new String[0]);
    this.mContext = paramContext;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    paramMap = new DisplayMetrics();
    ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics(paramMap);
    int i = paramMap.widthPixels;
    int j = paramMap.heightPixels;
    return di.u(i + "x" + j);
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/cj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */