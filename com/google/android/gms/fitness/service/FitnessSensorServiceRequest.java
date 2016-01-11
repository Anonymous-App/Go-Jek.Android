package com.google.android.gms.fitness.service;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.k;
import com.google.android.gms.fitness.data.k.a;
import java.util.concurrent.TimeUnit;

public class FitnessSensorServiceRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<FitnessSensorServiceRequest> CREATOR = new a();
  public static final int UNSPECIFIED = -1;
  private final int BR;
  private final DataSource Sq;
  private final k UA;
  private final long Vc;
  private final long Vd;
  
  FitnessSensorServiceRequest(int paramInt, DataSource paramDataSource, IBinder paramIBinder, long paramLong1, long paramLong2)
  {
    this.BR = paramInt;
    this.Sq = paramDataSource;
    this.UA = k.a.an(paramIBinder);
    this.Vc = paramLong1;
    this.Vd = paramLong2;
  }
  
  private boolean a(FitnessSensorServiceRequest paramFitnessSensorServiceRequest)
  {
    return (n.equal(this.Sq, paramFitnessSensorServiceRequest.Sq)) && (this.Vc == paramFitnessSensorServiceRequest.Vc) && (this.Vd == paramFitnessSensorServiceRequest.Vd);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof FitnessSensorServiceRequest)) && (a((FitnessSensorServiceRequest)paramObject)));
  }
  
  public long getBatchInterval(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.Vd, TimeUnit.MICROSECONDS);
  }
  
  public DataSource getDataSource()
  {
    return this.Sq;
  }
  
  public SensorEventDispatcher getDispatcher()
  {
    return new b(this.UA);
  }
  
  public long getSamplingRate(TimeUnit paramTimeUnit)
  {
    if (this.Vc == -1L) {
      return -1L;
    }
    return paramTimeUnit.convert(this.Vc, TimeUnit.MICROSECONDS);
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.Sq, Long.valueOf(this.Vc), Long.valueOf(this.Vd) });
  }
  
  public long iX()
  {
    return this.Vc;
  }
  
  public long jN()
  {
    return this.Vd;
  }
  
  IBinder jw()
  {
    return this.UA.asBinder();
  }
  
  public String toString()
  {
    return String.format("FitnessSensorServiceRequest{%s}", new Object[] { this.Sq });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/service/FitnessSensorServiceRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */