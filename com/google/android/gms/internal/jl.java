package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class jl
  implements Parcelable.Creator<jm.b>
{
  static void a(jm.b paramb, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramb.versionCode);
    b.a(paramParcel, 2, paramb.fv, false);
    b.a(paramParcel, 3, paramb.MM, paramInt, false);
    b.H(paramParcel, i);
  }
  
  public jm.b J(Parcel paramParcel)
  {
    ji.a locala = null;
    int j = a.C(paramParcel);
    int i = 0;
    String str = null;
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
        locala = (ji.a)a.a(paramParcel, k, ji.a.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new jm.b(i, str, locala);
  }
  
  public jm.b[] aJ(int paramInt)
  {
    return new jm.b[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/jl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */