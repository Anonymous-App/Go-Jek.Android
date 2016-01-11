package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.fitness.data.BleDevice;
import java.util.ArrayList;

public class a
  implements Parcelable.Creator<BleDevicesResult>
{
  static void a(BleDevicesResult paramBleDevicesResult, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramBleDevicesResult.getClaimedBleDevices(), false);
    b.c(paramParcel, 1000, paramBleDevicesResult.getVersionCode());
    b.a(paramParcel, 2, paramBleDevicesResult.getStatus(), paramInt, false);
    b.H(paramParcel, i);
  }
  
  public BleDevicesResult bV(Parcel paramParcel)
  {
    Status localStatus = null;
    int j = com.google.android.gms.common.internal.safeparcel.a.C(paramParcel);
    int i = 0;
    ArrayList localArrayList = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = com.google.android.gms.common.internal.safeparcel.a.B(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.a.aD(k))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.a.b(paramParcel, k);
        break;
      case 1: 
        localArrayList = com.google.android.gms.common.internal.safeparcel.a.c(paramParcel, k, BleDevice.CREATOR);
        break;
      case 1000: 
        i = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, k);
        break;
      case 2: 
        localStatus = (Status)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, k, Status.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new BleDevicesResult(i, localArrayList, localStatus);
  }
  
  public BleDevicesResult[] dn(int paramInt)
  {
    return new BleDevicesResult[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/result/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */