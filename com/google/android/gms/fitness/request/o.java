package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.k;
import com.google.android.gms.fitness.data.k.a;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class o
  implements SafeParcelable
{
  public static final Parcelable.Creator<o> CREATOR = new p();
  private final int BR;
  private final DataType Sp;
  private final DataSource Sq;
  private final long Tt;
  private final int Tu;
  private k UA;
  int UB;
  int UC;
  private final long UD;
  private final long UE;
  private final List<LocationRequest> UF;
  private final long UG;
  private final List UH;
  private final PendingIntent mPendingIntent;
  
  o(int paramInt1, DataSource paramDataSource, DataType paramDataType, IBinder paramIBinder, int paramInt2, int paramInt3, long paramLong1, long paramLong2, PendingIntent paramPendingIntent, long paramLong3, int paramInt4, List<LocationRequest> paramList, long paramLong4)
  {
    this.BR = paramInt1;
    this.Sq = paramDataSource;
    this.Sp = paramDataType;
    if (paramIBinder == null) {}
    for (paramDataSource = null;; paramDataSource = k.a.an(paramIBinder))
    {
      this.UA = paramDataSource;
      long l = paramLong1;
      if (paramLong1 == 0L) {
        l = paramInt2;
      }
      this.Tt = l;
      this.UE = paramLong3;
      paramLong1 = paramLong2;
      if (paramLong2 == 0L) {
        paramLong1 = paramInt3;
      }
      this.UD = paramLong1;
      this.UF = paramList;
      this.mPendingIntent = paramPendingIntent;
      this.Tu = paramInt4;
      this.UH = Collections.emptyList();
      this.UG = paramLong4;
      return;
    }
  }
  
  private o(DataSource paramDataSource, DataType paramDataType, k paramk, PendingIntent paramPendingIntent, long paramLong1, long paramLong2, long paramLong3, int paramInt, List paramList1, List paramList2, long paramLong4)
  {
    this.BR = 4;
    this.Sq = paramDataSource;
    this.Sp = paramDataType;
    this.UA = paramk;
    this.mPendingIntent = paramPendingIntent;
    this.Tt = paramLong1;
    this.UE = paramLong2;
    this.UD = paramLong3;
    this.Tu = paramInt;
    this.UF = paramList1;
    this.UH = paramList2;
    this.UG = paramLong4;
  }
  
  public o(SensorRequest paramSensorRequest, k paramk, PendingIntent paramPendingIntent)
  {
    this(paramSensorRequest.getDataSource(), paramSensorRequest.getDataType(), paramk, paramPendingIntent, paramSensorRequest.getSamplingRate(TimeUnit.MICROSECONDS), paramSensorRequest.getFastestRate(TimeUnit.MICROSECONDS), paramSensorRequest.getMaxDeliveryLatency(TimeUnit.MICROSECONDS), paramSensorRequest.getAccuracyMode(), null, Collections.emptyList(), paramSensorRequest.jx());
  }
  
  private boolean a(o paramo)
  {
    return (n.equal(this.Sq, paramo.Sq)) && (n.equal(this.Sp, paramo.Sp)) && (this.Tt == paramo.Tt) && (this.UE == paramo.UE) && (this.UD == paramo.UD) && (this.Tu == paramo.Tu) && (n.equal(this.UF, paramo.UF));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof o)) && (a((o)paramObject)));
  }
  
  public int getAccuracyMode()
  {
    return this.Tu;
  }
  
  public DataSource getDataSource()
  {
    return this.Sq;
  }
  
  public DataType getDataType()
  {
    return this.Sp;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.Sq, this.Sp, this.UA, Long.valueOf(this.Tt), Long.valueOf(this.UE), Long.valueOf(this.UD), Integer.valueOf(this.Tu), this.UF });
  }
  
  public long iX()
  {
    return this.Tt;
  }
  
  public PendingIntent jr()
  {
    return this.mPendingIntent;
  }
  
  public long js()
  {
    return this.UE;
  }
  
  public long jt()
  {
    return this.UD;
  }
  
  public List<LocationRequest> ju()
  {
    return this.UF;
  }
  
  public long jv()
  {
    return this.UG;
  }
  
  IBinder jw()
  {
    if (this.UA == null) {
      return null;
    }
    return this.UA.asBinder();
  }
  
  public String toString()
  {
    return String.format("SensorRegistrationRequest{type %s source %s interval %s fastest %s latency %s}", new Object[] { this.Sp, this.Sq, Long.valueOf(this.Tt), Long.valueOf(this.UE), Long.valueOf(this.UD) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    p.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */