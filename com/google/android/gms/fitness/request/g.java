package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

public class g
  implements Parcelable.Creator<DataReadRequest>
{
  static void a(DataReadRequest paramDataReadRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramDataReadRequest.getDataTypes(), false);
    b.c(paramParcel, 1000, paramDataReadRequest.getVersionCode());
    b.c(paramParcel, 2, paramDataReadRequest.getDataSources(), false);
    b.a(paramParcel, 3, paramDataReadRequest.iD());
    b.a(paramParcel, 4, paramDataReadRequest.iE());
    b.c(paramParcel, 5, paramDataReadRequest.getAggregatedDataTypes(), false);
    b.c(paramParcel, 6, paramDataReadRequest.getAggregatedDataSources(), false);
    b.c(paramParcel, 7, paramDataReadRequest.getBucketType());
    b.a(paramParcel, 8, paramDataReadRequest.jn());
    b.a(paramParcel, 9, paramDataReadRequest.getActivityDataSource(), paramInt, false);
    b.c(paramParcel, 10, paramDataReadRequest.getLimit());
    b.a(paramParcel, 11, paramDataReadRequest.jk());
    b.a(paramParcel, 12, paramDataReadRequest.jm());
    b.a(paramParcel, 13, paramDataReadRequest.jl());
    b.H(paramParcel, i);
  }
  
  public DataReadRequest bD(Parcel paramParcel)
  {
    int m = a.C(paramParcel);
    int k = 0;
    ArrayList localArrayList4 = null;
    ArrayList localArrayList3 = null;
    long l3 = 0L;
    long l2 = 0L;
    ArrayList localArrayList2 = null;
    ArrayList localArrayList1 = null;
    int j = 0;
    long l1 = 0L;
    DataSource localDataSource = null;
    int i = 0;
    boolean bool3 = false;
    boolean bool2 = false;
    boolean bool1 = false;
    while (paramParcel.dataPosition() < m)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        localArrayList4 = a.c(paramParcel, n, DataType.CREATOR);
        break;
      case 1000: 
        k = a.g(paramParcel, n);
        break;
      case 2: 
        localArrayList3 = a.c(paramParcel, n, DataSource.CREATOR);
        break;
      case 3: 
        l3 = a.i(paramParcel, n);
        break;
      case 4: 
        l2 = a.i(paramParcel, n);
        break;
      case 5: 
        localArrayList2 = a.c(paramParcel, n, DataType.CREATOR);
        break;
      case 6: 
        localArrayList1 = a.c(paramParcel, n, DataSource.CREATOR);
        break;
      case 7: 
        j = a.g(paramParcel, n);
        break;
      case 8: 
        l1 = a.i(paramParcel, n);
        break;
      case 9: 
        localDataSource = (DataSource)a.a(paramParcel, n, DataSource.CREATOR);
        break;
      case 10: 
        i = a.g(paramParcel, n);
        break;
      case 11: 
        bool3 = a.c(paramParcel, n);
        break;
      case 12: 
        bool2 = a.c(paramParcel, n);
        break;
      case 13: 
        bool1 = a.c(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new DataReadRequest(k, localArrayList4, localArrayList3, l3, l2, localArrayList2, localArrayList1, j, l1, localDataSource, i, bool3, bool2, bool1);
  }
  
  public DataReadRequest[] cU(int paramInt)
  {
    return new DataReadRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */