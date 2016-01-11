package com.google.android.gms.games.internal.game;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class GameBadgeEntityCreator
  implements Parcelable.Creator<GameBadgeEntity>
{
  static void a(GameBadgeEntity paramGameBadgeEntity, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramGameBadgeEntity.getType());
    b.c(paramParcel, 1000, paramGameBadgeEntity.getVersionCode());
    b.a(paramParcel, 2, paramGameBadgeEntity.getTitle(), false);
    b.a(paramParcel, 3, paramGameBadgeEntity.getDescription(), false);
    b.a(paramParcel, 4, paramGameBadgeEntity.getIconImageUri(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public GameBadgeEntity ch(Parcel paramParcel)
  {
    int i = 0;
    Uri localUri = null;
    int k = a.C(paramParcel);
    String str1 = null;
    String str2 = null;
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
        str2 = a.o(paramParcel, m);
        break;
      case 3: 
        str1 = a.o(paramParcel, m);
        break;
      case 4: 
        localUri = (Uri)a.a(paramParcel, m, Uri.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new GameBadgeEntity(j, i, str2, str1, localUri);
  }
  
  public GameBadgeEntity[] dL(int paramInt)
  {
    return new GameBadgeEntity[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/game/GameBadgeEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */