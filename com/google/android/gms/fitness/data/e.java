package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class e
  implements Parcelable.Creator<DataPoint>
{
  static void a(DataPoint paramDataPoint, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramDataPoint.getDataSource(), paramInt, false);
    b.c(paramParcel, 1000, paramDataPoint.getVersionCode());
    b.a(paramParcel, 3, paramDataPoint.getTimestampNanos());
    b.a(paramParcel, 4, paramDataPoint.iJ());
    b.a(paramParcel, 5, paramDataPoint.iG(), paramInt, false);
    b.a(paramParcel, 6, paramDataPoint.getOriginalDataSource(), paramInt, false);
    b.a(paramParcel, 7, paramDataPoint.iH());
    b.a(paramParcel, 8, paramDataPoint.iI());
    b.H(paramParcel, i);
  }
  
  public DataPoint bl(Parcel paramParcel)
  {
    int j = a.C(paramParcel);
    int i = 0;
    DataSource localDataSource2 = null;
    long l4 = 0L;
    long l3 = 0L;
    Value[] arrayOfValue = null;
    DataSource localDataSource1 = null;
    long l2 = 0L;
    long l1 = 0L;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        localDataSource2 = (DataSource)a.a(paramParcel, k, DataSource.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 3: 
        l4 = a.i(paramParcel, k);
        break;
      case 4: 
        l3 = a.i(paramParcel, k);
        break;
      case 5: 
        arrayOfValue = (Value[])a.b(paramParcel, k, Value.CREATOR);
        break;
      case 6: 
        localDataSource1 = (DataSource)a.a(paramParcel, k, DataSource.CREATOR);
        break;
      case 7: 
        l2 = a.i(paramParcel, k);
        break;
      case 8: 
        l1 = a.i(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new DataPoint(i, localDataSource2, l4, l3, arrayOfValue, localDataSource1, l2, l1);
  }
  
  public DataPoint[] cB(int paramInt)
  {
    return new DataPoint[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */