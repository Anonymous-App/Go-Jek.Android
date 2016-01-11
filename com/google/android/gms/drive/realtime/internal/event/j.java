package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class j
  implements Parcelable.Creator<ValuesSetDetails>
{
  static void a(ValuesSetDetails paramValuesSetDetails, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramValuesSetDetails.BR);
    b.c(paramParcel, 2, paramValuesSetDetails.mIndex);
    b.c(paramParcel, 3, paramValuesSetDetails.Rr);
    b.c(paramParcel, 4, paramValuesSetDetails.Rs);
    b.H(paramParcel, paramInt);
  }
  
  public ValuesSetDetails bh(Parcel paramParcel)
  {
    int m = 0;
    int n = a.C(paramParcel);
    int k = 0;
    int j = 0;
    int i = 0;
    while (paramParcel.dataPosition() < n)
    {
      int i1 = a.B(paramParcel);
      switch (a.aD(i1))
      {
      default: 
        a.b(paramParcel, i1);
        break;
      case 1: 
        i = a.g(paramParcel, i1);
        break;
      case 2: 
        j = a.g(paramParcel, i1);
        break;
      case 3: 
        k = a.g(paramParcel, i1);
        break;
      case 4: 
        m = a.g(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != n) {
      throw new a.a("Overread allowed size end=" + n, paramParcel);
    }
    return new ValuesSetDetails(i, j, k, m);
  }
  
  public ValuesSetDetails[] cu(int paramInt)
  {
    return new ValuesSetDetails[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/realtime/internal/event/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */