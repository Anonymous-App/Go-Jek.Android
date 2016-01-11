package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class az
  implements Parcelable.Creator<ay>
{
  static void a(ay paramay, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramay.versionCode);
    b.a(paramParcel, 2, paramay.of, false);
    b.c(paramParcel, 3, paramay.height);
    b.c(paramParcel, 4, paramay.heightPixels);
    b.a(paramParcel, 5, paramay.og);
    b.c(paramParcel, 6, paramay.width);
    b.c(paramParcel, 7, paramay.widthPixels);
    b.a(paramParcel, 8, paramay.oh, paramInt, false);
    b.H(paramParcel, i);
  }
  
  public ay c(Parcel paramParcel)
  {
    ay[] arrayOfay = null;
    int i = 0;
    int i1 = a.C(paramParcel);
    int j = 0;
    boolean bool = false;
    int k = 0;
    int m = 0;
    String str = null;
    int n = 0;
    while (paramParcel.dataPosition() < i1)
    {
      int i2 = a.B(paramParcel);
      switch (a.aD(i2))
      {
      default: 
        a.b(paramParcel, i2);
        break;
      case 1: 
        n = a.g(paramParcel, i2);
        break;
      case 2: 
        str = a.o(paramParcel, i2);
        break;
      case 3: 
        m = a.g(paramParcel, i2);
        break;
      case 4: 
        k = a.g(paramParcel, i2);
        break;
      case 5: 
        bool = a.c(paramParcel, i2);
        break;
      case 6: 
        j = a.g(paramParcel, i2);
        break;
      case 7: 
        i = a.g(paramParcel, i2);
        break;
      case 8: 
        arrayOfay = (ay[])a.b(paramParcel, i2, ay.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i1) {
      throw new a.a("Overread allowed size end=" + i1, paramParcel);
    }
    return new ay(n, str, m, k, bool, j, i, arrayOfay);
  }
  
  public ay[] f(int paramInt)
  {
    return new ay[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/az.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */