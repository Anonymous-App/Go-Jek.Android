package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;

public class p
  implements Parcelable.Creator<o>
{
  static void a(o paramo, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramo.getDataSource(), paramInt, false);
    b.c(paramParcel, 1000, paramo.getVersionCode());
    b.a(paramParcel, 2, paramo.getDataType(), paramInt, false);
    b.a(paramParcel, 3, paramo.jw(), false);
    b.c(paramParcel, 4, paramo.UB);
    b.c(paramParcel, 5, paramo.UC);
    b.a(paramParcel, 6, paramo.iX());
    b.a(paramParcel, 7, paramo.jt());
    b.a(paramParcel, 8, paramo.jr(), paramInt, false);
    b.a(paramParcel, 9, paramo.js());
    b.c(paramParcel, 10, paramo.getAccuracyMode());
    b.c(paramParcel, 11, paramo.ju(), false);
    b.a(paramParcel, 12, paramo.jv());
    b.H(paramParcel, i);
  }
  
  public o bI(Parcel paramParcel)
  {
    int n = a.C(paramParcel);
    int m = 0;
    DataSource localDataSource = null;
    DataType localDataType = null;
    IBinder localIBinder = null;
    int k = 0;
    int j = 0;
    long l4 = 0L;
    long l3 = 0L;
    PendingIntent localPendingIntent = null;
    long l2 = 0L;
    int i = 0;
    ArrayList localArrayList = null;
    long l1 = 0L;
    while (paramParcel.dataPosition() < n)
    {
      int i1 = a.B(paramParcel);
      switch (a.aD(i1))
      {
      default: 
        a.b(paramParcel, i1);
        break;
      case 1: 
        localDataSource = (DataSource)a.a(paramParcel, i1, DataSource.CREATOR);
        break;
      case 1000: 
        m = a.g(paramParcel, i1);
        break;
      case 2: 
        localDataType = (DataType)a.a(paramParcel, i1, DataType.CREATOR);
        break;
      case 3: 
        localIBinder = a.p(paramParcel, i1);
        break;
      case 4: 
        k = a.g(paramParcel, i1);
        break;
      case 5: 
        j = a.g(paramParcel, i1);
        break;
      case 6: 
        l4 = a.i(paramParcel, i1);
        break;
      case 7: 
        l3 = a.i(paramParcel, i1);
        break;
      case 8: 
        localPendingIntent = (PendingIntent)a.a(paramParcel, i1, PendingIntent.CREATOR);
        break;
      case 9: 
        l2 = a.i(paramParcel, i1);
        break;
      case 10: 
        i = a.g(paramParcel, i1);
        break;
      case 11: 
        localArrayList = a.c(paramParcel, i1, LocationRequest.CREATOR);
        break;
      case 12: 
        l1 = a.i(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != n) {
      throw new a.a("Overread allowed size end=" + n, paramParcel);
    }
    return new o(m, localDataSource, localDataType, localIBinder, k, j, l4, l3, localPendingIntent, l2, i, localArrayList, l1);
  }
  
  public o[] cZ(int paramInt)
  {
    return new o[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */