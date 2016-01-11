package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class d
  implements Parcelable.Creator<DrivePreferences>
{
  static void a(DrivePreferences paramDrivePreferences, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramDrivePreferences.BR);
    b.a(paramParcel, 2, paramDrivePreferences.Nm);
    b.H(paramParcel, paramInt);
  }
  
  public DrivePreferences P(Parcel paramParcel)
  {
    boolean bool = false;
    int j = a.C(paramParcel);
    int i = 0;
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
        bool = a.c(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new DrivePreferences(i, bool);
  }
  
  public DrivePreferences[] aU(int paramInt)
  {
    return new DrivePreferences[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */