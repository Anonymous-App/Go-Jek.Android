package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DrivePreferences;

public class OnDrivePreferencesResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnDrivePreferencesResponse> CREATOR = new al();
  final int BR;
  DrivePreferences Pr;
  
  OnDrivePreferencesResponse(int paramInt, DrivePreferences paramDrivePreferences)
  {
    this.BR = paramInt;
    this.Pr = paramDrivePreferences;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    al.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/OnDrivePreferencesResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */