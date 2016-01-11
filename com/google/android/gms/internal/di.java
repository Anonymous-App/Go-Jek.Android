package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class di
  implements Parcelable.Creator<dj>
{
  static void a(dj paramdj, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramdj.versionCode);
    b.a(paramParcel, 2, paramdj.rp, false);
    b.a(paramParcel, 3, paramdj.rq, false);
    b.a(paramParcel, 4, paramdj.mimeType, false);
    b.a(paramParcel, 5, paramdj.packageName, false);
    b.a(paramParcel, 6, paramdj.rr, false);
    b.a(paramParcel, 7, paramdj.rs, false);
    b.a(paramParcel, 8, paramdj.rt, false);
    b.H(paramParcel, paramInt);
  }
  
  public dj e(Parcel paramParcel)
  {
    String str1 = null;
    int j = a.C(paramParcel);
    int i = 0;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
    String str6 = null;
    String str7 = null;
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
        str7 = a.o(paramParcel, k);
        break;
      case 3: 
        str6 = a.o(paramParcel, k);
        break;
      case 4: 
        str5 = a.o(paramParcel, k);
        break;
      case 5: 
        str4 = a.o(paramParcel, k);
        break;
      case 6: 
        str3 = a.o(paramParcel, k);
        break;
      case 7: 
        str2 = a.o(paramParcel, k);
        break;
      case 8: 
        str1 = a.o(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new dj(i, str7, str6, str5, str4, str3, str2, str1);
  }
  
  public dj[] l(int paramInt)
  {
    return new dj[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/di.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */