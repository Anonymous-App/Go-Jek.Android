package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;
import java.util.List;

public class s
  implements Parcelable.Creator<SessionInsertRequest>
{
  static void a(SessionInsertRequest paramSessionInsertRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramSessionInsertRequest.getSession(), paramInt, false);
    b.c(paramParcel, 1000, paramSessionInsertRequest.getVersionCode());
    b.c(paramParcel, 2, paramSessionInsertRequest.getDataSets(), false);
    b.c(paramParcel, 3, paramSessionInsertRequest.getAggregateDataPoints(), false);
    b.H(paramParcel, i);
  }
  
  public SessionInsertRequest bK(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
    int j = a.C(paramParcel);
    int i = 0;
    Object localObject2 = null;
    Object localObject1 = null;
    if (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      Object localObject3;
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        localObject3 = localObject2;
        localObject2 = localObject1;
        localObject1 = localObject3;
      }
      for (;;)
      {
        localObject3 = localObject2;
        localObject2 = localObject1;
        localObject1 = localObject3;
        break;
        localObject3 = (Session)a.a(paramParcel, k, Session.CREATOR);
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        i = a.g(paramParcel, k);
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        localObject3 = a.c(paramParcel, k, DataSet.CREATOR);
        localObject2 = localObject1;
        localObject1 = localObject3;
        continue;
        localArrayList = a.c(paramParcel, k, DataPoint.CREATOR);
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new SessionInsertRequest(i, (Session)localObject1, (List)localObject2, localArrayList);
  }
  
  public SessionInsertRequest[] dc(int paramInt)
  {
    return new SessionInsertRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/s.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */