package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class HasFilter<T>
  extends AbstractFilter
{
  public static final g CREATOR = new g();
  final int BR;
  final MetadataBundle QL;
  final MetadataField<T> QM;
  
  HasFilter(int paramInt, MetadataBundle paramMetadataBundle)
  {
    this.BR = paramInt;
    this.QL = paramMetadataBundle;
    this.QM = e.b(paramMetadataBundle);
  }
  
  public <F> F a(f<F> paramf)
  {
    return (F)paramf.d(this.QM, getValue());
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
    g.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/query/internal/HasFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */