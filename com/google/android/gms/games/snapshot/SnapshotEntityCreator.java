package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class SnapshotEntityCreator
  implements Parcelable.Creator<SnapshotEntity>
{
  public static final int CONTENT_DESCRIPTION = 0;
  
  static void a(SnapshotEntity paramSnapshotEntity, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramSnapshotEntity.getMetadata(), paramInt, false);
    b.c(paramParcel, 1000, paramSnapshotEntity.getVersionCode());
    b.a(paramParcel, 3, paramSnapshotEntity.getSnapshotContents(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public SnapshotEntity createFromParcel(Parcel paramParcel)
  {
    SnapshotContents localSnapshotContents = null;
    int j = a.C(paramParcel);
    int i = 0;
    SnapshotMetadataEntity localSnapshotMetadataEntity = null;
    if (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
      }
      for (;;)
      {
        break;
        localSnapshotMetadataEntity = (SnapshotMetadataEntity)a.a(paramParcel, k, SnapshotMetadataEntity.CREATOR);
        continue;
        i = a.g(paramParcel, k);
        continue;
        localSnapshotContents = (SnapshotContents)a.a(paramParcel, k, SnapshotContents.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new SnapshotEntity(i, localSnapshotMetadataEntity, localSnapshotContents);
  }
  
  public SnapshotEntity[] newArray(int paramInt)
  {
    return new SnapshotEntity[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/snapshot/SnapshotEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */