package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class mg
  implements Parcelable.Creator<mf>
{
  static void a(mf parammf, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.a(paramParcel, 1, parammf.me());
    b.c(paramParcel, 1000, parammf.BR);
    b.c(paramParcel, 2, parammf.mf(), false);
    b.H(paramParcel, paramInt);
  }
  
  public mf cx(Parcel paramParcel)
  {
    boolean bool = false;
    int j = a.C(paramParcel);
    ArrayList localArrayList = null;
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
        bool = a.c(paramParcel, k);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        localArrayList = a.c(paramParcel, k, mp.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new mf(i, bool, localArrayList);
  }
  
  public mf[] en(int paramInt)
  {
    return new mf[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/mg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */