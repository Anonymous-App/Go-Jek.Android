package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class q
  implements Parcelable.Creator<StreetViewPanoramaCamera>
{
  static void a(StreetViewPanoramaCamera paramStreetViewPanoramaCamera, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramStreetViewPanoramaCamera.getVersionCode());
    b.a(paramParcel, 2, paramStreetViewPanoramaCamera.zoom);
    b.a(paramParcel, 3, paramStreetViewPanoramaCamera.tilt);
    b.a(paramParcel, 4, paramStreetViewPanoramaCamera.bearing);
    b.H(paramParcel, paramInt);
  }
  
  public StreetViewPanoramaCamera cQ(Parcel paramParcel)
  {
    float f3 = 0.0F;
    int j = a.C(paramParcel);
    float f1 = 0.0F;
    int i = 0;
    float f2 = 0.0F;
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
        f1 = a.l(paramParcel, k);
        break;
      case 3: 
        f2 = a.l(paramParcel, k);
        break;
      case 4: 
        f3 = a.l(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new StreetViewPanoramaCamera(i, f1, f2, f3);
  }
  
  public StreetViewPanoramaCamera[] eG(int paramInt)
  {
    return new StreetViewPanoramaCamera[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/model/q.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */