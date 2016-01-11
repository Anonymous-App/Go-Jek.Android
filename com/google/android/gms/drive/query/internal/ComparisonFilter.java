package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class ComparisonFilter<T>
  extends AbstractFilter
{
  public static final a CREATOR = new a();
  final int BR;
  final Operator QK;
  final MetadataBundle QL;
  final MetadataField<T> QM;
  
  ComparisonFilter(int paramInt, Operator paramOperator, MetadataBundle paramMetadataBundle)
  {
    this.BR = paramInt;
    this.QK = paramOperator;
    this.QL = paramMetadataBundle;
    this.QM = e.b(paramMetadataBundle);
  }
  
  public ComparisonFilter(Operator paramOperator, SearchableMetadataField<T> paramSearchableMetadataField, T paramT)
  {
    this(1, paramOperator, MetadataBundle.a(paramSearchableMetadataField, paramT));
  }
  
  public <F> F a(f<F> paramf)
  {
    return (F)paramf.b(this.QK, this.QM, getValue());
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public T getValue()
  {
    return (T)this.QL.a(this.QM);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/query/internal/ComparisonFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */