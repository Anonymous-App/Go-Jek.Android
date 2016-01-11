package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataType;

public class n
  implements Parcelable.Creator<m>
{
  static void a(m paramm, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramm.getDataType(), paramInt, false);
    b.c(paramParcel, 1000, paramm.getVersionCode());
    b.H(paramParcel, i);
  }
  
  public m bH(Parcel paramParcel)
  {
    int j = a.C(paramParcel);
    int i = 0;
    DataType localDataType = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        localDataType = (DataType)a.a(paramParcel, k, DataType.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new m(i, localDataType);
  }
  
  public m[] cY(int paramInt)
  {
    return new m[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */