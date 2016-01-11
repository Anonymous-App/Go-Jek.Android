package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class n
  implements Parcelable.Creator<RawDataPoint>
{
  static void a(RawDataPoint paramRawDataPoint, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramRawDataPoint.SG);
    b.c(paramParcel, 1000, paramRawDataPoint.BR);
    b.a(paramParcel, 2, paramRawDataPoint.SH);
    b.a(paramParcel, 3, paramRawDataPoint.SI, paramInt, false);
    b.c(paramParcel, 4, paramRawDataPoint.Tm);
    b.c(paramParcel, 5, paramRawDataPoint.Tn);
    b.a(paramParcel, 6, paramRawDataPoint.SK);
    b.a(paramParcel, 7, paramRawDataPoint.SL);
    b.H(paramParcel, i);
  }
  
  public RawDataPoint bs(Parcel paramParcel)
  {
    int m = a.C(paramParcel);
    int k = 0;
    long l4 = 0L;
    long l3 = 0L;
    Value[] arrayOfValue = null;
    int j = 0;
    int i = 0;
    long l2 = 0L;
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
        l4 = a.i(paramParcel, n);
        break;
      case 1000: 
        k = a.g(paramParcel, n);
        break;
      case 2: 
        l3 = a.i(paramParcel, n);
        break;
      case 3: 
        arrayOfValue = (Value[])a.b(paramParcel, n, Value.CREATOR);
        break;
      case 4: 
        j = a.g(paramParcel, n);
        break;
      case 5: 
        i = a.g(paramParcel, n);
        break;
      case 6: 
        l2 = a.i(paramParcel, n);
        break;
      case 7: 
        l1 = a.i(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new RawDataPoint(k, l4, l3, arrayOfValue, j, i, l2, l1);
  }
  
  public RawDataPoint[] cI(int paramInt)
  {
    return new RawDataPoint[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */