package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class je
  implements Parcelable.Creator<jd>
{
  static void a(jd paramjd, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramjd.getVersionCode());
    b.a(paramParcel, 2, paramjd.ha(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public jd F(Parcel paramParcel)
  {
    int j = a.C(paramParcel);
    int i = 0;
    jf localjf = null;
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
        localjf = (jf)a.a(paramParcel, k, jf.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new jd(i, localjf);
  }
  
  public jd[] aF(int paramInt)
  {
    return new jd[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/je.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */