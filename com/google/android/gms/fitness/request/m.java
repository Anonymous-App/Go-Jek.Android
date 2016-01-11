package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;

public class m
  implements SafeParcelable
{
  public static final Parcelable.Creator<m> CREATOR = new n();
  private final int BR;
  private final DataType Sp;
  
  m(int paramInt, DataType paramDataType)
  {
    this.BR = paramInt;
    this.Sp = paramDataType;
  }
  
  private m(a parama)
  {
    this.BR = 1;
    this.Sp = a.a(parama);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public DataType getDataType()
  {
    return this.Sp;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    n.a(this, paramParcel, paramInt);
  }
  
  public static class a
  {
    private DataType Sp;
    
    public a c(DataType paramDataType)
    {
      this.Sp = paramDataType;
      return this;
    }
    
    public m jq()
    {
      return new m(this, null);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */