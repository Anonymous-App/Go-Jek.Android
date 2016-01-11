package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DataSourcesResult
  implements Result, SafeParcelable
{
  public static final Parcelable.Creator<DataSourcesResult> CREATOR = new c();
  private final int BR;
  private final Status CM;
  private final List<DataSource> Uk;
  
  DataSourcesResult(int paramInt, List<DataSource> paramList, Status paramStatus)
  {
    this.BR = paramInt;
    this.Uk = Collections.unmodifiableList(paramList);
    this.CM = paramStatus;
  }
  
  public DataSourcesResult(List<DataSource> paramList, Status paramStatus)
  {
    this.BR = 3;
    this.Uk = Collections.unmodifiableList(paramList);
    this.CM = paramStatus;
  }
  
  public static DataSourcesResult E(Status paramStatus)
  {
    return new DataSourcesResult(Collections.emptyList(), paramStatus);
  }
  
  private boolean b(DataSourcesResult paramDataSourcesResult)
  {
    return (this.CM.equals(paramDataSourcesResult.CM)) && (n.equal(this.Uk, paramDataSourcesResult.Uk));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof DataSourcesResult)) && (b((DataSourcesResult)paramObject)));
  }
  
  public List<DataSource> getDataSources()
  {
    return this.Uk;
  }
  
  public List<DataSource> getDataSources(DataType paramDataType)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.Uk.iterator();
    while (localIterator.hasNext())
    {
      DataSource localDataSource = (DataSource)localIterator.next();
      if (localDataSource.getDataType().equals(paramDataType)) {
        localArrayList.add(localDataSource);
      }
    }
    return Collections.unmodifiableList(localArrayList);
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
    return n.hashCode(new Object[] { this.CM, this.Uk });
  }
  
  public String toString()
  {
    return n.h(this).a("status", this.CM).a("dataSets", this.Uk).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    c.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/result/DataSourcesResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */