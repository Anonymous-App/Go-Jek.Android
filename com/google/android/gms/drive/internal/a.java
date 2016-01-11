package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

public class a
  implements Parcelable.Creator<AddEventListenerRequest>
{
  static void a(AddEventListenerRequest paramAddEventListenerRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramAddEventListenerRequest.BR);
    b.a(paramParcel, 2, paramAddEventListenerRequest.MW, paramInt, false);
    b.c(paramParcel, 3, paramAddEventListenerRequest.Oa);
    b.H(paramParcel, i);
  }
  
  public AddEventListenerRequest V(Parcel paramParcel)
  {
    int i = 0;
    int k = com.google.android.gms.common.internal.safeparcel.a.C(paramParcel);
    DriveId localDriveId = null;
    int j = 0;
    if (paramParcel.dataPosition() < k)
    {
      int m = com.google.android.gms.common.internal.safeparcel.a.B(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.a.aD(m))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.a.b(paramParcel, m);
      }
      for (;;)
      {
        break;
        j = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, m);
        continue;
        localDriveId = (DriveId)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, m, DriveId.CREATOR);
        continue;
        i = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new AddEventListenerRequest(j, localDriveId, i);
  }
  
  public AddEventListenerRequest[] be(int paramInt)
  {
    return new AddEventListenerRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */