package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings.Secure;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class bl
  extends aj
{
  private static final String ID = a.af.toString();
  private final Context mContext;
  
  public bl(Context paramContext)
  {
    super(ID, new String[0]);
    this.mContext = paramContext;
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    paramMap = Y(this.mContext);
    if (paramMap == null) {
      return di.pK();
    }
    return di.u(paramMap);
  }
  
  protected String Y(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/bl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */