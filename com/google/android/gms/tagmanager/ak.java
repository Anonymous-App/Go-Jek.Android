package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;
import java.io.File;

class ak
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
      bh.T("Invalid version number: " + Build.VERSION.SDK);
    }
    return 0;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/ak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */