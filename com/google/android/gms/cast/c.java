package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class c
  implements Parcelable.Creator<LaunchOptions>
{
  static void a(LaunchOptions paramLaunchOptions, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramLaunchOptions.getVersionCode());
    b.a(paramParcel, 2, paramLaunchOptions.getRelaunchIfRunning());
    b.a(paramParcel, 3, paramLaunchOptions.getLanguage(), false);
    b.H(paramParcel, paramInt);
  }
  
  public LaunchOptions[] Z(int paramInt)
  {
    return new LaunchOptions[paramInt];
  }
  
  public LaunchOptions v(Parcel paramParcel)
  {
    boolean bool = false;
    int j = a.C(paramParcel);
    String str = null;
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
        break;
      case 3: 
        str = a.o(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new LaunchOptions(i, bool, str);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/cast/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */