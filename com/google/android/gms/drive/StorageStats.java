package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class StorageStats
  implements SafeParcelable
{
  public static final Parcelable.Creator<StorageStats> CREATOR = new g();
  final int BR;
  final long NB;
  final long NC;
  final long ND;
  final long NE;
  final int NF;
  
  StorageStats(int paramInt1, long paramLong1, long paramLong2, long paramLong3, long paramLong4, int paramInt2)
  {
    this.BR = paramInt1;
    this.NB = paramLong1;
    this.NC = paramLong2;
    this.ND = paramLong3;
    this.NE = paramLong4;
    this.NF = paramInt2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    g.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/StorageStats.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */