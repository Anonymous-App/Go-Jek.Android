package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;

public class g
  implements Parcelable.Creator<SessionStopResult>
{
  static void a(SessionStopResult paramSessionStopResult, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1000, paramSessionStopResult.getVersionCode());
    b.a(paramParcel, 2, paramSessionStopResult.getStatus(), paramInt, false);
    b.c(paramParcel, 3, paramSessionStopResult.getSessions(), false);
    b.H(paramParcel, i);
  }
  
  public SessionStopResult cb(Parcel paramParcel)
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
        localArrayList = a.c(paramParcel, k, Session.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new SessionStopResult(i, localStatus, localArrayList);
  }
  
  public SessionStopResult[] dt(int paramInt)
  {
    return new SessionStopResult[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/result/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */