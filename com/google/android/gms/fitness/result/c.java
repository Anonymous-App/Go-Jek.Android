package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataSource;
import java.util.ArrayList;

public class c
  implements Parcelable.Creator<DataSourcesResult>
{
  static void a(DataSourcesResult paramDataSourcesResult, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramDataSourcesResult.getDataSources(), false);
    b.c(paramParcel, 1000, paramDataSourcesResult.getVersionCode());
    b.a(paramParcel, 2, paramDataSourcesResult.getStatus(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public DataSourcesResult bX(Parcel paramParcel)
  {
    Status localStatus = null;
    int j = a.C(paramParcel);
    int i = 0;
    ArrayList localArrayList = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        localArrayList = a.c(paramParcel, k, DataSource.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        localStatus = (Status)a.a(paramParcel, k, Status.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new DataSourcesResult(i, localArrayList, localStatus);
  }
  
  public DataSourcesResult[] dp(int paramInt)
  {
    return new DataSourcesResult[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/result/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */