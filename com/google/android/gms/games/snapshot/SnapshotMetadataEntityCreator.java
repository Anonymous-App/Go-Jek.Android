package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;

public class SnapshotMetadataEntityCreator
  implements Parcelable.Creator<SnapshotMetadataEntity>
{
  public static final int CONTENT_DESCRIPTION = 0;
  
  static void a(SnapshotMetadataEntity paramSnapshotMetadataEntity, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramSnapshotMetadataEntity.getGame(), paramInt, false);
    b.c(paramParcel, 1000, paramSnapshotMetadataEntity.getVersionCode());
    b.a(paramParcel, 2, paramSnapshotMetadataEntity.getOwner(), paramInt, false);
    b.a(paramParcel, 3, paramSnapshotMetadataEntity.getSnapshotId(), false);
    b.a(paramParcel, 5, paramSnapshotMetadataEntity.getCoverImageUri(), paramInt, false);
    b.a(paramParcel, 6, paramSnapshotMetadataEntity.getCoverImageUrl(), false);
    b.a(paramParcel, 7, paramSnapshotMetadataEntity.getTitle(), false);
    b.a(paramParcel, 8, paramSnapshotMetadataEntity.getDescription(), false);
    b.a(paramParcel, 9, paramSnapshotMetadataEntity.getLastModifiedTimestamp());
    b.a(paramParcel, 10, paramSnapshotMetadataEntity.getPlayedTime());
    b.a(paramParcel, 11, paramSnapshotMetadataEntity.getCoverImageAspectRatio());
    b.a(paramParcel, 12, paramSnapshotMetadataEntity.getUniqueName(), false);
    b.H(paramParcel, i);
  }
  
  public SnapshotMetadataEntity createFromParcel(Parcel paramParcel)
  {
    int j = a.C(paramParcel);
    int i = 0;
    GameEntity localGameEntity = null;
    PlayerEntity localPlayerEntity = null;
    String str5 = null;
    Uri localUri = null;
    String str4 = null;
    String str3 = null;
    String str2 = null;
    long l2 = 0L;
    long l1 = 0L;
    float f = 0.0F;
    String str1 = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        localGameEntity = (GameEntity)a.a(paramParcel, k, GameEntity.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        localPlayerEntity = (PlayerEntity)a.a(paramParcel, k, PlayerEntity.CREATOR);
        break;
      case 3: 
        str5 = a.o(paramParcel, k);
        break;
      case 5: 
        localUri = (Uri)a.a(paramParcel, k, Uri.CREATOR);
        break;
      case 6: 
        str4 = a.o(paramParcel, k);
        break;
      case 7: 
        str3 = a.o(paramParcel, k);
        break;
      case 8: 
        str2 = a.o(paramParcel, k);
        break;
      case 9: 
        l2 = a.i(paramParcel, k);
        break;
      case 10: 
        l1 = a.i(paramParcel, k);
        break;
      case 11: 
        f = a.l(paramParcel, k);
        break;
      case 12: 
        str1 = a.o(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new SnapshotMetadataEntity(i, localGameEntity, localPlayerEntity, str5, localUri, str4, str3, str2, l2, l1, f, str1);
  }
  
  public SnapshotMetadataEntity[] newArray(int paramInt)
  {
    return new SnapshotMetadataEntity[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/snapshot/SnapshotMetadataEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */