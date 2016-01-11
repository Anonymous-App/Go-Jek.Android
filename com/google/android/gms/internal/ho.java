package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class ho
  implements Parcelable.Creator<hm.b>
{
  static void a(hm.b paramb, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1000, paramb.BR);
    b.a(paramParcel, 1, paramb.Ck, paramInt, false);
    b.c(paramParcel, 2, paramb.Cl, false);
    b.H(paramParcel, i);
  }
  
  public hm.b[] N(int paramInt)
  {
    return new hm.b[paramInt];
  }
  
  public hm.b q(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
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
        i = a.g(paramParcel, k);
        continue;
        localStatus = (Status)a.a(paramParcel, k, Status.CREATOR);
        continue;
        localArrayList = a.c(paramParcel, k, hs.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new hm.b(i, localStatus, localArrayList);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ho.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */