package com.google.android.gms.internal;

import android.os.Build.VERSION;

public final class kc
{
  private static boolean aR(int paramInt)
  {
    return Build.VERSION.SDK_INT >= paramInt;
  }
  
  public static boolean hB()
  {
    return aR(11);
  }
  
  public static boolean hC()
  {
    return aR(12);
  }
  
  public static boolean hD()
  {
    return aR(13);
  }
  
  public static boolean hE()
  {
    return aR(14);
  }
  
  public static boolean hF()
  {
    return aR(16);
  }
  
  public static boolean hG()
  {
    return aR(17);
  }
  
  @Deprecated
  public static boolean hH()
  {
    return hI();
  }
  
  public static boolean hI()
  {
    return aR(19);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/kc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */