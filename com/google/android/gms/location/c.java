package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class c
  implements SafeParcelable
{
  public static final d CREATOR = new d();
  private final int BR;
  int aex;
  int aey;
  long aez;
  
  c(int paramInt1, int paramInt2, int paramInt3, long paramLong)
  {
    this.BR = paramInt1;
    this.aex = paramInt2;
    this.aey = paramInt3;
    this.aez = paramLong;
  }
  
  private String ee(int paramInt)
  {
    switch (paramInt)
    {
    case 1: 
    default: 
      return "STATUS_UNKNOWN";
    case 0: 
      return "STATUS_SUCCESSFUL";
    case 2: 
      return "STATUS_TIMED_OUT_ON_SCAN";
    case 3: 
      return "STATUS_NO_INFO_IN_DATABASE";
    case 4: 
      return "STATUS_INVALID_SCAN";
    case 5: 
      return "STATUS_UNABLE_TO_QUERY_DATABASE";
    case 6: 
      return "STATUS_SCANS_DISABLED_IN_SETTINGS";
    case 7: 
      return "STATUS_LOCATION_DISABLED_IN_SETTINGS";
    }
    return "STATUS_IN_PROGRESS";
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof c)) {}
    do
    {
      return false;
      paramObject = (c)paramObject;
    } while ((this.aex != ((c)paramObject).aex) || (this.aey != ((c)paramObject).aey) || (this.aez != ((c)paramObject).aez));
    return true;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Integer.valueOf(this.aex), Integer.valueOf(this.aey), Long.valueOf(this.aez) });
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("LocationStatus[cell status: ").append(ee(this.aex));
    localStringBuilder.append(", wifi status: ").append(ee(this.aey));
    localStringBuilder.append(", elapsed realtime ns: ").append(this.aez);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    d.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/location/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */