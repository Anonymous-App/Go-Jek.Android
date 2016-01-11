package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class ar
  implements Parcelable.Creator<OnResourceIdSetResponse>
{
  static void a(OnResourceIdSetResponse paramOnResourceIdSetResponse, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramOnResourceIdSetResponse.getVersionCode());
    b.b(paramParcel, 2, paramOnResourceIdSetResponse.hX(), false);
    b.H(paramParcel, paramInt);
  }
  
  public OnResourceIdSetResponse at(Parcel paramParcel)
  {
    int j = a.C(paramParcel);
    int i = 0;
    ArrayList localArrayList = null;
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
        localArrayList = a.C(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new OnResourceIdSetResponse(i, localArrayList);
  }
  
  public OnResourceIdSetResponse[] bF(int paramInt)
  {
    return new OnResourceIdSetResponse[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/ar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */