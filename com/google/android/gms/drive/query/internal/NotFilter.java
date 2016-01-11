package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.drive.query.Filter;

public class NotFilter
  extends AbstractFilter
{
  public static final Parcelable.Creator<NotFilter> CREATOR = new k();
  final int BR;
  final FilterHolder QY;
  
  NotFilter(int paramInt, FilterHolder paramFilterHolder)
  {
    this.BR = paramInt;
    this.QY = paramFilterHolder;
  }
  
  public NotFilter(Filter paramFilter)
  {
    this(1, new FilterHolder(paramFilter));
  }
  
  public <T> T a(f<T> paramf)
  {
    return (T)paramf.j(this.QY.getFilter().a(paramf));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    k.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/query/internal/NotFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */