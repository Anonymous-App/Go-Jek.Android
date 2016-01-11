package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DataReadRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataReadRequest> CREATOR = new g();
  public static final int NO_LIMIT = 0;
  private final int BR;
  private final long KS;
  private final List<DataType> SB;
  private final int SE;
  private final long Sr;
  private final List<DataSource> Uk;
  private final List<DataType> Uo;
  private final List<DataSource> Up;
  private final long Uq;
  private final DataSource Ur;
  private final int Us;
  private final boolean Ut;
  private final boolean Uu;
  private final boolean Uv;
  
  DataReadRequest(int paramInt1, List<DataType> paramList1, List<DataSource> paramList2, long paramLong1, long paramLong2, List<DataType> paramList3, List<DataSource> paramList4, int paramInt2, long paramLong3, DataSource paramDataSource, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this.BR = paramInt1;
    this.SB = Collections.unmodifiableList(paramList1);
    this.Uk = Collections.unmodifiableList(paramList2);
    this.KS = paramLong1;
    this.Sr = paramLong2;
    this.Uo = Collections.unmodifiableList(paramList3);
    this.Up = Collections.unmodifiableList(paramList4);
    this.SE = paramInt2;
    this.Uq = paramLong3;
    this.Ur = paramDataSource;
    this.Us = paramInt3;
    this.Ut = paramBoolean1;
    this.Uu = paramBoolean2;
    this.Uv = paramBoolean3;
  }
  
  private DataReadRequest(Builder paramBuilder)
  {
    this.BR = 2;
    this.SB = Collections.unmodifiableList(Builder.a(paramBuilder));
    this.Uk = Collections.unmodifiableList(Builder.b(paramBuilder));
    this.KS = Builder.c(paramBuilder);
    this.Sr = Builder.d(paramBuilder);
    this.Uo = Collections.unmodifiableList(Builder.e(paramBuilder));
    this.Up = Collections.unmodifiableList(Builder.f(paramBuilder));
    this.SE = Builder.g(paramBuilder);
    this.Uq = Builder.h(paramBuilder);
    this.Ur = Builder.i(paramBuilder);
    this.Us = Builder.j(paramBuilder);
    this.Ut = Builder.k(paramBuilder);
    this.Uu = Builder.l(paramBuilder);
    this.Uv = Builder.m(paramBuilder);
  }
  
  private boolean a(DataReadRequest paramDataReadRequest)
  {
    return (this.SB.equals(paramDataReadRequest.SB)) && (this.Uk.equals(paramDataReadRequest.Uk)) && (this.KS == paramDataReadRequest.KS) && (this.Sr == paramDataReadRequest.Sr) && (this.SE == paramDataReadRequest.SE) && (this.Up.equals(paramDataReadRequest.Up)) && (this.Uo.equals(paramDataReadRequest.Uo)) && (n.equal(this.Ur, paramDataReadRequest.Ur)) && (this.Uq == paramDataReadRequest.Uq) && (this.Uv == paramDataReadRequest.Uv);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof DataReadRequest)) && (a((DataReadRequest)paramObject)));
  }
  
  public DataSource getActivityDataSource()
  {
    return this.Ur;
  }
  
  public List<DataSource> getAggregatedDataSources()
  {
    return this.Up;
  }
  
  public List<DataType> getAggregatedDataTypes()
  {
    return this.Uo;
  }
  
  public long getBucketDuration(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.Uq, TimeUnit.MILLISECONDS);
  }
  
  public int getBucketType()
  {
    return this.SE;
  }
  
  public List<DataSource> getDataSources()
  {
    return this.Uk;
  }
  
  public List<DataType> getDataTypes()
  {
    return this.SB;
  }
  
  public long getEndTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.Sr, TimeUnit.MILLISECONDS);
  }
  
  public int getLimit()
  {
    return this.Us;
  }
  
  public long getStartTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.KS, TimeUnit.MILLISECONDS);
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Integer.valueOf(this.SE), Long.valueOf(this.KS), Long.valueOf(this.Sr) });
  }
  
  public long iD()
  {
    return this.KS;
  }
  
  public long iE()
  {
    return this.Sr;
  }
  
  public boolean jk()
  {
    return this.Ut;
  }
  
  public boolean jl()
  {
    return this.Uv;
  }
  
  public boolean jm()
  {
    return this.Uu;
  }
  
  public long jn()
  {
    return this.Uq;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("DataReadRequest{");
    Iterator localIterator;
    if (!this.SB.isEmpty())
    {
      localIterator = this.SB.iterator();
      while (localIterator.hasNext()) {
        localStringBuilder.append(((DataType)localIterator.next()).iQ()).append(" ");
      }
    }
    if (!this.Uk.isEmpty())
    {
      localIterator = this.Uk.iterator();
      while (localIterator.hasNext()) {
        localStringBuilder.append(((DataSource)localIterator.next()).toDebugString()).append(" ");
      }
    }
    if (this.SE != 0)
    {
      localStringBuilder.append("bucket by ").append(Bucket.cy(this.SE));
      if (this.Uq > 0L) {
        localStringBuilder.append(" >").append(this.Uq).append("ms");
      }
      localStringBuilder.append(": ");
    }
    if (!this.Uo.isEmpty())
    {
      localIterator = this.Uo.iterator();
      while (localIterator.hasNext()) {
        localStringBuilder.append(((DataType)localIterator.next()).iQ()).append(" ");
      }
    }
    if (!this.Up.isEmpty())
    {
      localIterator = this.Up.iterator();
      while (localIterator.hasNext()) {
        localStringBuilder.append(((DataSource)localIterator.next()).toDebugString()).append(" ");
      }
    }
    localStringBuilder.append(String.format("(%tF %tT - %tF %tT)", new Object[] { Long.valueOf(this.KS), Long.valueOf(this.KS), Long.valueOf(this.Sr), Long.valueOf(this.Sr) }));
    if (this.Ur != null) {
      localStringBuilder.append("activities: ").append(this.Ur.toDebugString());
    }
    if (this.Uv) {
      localStringBuilder.append(" +server");
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    g.a(this, paramParcel, paramInt);
  }
  
  public static class Builder
  {
    private long KS;
    private List<DataType> SB = new ArrayList();
    private int SE = 0;
    private long Sr;
    private List<DataSource> Uk = new ArrayList();
    private List<DataType> Uo = new ArrayList();
    private List<DataSource> Up = new ArrayList();
    private long Uq = 0L;
    private DataSource Ur;
    private int Us = 0;
    private boolean Ut = false;
    private boolean Uu = false;
    private boolean Uv = false;
    
    public Builder aggregate(DataSource paramDataSource, DataType paramDataType)
    {
      o.b(paramDataSource, "Attempting to add a null data source");
      if (!this.Uk.contains(paramDataSource)) {}
      for (boolean bool = true;; bool = false)
      {
        o.a(bool, "Cannot add the same data source for aggregated and detailed");
        DataType localDataType = paramDataSource.getDataType();
        o.b(DataType.AGGREGATE_INPUT_TYPES.contains(localDataType), "Unsupported input data type specified for aggregation: %s", new Object[] { localDataType });
        o.b(DataType.getAggregatesForInput(localDataType).contains(paramDataType), "Invalid output aggregate data type specified: %s -> %s", new Object[] { localDataType, paramDataType });
        if (!this.Up.contains(paramDataSource)) {
          this.Up.add(paramDataSource);
        }
        return this;
      }
    }
    
    public Builder aggregate(DataType paramDataType1, DataType paramDataType2)
    {
      o.b(paramDataType1, "Attempting to use a null data type");
      if (!this.SB.contains(paramDataType1)) {}
      for (boolean bool = true;; bool = false)
      {
        o.a(bool, "Cannot add the same data type as aggregated and detailed");
        o.b(DataType.AGGREGATE_INPUT_TYPES.contains(paramDataType1), "Unsupported input data type specified for aggregation: %s", new Object[] { paramDataType1 });
        o.b(DataType.getAggregatesForInput(paramDataType1).contains(paramDataType2), "Invalid output aggregate data type specified: %s -> %s", new Object[] { paramDataType1, paramDataType2 });
        if (!this.Uo.contains(paramDataType1)) {
          this.Uo.add(paramDataType1);
        }
        return this;
      }
    }
    
    public Builder bucketByActivitySegment(int paramInt, TimeUnit paramTimeUnit)
    {
      if (this.SE == 0)
      {
        bool = true;
        o.b(bool, "Bucketing strategy already set to %s", new Object[] { Integer.valueOf(this.SE) });
        if (paramInt <= 0) {
          break label74;
        }
      }
      label74:
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Must specify a valid minimum duration for an activity segment: %d", new Object[] { Integer.valueOf(paramInt) });
        this.SE = 4;
        this.Uq = paramTimeUnit.toMillis(paramInt);
        return this;
        bool = false;
        break;
      }
    }
    
    public Builder bucketByActivitySegment(int paramInt, TimeUnit paramTimeUnit, DataSource paramDataSource)
    {
      if (this.SE == 0)
      {
        bool = true;
        o.b(bool, "Bucketing strategy already set to %s", new Object[] { Integer.valueOf(this.SE) });
        if (paramInt <= 0) {
          break label121;
        }
        bool = true;
        label38:
        o.b(bool, "Must specify a valid minimum duration for an activity segment: %d", new Object[] { Integer.valueOf(paramInt) });
        if (paramDataSource == null) {
          break label127;
        }
      }
      label121:
      label127:
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Invalid activity data source specified");
        o.b(paramDataSource.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", new Object[] { paramDataSource });
        this.Ur = paramDataSource;
        this.SE = 4;
        this.Uq = paramTimeUnit.toMillis(paramInt);
        return this;
        bool = false;
        break;
        bool = false;
        break label38;
      }
    }
    
    public Builder bucketByActivityType(int paramInt, TimeUnit paramTimeUnit)
    {
      if (this.SE == 0)
      {
        bool = true;
        o.b(bool, "Bucketing strategy already set to %s", new Object[] { Integer.valueOf(this.SE) });
        if (paramInt <= 0) {
          break label74;
        }
      }
      label74:
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Must specify a valid minimum duration for an activity segment: %d", new Object[] { Integer.valueOf(paramInt) });
        this.SE = 3;
        this.Uq = paramTimeUnit.toMillis(paramInt);
        return this;
        bool = false;
        break;
      }
    }
    
    public Builder bucketByActivityType(int paramInt, TimeUnit paramTimeUnit, DataSource paramDataSource)
    {
      if (this.SE == 0)
      {
        bool = true;
        o.b(bool, "Bucketing strategy already set to %s", new Object[] { Integer.valueOf(this.SE) });
        if (paramInt <= 0) {
          break label121;
        }
        bool = true;
        label38:
        o.b(bool, "Must specify a valid minimum duration for an activity segment: %d", new Object[] { Integer.valueOf(paramInt) });
        if (paramDataSource == null) {
          break label127;
        }
      }
      label121:
      label127:
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Invalid activity data source specified");
        o.b(paramDataSource.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", new Object[] { paramDataSource });
        this.Ur = paramDataSource;
        this.SE = 3;
        this.Uq = paramTimeUnit.toMillis(paramInt);
        return this;
        bool = false;
        break;
        bool = false;
        break label38;
      }
    }
    
    public Builder bucketBySession(int paramInt, TimeUnit paramTimeUnit)
    {
      if (this.SE == 0)
      {
        bool = true;
        o.b(bool, "Bucketing strategy already set to %s", new Object[] { Integer.valueOf(this.SE) });
        if (paramInt <= 0) {
          break label74;
        }
      }
      label74:
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Must specify a valid minimum duration for an activity segment: %d", new Object[] { Integer.valueOf(paramInt) });
        this.SE = 2;
        this.Uq = paramTimeUnit.toMillis(paramInt);
        return this;
        bool = false;
        break;
      }
    }
    
    public Builder bucketByTime(int paramInt, TimeUnit paramTimeUnit)
    {
      if (this.SE == 0)
      {
        bool = true;
        o.b(bool, "Bucketing strategy already set to %s", new Object[] { Integer.valueOf(this.SE) });
        if (paramInt <= 0) {
          break label74;
        }
      }
      label74:
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Must specify a valid minimum duration for an activity segment: %d", new Object[] { Integer.valueOf(paramInt) });
        this.SE = 1;
        this.Uq = paramTimeUnit.toMillis(paramInt);
        return this;
        bool = false;
        break;
      }
    }
    
    public DataReadRequest build()
    {
      boolean bool2 = true;
      label69:
      label112:
      int i;
      if ((!this.Uk.isEmpty()) || (!this.SB.isEmpty()) || (!this.Up.isEmpty()) || (!this.Uo.isEmpty()))
      {
        bool1 = true;
        o.a(bool1, "Must add at least one data source (aggregated or detailed)");
        if (this.KS <= 0L) {
          break label205;
        }
        bool1 = true;
        o.a(bool1, "Invalid start time: %s", new Object[] { Long.valueOf(this.KS) });
        if ((this.Sr <= 0L) || (this.Sr <= this.KS)) {
          break label210;
        }
        bool1 = true;
        o.a(bool1, "Invalid end time: %s", new Object[] { Long.valueOf(this.Sr) });
        if ((!this.Up.isEmpty()) || (!this.Uo.isEmpty())) {
          break label215;
        }
        i = 1;
        label158:
        if (i != 0)
        {
          bool1 = bool2;
          if (this.SE == 0) {}
        }
        else
        {
          if ((i != 0) || (this.SE == 0)) {
            break label220;
          }
        }
      }
      label205:
      label210:
      label215:
      label220:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        o.a(bool1, "Must specify a valid bucketing strategy while requesting aggregation");
        return new DataReadRequest(this, null);
        bool1 = false;
        break;
        bool1 = false;
        break label69;
        bool1 = false;
        break label112;
        i = 0;
        break label158;
      }
    }
    
    public Builder enableServerQueries()
    {
      this.Uv = true;
      return this;
    }
    
    public Builder read(DataSource paramDataSource)
    {
      o.b(paramDataSource, "Attempting to add a null data source");
      if (!this.Up.contains(paramDataSource)) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Cannot add the same data source as aggregated and detailed");
        if (!this.Uk.contains(paramDataSource)) {
          this.Uk.add(paramDataSource);
        }
        return this;
      }
    }
    
    public Builder read(DataType paramDataType)
    {
      o.b(paramDataType, "Attempting to use a null data type");
      if (!this.Uo.contains(paramDataType)) {}
      for (boolean bool = true;; bool = false)
      {
        o.a(bool, "Cannot add the same data type as aggregated and detailed");
        if (!this.SB.contains(paramDataType)) {
          this.SB.add(paramDataType);
        }
        return this;
      }
    }
    
    public Builder setLimit(int paramInt)
    {
      if (paramInt > 0) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Invalid limit %d is specified", new Object[] { Integer.valueOf(paramInt) });
        this.Us = paramInt;
        return this;
      }
    }
    
    public Builder setTimeRange(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    {
      this.KS = paramTimeUnit.toMillis(paramLong1);
      this.Sr = paramTimeUnit.toMillis(paramLong2);
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/DataReadRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */