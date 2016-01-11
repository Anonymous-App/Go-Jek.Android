package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class z
  implements Parcelable.Creator<GetDriveIdFromUniqueIdentifierRequest>
{
  static void a(GetDriveIdFromUniqueIdentifierRequest paramGetDriveIdFromUniqueIdentifierRequest, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramGetDriveIdFromUniqueIdentifierRequest.BR);
    b.a(paramParcel, 2, paramGetDriveIdFromUniqueIdentifierRequest.Ph, false);
    b.a(paramParcel, 3, paramGetDriveIdFromUniqueIdentifierRequest.Pi);
    b.H(paramParcel, paramInt);
  }
  
  public GetDriveIdFromUniqueIdentifierRequest ag(Parcel paramParcel)
  {
    boolean bool = false;
    int j = a.C(paramParcel);
    String str = null;
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        str = a.o(paramParcel, k);
        break;
      case 3: 
        bool = a.c(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new GetDriveIdFromUniqueIdentifierRequest(i, str, bool);
  }
  
  public GetDriveIdFromUniqueIdentifierRequest[] bs(int paramInt)
  {
    return new GetDriveIdFromUniqueIdentifierRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/z.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */