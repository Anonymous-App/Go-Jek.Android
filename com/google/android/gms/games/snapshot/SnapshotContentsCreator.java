package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;

public class SnapshotContentsCreator
  implements Parcelable.Creator<SnapshotContents>
{
  public static final int CONTENT_DESCRIPTION = 0;
  
  static void a(SnapshotContents paramSnapshotContents, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramSnapshotContents.getContents(), paramInt, false);
    b.c(paramParcel, 1000, paramSnapshotContents.getVersionCode());
    b.H(paramParcel, i);
  }
  
  public SnapshotContents createFromParcel(Parcel paramParcel)
  {
    int j = a.C(paramParcel);
    int i = 0;
    Contents localContents = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        localContents = (Contents)a.a(paramParcel, k, Contents.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new SnapshotContents(i, localContents);
  }
  
  public SnapshotContents[] newArray(int paramInt)
  {
    return new SnapshotContents[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/snapshot/SnapshotContentsCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */