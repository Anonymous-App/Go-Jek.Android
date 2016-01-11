package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class jo
  implements Parcelable.Creator<jm.a>
{
  static void a(jm.a parama, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, parama.versionCode);
    b.a(paramParcel, 2, parama.className, false);
    b.c(paramParcel, 3, parama.ML, false);
    b.H(paramParcel, paramInt);
  }
  
  public jm.a L(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
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
        localArrayList = a.c(paramParcel, k, jm.b.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new jm.a(i, str, localArrayList);
  }
  
  public jm.a[] aL(int paramInt)
  {
    return new jm.a[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/jo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */