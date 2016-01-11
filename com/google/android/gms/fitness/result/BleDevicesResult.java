package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BleDevicesResult
  implements Result, SafeParcelable
{
  public static final Parcelable.Creator<BleDevicesResult> CREATOR = new a();
  private final int BR;
  private final Status CM;
  private final List<BleDevice> UU;
  
  BleDevicesResult(int paramInt, List<BleDevice> paramList, Status paramStatus)
  {
    this.BR = paramInt;
    this.UU = Collections.unmodifiableList(paramList);
    this.CM = paramStatus;
  }
  
  public BleDevicesResult(List<BleDevice> paramList, Status paramStatus)
  {
    this.BR = 3;
    this.UU = Collections.unmodifiableList(paramList);
    this.CM = paramStatus;
  }
  
  public static BleDevicesResult D(Status paramStatus)
  {
    return new BleDevicesResult(Collections.emptyList(), paramStatus);
  }
  
  private boolean b(BleDevicesResult paramBleDevicesResult)
  {
    return (this.CM.equals(paramBleDevicesResult.CM)) && (n.equal(this.UU, paramBleDevicesResult.UU));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof BleDevicesResult)) && (b((BleDevicesResult)paramObject)));
  }
  
  public List<BleDevice> getClaimedBleDevices()
  {
    return this.UU;
  }
  
  public List<BleDevice> getClaimedBleDevices(DataType paramDataType)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.UU.iterator();
    while (localIterator.hasNext())
    {
      BleDevice localBleDevice = (BleDevice)localIterator.next();
      if (localBleDevice.getDataTypes().contains(paramDataType)) {
        localArrayList.add(localBleDevice);
      }
    }
    return Collections.unmodifiableList(localArrayList);
  }
  
  public Status getStatus()
  {
    return this.CM;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.CM, this.UU });
  }
  
  public String toString()
  {
    return n.h(this).a("status", this.CM).a("bleDevices", this.UU).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/result/BleDevicesResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */