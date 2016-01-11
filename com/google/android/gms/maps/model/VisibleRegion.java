package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.v;

public final class VisibleRegion
  implements SafeParcelable
{
  public static final y CREATOR = new y();
  private final int BR;
  public final LatLng farLeft;
  public final LatLng farRight;
  public final LatLngBounds latLngBounds;
  public final LatLng nearLeft;
  public final LatLng nearRight;
  
  VisibleRegion(int paramInt, LatLng paramLatLng1, LatLng paramLatLng2, LatLng paramLatLng3, LatLng paramLatLng4, LatLngBounds paramLatLngBounds)
  {
    this.BR = paramInt;
    this.nearLeft = paramLatLng1;
    this.nearRight = paramLatLng2;
    this.farLeft = paramLatLng3;
    this.farRight = paramLatLng4;
    this.latLngBounds = paramLatLngBounds;
  }
  
  public VisibleRegion(LatLng paramLatLng1, LatLng paramLatLng2, LatLng paramLatLng3, LatLng paramLatLng4, LatLngBounds paramLatLngBounds)
  {
    this(1, paramLatLng1, paramLatLng2, paramLatLng3, paramLatLng4, paramLatLngBounds);
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
      if (!(paramObject instanceof VisibleRegion)) {
        return false;
      }
      paramObject = (VisibleRegion)paramObject;
    } while ((this.nearLeft.equals(((VisibleRegion)paramObject).nearLeft)) && (this.nearRight.equals(((VisibleRegion)paramObject).nearRight)) && (this.farLeft.equals(((VisibleRegion)paramObject).farLeft)) && (this.farRight.equals(((VisibleRegion)paramObject).farRight)) && (this.latLngBounds.equals(((VisibleRegion)paramObject).latLngBounds)));
    return false;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.nearLeft, this.nearRight, this.farLeft, this.farRight, this.latLngBounds });
  }
  
  public String toString()
  {
    return n.h(this).a("nearLeft", this.nearLeft).a("nearRight", this.nearRight).a("farLeft", this.farLeft).a("farRight", this.farRight).a("latLngBounds", this.latLngBounds).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (v.mM())
    {
      z.a(this, paramParcel, paramInt);
      return;
    }
    y.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/model/VisibleRegion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */