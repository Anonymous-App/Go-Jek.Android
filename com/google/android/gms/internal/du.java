package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class du
  implements Parcelable.Creator<dv>
{
  static void a(dv paramdv, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramdv.versionCode);
    b.a(paramParcel, 2, paramdv.ck(), false);
    b.a(paramParcel, 3, paramdv.cl(), false);
    b.a(paramParcel, 4, paramdv.cm(), false);
    b.a(paramParcel, 5, paramdv.cn(), false);
    b.H(paramParcel, paramInt);
  }
  
  public dv g(Parcel paramParcel)
  {
    IBinder localIBinder1 = null;
    int j = a.C(paramParcel);
    int i = 0;
    IBinder localIBinder2 = null;
    IBinder localIBinder3 = null;
    IBinder localIBinder4 = null;
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
        localIBinder4 = a.p(paramParcel, k);
        break;
      case 3: 
        localIBinder3 = a.p(paramParcel, k);
        break;
      case 4: 
        localIBinder2 = a.p(paramParcel, k);
        break;
      case 5: 
        localIBinder1 = a.p(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new dv(i, localIBinder4, localIBinder3, localIBinder2, localIBinder1);
  }
  
  public dv[] n(int paramInt)
  {
    return new dv[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/du.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */