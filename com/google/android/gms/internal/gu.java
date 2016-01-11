package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class gu
  implements Parcelable.Creator<gt>
{
  static void a(gt paramgt, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramgt.versionCode);
    b.a(paramParcel, 2, paramgt.wD, false);
    b.c(paramParcel, 3, paramgt.wE);
    b.c(paramParcel, 4, paramgt.wF);
    b.a(paramParcel, 5, paramgt.wG);
    b.H(paramParcel, paramInt);
  }
  
  public gt j(Parcel paramParcel)
  {
    boolean bool = false;
    int m = a.C(paramParcel);
    String str = null;
    int i = 0;
    int j = 0;
    int k = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        k = a.g(paramParcel, n);
        break;
      case 2: 
        str = a.o(paramParcel, n);
        break;
      case 3: 
        j = a.g(paramParcel, n);
        break;
      case 4: 
        i = a.g(paramParcel, n);
        break;
      case 5: 
        bool = a.c(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new gt(k, str, j, i, bool);
  }
  
  public gt[] v(int paramInt)
  {
    return new gt[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/gu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */