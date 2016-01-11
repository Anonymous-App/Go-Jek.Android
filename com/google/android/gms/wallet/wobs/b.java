package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class b
  implements SafeParcelable
{
  public static final Parcelable.Creator<b> CREATOR = new c();
  private final int BR;
  String label;
  String value;
  
  b()
  {
    this.BR = 1;
  }
  
  b(int paramInt, String paramString1, String paramString2)
  {
    this.BR = paramInt;
    this.label = paramString1;
    this.value = paramString2;
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
    c.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/wobs/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */