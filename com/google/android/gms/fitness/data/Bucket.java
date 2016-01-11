package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.FitnessActivities;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Bucket
  implements SafeParcelable
{
  public static final Parcelable.Creator<Bucket> CREATOR = new d();
  public static final int TYPE_ACTIVITY_SEGMENT = 4;
  public static final int TYPE_ACTIVITY_TYPE = 3;
  public static final int TYPE_SESSION = 2;
  public static final int TYPE_TIME = 1;
  private final int BR;
  private final long KS;
  private final int SC;
  private final List<DataSet> SD;
  private final int SE;
  private boolean SF = false;
  private final long Sr;
  private final Session St;
  
  Bucket(int paramInt1, long paramLong1, long paramLong2, Session paramSession, int paramInt2, List<DataSet> paramList, int paramInt3, boolean paramBoolean)
  {
    this.BR = paramInt1;
    this.KS = paramLong1;
    this.Sr = paramLong2;
    this.St = paramSession;
    this.SC = paramInt2;
    this.SD = paramList;
    this.SE = paramInt3;
    this.SF = paramBoolean;
  }
  
  public Bucket(RawBucket paramRawBucket, List<DataSource> paramList, List<DataType> paramList1)
  {
    this(2, paramRawBucket.KS, paramRawBucket.Sr, paramRawBucket.St, paramRawBucket.Tl, a(paramRawBucket.SD, paramList, paramList1), paramRawBucket.SE, paramRawBucket.SF);
  }
  
  private static List<DataSet> a(List<RawDataSet> paramList, List<DataSource> paramList1, List<DataType> paramList2)
  {
    ArrayList localArrayList = new ArrayList(paramList.size());
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localArrayList.add(new DataSet((RawDataSet)paramList.next(), paramList1, paramList2));
    }
    return localArrayList;
  }
  
  private boolean a(Bucket paramBucket)
  {
    return (this.KS == paramBucket.KS) && (this.Sr == paramBucket.Sr) && (this.SC == paramBucket.SC) && (n.equal(this.SD, paramBucket.SD)) && (this.SE == paramBucket.SE) && (this.SF == paramBucket.SF);
  }
  
  public static String cy(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "bug";
    case 1: 
      return "time";
    case 3: 
      return "type";
    case 4: 
      return "segment";
    case 2: 
      return "session";
    }
    return "unknown";
  }
  
  public boolean b(Bucket paramBucket)
  {
    return (this.KS == paramBucket.KS) && (this.Sr == paramBucket.Sr) && (this.SC == paramBucket.SC) && (this.SE == paramBucket.SE);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof Bucket)) && (a((Bucket)paramObject)));
  }
  
  public String getActivity()
  {
    return FitnessActivities.getName(this.SC);
  }
  
  public int getBucketType()
  {
    return this.SE;
  }
  
  public DataSet getDataSet(DataType paramDataType)
  {
    Iterator localIterator = this.SD.iterator();
    while (localIterator.hasNext())
    {
      DataSet localDataSet = (DataSet)localIterator.next();
      if (localDataSet.getDataType().equals(paramDataType)) {
        return localDataSet;
      }
    }
    return null;
  }
  
  public List<DataSet> getDataSets()
  {
    return this.SD;
  }
  
  public long getEndTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.Sr, TimeUnit.MILLISECONDS);
  }
  
  public Session getSession()
  {
    return this.St;
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
    return n.hashCode(new Object[] { Long.valueOf(this.KS), Long.valueOf(this.Sr), Integer.valueOf(this.SC), Integer.valueOf(this.SE) });
  }
  
  public int iB()
  {
    return this.SC;
  }
  
  public boolean iC()
  {
    if (this.SF) {
      return true;
    }
    Iterator localIterator = this.SD.iterator();
    while (localIterator.hasNext()) {
      if (((DataSet)localIterator.next()).iC()) {
        return true;
      }
    }
    return false;
  }
  
  public long iD()
  {
    return this.KS;
  }
  
  public long iE()
  {
    return this.Sr;
  }
  
  public String toString()
  {
    return n.h(this).a("startTime", Long.valueOf(this.KS)).a("endTime", Long.valueOf(this.Sr)).a("activity", Integer.valueOf(this.SC)).a("dataSets", this.SD).a("bucketType", cy(this.SE)).a("serverHasMoreData", Boolean.valueOf(this.SF)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    d.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/Bucket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */