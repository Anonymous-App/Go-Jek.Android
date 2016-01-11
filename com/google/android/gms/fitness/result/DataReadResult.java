package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataSource.Builder;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.RawBucket;
import com.google.android.gms.fitness.data.RawDataSet;
import com.google.android.gms.fitness.request.DataReadRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DataReadResult
  implements Result, SafeParcelable
{
  public static final Parcelable.Creator<DataReadResult> CREATOR = new b();
  private final int BR;
  private final Status CM;
  private final List<DataSet> SD;
  private final List<DataSource> SN;
  private final List<Bucket> UV;
  private int UW;
  private final List<DataType> UX;
  
  DataReadResult(int paramInt1, List<RawDataSet> paramList, Status paramStatus, List<RawBucket> paramList1, int paramInt2, List<DataSource> paramList2, List<DataType> paramList3)
  {
    this.BR = paramInt1;
    this.CM = paramStatus;
    this.UW = paramInt2;
    this.SN = paramList2;
    this.UX = paramList3;
    this.SD = new ArrayList(paramList.size());
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      paramStatus = (RawDataSet)paramList.next();
      this.SD.add(new DataSet(paramStatus, paramList2, paramList3));
    }
    this.UV = new ArrayList(paramList1.size());
    paramList = paramList1.iterator();
    while (paramList.hasNext())
    {
      paramStatus = (RawBucket)paramList.next();
      this.UV.add(new Bucket(paramStatus, paramList2, paramList3));
    }
  }
  
  public DataReadResult(List<DataSet> paramList, List<Bucket> paramList1, Status paramStatus)
  {
    this.BR = 5;
    this.SD = paramList;
    this.CM = paramStatus;
    this.UV = paramList1;
    this.UW = 1;
    this.SN = new ArrayList();
    this.UX = new ArrayList();
  }
  
  public static DataReadResult a(Status paramStatus, DataReadRequest paramDataReadRequest)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = paramDataReadRequest.getDataSources().iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add(DataSet.create((DataSource)((Iterator)localObject).next()));
    }
    paramDataReadRequest = paramDataReadRequest.getDataTypes().iterator();
    while (paramDataReadRequest.hasNext())
    {
      localObject = (DataType)paramDataReadRequest.next();
      localArrayList.add(DataSet.create(new DataSource.Builder().setDataType((DataType)localObject).setType(1).setName("Default").build()));
    }
    return new DataReadResult(localArrayList, Collections.emptyList(), paramStatus);
  }
  
  private void a(Bucket paramBucket, List<Bucket> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      paramList = (Bucket)localIterator.next();
      if (paramList.b(paramBucket))
      {
        paramBucket = paramBucket.getDataSets().iterator();
        while (paramBucket.hasNext()) {
          a((DataSet)paramBucket.next(), paramList.getDataSets());
        }
      }
    }
    this.UV.add(paramBucket);
  }
  
  private void a(DataSet paramDataSet, List<DataSet> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      DataSet localDataSet = (DataSet)localIterator.next();
      if (localDataSet.getDataSource().equals(paramDataSet.getDataSource()))
      {
        localDataSet.a(paramDataSet.getDataPoints());
        return;
      }
    }
    paramList.add(paramDataSet);
  }
  
  private boolean c(DataReadResult paramDataReadResult)
  {
    return (this.CM.equals(paramDataReadResult.CM)) && (n.equal(this.SD, paramDataReadResult.SD)) && (n.equal(this.UV, paramDataReadResult.UV));
  }
  
  public void b(DataReadResult paramDataReadResult)
  {
    Iterator localIterator = paramDataReadResult.getDataSets().iterator();
    while (localIterator.hasNext()) {
      a((DataSet)localIterator.next(), this.SD);
    }
    paramDataReadResult = paramDataReadResult.getBuckets().iterator();
    while (paramDataReadResult.hasNext()) {
      a((Bucket)paramDataReadResult.next(), this.UV);
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof DataReadResult)) && (c((DataReadResult)paramObject)));
  }
  
  public List<Bucket> getBuckets()
  {
    return this.UV;
  }
  
  public DataSet getDataSet(DataSource paramDataSource)
  {
    Iterator localIterator = this.SD.iterator();
    while (localIterator.hasNext())
    {
      DataSet localDataSet = (DataSet)localIterator.next();
      if (paramDataSource.equals(localDataSet.getDataSource())) {
        return localDataSet;
      }
    }
    throw new IllegalArgumentException(String.format("Attempting to read data for %s, which was not requested", new Object[] { paramDataSource.getStreamIdentifier() }));
  }
  
  public DataSet getDataSet(DataType paramDataType)
  {
    Iterator localIterator = this.SD.iterator();
    while (localIterator.hasNext())
    {
      DataSet localDataSet = (DataSet)localIterator.next();
      if (paramDataType.equals(localDataSet.getDataType())) {
        return localDataSet;
      }
    }
    throw new IllegalArgumentException(String.format("Attempting to read data for %s, which was not requested", new Object[] { paramDataType.getName() }));
  }
  
  public List<DataSet> getDataSets()
  {
    return this.SD;
  }
  
  public Status getStatus()
  {
    return this.CM;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.CM, this.SD, this.UV });
  }
  
  List<DataSource> iL()
  {
    return this.SN;
  }
  
  public int jH()
  {
    return this.UW;
  }
  
  List<RawBucket> jI()
  {
    ArrayList localArrayList = new ArrayList(this.UV.size());
    Iterator localIterator = this.UV.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(new RawBucket((Bucket)localIterator.next(), this.SN, this.UX));
    }
    return localArrayList;
  }
  
  List<RawDataSet> jJ()
  {
    ArrayList localArrayList = new ArrayList(this.SD.size());
    Iterator localIterator = this.SD.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(new RawDataSet((DataSet)localIterator.next(), this.SN, this.UX));
    }
    return localArrayList;
  }
  
  List<DataType> jK()
  {
    return this.UX;
  }
  
  public String toString()
  {
    n.a locala = n.h(this).a("status", this.CM);
    if (this.SD.size() > 5)
    {
      localObject = this.SD.size() + " data sets";
      locala = locala.a("dataSets", localObject);
      if (this.UV.size() <= 5) {
        break label127;
      }
    }
    label127:
    for (Object localObject = this.UV.size() + " buckets";; localObject = this.UV)
    {
      return locala.a("buckets", localObject).toString();
      localObject = this.SD;
      break;
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    b.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/result/DataReadResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */