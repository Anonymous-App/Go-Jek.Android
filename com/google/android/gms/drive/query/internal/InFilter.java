package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.b;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class InFilter<T>
  extends AbstractFilter
{
  public static final h CREATOR = new h();
  final int BR;
  final MetadataBundle QL;
  private final b<T> QW;
  
  InFilter(int paramInt, MetadataBundle paramMetadataBundle)
  {
    this.BR = paramInt;
    this.QL = paramMetadataBundle;
    this.QW = ((b)e.b(paramMetadataBundle));
  }
  
  public InFilter(SearchableCollectionMetadataField<T> paramSearchableCollectionMetadataField, T paramT)
  {
    this(1, MetadataBundle.a(paramSearchableCollectionMetadataField, Collections.singleton(paramT)));
  }
  
  public <F> F a(f<F> paramf)
  {
    return (F)paramf.b(this.QW, getValue());
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public T getValue()
  {
    return (T)((Collection)this.QL.a(this.QW)).iterator().next();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    h.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/query/internal/InFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */