package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class q
  implements Parcelable.Creator<p>
{
  static void a(p paramp, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramp.getVersionCode());
    b.a(paramParcel, 2, paramp.auJ, false);
    b.a(paramParcel, 3, paramp.tG, false);
    b.a(paramParcel, 4, paramp.auN, paramInt, false);
    b.a(paramParcel, 5, paramp.auO, paramInt, false);
    b.a(paramParcel, 6, paramp.auP, paramInt, false);
    b.H(paramParcel, i);
  }
  
  public p dO(Parcel paramParcel)
  {
    n localn1 = null;
    int j = a.C(paramParcel);
    int i = 0;
    n localn2 = null;
    l locall = null;
    String str1 = null;
    String str2 = null;
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
        str2 = a.o(paramParcel, k);
        break;
      case 3: 
        str1 = a.o(paramParcel, k);
        break;
      case 4: 
        locall = (l)a.a(paramParcel, k, l.CREATOR);
        break;
      case 5: 
        localn2 = (n)a.a(paramParcel, k, n.CREATOR);
        break;
      case 6: 
        localn1 = (n)a.a(paramParcel, k, n.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new p(i, str2, str1, locall, localn2, localn1);
  }
  
  public p[] fR(int paramInt)
  {
    return new p[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/wobs/q.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */