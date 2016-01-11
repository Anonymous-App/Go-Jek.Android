package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class a
  implements Parcelable.Creator<ObjectChangedDetails>
{
  static void a(ObjectChangedDetails paramObjectChangedDetails, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramObjectChangedDetails.BR);
    b.c(paramParcel, 2, paramObjectChangedDetails.Rr);
    b.c(paramParcel, 3, paramObjectChangedDetails.Rs);
    b.H(paramParcel, paramInt);
  }
  
  public ObjectChangedDetails aY(Parcel paramParcel)
  {
    int k = 0;
    int m = com.google.android.gms.common.internal.safeparcel.a.C(paramParcel);
    int j = 0;
    int i = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = com.google.android.gms.common.internal.safeparcel.a.B(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.a.aD(n))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.a.b(paramParcel, n);
        break;
      case 1: 
        i = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, n);
        break;
      case 2: 
        j = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, n);
        break;
      case 3: 
        k = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new ObjectChangedDetails(i, j, k);
  }
  
  public ObjectChangedDetails[] cl(int paramInt)
  {
    return new ObjectChangedDetails[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/realtime/internal/event/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */