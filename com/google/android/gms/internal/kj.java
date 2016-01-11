package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class kj
{
  private static final List<TimeUnit> Sv = Arrays.asList(new TimeUnit[] { TimeUnit.NANOSECONDS, TimeUnit.MICROSECONDS, TimeUnit.MILLISECONDS, TimeUnit.SECONDS, TimeUnit.MINUTES, TimeUnit.HOURS, TimeUnit.DAYS });
  
  public static long a(long paramLong, TimeUnit paramTimeUnit1, TimeUnit paramTimeUnit2)
  {
    return paramTimeUnit1.convert(paramTimeUnit2.convert(paramLong, paramTimeUnit1), paramTimeUnit2);
  }
  
  public static boolean a(TimeUnit paramTimeUnit)
  {
    return a(paramTimeUnit, TimeUnit.MILLISECONDS);
  }
  
  private static boolean a(TimeUnit paramTimeUnit1, TimeUnit paramTimeUnit2)
  {
    return Sv.indexOf(paramTimeUnit1) < Sv.indexOf(paramTimeUnit2);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/kj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */