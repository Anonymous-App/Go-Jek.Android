package com.google.android.gms.wallet.fragment;

import android.util.DisplayMetrics;
import android.util.TypedValue;

public class Dimension
{
  public static final int MATCH_PARENT = -1;
  public static final int UNIT_DIP = 1;
  public static final int UNIT_IN = 4;
  public static final int UNIT_MM = 5;
  public static final int UNIT_PT = 3;
  public static final int UNIT_PX = 0;
  public static final int UNIT_SP = 2;
  public static final int WRAP_CONTENT = -2;
  
  public static int a(long paramLong, DisplayMetrics paramDisplayMetrics)
  {
    int i = (int)(paramLong >>> 32);
    int j = (int)paramLong;
    switch (i)
    {
    default: 
      throw new IllegalStateException("Unexpected unit or type: " + i);
    case 129: 
      return j;
    case 128: 
      return TypedValue.complexToDimensionPixelSize(j, paramDisplayMetrics);
    case 0: 
      i = 0;
    }
    for (;;)
    {
      return Math.round(TypedValue.applyDimension(i, Float.intBitsToFloat(j), paramDisplayMetrics));
      i = 1;
      continue;
      i = 2;
      continue;
      i = 3;
      continue;
      i = 4;
      continue;
      i = 5;
    }
  }
  
  public static long a(int paramInt, float paramFloat)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("Unrecognized unit: " + paramInt);
    }
    return o(paramInt, Float.floatToIntBits(paramFloat));
  }
  
  public static long a(TypedValue paramTypedValue)
  {
    switch (paramTypedValue.type)
    {
    default: 
      throw new IllegalArgumentException("Unexpected dimension type: " + paramTypedValue.type);
    case 16: 
      return fE(paramTypedValue.data);
    }
    return o(128, paramTypedValue.data);
  }
  
  public static long fE(int paramInt)
  {
    if (paramInt < 0)
    {
      if ((paramInt == -1) || (paramInt == -2)) {
        return o(129, paramInt);
      }
      throw new IllegalArgumentException("Unexpected dimension value: " + paramInt);
    }
    return a(0, paramInt);
  }
  
  private static long o(int paramInt1, int paramInt2)
  {
    return paramInt1 << 32 | paramInt2 & 0xFFFFFFFF;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/fragment/Dimension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */