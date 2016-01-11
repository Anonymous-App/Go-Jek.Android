package com.google.android.gms.location;

import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LocationRequest
  implements SafeParcelable
{
  public static final b CREATOR = new b();
  public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
  public static final int PRIORITY_HIGH_ACCURACY = 100;
  public static final int PRIORITY_LOW_POWER = 104;
  public static final int PRIORITY_NO_POWER = 105;
  private final int BR;
  boolean UK;
  long aei;
  long aes;
  long aet;
  int aeu;
  float aev;
  long aew;
  int mPriority;
  
  public LocationRequest()
  {
    this.BR = 1;
    this.mPriority = 102;
    this.aes = 3600000L;
    this.aet = 600000L;
    this.UK = false;
    this.aei = Long.MAX_VALUE;
    this.aeu = Integer.MAX_VALUE;
    this.aev = 0.0F;
    this.aew = 0L;
  }
  
  LocationRequest(int paramInt1, int paramInt2, long paramLong1, long paramLong2, boolean paramBoolean, long paramLong3, int paramInt3, float paramFloat, long paramLong4)
  {
    this.BR = paramInt1;
    this.mPriority = paramInt2;
    this.aes = paramLong1;
    this.aet = paramLong2;
    this.UK = paramBoolean;
    this.aei = paramLong3;
    this.aeu = paramInt3;
    this.aev = paramFloat;
    this.aew = paramLong4;
  }
  
  private static void a(float paramFloat)
  {
    if (paramFloat < 0.0F) {
      throw new IllegalArgumentException("invalid displacement: " + paramFloat);
    }
  }
  
  public static LocationRequest create()
  {
    return new LocationRequest();
  }
  
  private static void eb(int paramInt)
  {
    switch (paramInt)
    {
    case 101: 
    case 103: 
    default: 
      throw new IllegalArgumentException("invalid quality: " + paramInt);
    }
  }
  
  public static String ec(int paramInt)
  {
    switch (paramInt)
    {
    case 101: 
    case 103: 
    default: 
      return "???";
    case 100: 
      return "PRIORITY_HIGH_ACCURACY";
    case 102: 
      return "PRIORITY_BALANCED_POWER_ACCURACY";
    case 104: 
      return "PRIORITY_LOW_POWER";
    }
    return "PRIORITY_NO_POWER";
  }
  
  private static void v(long paramLong)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("invalid interval: " + paramLong);
    }
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
      if (!(paramObject instanceof LocationRequest)) {
        return false;
      }
      paramObject = (LocationRequest)paramObject;
    } while ((this.mPriority == ((LocationRequest)paramObject).mPriority) && (this.aes == ((LocationRequest)paramObject).aes) && (this.aet == ((LocationRequest)paramObject).aet) && (this.UK == ((LocationRequest)paramObject).UK) && (this.aei == ((LocationRequest)paramObject).aei) && (this.aeu == ((LocationRequest)paramObject).aeu) && (this.aev == ((LocationRequest)paramObject).aev));
    return false;
  }
  
  public long getExpirationTime()
  {
    return this.aei;
  }
  
  public long getFastestInterval()
  {
    return this.aet;
  }
  
  public long getInterval()
  {
    return this.aes;
  }
  
  public int getNumUpdates()
  {
    return this.aeu;
  }
  
  public int getPriority()
  {
    return this.mPriority;
  }
  
  public float getSmallestDisplacement()
  {
    return this.aev;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Integer.valueOf(this.mPriority), Long.valueOf(this.aes), Long.valueOf(this.aet), Boolean.valueOf(this.UK), Long.valueOf(this.aei), Integer.valueOf(this.aeu), Float.valueOf(this.aev) });
  }
  
  public LocationRequest setExpirationDuration(long paramLong)
  {
    long l = SystemClock.elapsedRealtime();
    if (paramLong > Long.MAX_VALUE - l) {}
    for (this.aei = Long.MAX_VALUE;; this.aei = (l + paramLong))
    {
      if (this.aei < 0L) {
        this.aei = 0L;
      }
      return this;
    }
  }
  
  public LocationRequest setExpirationTime(long paramLong)
  {
    this.aei = paramLong;
    if (this.aei < 0L) {
      this.aei = 0L;
    }
    return this;
  }
  
  public LocationRequest setFastestInterval(long paramLong)
  {
    v(paramLong);
    this.UK = true;
    this.aet = paramLong;
    return this;
  }
  
  public LocationRequest setInterval(long paramLong)
  {
    v(paramLong);
    this.aes = paramLong;
    if (!this.UK) {
      this.aet = ((this.aes / 6.0D));
    }
    return this;
  }
  
  public LocationRequest setNumUpdates(int paramInt)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("invalid numUpdates: " + paramInt);
    }
    this.aeu = paramInt;
    return this;
  }
  
  public LocationRequest setPriority(int paramInt)
  {
    eb(paramInt);
    this.mPriority = paramInt;
    return this;
  }
  
  public LocationRequest setSmallestDisplacement(float paramFloat)
  {
    a(paramFloat);
    this.aev = paramFloat;
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Request[").append(ec(this.mPriority));
    if (this.mPriority != 105)
    {
      localStringBuilder.append(" requested=");
      localStringBuilder.append(this.aes + "ms");
    }
    localStringBuilder.append(" fastest=");
    localStringBuilder.append(this.aet + "ms");
    if (this.aei != Long.MAX_VALUE)
    {
      long l1 = this.aei;
      long l2 = SystemClock.elapsedRealtime();
      localStringBuilder.append(" expireIn=");
      localStringBuilder.append(l1 - l2 + "ms");
    }
    if (this.aeu != Integer.MAX_VALUE) {
      localStringBuilder.append(" num=").append(this.aeu);
    }
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    b.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/location/LocationRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */