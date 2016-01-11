package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;
import java.util.ArrayList;

public class GameRequestEntityCreator
  implements Parcelable.Creator<GameRequestEntity>
{
  public static final int CONTENT_DESCRIPTION = 0;
  
  static void a(GameRequestEntity paramGameRequestEntity, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramGameRequestEntity.getGame(), paramInt, false);
    b.c(paramParcel, 1000, paramGameRequestEntity.getVersionCode());
    b.a(paramParcel, 2, paramGameRequestEntity.getSender(), paramInt, false);
    b.a(paramParcel, 3, paramGameRequestEntity.getData(), false);
    b.a(paramParcel, 4, paramGameRequestEntity.getRequestId(), false);
    b.c(paramParcel, 5, paramGameRequestEntity.getRecipients(), false);
    b.c(paramParcel, 7, paramGameRequestEntity.getType());
    b.a(paramParcel, 9, paramGameRequestEntity.getCreationTimestamp());
    b.a(paramParcel, 10, paramGameRequestEntity.getExpirationTimestamp());
    b.a(paramParcel, 11, paramGameRequestEntity.lL(), false);
    b.c(paramParcel, 12, paramGameRequestEntity.getStatus());
    b.H(paramParcel, i);
  }
  
  public GameRequestEntity createFromParcel(Parcel paramParcel)
  {
    int m = a.C(paramParcel);
    int k = 0;
    GameEntity localGameEntity = null;
    PlayerEntity localPlayerEntity = null;
    byte[] arrayOfByte = null;
    String str = null;
    ArrayList localArrayList = null;
    int j = 0;
    long l2 = 0L;
    long l1 = 0L;
    Bundle localBundle = null;
    int i = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        localGameEntity = (GameEntity)a.a(paramParcel, n, GameEntity.CREATOR);
        break;
      case 1000: 
        k = a.g(paramParcel, n);
        break;
      case 2: 
        localPlayerEntity = (PlayerEntity)a.a(paramParcel, n, PlayerEntity.CREATOR);
        break;
      case 3: 
        arrayOfByte = a.r(paramParcel, n);
        break;
      case 4: 
        str = a.o(paramParcel, n);
        break;
      case 5: 
        localArrayList = a.c(paramParcel, n, PlayerEntity.CREATOR);
        break;
      case 7: 
        j = a.g(paramParcel, n);
        break;
      case 9: 
        l2 = a.i(paramParcel, n);
        break;
      case 10: 
        l1 = a.i(paramParcel, n);
        break;
      case 11: 
        localBundle = a.q(paramParcel, n);
        break;
      case 12: 
        i = a.g(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new GameRequestEntity(k, localGameEntity, localPlayerEntity, arrayOfByte, str, localArrayList, j, l2, l1, localBundle, i);
  }
  
  public GameRequestEntity[] newArray(int paramInt)
  {
    return new GameRequestEntity[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/request/GameRequestEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */