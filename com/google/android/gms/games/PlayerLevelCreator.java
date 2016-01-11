package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class PlayerLevelCreator
  implements Parcelable.Creator<PlayerLevel>
{
  public static final int CONTENT_DESCRIPTION = 0;
  
  static void a(PlayerLevel paramPlayerLevel, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramPlayerLevel.getLevelNumber());
    b.c(paramParcel, 1000, paramPlayerLevel.getVersionCode());
    b.a(paramParcel, 2, paramPlayerLevel.getMinXp());
    b.a(paramParcel, 3, paramPlayerLevel.getMaxXp());
    b.H(paramParcel, paramInt);
  }
  
  public PlayerLevel createFromParcel(Parcel paramParcel)
  {
    long l1 = 0L;
    int i = 0;
    int k = a.C(paramParcel);
    long l2 = 0L;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        i = a.g(paramParcel, m);
        break;
      case 1000: 
        j = a.g(paramParcel, m);
        break;
      case 2: 
        l2 = a.i(paramParcel, m);
        break;
      case 3: 
        l1 = a.i(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new PlayerLevel(j, i, l2, l1);
  }
  
  public PlayerLevel[] newArray(int paramInt)
  {
    return new PlayerLevel[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/PlayerLevelCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */