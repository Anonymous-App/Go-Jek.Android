package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DrivePreferences;

public class al
  implements Parcelable.Creator<OnDrivePreferencesResponse>
{
  static void a(OnDrivePreferencesResponse paramOnDrivePreferencesResponse, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramOnDrivePreferencesResponse.BR);
    b.a(paramParcel, 2, paramOnDrivePreferencesResponse.Pr, paramInt, false);
    b.H(paramParcel, i);
  }
  
  public OnDrivePreferencesResponse an(Parcel paramParcel)
  {
    int j = a.C(paramParcel);
    int i = 0;
    DrivePreferences localDrivePreferences = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        localDrivePreferences = (DrivePreferences)a.a(paramParcel, k, DrivePreferences.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new OnDrivePreferencesResponse(i, localDrivePreferences);
  }
  
  public OnDrivePreferencesResponse[] bz(int paramInt)
  {
    return new OnDrivePreferencesResponse[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/al.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */