package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.d;
import com.google.android.gms.internal.jx;

public abstract class GamesDowngradeableSafeParcel
  extends d
{
  protected static boolean c(Integer paramInteger)
  {
    if (paramInteger == null) {
      return false;
    }
    return jx.aQ(paramInteger.intValue());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/GamesDowngradeableSafeParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */