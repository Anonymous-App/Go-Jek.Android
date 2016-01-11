package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class StreetViewPanoramaLocation
  implements SafeParcelable
{
  public static final s CREATOR = new s();
  private final int BR;
  public final StreetViewPanoramaLink[] links;
  public final String panoId;
  public final LatLng position;
  
  StreetViewPanoramaLocation(int paramInt, StreetViewPanoramaLink[] paramArrayOfStreetViewPanoramaLink, LatLng paramLatLng, String paramString)
  {
    this.BR = paramInt;
    this.links = paramArrayOfStreetViewPanoramaLink;
    this.position = paramLatLng;
    this.panoId = paramString;
  }
  
  public StreetViewPanoramaLocation(StreetViewPanoramaLink[] paramArrayOfStreetViewPanoramaLink, LatLng paramLatLng, String paramString)
  {
    this(1, paramArrayOfStreetViewPanoramaLink, paramLatLng, paramString);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof StreetViewPanoramaLocation)) {
        return false;
      }
      paramObject = (StreetViewPanoramaLocation)paramObject;
    } while ((this.panoId.equals(((StreetViewPanoramaLocation)paramObject).panoId)) && (this.position.equals(((StreetViewPanoramaLocation)paramObject).position)));
    return false;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.position, this.panoId });
  }
  
  public String toString()
  {
    return n.h(this).a("panoId", this.panoId).a("position", this.position.toString()).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    s.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/model/StreetViewPanoramaLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */