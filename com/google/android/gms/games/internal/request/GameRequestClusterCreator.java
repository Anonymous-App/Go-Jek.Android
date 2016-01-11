package com.google.android.gms.games.internal.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.request.GameRequestEntity;
import java.util.ArrayList;

public class GameRequestClusterCreator
  implements Parcelable.Creator<GameRequestCluster>
{
  static void a(GameRequestCluster paramGameRequestCluster, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramGameRequestCluster.lw(), false);
    b.c(paramParcel, 1000, paramGameRequestCluster.getVersionCode());
    b.H(paramParcel, paramInt);
  }
  
  public GameRequestCluster ck(Parcel paramParcel)
  {
    int j = a.C(paramParcel);
    int i = 0;
    ArrayList localArrayList = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        localArrayList = a.c(paramParcel, k, GameRequestEntity.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new GameRequestCluster(i, localArrayList);
  }
  
  public GameRequestCluster[] dQ(int paramInt)
  {
    return new GameRequestCluster[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/request/GameRequestClusterCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */