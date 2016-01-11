package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@ez
public final class av
  implements SafeParcelable
{
  public static final aw CREATOR = new aw();
  public final Bundle extras;
  public final long nT;
  public final int nU;
  public final List<String> nV;
  public final boolean nW;
  public final int nX;
  public final boolean nY;
  public final String nZ;
  public final bj oa;
  public final Location ob;
  public final String oc;
  public final Bundle od;
  public final int versionCode;
  
  public av(int paramInt1, long paramLong, Bundle paramBundle1, int paramInt2, List<String> paramList, boolean paramBoolean1, int paramInt3, boolean paramBoolean2, String paramString1, bj parambj, Location paramLocation, String paramString2, Bundle paramBundle2)
  {
    this.versionCode = paramInt1;
    this.nT = paramLong;
    this.extras = paramBundle1;
    this.nU = paramInt2;
    this.nV = paramList;
    this.nW = paramBoolean1;
    this.nX = paramInt3;
    this.nY = paramBoolean2;
    this.nZ = paramString1;
    this.oa = parambj;
    this.ob = paramLocation;
    this.oc = paramString2;
    this.od = paramBundle2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    aw.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/av.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */