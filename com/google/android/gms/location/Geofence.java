package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.internal.mc;

public abstract interface Geofence
{
  public static final int GEOFENCE_TRANSITION_DWELL = 4;
  public static final int GEOFENCE_TRANSITION_ENTER = 1;
  public static final int GEOFENCE_TRANSITION_EXIT = 2;
  public static final long NEVER_EXPIRE = -1L;
  
  public abstract String getRequestId();
  
  public static final class Builder
  {
    private String XC = null;
    private int aeh = 0;
    private long aei = Long.MIN_VALUE;
    private short aej = -1;
    private double aek;
    private double ael;
    private float aem;
    private int aen = 0;
    private int aeo = -1;
    
    public Geofence build()
    {
      if (this.XC == null) {
        throw new IllegalArgumentException("Request ID not set.");
      }
      if (this.aeh == 0) {
        throw new IllegalArgumentException("Transitions types not set.");
      }
      if (((this.aeh & 0x4) != 0) && (this.aeo < 0)) {
        throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
      }
      if (this.aei == Long.MIN_VALUE) {
        throw new IllegalArgumentException("Expiration not set.");
      }
      if (this.aej == -1) {
        throw new IllegalArgumentException("Geofence region not set.");
      }
      if (this.aen < 0) {
        throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
      }
      return new mc(this.XC, this.aeh, (short)1, this.aek, this.ael, this.aem, this.aei, this.aen, this.aeo);
    }
    
    public Builder setCircularRegion(double paramDouble1, double paramDouble2, float paramFloat)
    {
      this.aej = 1;
      this.aek = paramDouble1;
      this.ael = paramDouble2;
      this.aem = paramFloat;
      return this;
    }
    
    public Builder setExpirationDuration(long paramLong)
    {
      if (paramLong < 0L)
      {
        this.aei = -1L;
        return this;
      }
      this.aei = (SystemClock.elapsedRealtime() + paramLong);
      return this;
    }
    
    public Builder setLoiteringDelay(int paramInt)
    {
      this.aeo = paramInt;
      return this;
    }
    
    public Builder setNotificationResponsiveness(int paramInt)
    {
      this.aen = paramInt;
      return this;
    }
    
    public Builder setRequestId(String paramString)
    {
      this.XC = paramString;
      return this;
    }
    
    public Builder setTransitionTypes(int paramInt)
    {
      this.aeh = paramInt;
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/location/Geofence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */