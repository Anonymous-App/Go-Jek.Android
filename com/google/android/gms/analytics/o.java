package com.google.android.gms.analytics;

import android.os.Build.VERSION;
import java.io.File;

class o
{
  static boolean ag(String paramString)
  {
    if (version() < 9) {
      return false;
    }
    paramString = new File(paramString);
    paramString.setReadable(false, false);
    paramString.setWritable(false, false);
    paramString.setReadable(true, true);
    paramString.setWritable(true, true);
    return true;
  }
  
  public static int version()
  {
    try
    {
      int i = Integer.parseInt(Build.VERSION.SDK);
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      z.T("Invalid version number: " + Build.VERSION.SDK);
    }
    return 0;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */