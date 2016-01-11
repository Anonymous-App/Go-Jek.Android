package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class o
  implements Parcelable.Creator<PolylineOptions>
{
  static void a(PolylineOptions paramPolylineOptions, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramPolylineOptions.getVersionCode());
    b.c(paramParcel, 2, paramPolylineOptions.getPoints(), false);
    b.a(paramParcel, 3, paramPolylineOptions.getWidth());
    b.c(paramParcel, 4, paramPolylineOptions.getColor());
    b.a(paramParcel, 5, paramPolylineOptions.getZIndex());
    b.a(paramParcel, 6, paramPolylineOptions.isVisible());
    b.a(paramParcel, 7, paramPolylineOptions.isGeodesic());
    b.H(paramParcel, paramInt);
  }
  
  public PolylineOptions cP(Parcel paramParcel)
  {
    float f1 = 0.0F;
    boolean bool1 = false;
    int k = a.C(paramParcel);
    ArrayList localArrayList = null;
    boolean bool2 = false;
    int i = 0;
    float f2 = 0.0F;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        j = a.g(paramParcel, m);
        break;
      case 2: 
        localArrayList = a.c(paramParcel, m, LatLng.CREATOR);
        break;
      case 3: 
        f2 = a.l(paramParcel, m);
        break;
      case 4: 
        i = a.g(paramParcel, m);
        break;
      case 5: 
        f1 = a.l(paramParcel, m);
        break;
      case 6: 
        bool2 = a.c(paramParcel, m);
        break;
      case 7: 
        bool1 = a.c(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new PolylineOptions(j, localArrayList, f2, i, f1, bool2, bool1);
  }
  
  public PolylineOptions[] eF(int paramInt)
  {
    return new PolylineOptions[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/model/o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */