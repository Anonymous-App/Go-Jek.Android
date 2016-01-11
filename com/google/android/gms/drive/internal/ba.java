package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import java.util.ArrayList;

public class ba
  implements Parcelable.Creator<SetResourceParentsRequest>
{
  static void a(SetResourceParentsRequest paramSetResourceParentsRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramSetResourceParentsRequest.BR);
    b.a(paramParcel, 2, paramSetResourceParentsRequest.Pz, paramInt, false);
    b.c(paramParcel, 3, paramSetResourceParentsRequest.PA, false);
    b.H(paramParcel, i);
  }
  
  public SetResourceParentsRequest aB(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
    int j = a.C(paramParcel);
    int i = 0;
    DriveId localDriveId = null;
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
        localArrayList = a.c(paramParcel, k, DriveId.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new SetResourceParentsRequest(i, localDriveId, localArrayList);
  }
  
  public SetResourceParentsRequest[] bN(int paramInt)
  {
    return new SetResourceParentsRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/ba.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */