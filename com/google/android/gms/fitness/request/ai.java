package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ai
  implements Parcelable.Creator<ah>
{
  static void a(ah paramah, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1000, paramah.getVersionCode());
    b.a(paramParcel, 2, paramah.getDeviceAddress(), false);
    b.H(paramParcel, paramInt);
  }
  
  public ah bT(Parcel paramParcel)
  {
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
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        str = a.o(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new ah(i, str);
  }
  
  public ah[] dl(int paramInt)
  {
    return new ah[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/ai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */