package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class jn
  implements Parcelable.Creator<jm>
{
  static void a(jm paramjm, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramjm.getVersionCode());
    b.c(paramParcel, 2, paramjm.hu(), false);
    b.a(paramParcel, 3, paramjm.hv(), false);
    b.H(paramParcel, paramInt);
  }
  
  public jm K(Parcel paramParcel)
  {
    String str = null;
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
        localArrayList = a.c(paramParcel, k, jm.a.CREATOR);
        break;
      case 3: 
        str = a.o(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new jm(i, localArrayList, str);
  }
  
  public jm[] aK(int paramInt)
  {
    return new jm[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/jn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */