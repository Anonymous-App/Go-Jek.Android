package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

public class ag
  implements Parcelable.Creator<LoadRealtimeRequest>
{
  static void a(LoadRealtimeRequest paramLoadRealtimeRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramLoadRealtimeRequest.BR);
    b.a(paramParcel, 2, paramLoadRealtimeRequest.MW, paramInt, false);
    b.a(paramParcel, 3, paramLoadRealtimeRequest.Pk);
    b.H(paramParcel, i);
  }
  
  public LoadRealtimeRequest aj(Parcel paramParcel)
  {
    boolean bool = false;
    int j = a.C(paramParcel);
    DriveId localDriveId = null;
    int i = 0;
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
        i = a.g(paramParcel, k);
        continue;
        localDriveId = (DriveId)a.a(paramParcel, k, DriveId.CREATOR);
        continue;
        bool = a.c(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new LoadRealtimeRequest(i, localDriveId, bool);
  }
  
  public LoadRealtimeRequest[] bv(int paramInt)
  {
    return new LoadRealtimeRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/ag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */