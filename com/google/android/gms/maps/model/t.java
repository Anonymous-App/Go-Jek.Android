package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class t
  implements Parcelable.Creator<StreetViewPanoramaOrientation>
{
  static void a(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramStreetViewPanoramaOrientation.getVersionCode());
    b.a(paramParcel, 2, paramStreetViewPanoramaOrientation.tilt);
    b.a(paramParcel, 3, paramStreetViewPanoramaOrientation.bearing);
    b.H(paramParcel, paramInt);
  }
  
  public StreetViewPanoramaOrientation cT(Parcel paramParcel)
  {
    float f2 = 0.0F;
    int j = a.C(paramParcel);
    int i = 0;
    float f1 = 0.0F;
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
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new StreetViewPanoramaOrientation(i, f1, f2);
  }
  
  public StreetViewPanoramaOrientation[] eJ(int paramInt)
  {
    return new StreetViewPanoramaOrientation[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/model/t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */