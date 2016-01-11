package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.Subscription;

public class ag
  implements Parcelable.Creator<af>
{
  static void a(af paramaf, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramaf.jD(), paramInt, false);
    b.c(paramParcel, 1000, paramaf.getVersionCode());
    b.a(paramParcel, 2, paramaf.jE());
    b.H(paramParcel, i);
  }
  
  public af bS(Parcel paramParcel)
  {
    boolean bool = false;
    int j = a.C(paramParcel);
    Subscription localSubscription = null;
    int i = 0;
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
        localSubscription = (Subscription)a.a(paramParcel, k, Subscription.CREATOR);
        continue;
        i = a.g(paramParcel, k);
        continue;
        bool = a.c(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new af(i, localSubscription, bool);
  }
  
  public af[] dk(int paramInt)
  {
    return new af[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/ag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */