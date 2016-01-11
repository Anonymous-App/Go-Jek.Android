package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnLoadRealtimeResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnLoadRealtimeResponse> CREATOR = new aq();
  final int BR;
  final boolean vR;
  
  OnLoadRealtimeResponse(int paramInt, boolean paramBoolean)
  {
    this.BR = paramInt;
    this.vR = paramBoolean;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    aq.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/OnLoadRealtimeResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */