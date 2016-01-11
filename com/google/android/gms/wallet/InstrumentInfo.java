package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class InstrumentInfo
  implements SafeParcelable
{
  public static final Parcelable.Creator<InstrumentInfo> CREATOR = new h();
  private final int BR;
  private String asN;
  private String asO;
  
  InstrumentInfo(int paramInt, String paramString1, String paramString2)
  {
    this.BR = paramInt;
    this.asN = paramString1;
    this.asO = paramString2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getInstrumentDetails()
  {
    return this.asO;
  }
  
  public String getInstrumentType()
  {
    return this.asN;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    h.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/InstrumentInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */