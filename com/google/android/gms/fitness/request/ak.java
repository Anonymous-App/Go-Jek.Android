package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

public class ak
  implements Parcelable.Creator<aj>
{
  static void a(aj paramaj, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramaj.getDataType(), paramInt, false);
    b.c(paramParcel, 1000, paramaj.getVersionCode());
    b.a(paramParcel, 2, paramaj.getDataSource(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public aj bU(Parcel paramParcel)
  {
    DataSource localDataSource = null;
    int j = a.C(paramParcel);
    int i = 0;
    DataType localDataType = null;
    if (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
      }
      for (;;)
      {
        break;
        localDataType = (DataType)a.a(paramParcel, k, DataType.CREATOR);
        continue;
        i = a.g(paramParcel, k);
        continue;
        localDataSource = (DataSource)a.a(paramParcel, k, DataSource.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new aj(i, localDataType, localDataSource);
  }
  
  public aj[] dm(int paramInt)
  {
    return new aj[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/ak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */