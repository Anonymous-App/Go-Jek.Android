package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class DataSet
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataSet> CREATOR = new f();
  private final int BR;
  private boolean SF = false;
  private final List<DataPoint> SM;
  private final List<DataSource> SN;
  private final DataType Sp;
  private final DataSource Sq;
  
  DataSet(int paramInt, DataSource paramDataSource, DataType paramDataType, List<RawDataPoint> paramList, List<DataSource> paramList1, boolean paramBoolean)
  {
    this.BR = paramInt;
    this.Sq = paramDataSource;
    this.Sp = paramDataType;
    this.SF = paramBoolean;
    this.SM = new ArrayList(paramList.size());
    if (paramInt >= 2) {}
    for (;;)
    {
      this.SN = paramList1;
      paramDataSource = paramList.iterator();
      while (paramDataSource.hasNext())
      {
        paramDataType = (RawDataPoint)paramDataSource.next();
        this.SM.add(new DataPoint(this.SN, paramDataType));
      }
      paramList1 = Collections.singletonList(paramDataSource);
    }
  }
  
  private DataSet(DataSource paramDataSource, DataType paramDataType)
  {
    this.BR = 3;
    this.Sq = ((DataSource)o.i(paramDataSource));
    this.Sp = ((DataType)o.i(paramDataType));
    this.SM = new ArrayList();
    this.SN = new ArrayList();
    this.SN.add(this.Sq);
  }
  
  public DataSet(RawDataSet paramRawDataSet, List<DataSource> paramList, List<DataType> paramList1)
  {
    this(3, (DataSource)b(paramList, paramRawDataSet.Tm), (DataType)b(paramList1, paramRawDataSet.To), paramRawDataSet.Tp, paramList, paramRawDataSet.SF);
  }
  
  private boolean a(DataSet paramDataSet)
  {
    return (n.equal(this.Sp, paramDataSet.Sp)) && (n.equal(this.Sq, paramDataSet.Sq)) && (n.equal(this.SM, paramDataSet.SM)) && (this.SF == paramDataSet.SF);
  }
  
  private static <T> T b(List<T> paramList, int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < paramList.size())) {
      return (T)paramList.get(paramInt);
    }
    return null;
  }
  
  public static DataSet create(DataSource paramDataSource)
  {
    o.b(paramDataSource, "DataSource should be specified");
    return new DataSet(paramDataSource, paramDataSource.getDataType());
  }
  
  public void a(Iterable<DataPoint> paramIterable)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext()) {
      b((DataPoint)paramIterable.next());
    }
  }
  
  public void add(DataPoint paramDataPoint)
  {
    DataSource localDataSource = paramDataPoint.getDataSource();
    o.b(localDataSource.getStreamIdentifier().equals(this.Sq.getStreamIdentifier()), "Conflicting data sources found %s vs %s", new Object[] { localDataSource, this.Sq });
    o.b(paramDataPoint.getDataType().getName().equals(this.Sp.getName()), "Conflicting data types found %s vs %s", new Object[] { paramDataPoint.getDataType(), this.Sp });
    if (paramDataPoint.getTimestamp(TimeUnit.NANOSECONDS) > 0L)
    {
      bool = true;
      o.b(bool, "Data point does not have the timestamp set: %s", new Object[] { paramDataPoint });
      if (paramDataPoint.getStartTime(TimeUnit.NANOSECONDS) > paramDataPoint.getEndTime(TimeUnit.NANOSECONDS)) {
        break label152;
      }
    }
    label152:
    for (boolean bool = true;; bool = false)
    {
      o.b(bool, "Data point with start time greater than end time found: %s", new Object[] { paramDataPoint });
      b(paramDataPoint);
      return;
      bool = false;
      break;
    }
  }
  
  public void addAll(Iterable<DataPoint> paramIterable)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext()) {
      add((DataPoint)paramIterable.next());
    }
  }
  
  public void b(DataPoint paramDataPoint)
  {
    this.SM.add(paramDataPoint);
    paramDataPoint = paramDataPoint.getOriginalDataSource();
    if ((paramDataPoint != null) && (!this.SN.contains(paramDataPoint))) {
      this.SN.add(paramDataPoint);
    }
  }
  
  public DataPoint createDataPoint()
  {
    return DataPoint.create(this.Sq);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  List<RawDataPoint> e(List<DataSource> paramList)
  {
    ArrayList localArrayList = new ArrayList(this.SM.size());
    Iterator localIterator = this.SM.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(new RawDataPoint((DataPoint)localIterator.next(), paramList));
    }
    return localArrayList;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof DataSet)) && (a((DataSet)paramObject)));
  }
  
  public List<DataPoint> getDataPoints()
  {
    return Collections.unmodifiableList(this.SM);
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
    return n.hashCode(new Object[] { this.Sp, this.Sq });
  }
  
  public boolean iC()
  {
    return this.SF;
  }
  
  List<RawDataPoint> iK()
  {
    return e(this.SN);
  }
  
  List<DataSource> iL()
  {
    return this.SN;
  }
  
  public String toString()
  {
    Object localObject = iK();
    String str = this.Sq.toDebugString();
    if (this.SM.size() < 10) {}
    for (;;)
    {
      return String.format("DataSet{%s %s}", new Object[] { str, localObject });
      localObject = String.format("%d data points, first 5: %s", new Object[] { Integer.valueOf(this.SM.size()), ((List)localObject).subList(0, 5) });
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    f.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/DataSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */