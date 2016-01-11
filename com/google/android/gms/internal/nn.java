package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class nn
  implements Parcelable.Creator<nm>
{
  static void a(nm paramnm, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramnm.versionCode);
    b.a(paramParcel, 2, paramnm.packageName, false);
    b.c(paramParcel, 3, paramnm.akR);
    b.c(paramParcel, 4, paramnm.akS);
    b.a(paramParcel, 5, paramnm.akT, false);
    b.a(paramParcel, 6, paramnm.akU, false);
    b.a(paramParcel, 7, paramnm.akV);
    b.H(paramParcel, paramInt);
  }
  
  public nm cY(Parcel paramParcel)
  {
    String str1 = null;
    int i = 0;
    int m = a.C(paramParcel);
    boolean bool = true;
    String str2 = null;
    int j = 0;
    String str3 = null;
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
        str3 = a.o(paramParcel, n);
        break;
      case 3: 
        j = a.g(paramParcel, n);
        break;
      case 4: 
        i = a.g(paramParcel, n);
        break;
      case 5: 
        str2 = a.o(paramParcel, n);
        break;
      case 6: 
        str1 = a.o(paramParcel, n);
        break;
      case 7: 
        bool = a.c(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new nm(k, str3, j, i, str2, str1, bool);
  }
  
  public nm[] eP(int paramInt)
  {
    return new nm[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/nn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */