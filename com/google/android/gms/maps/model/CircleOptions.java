package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.v;

public final class CircleOptions
  implements SafeParcelable
{
  public static final c CREATOR = new c();
  private final int BR;
  private LatLng ajG = null;
  private double ajH = 0.0D;
  private float ajI = 10.0F;
  private int ajJ = -16777216;
  private int ajK = 0;
  private float ajL = 0.0F;
  private boolean ajM = true;
  
  public CircleOptions()
  {
    this.BR = 1;
  }
  
  CircleOptions(int paramInt1, LatLng paramLatLng, double paramDouble, float paramFloat1, int paramInt2, int paramInt3, float paramFloat2, boolean paramBoolean)
  {
    this.BR = paramInt1;
    this.ajG = paramLatLng;
    this.ajH = paramDouble;
    this.ajI = paramFloat1;
    this.ajJ = paramInt2;
    this.ajK = paramInt3;
    this.ajL = paramFloat2;
    this.ajM = paramBoolean;
  }
  
  public CircleOptions center(LatLng paramLatLng)
  {
    this.ajG = paramLatLng;
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public CircleOptions fillColor(int paramInt)
  {
    this.ajK = paramInt;
    return this;
  }
  
  public LatLng getCenter()
  {
    return this.ajG;
  }
  
  public int getFillColor()
  {
    return this.ajK;
  }
  
  public double getRadius()
  {
    return this.ajH;
  }
  
  public int getStrokeColor()
  {
    return this.ajJ;
  }
  
  public float getStrokeWidth()
  {
    return this.ajI;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public float getZIndex()
  {
    return this.ajL;
  }
  
  public boolean isVisible()
  {
    return this.ajM;
  }
  
  public CircleOptions radius(double paramDouble)
  {
    this.ajH = paramDouble;
    return this;
  }
  
  public CircleOptions strokeColor(int paramInt)
  {
    this.ajJ = paramInt;
    return this;
  }
  
  public CircleOptions strokeWidth(float paramFloat)
  {
    this.ajI = paramFloat;
    return this;
  }
  
  public CircleOptions visible(boolean paramBoolean)
  {
    this.ajM = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (v.mM())
    {
      d.a(this, paramParcel, paramInt);
      return;
    }
    c.a(this, paramParcel, paramInt);
  }
  
  public CircleOptions zIndex(float paramFloat)
  {
    this.ajL = paramFloat;
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/model/CircleOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */