package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnSyncMoreResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnSyncMoreResponse> CREATOR = new at();
  final int BR;
  final boolean Oz;
  
  OnSyncMoreResponse(int paramInt, boolean paramBoolean)
  {
    this.BR = paramInt;
    this.Oz = paramBoolean;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    at.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/OnSyncMoreResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */