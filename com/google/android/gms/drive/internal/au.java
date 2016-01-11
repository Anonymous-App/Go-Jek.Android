package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

public class au
  implements Parcelable.Creator<OpenContentsRequest>
{
  static void a(OpenContentsRequest paramOpenContentsRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramOpenContentsRequest.BR);
    b.a(paramParcel, 2, paramOpenContentsRequest.Od, paramInt, false);
    b.c(paramParcel, 3, paramOpenContentsRequest.MV);
    b.c(paramParcel, 4, paramOpenContentsRequest.Px);
    b.H(paramParcel, i);
  }
  
  public OpenContentsRequest aw(Parcel paramParcel)
  {
    int i = 0;
    int m = a.C(paramParcel);
    DriveId localDriveId = null;
    int j = 0;
    int k = 0;
    if (paramParcel.dataPosition() < m)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
      }
      for (;;)
      {
        break;
        k = a.g(paramParcel, n);
        continue;
        localDriveId = (DriveId)a.a(paramParcel, n, DriveId.CREATOR);
        continue;
        j = a.g(paramParcel, n);
        continue;
        i = a.g(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new OpenContentsRequest(k, localDriveId, j, i);
  }
  
  public OpenContentsRequest[] bI(int paramInt)
  {
    return new OpenContentsRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/au.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */