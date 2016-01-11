package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class hj
  implements Parcelable.Creator<hi>
{
  static void a(hi paramhi, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramhi.Ce, false);
    b.c(paramParcel, 1000, paramhi.BR);
    b.a(paramParcel, 3, paramhi.Cf, paramInt, false);
    b.c(paramParcel, 4, paramhi.Cg);
    b.a(paramParcel, 5, paramhi.Ch, false);
    b.H(paramParcel, i);
  }
  
  public hi[] K(int paramInt)
  {
    return new hi[paramInt];
  }
  
  public hi n(Parcel paramParcel)
  {
    byte[] arrayOfByte = null;
    int k = a.C(paramParcel);
    int j = 0;
    int i = -1;
    hq localhq = null;
    String str = null;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        str = a.o(paramParcel, m);
        break;
      case 1000: 
        j = a.g(paramParcel, m);
        break;
      case 3: 
        localhq = (hq)a.a(paramParcel, m, hq.CREATOR);
        break;
      case 4: 
        i = a.g(paramParcel, m);
        break;
      case 5: 
        arrayOfByte = a.r(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new hi(j, str, localhq, i, arrayOfByte);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/hj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */