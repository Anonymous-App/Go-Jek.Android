package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
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
    b.a(paramParcel, 1, paramq.jw(), false);
    b.c(paramParcel, 1000, paramq.getVersionCode());
    b.a(paramParcel, 2, paramq.jr(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public q bJ(Parcel paramParcel)
  {
    PendingIntent localPendingIntent = null;
    int j = a.C(paramParcel);
    int i = 0;
    IBinder localIBinder = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        localIBinder = a.p(paramParcel, k);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        localPendingIntent = (PendingIntent)a.a(paramParcel, k, PendingIntent.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new q(i, localIBinder, localPendingIntent);
  }
  
  public q[] db(int paramInt)
  {
    return new q[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/r.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */