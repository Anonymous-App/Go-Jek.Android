package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataSet;

public class f
  implements Parcelable.Creator<e>
{
  static void a(e parame, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, parame.iW(), paramInt, false);
    b.c(paramParcel, 1000, parame.getVersionCode());
    b.H(paramParcel, i);
  }
  
  public e bC(Parcel paramParcel)
  {
    int j = a.C(paramParcel);
    int i = 0;
    DataSet localDataSet = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        localDataSet = (DataSet)a.a(paramParcel, k, DataSet.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new e(i, localDataSet);
  }
  
  public e[] cT(int paramInt)
  {
    return new e[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */