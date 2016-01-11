package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.Subscription;
import java.util.ArrayList;

public class e
  implements Parcelable.Creator<ListSubscriptionsResult>
{
  static void a(ListSubscriptionsResult paramListSubscriptionsResult, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramListSubscriptionsResult.getSubscriptions(), false);
    b.c(paramParcel, 1000, paramListSubscriptionsResult.getVersionCode());
    b.a(paramParcel, 2, paramListSubscriptionsResult.getStatus(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public ListSubscriptionsResult bZ(Parcel paramParcel)
  {
    Status localStatus = null;
    int j = a.C(paramParcel);
    int i = 0;
    ArrayList localArrayList = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        localArrayList = a.c(paramParcel, k, Subscription.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        localStatus = (Status)a.a(paramParcel, k, Status.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new ListSubscriptionsResult(i, localArrayList, localStatus);
  }
  
  public ListSubscriptionsResult[] dr(int paramInt)
  {
    return new ListSubscriptionsResult[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/result/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */