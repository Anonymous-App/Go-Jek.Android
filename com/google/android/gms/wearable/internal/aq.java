package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class aq
  implements SafeParcelable
{
  public static final Parcelable.Creator<aq> CREATOR = new ar();
  final int BR;
  public final ae avk;
  
  aq(int paramInt, IBinder paramIBinder)
  {
    this.BR = paramInt;
    if (paramIBinder != null)
    {
      this.avk = ae.a.bS(paramIBinder);
      return;
    }
    this.avk = null;
  }
  
  public aq(ae paramae)
  {
    this.BR = 1;
    this.avk = paramae;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  IBinder pV()
  {
    if (this.avk == null) {
      return null;
    }
    return this.avk.asBinder();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ar.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/internal/aq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */