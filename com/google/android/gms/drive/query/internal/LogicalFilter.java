package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.drive.query.Filter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LogicalFilter
  extends AbstractFilter
{
  public static final Parcelable.Creator<LogicalFilter> CREATOR = new i();
  final int BR;
  private List<Filter> QF;
  final Operator QK;
  final List<FilterHolder> QX;
  
  LogicalFilter(int paramInt, Operator paramOperator, List<FilterHolder> paramList)
  {
    this.BR = paramInt;
    this.QK = paramOperator;
    this.QX = paramList;
  }
  
  public LogicalFilter(Operator paramOperator, Filter paramFilter, Filter... paramVarArgs)
  {
    this.BR = 1;
    this.QK = paramOperator;
    this.QX = new ArrayList(paramVarArgs.length + 1);
    this.QX.add(new FilterHolder(paramFilter));
    this.QF = new ArrayList(paramVarArgs.length + 1);
    this.QF.add(paramFilter);
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      paramOperator = paramVarArgs[i];
      this.QX.add(new FilterHolder(paramOperator));
      this.QF.add(paramOperator);
      i += 1;
    }
  }
  
  public LogicalFilter(Operator paramOperator, Iterable<Filter> paramIterable)
  {
    this.BR = 1;
    this.QK = paramOperator;
    this.QF = new ArrayList();
    this.QX = new ArrayList();
    paramOperator = paramIterable.iterator();
    while (paramOperator.hasNext())
    {
      paramIterable = (Filter)paramOperator.next();
      this.QF.add(paramIterable);
      this.QX.add(new FilterHolder(paramIterable));
    }
  }
  
  public <T> T a(f<T> paramf)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.QX.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((FilterHolder)localIterator.next()).getFilter().a(paramf));
    }
    return (T)paramf.b(this.QK, localArrayList);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    i.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/query/internal/LogicalFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */