package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;

public class mr
  implements SafeParcelable
{
  public static final Parcelable.Creator<mr> CREATOR = new ms();
  final int BR;
  private final String Sz;
  private final LatLng ahY;
  private final List<mp> ahZ;
  private final String aia;
  private final String aib;
  private final String mName;
  
  mr(int paramInt, String paramString1, LatLng paramLatLng, String paramString2, List<mp> paramList, String paramString3, String paramString4)
  {
    this.BR = paramInt;
    this.mName = paramString1;
    this.ahY = paramLatLng;
    this.Sz = paramString2;
    this.ahZ = new ArrayList(paramList);
    this.aia = paramString3;
    this.aib = paramString4;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getAddress()
  {
    return this.Sz;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public String getPhoneNumber()
  {
    return this.aia;
  }
  
  public LatLng ml()
  {
    return this.ahY;
  }
  
  public List<mp> mm()
  {
    return this.ahZ;
  }
  
  public String mn()
  {
    return this.aib;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ms.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/mr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */