package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class f
  implements SafeParcelable
{
  public static final Parcelable.Creator<f> CREATOR = new i();
  private final int BR;
  l atc;
  g auC;
  String label;
  String type;
  
  f()
  {
    this.BR = 1;
  }
  
  f(int paramInt, String paramString1, g paramg, String paramString2, l paraml)
  {
    this.BR = paramInt;
    this.label = paramString1;
    this.auC = paramg;
    this.type = paramString2;
    this.atc = paraml;
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
    i.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/wobs/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */