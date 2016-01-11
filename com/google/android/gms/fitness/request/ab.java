package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ab
  implements Parcelable.Creator<aa>
{
  static void a(aa paramaa, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramaa.jr(), paramInt, false);
    b.c(paramParcel, 1000, paramaa.getVersionCode());
    b.H(paramParcel, i);
  }
  
  public aa bP(Parcel paramParcel)
  {
    int j = a.C(paramParcel);
    int i = 0;
    PendingIntent localPendingIntent = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        localPendingIntent = (PendingIntent)a.a(paramParcel, k, PendingIntent.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new aa(i, localPendingIntent);
  }
  
  public aa[] dh(int paramInt)
  {
    return new aa[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/ab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */