package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ae
  implements Parcelable.Creator<ad>
{
  static void a(ad paramad, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.a(paramParcel, 1, paramad.jC(), false);
    b.c(paramParcel, 1000, paramad.getVersionCode());
    b.H(paramParcel, paramInt);
  }
  
  public ad bR(Parcel paramParcel)
  {
    int j = a.C(paramParcel);
    int i = 0;
    IBinder localIBinder = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        localIBinder = a.p(paramParcel, k);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new ad(i, localIBinder);
  }
  
  public ad[] dj(int paramInt)
  {
    return new ad[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/ae.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */