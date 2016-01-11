package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class s
  implements Parcelable.Creator<StreetViewPanoramaLocation>
{
  static void a(StreetViewPanoramaLocation paramStreetViewPanoramaLocation, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramStreetViewPanoramaLocation.getVersionCode());
    b.a(paramParcel, 2, paramStreetViewPanoramaLocation.links, paramInt, false);
    b.a(paramParcel, 3, paramStreetViewPanoramaLocation.position, paramInt, false);
    b.a(paramParcel, 4, paramStreetViewPanoramaLocation.panoId, false);
    b.H(paramParcel, i);
  }
  
  public StreetViewPanoramaLocation cS(Parcel paramParcel)
  {
    String str = null;
    int j = a.C(paramParcel);
    int i = 0;
    Object localObject2 = null;
    Object localObject1 = null;
    if (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      Object localObject3;
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        localObject3 = localObject2;
        localObject2 = localObject1;
        localObject1 = localObject3;
      }
      for (;;)
      {
        localObject3 = localObject2;
        localObject2 = localObject1;
        localObject1 = localObject3;
        break;
        i = a.g(paramParcel, k);
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        localObject3 = (StreetViewPanoramaLink[])a.b(paramParcel, k, StreetViewPanoramaLink.CREATOR);
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        localObject3 = (LatLng)a.a(paramParcel, k, LatLng.CREATOR);
        localObject2 = localObject1;
        localObject1 = localObject3;
        continue;
        str = a.o(paramParcel, k);
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new StreetViewPanoramaLocation(i, (StreetViewPanoramaLink[])localObject1, (LatLng)localObject2, str);
  }
  
  public StreetViewPanoramaLocation[] eI(int paramInt)
  {
    return new StreetViewPanoramaLocation[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/model/s.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */