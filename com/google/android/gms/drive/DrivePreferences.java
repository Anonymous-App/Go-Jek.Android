package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DrivePreferences
  implements SafeParcelable
{
  public static final Parcelable.Creator<DrivePreferences> CREATOR = new d();
  final int BR;
  final boolean Nm;
  
  DrivePreferences(int paramInt, boolean paramBoolean)
  {
    this.BR = paramInt;
    this.Nm = paramBoolean;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    d.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/DrivePreferences.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */