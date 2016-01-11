package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;

public class b
  implements Parcelable.Creator<LocationRequest>
{
  static void a(LocationRequest paramLocationRequest, Parcel paramParcel, int paramInt)
  {
    paramInt = com.google.android.gms.common.internal.safeparcel.b.D(paramParcel);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 1, paramLocationRequest.mPriority);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 1000, paramLocationRequest.getVersionCode());
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 2, paramLocationRequest.aes);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 3, paramLocationRequest.aet);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 4, paramLocationRequest.UK);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 5, paramLocationRequest.aei);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 6, paramLocationRequest.aeu);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 7, paramLocationRequest.aev);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 8, paramLocationRequest.aew);
    com.google.android.gms.common.internal.safeparcel.b.H(paramParcel, paramInt);
  }
  
  public LocationRequest cs(Parcel paramParcel)
  {
    int m = a.C(paramParcel);
    int k = 0;
    int j = 102;
    long l4 = 3600000L;
    long l3 = 600000L;
    boolean bool = false;
    long l2 = Long.MAX_VALUE;
    int i = Integer.MAX_VALUE;
    float f = 0.0F;
    long l1 = 0L;
    while (paramParcel.dataPosition() < m)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        j = a.g(paramParcel, n);
        break;
      case 1000: 
        k = a.g(paramParcel, n);
        break;
      case 2: 
        l4 = a.i(paramParcel, n);
        break;
      case 3: 
        l3 = a.i(paramParcel, n);
        break;
      case 4: 
        bool = a.c(paramParcel, n);
        break;
      case 5: 
        l2 = a.i(paramParcel, n);
        break;
      case 6: 
        i = a.g(paramParcel, n);
        break;
      case 7: 
        f = a.l(paramParcel, n);
        break;
      case 8: 
        l1 = a.i(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new LocationRequest(k, j, l4, l3, bool, l2, i, f, l1);
  }
  
  public LocationRequest[] ed(int paramInt)
  {
    return new LocationRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/location/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */