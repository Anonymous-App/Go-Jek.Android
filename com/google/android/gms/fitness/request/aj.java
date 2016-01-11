package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

public class aj
  implements SafeParcelable
{
  public static final Parcelable.Creator<aj> CREATOR = new ak();
  private final int BR;
  private final DataType Sp;
  private final DataSource Sq;
  
  aj(int paramInt, DataType paramDataType, DataSource paramDataSource)
  {
    this.BR = paramInt;
    this.Sp = paramDataType;
    this.Sq = paramDataSource;
  }
  
  private aj(a parama)
  {
    this.BR = 1;
    this.Sp = a.a(parama);
    this.Sq = a.b(parama);
  }
  
  private boolean a(aj paramaj)
  {
    return (n.equal(this.Sq, paramaj.Sq)) && (n.equal(this.Sp, paramaj.Sp));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof aj)) && (a((aj)paramObject)));
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
    return n.hashCode(new Object[] { this.Sq, this.Sp });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ak.a(this, paramParcel, paramInt);
  }
  
  public static class a
  {
    private DataType Sp;
    private DataSource Sq;
    
    public a d(DataSource paramDataSource)
    {
      this.Sq = paramDataSource;
      return this;
    }
    
    public a d(DataType paramDataType)
    {
      this.Sp = paramDataType;
      return this;
    }
    
    public aj jG()
    {
      if ((this.Sp != null) && (this.Sq != null)) {
        throw new IllegalArgumentException("Cannot specify both dataType and dataSource");
      }
      return new aj(this, null);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/aj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */