package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class g
  implements Parcelable.Creator<CreateContentsRequest>
{
  static void a(CreateContentsRequest paramCreateContentsRequest, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramCreateContentsRequest.BR);
    b.c(paramParcel, 2, paramCreateContentsRequest.MV);
    b.H(paramParcel, paramInt);
  }
  
  public CreateContentsRequest aa(Parcel paramParcel)
  {
    int k = a.C(paramParcel);
    int i = 0;
    int j = 536870912;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        i = a.g(paramParcel, m);
        break;
      case 2: 
        j = a.g(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new CreateContentsRequest(i, j);
  }
  
  public CreateContentsRequest[] bj(int paramInt)
  {
    return new CreateContentsRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */