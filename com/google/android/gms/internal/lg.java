package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import java.util.Collections;
import java.util.List;

public class lg
  implements SafeParcelable
{
  public static final Parcelable.Creator<lg> CREATOR = new lh();
  private final int BR;
  private final List<DataType> SB;
  
  lg(int paramInt, List<DataType> paramList)
  {
    this.BR = paramInt;
    this.SB = paramList;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public List<DataType> getDataTypes()
  {
    return Collections.unmodifiableList(this.SB);
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public String toString()
  {
    return n.h(this).a("dataTypes", this.SB).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    lh.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/lg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */