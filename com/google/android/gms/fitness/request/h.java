package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

public class h
  implements Parcelable.Creator<DataSourcesRequest>
{
  static void a(DataSourcesRequest paramDataSourcesRequest, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramDataSourcesRequest.getDataTypes(), false);
    b.c(paramParcel, 1000, paramDataSourcesRequest.getVersionCode());
    b.a(paramParcel, 2, paramDataSourcesRequest.jp(), false);
    b.a(paramParcel, 3, paramDataSourcesRequest.jo());
    b.H(paramParcel, paramInt);
  }
  
  public DataSourcesRequest bE(Parcel paramParcel)
  {
    ArrayList localArrayList2 = null;
    boolean bool = false;
    int j = a.C(paramParcel);
    ArrayList localArrayList1 = null;
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        localArrayList1 = a.c(paramParcel, k, DataType.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        localArrayList2 = a.B(paramParcel, k);
        break;
      case 3: 
        bool = a.c(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new DataSourcesRequest(i, localArrayList1, localArrayList2, bool);
  }
  
  public DataSourcesRequest[] cV(int paramInt)
  {
    return new DataSourcesRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */