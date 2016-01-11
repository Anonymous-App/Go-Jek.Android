package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class i
  implements Parcelable.Creator<Device>
{
  static void a(Device paramDevice, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.a(paramParcel, 1, paramDevice.getManufacturer(), false);
    b.c(paramParcel, 1000, paramDevice.getVersionCode());
    b.a(paramParcel, 2, paramDevice.getModel(), false);
    b.a(paramParcel, 3, paramDevice.getVersion(), false);
    b.a(paramParcel, 4, paramDevice.iU(), false);
    b.c(paramParcel, 5, paramDevice.getType());
    b.c(paramParcel, 6, paramDevice.iR());
    b.H(paramParcel, paramInt);
  }
  
  public Device bp(Parcel paramParcel)
  {
    int i = 0;
    String str1 = null;
    int m = a.C(paramParcel);
    int j = 0;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    int k = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        str4 = a.o(paramParcel, n);
        break;
      case 1000: 
        k = a.g(paramParcel, n);
        break;
      case 2: 
        str3 = a.o(paramParcel, n);
        break;
      case 3: 
        str2 = a.o(paramParcel, n);
        break;
      case 4: 
        str1 = a.o(paramParcel, n);
        break;
      case 5: 
        j = a.g(paramParcel, n);
        break;
      case 6: 
        i = a.g(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new Device(k, str4, str3, str2, str1, j, i);
  }
  
  public Device[] cF(int paramInt)
  {
    return new Device[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */