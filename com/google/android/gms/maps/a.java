package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.maps.model.CameraPosition;

public class a
  implements Parcelable.Creator<GoogleMapOptions>
{
  static void a(GoogleMapOptions paramGoogleMapOptions, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramGoogleMapOptions.getVersionCode());
    b.a(paramParcel, 2, paramGoogleMapOptions.mr());
    b.a(paramParcel, 3, paramGoogleMapOptions.ms());
    b.c(paramParcel, 4, paramGoogleMapOptions.getMapType());
    b.a(paramParcel, 5, paramGoogleMapOptions.getCamera(), paramInt, false);
    b.a(paramParcel, 6, paramGoogleMapOptions.mt());
    b.a(paramParcel, 7, paramGoogleMapOptions.mu());
    b.a(paramParcel, 8, paramGoogleMapOptions.mv());
    b.a(paramParcel, 9, paramGoogleMapOptions.mw());
    b.a(paramParcel, 10, paramGoogleMapOptions.mx());
    b.a(paramParcel, 11, paramGoogleMapOptions.my());
    b.H(paramParcel, i);
  }
  
  public GoogleMapOptions cG(Parcel paramParcel)
  {
    byte b1 = 0;
    int k = com.google.android.gms.common.internal.safeparcel.a.C(paramParcel);
    CameraPosition localCameraPosition = null;
    byte b2 = 0;
    byte b3 = 0;
    byte b4 = 0;
    byte b5 = 0;
    byte b6 = 0;
    int i = 0;
    byte b7 = 0;
    byte b8 = 0;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = com.google.android.gms.common.internal.safeparcel.a.B(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.a.aD(m))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.a.b(paramParcel, m);
        break;
      case 1: 
        j = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, m);
        break;
      case 2: 
        b8 = com.google.android.gms.common.internal.safeparcel.a.e(paramParcel, m);
        break;
      case 3: 
        b7 = com.google.android.gms.common.internal.safeparcel.a.e(paramParcel, m);
        break;
      case 4: 
        i = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, m);
        break;
      case 5: 
        localCameraPosition = (CameraPosition)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, m, CameraPosition.CREATOR);
        break;
      case 6: 
        b6 = com.google.android.gms.common.internal.safeparcel.a.e(paramParcel, m);
        break;
      case 7: 
        b5 = com.google.android.gms.common.internal.safeparcel.a.e(paramParcel, m);
        break;
      case 8: 
        b4 = com.google.android.gms.common.internal.safeparcel.a.e(paramParcel, m);
        break;
      case 9: 
        b3 = com.google.android.gms.common.internal.safeparcel.a.e(paramParcel, m);
        break;
      case 10: 
        b2 = com.google.android.gms.common.internal.safeparcel.a.e(paramParcel, m);
        break;
      case 11: 
        b1 = com.google.android.gms.common.internal.safeparcel.a.e(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new GoogleMapOptions(j, b8, b7, i, localCameraPosition, b6, b5, b4, b3, b2, b1);
  }
  
  public GoogleMapOptions[] ew(int paramInt)
  {
    return new GoogleMapOptions[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */