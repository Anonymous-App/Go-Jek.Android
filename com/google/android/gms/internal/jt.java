package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.regex.Pattern;

public final class jt
{
  private static Pattern MR = null;
  
  public static boolean K(Context paramContext)
  {
    return paramContext.getPackageManager().hasSystemFeature("android.hardware.type.watch");
  }
  
  public static int aN(int paramInt)
  {
    return paramInt / 1000;
  }
  
  public static int aO(int paramInt)
  {
    return paramInt % 1000 / 100;
  }
  
  public static boolean aP(int paramInt)
  {
    return aO(paramInt) == 3;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/jt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */