package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class dl
  implements Parcelable.Creator<dm>
{
  static void a(dm paramdm, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramdm.versionCode);
    b.a(paramParcel, 2, paramdm.rK, paramInt, false);
    b.a(paramParcel, 3, paramdm.cb(), false);
    b.a(paramParcel, 4, paramdm.cc(), false);
    b.a(paramParcel, 5, paramdm.cd(), false);
    b.a(paramParcel, 6, paramdm.ce(), false);
    b.a(paramParcel, 7, paramdm.rP, false);
    b.a(paramParcel, 8, paramdm.rQ);
    b.a(paramParcel, 9, paramdm.rR, false);
    b.a(paramParcel, 10, paramdm.cg(), false);
    b.c(paramParcel, 11, paramdm.orientation);
    b.c(paramParcel, 12, paramdm.rT);
    b.a(paramParcel, 13, paramdm.rq, false);
    b.a(paramParcel, 14, paramdm.lD, paramInt, false);
    b.a(paramParcel, 15, paramdm.cf(), false);
    b.a(paramParcel, 17, paramdm.rW, paramInt, false);
    b.a(paramParcel, 16, paramdm.rV, false);
    b.H(paramParcel, i);
  }
  
  public dm f(Parcel paramParcel)
  {
    int m = a.C(paramParcel);
    int k = 0;
    dj localdj = null;
    IBinder localIBinder6 = null;
    IBinder localIBinder5 = null;
    IBinder localIBinder4 = null;
    IBinder localIBinder3 = null;
    String str4 = null;
    boolean bool = false;
    String str3 = null;
    IBinder localIBinder2 = null;
    int j = 0;
    int i = 0;
    String str2 = null;
    gt localgt = null;
    IBinder localIBinder1 = null;
    String str1 = null;
    x localx = null;
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
        localdj = (dj)a.a(paramParcel, n, dj.CREATOR);
        break;
      case 3: 
        localIBinder6 = a.p(paramParcel, n);
        break;
      case 4: 
        localIBinder5 = a.p(paramParcel, n);
        break;
      case 5: 
        localIBinder4 = a.p(paramParcel, n);
        break;
      case 6: 
        localIBinder3 = a.p(paramParcel, n);
        break;
      case 7: 
        str4 = a.o(paramParcel, n);
        break;
      case 8: 
        bool = a.c(paramParcel, n);
        break;
      case 9: 
        str3 = a.o(paramParcel, n);
        break;
      case 10: 
        localIBinder2 = a.p(paramParcel, n);
        break;
      case 11: 
        j = a.g(paramParcel, n);
        break;
      case 12: 
        i = a.g(paramParcel, n);
        break;
      case 13: 
        str2 = a.o(paramParcel, n);
        break;
      case 14: 
        localgt = (gt)a.a(paramParcel, n, gt.CREATOR);
        break;
      case 15: 
        localIBinder1 = a.p(paramParcel, n);
        break;
      case 17: 
        localx = (x)a.a(paramParcel, n, x.CREATOR);
        break;
      case 16: 
        str1 = a.o(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new dm(k, localdj, localIBinder6, localIBinder5, localIBinder4, localIBinder3, str4, bool, str3, localIBinder2, j, i, str2, localgt, localIBinder1, str1, localx);
  }
  
  public dm[] m(int paramInt)
  {
    return new dm[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/dl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */