package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.q;
import java.util.ArrayList;

public class f
  implements Parcelable.Creator<SessionReadResult>
{
  static void a(SessionReadResult paramSessionReadResult, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramSessionReadResult.getSessions(), false);
    b.c(paramParcel, 1000, paramSessionReadResult.getVersionCode());
    b.c(paramParcel, 2, paramSessionReadResult.jL(), false);
    b.a(paramParcel, 3, paramSessionReadResult.getStatus(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public SessionReadResult ca(Parcel paramParcel)
  {
    Status localStatus = null;
    int j = a.C(paramParcel);
    int i = 0;
    ArrayList localArrayList2 = null;
    ArrayList localArrayList1 = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        localArrayList1 = a.c(paramParcel, k, Session.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        localArrayList2 = a.c(paramParcel, k, q.CREATOR);
        break;
      case 3: 
        localStatus = (Status)a.a(paramParcel, k, Status.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new SessionReadResult(i, localArrayList1, localArrayList2, localStatus);
  }
  
  public SessionReadResult[] ds(int paramInt)
  {
    return new SessionReadResult[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/result/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */