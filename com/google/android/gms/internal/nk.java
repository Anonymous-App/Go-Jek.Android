package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class nk
  implements Parcelable.Creator<ni>
{
  static void a(ni paramni, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramni.versionCode);
    b.a(paramParcel, 2, paramni.akH);
    b.a(paramParcel, 3, paramni.tag, false);
    b.a(paramParcel, 4, paramni.akI, false);
    b.a(paramParcel, 5, paramni.akJ, false);
    b.H(paramParcel, paramInt);
  }
  
  public ni cX(Parcel paramParcel)
  {
    Bundle localBundle = null;
    int j = a.C(paramParcel);
    int i = 0;
    long l = 0L;
    byte[] arrayOfByte = null;
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
        l = a.i(paramParcel, k);
        break;
      case 3: 
        str = a.o(paramParcel, k);
        break;
      case 4: 
        arrayOfByte = a.r(paramParcel, k);
        break;
      case 5: 
        localBundle = a.q(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new ni(i, l, str, arrayOfByte, localBundle);
  }
  
  public ni[] eO(int paramInt)
  {
    return new ni[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/nk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */