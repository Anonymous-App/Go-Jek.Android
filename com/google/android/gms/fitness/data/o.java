package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class o
  implements Parcelable.Creator<RawDataSet>
{
  static void a(RawDataSet paramRawDataSet, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramRawDataSet.Tm);
    b.c(paramParcel, 1000, paramRawDataSet.BR);
    b.c(paramParcel, 2, paramRawDataSet.To);
    b.c(paramParcel, 3, paramRawDataSet.Tp, false);
    b.a(paramParcel, 4, paramRawDataSet.SF);
    b.H(paramParcel, paramInt);
  }
  
  public RawDataSet bt(Parcel paramParcel)
  {
    boolean bool = false;
    int m = a.C(paramParcel);
    ArrayList localArrayList = null;
    int i = 0;
    int j = 0;
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
        j = a.g(paramParcel, n);
        break;
      case 1000: 
        k = a.g(paramParcel, n);
        break;
      case 2: 
        i = a.g(paramParcel, n);
        break;
      case 3: 
        localArrayList = a.c(paramParcel, n, RawDataPoint.CREATOR);
        break;
      case 4: 
        bool = a.c(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new RawDataSet(k, j, i, localArrayList, bool);
  }
  
  public RawDataSet[] cJ(int paramInt)
  {
    return new RawDataSet[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */