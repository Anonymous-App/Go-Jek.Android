package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class l
  implements SafeParcelable
{
  public static final Parcelable.Creator<l> CREATOR = new m();
  private final int BR;
  long auK;
  long auL;
  
  l()
  {
    this.BR = 1;
  }
  
  l(int paramInt, long paramLong1, long paramLong2)
  {
    this.BR = paramInt;
    this.auK = paramLong1;
    this.auL = paramLong2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    m.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/wobs/l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */