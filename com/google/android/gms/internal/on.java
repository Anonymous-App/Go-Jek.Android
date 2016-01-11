package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class on
  implements SafeParcelable
{
  public static final Parcelable.Creator<on> CREATOR = new oo();
  private final int BR;
  int[] atN;
  
  on()
  {
    this(1, null);
  }
  
  on(int paramInt, int[] paramArrayOfInt)
  {
    this.BR = paramInt;
    this.atN = paramArrayOfInt;
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
    oo.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/on.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */