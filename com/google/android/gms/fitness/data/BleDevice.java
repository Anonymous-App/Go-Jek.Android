package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ki;
import java.util.Collections;
import java.util.List;

public class BleDevice
  implements SafeParcelable
{
  public static final Parcelable.Creator<BleDevice> CREATOR = new c();
  private final int BR;
  private final List<String> SA;
  private final List<DataType> SB;
  private final String Sz;
  private final String mName;
  
  BleDevice(int paramInt, String paramString1, String paramString2, List<String> paramList, List<DataType> paramList1)
  {
    this.BR = paramInt;
    this.Sz = paramString1;
    this.mName = paramString2;
    this.SA = Collections.unmodifiableList(paramList);
    this.SB = Collections.unmodifiableList(paramList1);
  }
  
  private boolean a(BleDevice paramBleDevice)
  {
    return (this.mName.equals(paramBleDevice.mName)) && (this.Sz.equals(paramBleDevice.Sz)) && (ki.a(paramBleDevice.SA, this.SA)) && (ki.a(this.SB, paramBleDevice.SB));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof BleDevice)) && (a((BleDevice)paramObject)));
  }
  
  public String getAddress()
  {
    return this.Sz;
  }
  
  public List<DataType> getDataTypes()
  {
    return this.SB;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public List<String> getSupportedProfiles()
  {
    return this.SA;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.mName, this.Sz, this.SA, this.SB });
  }
  
  public String toString()
  {
    return n.h(this).a("name", this.mName).a("address", this.Sz).a("dataTypes", this.SB).a("supportedProfiles", this.SA).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    c.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/BleDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */