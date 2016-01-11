package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class j
  implements SafeParcelable
{
  public static final Parcelable.Creator<j> CREATOR = new k();
  private final int BR;
  String auJ;
  String tG;
  
  j()
  {
    this.BR = 1;
  }
  
  j(int paramInt, String paramString1, String paramString2)
  {
    this.BR = paramInt;
    this.auJ = paramString1;
    this.tG = paramString2;
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
    k.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/wobs/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */