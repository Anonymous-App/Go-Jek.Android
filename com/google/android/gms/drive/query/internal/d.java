package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class d
  implements Parcelable.Creator<FilterHolder>
{
  static void a(FilterHolder paramFilterHolder, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramFilterHolder.QO, paramInt, false);
    b.c(paramParcel, 1000, paramFilterHolder.BR);
    b.a(paramParcel, 2, paramFilterHolder.QP, paramInt, false);
    b.a(paramParcel, 3, paramFilterHolder.QQ, paramInt, false);
    b.a(paramParcel, 4, paramFilterHolder.QR, paramInt, false);
    b.a(paramParcel, 5, paramFilterHolder.QS, paramInt, false);
    b.a(paramParcel, 6, paramFilterHolder.QT, paramInt, false);
    b.a(paramParcel, 7, paramFilterHolder.QU, paramInt, false);
    b.H(paramParcel, i);
  }
  
  public FilterHolder aN(Parcel paramParcel)
  {
    HasFilter localHasFilter = null;
    int j = a.C(paramParcel);
    int i = 0;
    MatchAllFilter localMatchAllFilter = null;
    InFilter localInFilter = null;
    NotFilter localNotFilter = null;
    LogicalFilter localLogicalFilter = null;
    FieldOnlyFilter localFieldOnlyFilter = null;
    ComparisonFilter localComparisonFilter = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        localComparisonFilter = (ComparisonFilter)a.a(paramParcel, k, ComparisonFilter.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        localFieldOnlyFilter = (FieldOnlyFilter)a.a(paramParcel, k, FieldOnlyFilter.CREATOR);
        break;
      case 3: 
        localLogicalFilter = (LogicalFilter)a.a(paramParcel, k, LogicalFilter.CREATOR);
        break;
      case 4: 
        localNotFilter = (NotFilter)a.a(paramParcel, k, NotFilter.CREATOR);
        break;
      case 5: 
        localInFilter = (InFilter)a.a(paramParcel, k, InFilter.CREATOR);
        break;
      case 6: 
        localMatchAllFilter = (MatchAllFilter)a.a(paramParcel, k, MatchAllFilter.CREATOR);
        break;
      case 7: 
        localHasFilter = (HasFilter)a.a(paramParcel, k, HasFilter.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new FilterHolder(i, localComparisonFilter, localFieldOnlyFilter, localLogicalFilter, localNotFilter, localInFilter, localMatchAllFilter, localHasFilter);
  }
  
  public FilterHolder[] bZ(int paramInt)
  {
    return new FilterHolder[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/query/internal/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */