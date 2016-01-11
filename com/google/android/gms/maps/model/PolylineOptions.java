package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.v;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class PolylineOptions
  implements SafeParcelable
{
  public static final o CREATOR = new o();
  private final int BR;
  private float ajL = 0.0F;
  private boolean ajM = true;
  private float ajQ = 10.0F;
  private final List<LatLng> akl;
  private boolean akn = false;
  private int mColor = -16777216;
  
  public PolylineOptions()
  {
    this.BR = 1;
    this.akl = new ArrayList();
  }
  
  PolylineOptions(int paramInt1, List paramList, float paramFloat1, int paramInt2, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.BR = paramInt1;
    this.akl = paramList;
    this.ajQ = paramFloat1;
    this.mColor = paramInt2;
    this.ajL = paramFloat2;
    this.ajM = paramBoolean1;
    this.akn = paramBoolean2;
  }
  
  public PolylineOptions add(LatLng paramLatLng)
  {
    this.akl.add(paramLatLng);
    return this;
  }
  
  public PolylineOptions add(LatLng... paramVarArgs)
  {
    this.akl.addAll(Arrays.asList(paramVarArgs));
    return this;
  }
  
  public PolylineOptions addAll(Iterable<LatLng> paramIterable)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      LatLng localLatLng = (LatLng)paramIterable.next();
      this.akl.add(localLatLng);
    }
    return this;
  }
  
  public PolylineOptions color(int paramInt)
  {
    this.mColor = paramInt;
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public PolylineOptions geodesic(boolean paramBoolean)
  {
    this.akn = paramBoolean;
    return this;
  }
  
  public int getColor()
  {
    return this.mColor;
  }
  
  public List<LatLng> getPoints()
  {
    return this.akl;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public float getWidth()
  {
    return this.ajQ;
  }
  
  public float getZIndex()
  {
    return this.ajL;
  }
  
  public boolean isGeodesic()
  {
    return this.akn;
  }
  
  public boolean isVisible()
  {
    return this.ajM;
  }
  
  public PolylineOptions visible(boolean paramBoolean)
  {
    this.ajM = paramBoolean;
    return this;
  }
  
  public PolylineOptions width(float paramFloat)
  {
    this.ajQ = paramFloat;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (v.mM())
    {
      p.a(this, paramParcel, paramInt);
      return;
    }
    o.a(this, paramParcel, paramInt);
  }
  
  public PolylineOptions zIndex(float paramFloat)
  {
    this.ajL = paramFloat;
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/model/PolylineOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */