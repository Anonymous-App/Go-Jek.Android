package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ht
  implements Parcelable.Creator<hs>
{
  static void a(hs paramhs, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramhs.CD, paramInt, false);
    b.c(paramParcel, 1000, paramhs.BR);
    b.a(paramParcel, 2, paramhs.CE);
    b.c(paramParcel, 3, paramhs.CF);
    b.a(paramParcel, 4, paramhs.oT, false);
    b.a(paramParcel, 5, paramhs.CG, paramInt, false);
    b.H(paramParcel, i);
  }
  
  public hs[] R(int paramInt)
  {
    return new hs[paramInt];
  }
  
  public hs s(Parcel paramParcel)
  {
    int i = 0;
    he localhe = null;
    int k = a.C(paramParcel);
    long l = 0L;
    String str = null;
    hg localhg = null;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        localhg = (hg)a.a(paramParcel, m, hg.CREATOR);
        break;
      case 1000: 
        j = a.g(paramParcel, m);
        break;
      case 2: 
        l = a.i(paramParcel, m);
        break;
      case 3: 
        i = a.g(paramParcel, m);
        break;
      case 4: 
        str = a.o(paramParcel, m);
        break;
      case 5: 
        localhe = (he)a.a(paramParcel, m, he.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new hs(j, localhg, l, i, str, localhe);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ht.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */