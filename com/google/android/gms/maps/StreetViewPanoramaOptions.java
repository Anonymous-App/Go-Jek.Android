package com.google.android.gms.maps;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public final class StreetViewPanoramaOptions
  implements SafeParcelable
{
  public static final c CREATOR = new c();
  private final int BR;
  private Boolean aiH;
  private Boolean aiN = Boolean.valueOf(true);
  private StreetViewPanoramaCamera ajj;
  private String ajk;
  private LatLng ajl;
  private Integer ajm;
  private Boolean ajn = Boolean.valueOf(true);
  private Boolean ajo = Boolean.valueOf(true);
  private Boolean ajp = Boolean.valueOf(true);
  
  public StreetViewPanoramaOptions()
  {
    this.BR = 1;
  }
  
  StreetViewPanoramaOptions(int paramInt, StreetViewPanoramaCamera paramStreetViewPanoramaCamera, String paramString, LatLng paramLatLng, Integer paramInteger, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5)
  {
    this.BR = paramInt;
    this.ajj = paramStreetViewPanoramaCamera;
    this.ajl = paramLatLng;
    this.ajm = paramInteger;
    this.ajk = paramString;
    this.ajn = a.a(paramByte1);
    this.aiN = a.a(paramByte2);
    this.ajo = a.a(paramByte3);
    this.ajp = a.a(paramByte4);
    this.aiH = a.a(paramByte5);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Boolean getPanningGesturesEnabled()
  {
    return this.ajo;
  }
  
  public String getPanoramaId()
  {
    return this.ajk;
  }
  
  public LatLng getPosition()
  {
    return this.ajl;
  }
  
  public Integer getRadius()
  {
    return this.ajm;
  }
  
  public Boolean getStreetNamesEnabled()
  {
    return this.ajp;
  }
  
  public StreetViewPanoramaCamera getStreetViewPanoramaCamera()
  {
    return this.ajj;
  }
  
  public Boolean getUseViewLifecycleInFragment()
  {
    return this.aiH;
  }
  
  public Boolean getUserNavigationEnabled()
  {
    return this.ajn;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public Boolean getZoomGesturesEnabled()
  {
    return this.aiN;
  }
  
  byte mE()
  {
    return a.c(this.ajn);
  }
  
  byte mF()
  {
    return a.c(this.ajo);
  }
  
  byte mG()
  {
    return a.c(this.ajp);
  }
  
  byte ms()
  {
    return a.c(this.aiH);
  }
  
  byte mw()
  {
    return a.c(this.aiN);
  }
  
  public StreetViewPanoramaOptions panningGesturesEnabled(boolean paramBoolean)
  {
    this.ajo = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public StreetViewPanoramaOptions panoramaCamera(StreetViewPanoramaCamera paramStreetViewPanoramaCamera)
  {
    this.ajj = paramStreetViewPanoramaCamera;
    return this;
  }
  
  public StreetViewPanoramaOptions panoramaId(String paramString)
  {
    this.ajk = paramString;
    return this;
  }
  
  public StreetViewPanoramaOptions position(LatLng paramLatLng)
  {
    this.ajl = paramLatLng;
    return this;
  }
  
  public StreetViewPanoramaOptions position(LatLng paramLatLng, Integer paramInteger)
  {
    this.ajl = paramLatLng;
    this.ajm = paramInteger;
    return this;
  }
  
  public StreetViewPanoramaOptions streetNamesEnabled(boolean paramBoolean)
  {
    this.ajp = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public StreetViewPanoramaOptions useViewLifecycleInFragment(boolean paramBoolean)
  {
    this.aiH = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public StreetViewPanoramaOptions userNavigationEnabled(boolean paramBoolean)
  {
    this.ajn = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    c.a(this, paramParcel, paramInt);
  }
  
  public StreetViewPanoramaOptions zoomGesturesEnabled(boolean paramBoolean)
  {
    this.aiN = Boolean.valueOf(paramBoolean);
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/StreetViewPanoramaOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */