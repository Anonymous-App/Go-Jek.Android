package com.google.android.gms.games.multiplayer;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.PlayerEntity;

public class ParticipantEntityCreator
  implements Parcelable.Creator<ParticipantEntity>
{
  static void a(ParticipantEntity paramParticipantEntity, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramParticipantEntity.getParticipantId(), false);
    b.c(paramParcel, 1000, paramParticipantEntity.getVersionCode());
    b.a(paramParcel, 2, paramParticipantEntity.getDisplayName(), false);
    b.a(paramParcel, 3, paramParticipantEntity.getIconImageUri(), paramInt, false);
    b.a(paramParcel, 4, paramParticipantEntity.getHiResImageUri(), paramInt, false);
    b.c(paramParcel, 5, paramParticipantEntity.getStatus());
    b.a(paramParcel, 6, paramParticipantEntity.jX(), false);
    b.a(paramParcel, 7, paramParticipantEntity.isConnectedToRoom());
    b.a(paramParcel, 8, paramParticipantEntity.getPlayer(), paramInt, false);
    b.c(paramParcel, 9, paramParticipantEntity.getCapabilities());
    b.a(paramParcel, 10, paramParticipantEntity.getResult(), paramInt, false);
    b.a(paramParcel, 11, paramParticipantEntity.getIconImageUrl(), false);
    b.a(paramParcel, 12, paramParticipantEntity.getHiResImageUrl(), false);
    b.H(paramParcel, i);
  }
  
  public ParticipantEntity cm(Parcel paramParcel)
  {
    int m = a.C(paramParcel);
    int k = 0;
    String str5 = null;
    String str4 = null;
    Uri localUri2 = null;
    Uri localUri1 = null;
    int j = 0;
    String str3 = null;
    boolean bool = false;
    PlayerEntity localPlayerEntity = null;
    int i = 0;
    ParticipantResult localParticipantResult = null;
    String str2 = null;
    String str1 = null;
    while (paramParcel.dataPosition() < m)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        str5 = a.o(paramParcel, n);
        break;
      case 1000: 
        k = a.g(paramParcel, n);
        break;
      case 2: 
        str4 = a.o(paramParcel, n);
        break;
      case 3: 
        localUri2 = (Uri)a.a(paramParcel, n, Uri.CREATOR);
        break;
      case 4: 
        localUri1 = (Uri)a.a(paramParcel, n, Uri.CREATOR);
        break;
      case 5: 
        j = a.g(paramParcel, n);
        break;
      case 6: 
        str3 = a.o(paramParcel, n);
        break;
      case 7: 
        bool = a.c(paramParcel, n);
        break;
      case 8: 
        localPlayerEntity = (PlayerEntity)a.a(paramParcel, n, PlayerEntity.CREATOR);
        break;
      case 9: 
        i = a.g(paramParcel, n);
        break;
      case 10: 
        localParticipantResult = (ParticipantResult)a.a(paramParcel, n, ParticipantResult.CREATOR);
        break;
      case 11: 
        str2 = a.o(paramParcel, n);
        break;
      case 12: 
        str1 = a.o(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new ParticipantEntity(k, str5, str4, localUri2, localUri1, j, str3, bool, localPlayerEntity, i, localParticipantResult, str2, str1);
  }
  
  public ParticipantEntity[] dT(int paramInt)
  {
    return new ParticipantEntity[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/multiplayer/ParticipantEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */