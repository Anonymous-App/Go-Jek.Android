package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

public class ac
  implements Parcelable.Creator<StartBleScanRequest>
{
  static void a(StartBleScanRequest paramStartBleScanRequest, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramStartBleScanRequest.getDataTypes(), false);
    b.c(paramParcel, 1000, paramStartBleScanRequest.getVersionCode());
    b.a(paramParcel, 2, paramStartBleScanRequest.jC(), false);
    b.c(paramParcel, 3, paramStartBleScanRequest.getTimeoutSecs());
    b.H(paramParcel, paramInt);
  }
  
  public StartBleScanRequest bQ(Parcel paramParcel)
  {
    IBinder localIBinder = null;
    int j = 0;
    int k = a.C(paramParcel);
    ArrayList localArrayList = null;
    int i = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        localArrayList = a.c(paramParcel, m, DataType.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, m);
        break;
      case 2: 
        localIBinder = a.p(paramParcel, m);
        break;
      case 3: 
        j = a.g(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new StartBleScanRequest(i, localArrayList, localIBinder, j);
  }
  
  public StartBleScanRequest[] di(int paramInt)
  {
    return new StartBleScanRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/ac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */