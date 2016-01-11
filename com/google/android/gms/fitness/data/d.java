package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class d
  implements Parcelable.Creator<Bucket>
{
  static void a(Bucket paramBucket, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramBucket.iD());
    b.c(paramParcel, 1000, paramBucket.getVersionCode());
    b.a(paramParcel, 2, paramBucket.iE());
    b.a(paramParcel, 3, paramBucket.getSession(), paramInt, false);
    b.c(paramParcel, 4, paramBucket.iB());
    b.c(paramParcel, 5, paramBucket.getDataSets(), false);
    b.c(paramParcel, 6, paramBucket.getBucketType());
    b.a(paramParcel, 7, paramBucket.iC());
    b.H(paramParcel, i);
  }
  
  public Bucket bk(Parcel paramParcel)
  {
    long l1 = 0L;
    ArrayList localArrayList = null;
    boolean bool = false;
    int m = a.C(paramParcel);
    int i = 0;
    int j = 0;
    Session localSession = null;
    long l2 = 0L;
    int k = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        l2 = a.i(paramParcel, n);
        break;
      case 1000: 
        k = a.g(paramParcel, n);
        break;
      case 2: 
        l1 = a.i(paramParcel, n);
        break;
      case 3: 
        localSession = (Session)a.a(paramParcel, n, Session.CREATOR);
        break;
      case 4: 
        j = a.g(paramParcel, n);
        break;
      case 5: 
        localArrayList = a.c(paramParcel, n, DataSet.CREATOR);
        break;
      case 6: 
        i = a.g(paramParcel, n);
        break;
      case 7: 
        bool = a.c(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new Bucket(k, l2, l1, localSession, j, localArrayList, i, bool);
  }
  
  public Bucket[] cz(int paramInt)
  {
    return new Bucket[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */