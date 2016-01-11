package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Filter;

public class FilterHolder
  implements SafeParcelable
{
  public static final Parcelable.Creator<FilterHolder> CREATOR = new d();
  final int BR;
  final ComparisonFilter<?> QO;
  final FieldOnlyFilter QP;
  final LogicalFilter QQ;
  final NotFilter QR;
  final InFilter<?> QS;
  final MatchAllFilter QT;
  final HasFilter QU;
  private final Filter QV;
  
  FilterHolder(int paramInt, ComparisonFilter<?> paramComparisonFilter, FieldOnlyFilter paramFieldOnlyFilter, LogicalFilter paramLogicalFilter, NotFilter paramNotFilter, InFilter<?> paramInFilter, MatchAllFilter paramMatchAllFilter, HasFilter<?> paramHasFilter)
  {
    this.BR = paramInt;
    this.QO = paramComparisonFilter;
    this.QP = paramFieldOnlyFilter;
    this.QQ = paramLogicalFilter;
    this.QR = paramNotFilter;
    this.QS = paramInFilter;
    this.QT = paramMatchAllFilter;
    this.QU = paramHasFilter;
    if (this.QO != null)
    {
      this.QV = this.QO;
      return;
    }
    if (this.QP != null)
    {
      this.QV = this.QP;
      return;
    }
    if (this.QQ != null)
    {
      this.QV = this.QQ;
      return;
    }
    if (this.QR != null)
    {
      this.QV = this.QR;
      return;
    }
    if (this.QS != null)
    {
      this.QV = this.QS;
      return;
    }
    if (this.QT != null)
    {
      this.QV = this.QT;
      return;
    }
    if (this.QU != null)
    {
      this.QV = this.QU;
      return;
    }
    throw new IllegalArgumentException("At least one filter must be set.");
  }
  
  public FilterHolder(Filter paramFilter)
  {
    this.BR = 2;
    if ((paramFilter instanceof ComparisonFilter))
    {
      localObject = (ComparisonFilter)paramFilter;
      this.QO = ((ComparisonFilter)localObject);
      if (!(paramFilter instanceof FieldOnlyFilter)) {
        break label192;
      }
      localObject = (FieldOnlyFilter)paramFilter;
      label38:
      this.QP = ((FieldOnlyFilter)localObject);
      if (!(paramFilter instanceof LogicalFilter)) {
        break label197;
      }
      localObject = (LogicalFilter)paramFilter;
      label55:
      this.QQ = ((LogicalFilter)localObject);
      if (!(paramFilter instanceof NotFilter)) {
        break label202;
      }
      localObject = (NotFilter)paramFilter;
      label72:
      this.QR = ((NotFilter)localObject);
      if (!(paramFilter instanceof InFilter)) {
        break label207;
      }
      localObject = (InFilter)paramFilter;
      label89:
      this.QS = ((InFilter)localObject);
      if (!(paramFilter instanceof MatchAllFilter)) {
        break label212;
      }
      localObject = (MatchAllFilter)paramFilter;
      label106:
      this.QT = ((MatchAllFilter)localObject);
      if (!(paramFilter instanceof HasFilter)) {
        break label217;
      }
    }
    label192:
    label197:
    label202:
    label207:
    label212:
    label217:
    for (Object localObject = (HasFilter)paramFilter;; localObject = null)
    {
      this.QU = ((HasFilter)localObject);
      if ((this.QO != null) || (this.QP != null) || (this.QQ != null) || (this.QR != null) || (this.QS != null) || (this.QT != null) || (this.QU != null)) {
        break label222;
      }
      throw new IllegalArgumentException("Invalid filter type or null filter.");
      localObject = null;
      break;
      localObject = null;
      break label38;
      localObject = null;
      break label55;
      localObject = null;
      break label72;
      localObject = null;
      break label89;
      localObject = null;
      break label106;
    }
    label222:
    this.QV = paramFilter;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Filter getFilter()
  {
    return this.QV;
  }
  
  public String toString()
  {
    return String.format("FilterHolder[%s]", new Object[] { this.QV });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    d.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/query/internal/FilterHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */