package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.v;

public final class LatLngBounds
  implements SafeParcelable
{
  public static final g CREATOR = new g();
  private final int BR;
  public final LatLng northeast;
  public final LatLng southwest;
  
  LatLngBounds(int paramInt, LatLng paramLatLng1, LatLng paramLatLng2)
  {
    o.b(paramLatLng1, "null southwest");
    o.b(paramLatLng2, "null northeast");
    if (paramLatLng2.latitude >= paramLatLng1.latitude) {}
    for (boolean bool = true;; bool = false)
    {
      o.b(bool, "southern latitude exceeds northern latitude (%s > %s)", new Object[] { Double.valueOf(paramLatLng1.latitude), Double.valueOf(paramLatLng2.latitude) });
      this.BR = paramInt;
      this.southwest = paramLatLng1;
      this.northeast = paramLatLng2;
      return;
    }
  }
  
  public LatLngBounds(LatLng paramLatLng1, LatLng paramLatLng2)
  {
    this(1, paramLatLng1, paramLatLng2);
  }
  
  private static double b(double paramDouble1, double paramDouble2)
  {
    return (paramDouble1 - paramDouble2 + 360.0D) % 360.0D;
  }
  
  public static Builder builder()
  {
    return new Builder();
  }
  
  private static double c(double paramDouble1, double paramDouble2)
  {
    return (paramDouble2 - paramDouble1 + 360.0D) % 360.0D;
  }
  
  private boolean c(double paramDouble)
  {
    return (this.southwest.latitude <= paramDouble) && (paramDouble <= this.northeast.latitude);
  }
  
  private boolean d(double paramDouble)
  {
    boolean bool = false;
    if (this.southwest.longitude <= this.northeast.longitude) {
      return (this.southwest.longitude <= paramDouble) && (paramDouble <= this.northeast.longitude);
    }
    if ((this.southwest.longitude <= paramDouble) || (paramDouble <= this.northeast.longitude)) {
      bool = true;
    }
    return bool;
  }
  
  public boolean contains(LatLng paramLatLng)
  {
    return (c(paramLatLng.latitude)) && (d(paramLatLng.longitude));
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
      if (!(paramObject instanceof LatLngBounds)) {
        return false;
      }
      paramObject = (LatLngBounds)paramObject;
    } while ((this.southwest.equals(((LatLngBounds)paramObject).southwest)) && (this.northeast.equals(((LatLngBounds)paramObject).northeast)));
    return false;
  }
  
  public LatLng getCenter()
  {
    double d2 = (this.southwest.latitude + this.northeast.latitude) / 2.0D;
    double d1 = this.northeast.longitude;
    double d3 = this.southwest.longitude;
    if (d3 <= d1) {}
    for (d1 = (d1 + d3) / 2.0D;; d1 = (d1 + 360.0D + d3) / 2.0D) {
      return new LatLng(d2, d1);
    }
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.southwest, this.northeast });
  }
  
  public LatLngBounds including(LatLng paramLatLng)
  {
    double d4 = Math.min(this.southwest.latitude, paramLatLng.latitude);
    double d5 = Math.max(this.northeast.latitude, paramLatLng.latitude);
    double d2 = this.northeast.longitude;
    double d3 = this.southwest.longitude;
    double d1 = paramLatLng.longitude;
    if (!d(d1)) {
      if (b(d3, d1) >= c(d2, d1)) {}
    }
    for (;;)
    {
      return new LatLngBounds(new LatLng(d4, d1), new LatLng(d5, d2));
      d2 = d1;
      d1 = d3;
      continue;
      d1 = d3;
    }
  }
  
  public String toString()
  {
    return n.h(this).a("southwest", this.southwest).a("northeast", this.northeast).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (v.mM())
    {
      h.a(this, paramParcel, paramInt);
      return;
    }
    g.a(this, paramParcel, paramInt);
  }
  
  public static final class Builder
  {
    private double ajY = Double.POSITIVE_INFINITY;
    private double ajZ = Double.NEGATIVE_INFINITY;
    private double aka = NaN.0D;
    private double akb = NaN.0D;
    
    private boolean d(double paramDouble)
    {
      boolean bool = false;
      if (this.aka <= this.akb) {
        return (this.aka <= paramDouble) && (paramDouble <= this.akb);
      }
      if ((this.aka <= paramDouble) || (paramDouble <= this.akb)) {
        bool = true;
      }
      return bool;
    }
    
    public LatLngBounds build()
    {
      if (!Double.isNaN(this.aka)) {}
      for (boolean bool = true;; bool = false)
      {
        o.a(bool, "no included points");
        return new LatLngBounds(new LatLng(this.ajY, this.aka), new LatLng(this.ajZ, this.akb));
      }
    }
    
    public Builder include(LatLng paramLatLng)
    {
      this.ajY = Math.min(this.ajY, paramLatLng.latitude);
      this.ajZ = Math.max(this.ajZ, paramLatLng.latitude);
      double d = paramLatLng.longitude;
      if (Double.isNaN(this.aka))
      {
        this.aka = d;
        this.akb = d;
      }
      while (d(d)) {
        return this;
      }
      if (LatLngBounds.d(this.aka, d) < LatLngBounds.e(this.akb, d))
      {
        this.aka = d;
        return this;
      }
      this.akb = d;
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/model/LatLngBounds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */