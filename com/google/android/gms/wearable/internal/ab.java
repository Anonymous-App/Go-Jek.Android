package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ab
  implements SafeParcelable
{
  public static final Parcelable.Creator<ab> CREATOR = new ac();
  public final ak avC;
  public final int statusCode;
  public final int versionCode;
  
  ab(int paramInt1, int paramInt2, ak paramak)
  {
    this.versionCode = paramInt1;
    this.statusCode = paramInt2;
    this.avC = paramak;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ac.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/internal/ab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */