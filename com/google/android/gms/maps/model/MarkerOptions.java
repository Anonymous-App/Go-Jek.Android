package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;
import com.google.android.gms.maps.internal.v;

public final class MarkerOptions
  implements SafeParcelable
{
  public static final k CREATOR = new k();
  private final int BR;
  private String Nw;
  private boolean ajM = true;
  private float ajU = 0.5F;
  private float ajV = 1.0F;
  private LatLng ajl;
  private String akd;
  private BitmapDescriptor ake;
  private boolean akf;
  private boolean akg = false;
  private float akh = 0.0F;
  private float aki = 0.5F;
  private float akj = 0.0F;
  private float mAlpha = 1.0F;
  
  public MarkerOptions()
  {
    this.BR = 1;
  }
  
  MarkerOptions(int paramInt, LatLng paramLatLng, String paramString1, String paramString2, IBinder paramIBinder, float paramFloat1, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    this.BR = paramInt;
    this.ajl = paramLatLng;
    this.Nw = paramString1;
    this.akd = paramString2;
    if (paramIBinder == null) {}
    for (paramLatLng = null;; paramLatLng = new BitmapDescriptor(d.a.am(paramIBinder)))
    {
      this.ake = paramLatLng;
      this.ajU = paramFloat1;
      this.ajV = paramFloat2;
      this.akf = paramBoolean1;
      this.ajM = paramBoolean2;
      this.akg = paramBoolean3;
      this.akh = paramFloat3;
      this.aki = paramFloat4;
      this.akj = paramFloat5;
      this.mAlpha = paramFloat6;
      return;
    }
  }
  
  public MarkerOptions alpha(float paramFloat)
  {
    this.mAlpha = paramFloat;
    return this;
  }
  
  public MarkerOptions anchor(float paramFloat1, float paramFloat2)
  {
    this.ajU = paramFloat1;
    this.ajV = paramFloat2;
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public MarkerOptions draggable(boolean paramBoolean)
  {
    this.akf = paramBoolean;
    return this;
  }
  
  public MarkerOptions flat(boolean paramBoolean)
  {
    this.akg = paramBoolean;
    return this;
  }
  
  public float getAlpha()
  {
    return this.mAlpha;
  }
  
  public float getAnchorU()
  {
    return this.ajU;
  }
  
  public float getAnchorV()
  {
    return this.ajV;
  }
  
  public BitmapDescriptor getIcon()
  {
    return this.ake;
  }
  
  public float getInfoWindowAnchorU()
  {
    return this.aki;
  }
  
  public float getInfoWindowAnchorV()
  {
    return this.akj;
  }
  
  public LatLng getPosition()
  {
    return this.ajl;
  }
  
  public float getRotation()
  {
    return this.akh;
  }
  
  public String getSnippet()
  {
    return this.akd;
  }
  
  public String getTitle()
  {
    return this.Nw;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public MarkerOptions icon(BitmapDescriptor paramBitmapDescriptor)
  {
    this.ake = paramBitmapDescriptor;
    return this;
  }
  
  public MarkerOptions infoWindowAnchor(float paramFloat1, float paramFloat2)
  {
    this.aki = paramFloat1;
    this.akj = paramFloat2;
    return this;
  }
  
  public boolean isDraggable()
  {
    return this.akf;
  }
  
  public boolean isFlat()
  {
    return this.akg;
  }
  
  public boolean isVisible()
  {
    return this.ajM;
  }
  
  IBinder mP()
  {
    if (this.ake == null) {
      return null;
    }
    return this.ake.mo().asBinder();
  }
  
  public MarkerOptions position(LatLng paramLatLng)
  {
    this.ajl = paramLatLng;
    return this;
  }
  
  public MarkerOptions rotation(float paramFloat)
  {
    this.akh = paramFloat;
    return this;
  }
  
  public MarkerOptions snippet(String paramString)
  {
    this.akd = paramString;
    return this;
  }
  
  public MarkerOptions title(String paramString)
  {
    this.Nw = paramString;
    return this;
  }
  
  public MarkerOptions visible(boolean paramBoolean)
  {
    this.ajM = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (v.mM())
    {
      l.a(this, paramParcel, paramInt);
      return;
    }
    k.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/model/MarkerOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */