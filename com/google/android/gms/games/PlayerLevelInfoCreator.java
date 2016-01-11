package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class PlayerLevelInfoCreator
  implements Parcelable.Creator<PlayerLevelInfo>
{
  public static final int CONTENT_DESCRIPTION = 0;
  
  static void a(PlayerLevelInfo paramPlayerLevelInfo, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramPlayerLevelInfo.getCurrentXpTotal());
    b.c(paramParcel, 1000, paramPlayerLevelInfo.getVersionCode());
    b.a(paramParcel, 2, paramPlayerLevelInfo.getLastLevelUpTimestamp());
    b.a(paramParcel, 3, paramPlayerLevelInfo.getCurrentLevel(), paramInt, false);
    b.a(paramParcel, 4, paramPlayerLevelInfo.getNextLevel(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public PlayerLevelInfo createFromParcel(Parcel paramParcel)
  {
    long l1 = 0L;
    PlayerLevel localPlayerLevel1 = null;
    int j = a.C(paramParcel);
    int i = 0;
    PlayerLevel localPlayerLevel2 = null;
    long l2 = 0L;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        l2 = a.i(paramParcel, k);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        l1 = a.i(paramParcel, k);
        break;
      case 3: 
        localPlayerLevel2 = (PlayerLevel)a.a(paramParcel, k, PlayerLevel.CREATOR);
        break;
      case 4: 
        localPlayerLevel1 = (PlayerLevel)a.a(paramParcel, k, PlayerLevel.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new PlayerLevelInfo(i, l2, l1, localPlayerLevel2, localPlayerLevel1);
  }
  
  public PlayerLevelInfo[] newArray(int paramInt)
  {
    return new PlayerLevelInfo[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/PlayerLevelInfoCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */