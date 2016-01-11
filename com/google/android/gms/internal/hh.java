package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class hh
  implements Parcelable.Creator<hg>
{
  static void a(hg paramhg, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.a(paramParcel, 1, paramhg.BZ, false);
    b.c(paramParcel, 1000, paramhg.BR);
    b.a(paramParcel, 2, paramhg.Ca, false);
    b.a(paramParcel, 3, paramhg.Cb, false);
    b.H(paramParcel, paramInt);
  }
  
  public hg[] J(int paramInt)
  {
    return new hg[paramInt];
  }
  
  public hg m(Parcel paramParcel)
  {
    String str3 = null;
    int j = a.C(paramParcel);
    int i = 0;
    String str2 = null;
    String str1 = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        str1 = a.o(paramParcel, k);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        str2 = a.o(paramParcel, k);
        break;
      case 3: 
        str3 = a.o(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new hg(i, str1, str2, str3);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/hh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */