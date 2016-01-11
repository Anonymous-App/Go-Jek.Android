package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataType;

public class d
  implements Parcelable.Creator<DataTypeResult>
{
  static void a(DataTypeResult paramDataTypeResult, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramDataTypeResult.getStatus(), paramInt, false);
    b.c(paramParcel, 1000, paramDataTypeResult.getVersionCode());
    b.a(paramParcel, 3, paramDataTypeResult.getDataType(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public DataTypeResult bY(Parcel paramParcel)
  {
    DataType localDataType = null;
    int j = a.C(paramParcel);
    int i = 0;
    Status localStatus = null;
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
        localStatus = (Status)a.a(paramParcel, k, Status.CREATOR);
        continue;
        i = a.g(paramParcel, k);
        continue;
        localDataType = (DataType)a.a(paramParcel, k, DataType.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new DataTypeResult(i, localStatus, localDataType);
  }
  
  public DataTypeResult[] dq(int paramInt)
  {
    return new DataTypeResult[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/result/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */