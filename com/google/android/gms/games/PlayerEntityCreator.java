package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.internal.player.MostRecentGameInfoEntity;

public class PlayerEntityCreator
  implements Parcelable.Creator<PlayerEntity>
{
  static void a(PlayerEntity paramPlayerEntity, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramPlayerEntity.getPlayerId(), false);
    b.a(paramParcel, 2, paramPlayerEntity.getDisplayName(), false);
    b.a(paramParcel, 3, paramPlayerEntity.getIconImageUri(), paramInt, false);
    b.a(paramParcel, 4, paramPlayerEntity.getHiResImageUri(), paramInt, false);
    b.a(paramParcel, 5, paramPlayerEntity.getRetrievedTimestamp());
    b.c(paramParcel, 6, paramPlayerEntity.jU());
    b.a(paramParcel, 7, paramPlayerEntity.getLastPlayedWithTimestamp());
    b.a(paramParcel, 8, paramPlayerEntity.getIconImageUrl(), false);
    b.a(paramParcel, 9, paramPlayerEntity.getHiResImageUrl(), false);
    b.a(paramParcel, 14, paramPlayerEntity.getTitle(), false);
    b.a(paramParcel, 15, paramPlayerEntity.jV(), paramInt, false);
    b.a(paramParcel, 16, paramPlayerEntity.getLevelInfo(), paramInt, false);
    b.c(paramParcel, 1000, paramPlayerEntity.getVersionCode());
    b.a(paramParcel, 18, paramPlayerEntity.isProfileVisible());
    b.H(paramParcel, i);
  }
  
  public PlayerEntity ce(Parcel paramParcel)
  {
    int k = a.C(paramParcel);
    int j = 0;
    String str5 = null;
    String str4 = null;
    Uri localUri2 = null;
    Uri localUri1 = null;
    long l2 = 0L;
    int i = 0;
    long l1 = 0L;
    String str3 = null;
    String str2 = null;
    String str1 = null;
    MostRecentGameInfoEntity localMostRecentGameInfoEntity = null;
    PlayerLevelInfo localPlayerLevelInfo = null;
    boolean bool = false;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        str5 = a.o(paramParcel, m);
        break;
      case 2: 
        str4 = a.o(paramParcel, m);
        break;
      case 3: 
        localUri2 = (Uri)a.a(paramParcel, m, Uri.CREATOR);
        break;
      case 4: 
        localUri1 = (Uri)a.a(paramParcel, m, Uri.CREATOR);
        break;
      case 5: 
        l2 = a.i(paramParcel, m);
        break;
      case 6: 
        i = a.g(paramParcel, m);
        break;
      case 7: 
        l1 = a.i(paramParcel, m);
        break;
      case 8: 
        str3 = a.o(paramParcel, m);
        break;
      case 9: 
        str2 = a.o(paramParcel, m);
        break;
      case 14: 
        str1 = a.o(paramParcel, m);
        break;
      case 15: 
        localMostRecentGameInfoEntity = (MostRecentGameInfoEntity)a.a(paramParcel, m, MostRecentGameInfoEntity.CREATOR);
        break;
      case 16: 
        localPlayerLevelInfo = (PlayerLevelInfo)a.a(paramParcel, m, PlayerLevelInfo.CREATOR);
        break;
      case 1000: 
        j = a.g(paramParcel, m);
        break;
      case 18: 
        bool = a.c(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new PlayerEntity(j, str5, str4, localUri2, localUri1, l2, i, l1, str3, str2, str1, localMostRecentGameInfoEntity, localPlayerLevelInfo, bool);
  }
  
  public PlayerEntity[] dw(int paramInt)
  {
    return new PlayerEntity[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/PlayerEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */