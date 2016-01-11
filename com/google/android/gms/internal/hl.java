package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class hl
  implements Parcelable.Creator<hk>
{
  static void a(hk paramhk, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramhk.id);
    b.c(paramParcel, 1000, paramhk.BR);
    b.a(paramParcel, 2, paramhk.Ci, false);
    b.H(paramParcel, paramInt);
  }
  
  public hk[] L(int paramInt)
  {
    return new hk[paramInt];
  }
  
  public hk o(Parcel paramParcel)
  {
    int j = 0;
    int k = a.C(paramParcel);
    Bundle localBundle = null;
    int i = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        j = a.g(paramParcel, m);
        break;
      case 1000: 
        i = a.g(paramParcel, m);
        break;
      case 2: 
        localBundle = a.q(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new hk(i, j, localBundle);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/hl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */