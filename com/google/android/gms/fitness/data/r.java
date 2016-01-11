package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class r
  implements Parcelable.Creator<q>
{
  static void a(q paramq, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramq.getSession(), paramInt, false);
    b.c(paramParcel, 1000, paramq.BR);
    b.a(paramParcel, 2, paramq.iW(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public q bv(Parcel paramParcel)
  {
    DataSet localDataSet = null;
    int j = a.C(paramParcel);
    int i = 0;
    Session localSession = null;
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
        localSession = (Session)a.a(paramParcel, k, Session.CREATOR);
        continue;
        i = a.g(paramParcel, k);
        continue;
        localDataSet = (DataSet)a.a(paramParcel, k, DataSet.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new q(i, localSession, localDataSet);
  }
  
  public q[] cM(int paramInt)
  {
    return new q[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/r.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */