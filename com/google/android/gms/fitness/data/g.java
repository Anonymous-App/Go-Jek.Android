package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class g
  implements Parcelable.Creator<DataSource>
{
  static void a(DataSource paramDataSource, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramDataSource.getDataType(), paramInt, false);
    b.c(paramParcel, 1000, paramDataSource.getVersionCode());
    b.a(paramParcel, 2, paramDataSource.getName(), false);
    b.c(paramParcel, 3, paramDataSource.getType());
    b.a(paramParcel, 4, paramDataSource.getDevice(), paramInt, false);
    b.a(paramParcel, 5, paramDataSource.iM(), paramInt, false);
    b.a(paramParcel, 6, paramDataSource.getStreamName(), false);
    b.a(paramParcel, 7, paramDataSource.iO());
    b.H(paramParcel, i);
  }
  
  public DataSource bn(Parcel paramParcel)
  {
    boolean bool = false;
    String str1 = null;
    int k = com.google.android.gms.common.internal.safeparcel.a.C(paramParcel);
    a locala = null;
    Device localDevice = null;
    int i = 0;
    String str2 = null;
    DataType localDataType = null;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = com.google.android.gms.common.internal.safeparcel.a.B(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.a.aD(m))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.a.b(paramParcel, m);
        break;
      case 1: 
        localDataType = (DataType)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, m, DataType.CREATOR);
        break;
      case 1000: 
        j = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, m);
        break;
      case 2: 
        str2 = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel, m);
        break;
      case 3: 
        i = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, m);
        break;
      case 4: 
        localDevice = (Device)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, m, Device.CREATOR);
        break;
      case 5: 
        locala = (a)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, m, a.CREATOR);
        break;
      case 6: 
        str1 = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel, m);
        break;
      case 7: 
        bool = com.google.android.gms.common.internal.safeparcel.a.c(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new DataSource(j, localDataType, str2, i, localDevice, locala, str1, bool);
  }
  
  public DataSource[] cD(int paramInt)
  {
    return new DataSource[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */