package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ad
  implements SafeParcelable
{
  public static final Parcelable.Creator<ad> CREATOR = new ae();
  private final int BR;
  private final l UQ;
  
  ad(int paramInt, IBinder paramIBinder)
  {
    this.BR = paramInt;
    this.UQ = l.a.ay(paramIBinder);
  }
  
  public ad(BleScanCallback paramBleScanCallback)
  {
    this.BR = 1;
    this.UQ = a.a.je().b(paramBleScanCallback);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public IBinder jC()
  {
    return this.UQ.asBinder();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ae.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/ad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */