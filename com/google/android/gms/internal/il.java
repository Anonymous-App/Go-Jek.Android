package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class il
  implements SafeParcelable
{
  public static final Parcelable.Creator<il> CREATOR = new im();
  private final int BR;
  private double FA;
  private boolean FB;
  private int GB;
  private int GC;
  private ApplicationMetadata GN;
  
  public il()
  {
    this(3, NaN.0D, false, -1, null, -1);
  }
  
  il(int paramInt1, double paramDouble, boolean paramBoolean, int paramInt2, ApplicationMetadata paramApplicationMetadata, int paramInt3)
  {
    this.BR = paramInt1;
    this.FA = paramDouble;
    this.FB = paramBoolean;
    this.GB = paramInt2;
    this.GN = paramApplicationMetadata;
    this.GC = paramInt3;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof il)) {
        return false;
      }
      paramObject = (il)paramObject;
    } while ((this.FA == ((il)paramObject).FA) && (this.FB == ((il)paramObject).FB) && (this.GB == ((il)paramObject).GB) && (ik.a(this.GN, ((il)paramObject).GN)) && (this.GC == ((il)paramObject).GC));
    return false;
  }
  
  public double fE()
  {
    return this.FA;
  }
  
  public boolean fM()
  {
    return this.FB;
  }
  
  public int fN()
  {
    return this.GB;
  }
  
  public int fO()
  {
    return this.GC;
  }
  
  public ApplicationMetadata getApplicationMetadata()
  {
    return this.GN;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Double.valueOf(this.FA), Boolean.valueOf(this.FB), Integer.valueOf(this.GB), this.GN, Integer.valueOf(this.GC) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    im.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/il.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */