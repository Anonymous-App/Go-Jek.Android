package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class RawDataPoint
  implements SafeParcelable
{
  public static final Parcelable.Creator<RawDataPoint> CREATOR = new n();
  final int BR;
  final long SG;
  final long SH;
  final Value[] SI;
  final long SK;
  final long SL;
  final int Tm;
  final int Tn;
  
  RawDataPoint(int paramInt1, long paramLong1, long paramLong2, Value[] paramArrayOfValue, int paramInt2, int paramInt3, long paramLong3, long paramLong4)
  {
    this.BR = paramInt1;
    this.SG = paramLong1;
    this.SH = paramLong2;
    this.Tm = paramInt2;
    this.Tn = paramInt3;
    this.SK = paramLong3;
    this.SL = paramLong4;
    this.SI = paramArrayOfValue;
  }
  
  RawDataPoint(DataPoint paramDataPoint, List<DataSource> paramList)
  {
    this.BR = 4;
    this.SG = paramDataPoint.getTimestamp(TimeUnit.NANOSECONDS);
    this.SH = paramDataPoint.getStartTime(TimeUnit.NANOSECONDS);
    this.SI = paramDataPoint.iG();
    this.Tm = t.a(paramDataPoint.getDataSource(), paramList);
    this.Tn = t.a(paramDataPoint.getOriginalDataSource(), paramList);
    this.SK = paramDataPoint.iH();
    this.SL = paramDataPoint.iI();
  }
  
  private boolean a(RawDataPoint paramRawDataPoint)
  {
    return (this.SG == paramRawDataPoint.SG) && (this.SH == paramRawDataPoint.SH) && (Arrays.equals(this.SI, paramRawDataPoint.SI)) && (this.Tm == paramRawDataPoint.Tm) && (this.Tn == paramRawDataPoint.Tn) && (this.SK == paramRawDataPoint.SK);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof RawDataPoint)) && (a((RawDataPoint)paramObject)));
  }
  
  public int hashCode()
  {
    return com.google.android.gms.common.internal.n.hashCode(new Object[] { Long.valueOf(this.SG), Long.valueOf(this.SH) });
  }
  
  public String toString()
  {
    return String.format("RawDataPoint{%s@[%s, %s](%d,%d)}", new Object[] { Arrays.toString(this.SI), Long.valueOf(this.SH), Long.valueOf(this.SG), Integer.valueOf(this.Tm), Integer.valueOf(this.Tn) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    n.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/RawDataPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */