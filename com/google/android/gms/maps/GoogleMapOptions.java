package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.util.AttributeSet;
import com.google.android.gms.R.styleable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.v;
import com.google.android.gms.maps.model.CameraPosition;

public final class GoogleMapOptions
  implements SafeParcelable
{
  public static final a CREATOR = new a();
  private final int BR;
  private Boolean aiG;
  private Boolean aiH;
  private int aiI = -1;
  private CameraPosition aiJ;
  private Boolean aiK;
  private Boolean aiL;
  private Boolean aiM;
  private Boolean aiN;
  private Boolean aiO;
  private Boolean aiP;
  
  public GoogleMapOptions()
  {
    this.BR = 1;
  }
  
  GoogleMapOptions(int paramInt1, byte paramByte1, byte paramByte2, int paramInt2, CameraPosition paramCameraPosition, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, byte paramByte7, byte paramByte8)
  {
    this.BR = paramInt1;
    this.aiG = com.google.android.gms.maps.internal.a.a(paramByte1);
    this.aiH = com.google.android.gms.maps.internal.a.a(paramByte2);
    this.aiI = paramInt2;
    this.aiJ = paramCameraPosition;
    this.aiK = com.google.android.gms.maps.internal.a.a(paramByte3);
    this.aiL = com.google.android.gms.maps.internal.a.a(paramByte4);
    this.aiM = com.google.android.gms.maps.internal.a.a(paramByte5);
    this.aiN = com.google.android.gms.maps.internal.a.a(paramByte6);
    this.aiO = com.google.android.gms.maps.internal.a.a(paramByte7);
    this.aiP = com.google.android.gms.maps.internal.a.a(paramByte8);
  }
  
  public static GoogleMapOptions createFromAttributes(Context paramContext, AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet == null) {
      return null;
    }
    TypedArray localTypedArray = paramContext.getResources().obtainAttributes(paramAttributeSet, R.styleable.MapAttrs);
    GoogleMapOptions localGoogleMapOptions = new GoogleMapOptions();
    if (localTypedArray.hasValue(R.styleable.MapAttrs_mapType)) {
      localGoogleMapOptions.mapType(localTypedArray.getInt(R.styleable.MapAttrs_mapType, -1));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_zOrderOnTop)) {
      localGoogleMapOptions.zOrderOnTop(localTypedArray.getBoolean(R.styleable.MapAttrs_zOrderOnTop, false));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_useViewLifecycle)) {
      localGoogleMapOptions.useViewLifecycleInFragment(localTypedArray.getBoolean(R.styleable.MapAttrs_useViewLifecycle, false));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiCompass)) {
      localGoogleMapOptions.compassEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiCompass, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiRotateGestures)) {
      localGoogleMapOptions.rotateGesturesEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiRotateGestures, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiScrollGestures)) {
      localGoogleMapOptions.scrollGesturesEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiScrollGestures, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiTiltGestures)) {
      localGoogleMapOptions.tiltGesturesEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiTiltGestures, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiZoomGestures)) {
      localGoogleMapOptions.zoomGesturesEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiZoomGestures, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiZoomControls)) {
      localGoogleMapOptions.zoomControlsEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiZoomControls, true));
    }
    localGoogleMapOptions.camera(CameraPosition.createFromAttributes(paramContext, paramAttributeSet));
    localTypedArray.recycle();
    return localGoogleMapOptions;
  }
  
  public GoogleMapOptions camera(CameraPosition paramCameraPosition)
  {
    this.aiJ = paramCameraPosition;
    return this;
  }
  
  public GoogleMapOptions compassEnabled(boolean paramBoolean)
  {
    this.aiL = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public CameraPosition getCamera()
  {
    return this.aiJ;
  }
  
  public Boolean getCompassEnabled()
  {
    return this.aiL;
  }
  
  public int getMapType()
  {
    return this.aiI;
  }
  
  public Boolean getRotateGesturesEnabled()
  {
    return this.aiP;
  }
  
  public Boolean getScrollGesturesEnabled()
  {
    return this.aiM;
  }
  
  public Boolean getTiltGesturesEnabled()
  {
    return this.aiO;
  }
  
  public Boolean getUseViewLifecycleInFragment()
  {
    return this.aiH;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public Boolean getZOrderOnTop()
  {
    return this.aiG;
  }
  
  public Boolean getZoomControlsEnabled()
  {
    return this.aiK;
  }
  
  public Boolean getZoomGesturesEnabled()
  {
    return this.aiN;
  }
  
  public GoogleMapOptions mapType(int paramInt)
  {
    this.aiI = paramInt;
    return this;
  }
  
  byte mr()
  {
    return com.google.android.gms.maps.internal.a.c(this.aiG);
  }
  
  byte ms()
  {
    return com.google.android.gms.maps.internal.a.c(this.aiH);
  }
  
  byte mt()
  {
    return com.google.android.gms.maps.internal.a.c(this.aiK);
  }
  
  byte mu()
  {
    return com.google.android.gms.maps.internal.a.c(this.aiL);
  }
  
  byte mv()
  {
    return com.google.android.gms.maps.internal.a.c(this.aiM);
  }
  
  byte mw()
  {
    return com.google.android.gms.maps.internal.a.c(this.aiN);
  }
  
  byte mx()
  {
    return com.google.android.gms.maps.internal.a.c(this.aiO);
  }
  
  byte my()
  {
    return com.google.android.gms.maps.internal.a.c(this.aiP);
  }
  
  public GoogleMapOptions rotateGesturesEnabled(boolean paramBoolean)
  {
    this.aiP = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions scrollGesturesEnabled(boolean paramBoolean)
  {
    this.aiM = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions tiltGesturesEnabled(boolean paramBoolean)
  {
    this.aiO = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions useViewLifecycleInFragment(boolean paramBoolean)
  {
    this.aiH = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (v.mM())
    {
      b.a(this, paramParcel, paramInt);
      return;
    }
    a.a(this, paramParcel, paramInt);
  }
  
  public GoogleMapOptions zOrderOnTop(boolean paramBoolean)
  {
    this.aiG = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions zoomControlsEnabled(boolean paramBoolean)
  {
    this.aiK = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions zoomGesturesEnabled(boolean paramBoolean)
  {
    this.aiN = Boolean.valueOf(paramBoolean);
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/GoogleMapOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */