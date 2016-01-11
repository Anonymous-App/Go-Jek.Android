package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Subscription
  implements SafeParcelable
{
  public static final Parcelable.Creator<Subscription> CREATOR = new s();
  private final int BR;
  private final DataType Sp;
  private final DataSource Sq;
  private final long Tt;
  private final int Tu;
  
  Subscription(int paramInt1, DataSource paramDataSource, DataType paramDataType, long paramLong, int paramInt2)
  {
    this.BR = paramInt1;
    this.Sq = paramDataSource;
    this.Sp = paramDataType;
    this.Tt = paramLong;
    this.Tu = paramInt2;
  }
  
  private Subscription(a parama)
  {
    this.BR = 1;
    this.Sp = a.a(parama);
    this.Sq = a.b(parama);
    this.Tt = a.c(parama);
    this.Tu = a.d(parama);
  }
  
  private boolean a(Subscription paramSubscription)
  {
    return (n.equal(this.Sq, paramSubscription.Sq)) && (n.equal(this.Sp, paramSubscription.Sp)) && (this.Tt == paramSubscription.Tt) && (this.Tu == paramSubscription.Tu);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof Subscription)) && (a((Subscription)paramObject)));
  }
  
  public int getAccuracyMode()
  {
    return this.Tu;
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
    return n.hashCode(new Object[] { this.Sq, this.Sq, Long.valueOf(this.Tt), Integer.valueOf(this.Tu) });
  }
  
  public long iX()
  {
    return this.Tt;
  }
  
  public DataType iY()
  {
    if (this.Sp == null) {
      return this.Sq.getDataType();
    }
    return this.Sp;
  }
  
  public String toDebugString()
  {
    if (this.Sq == null) {}
    for (String str = this.Sp.getName();; str = this.Sq.toDebugString()) {
      return String.format("Subscription{%s}", new Object[] { str });
    }
  }
  
  public String toString()
  {
    return n.h(this).a("dataSource", this.Sq).a("dataType", this.Sp).a("samplingIntervalMicros", Long.valueOf(this.Tt)).a("accuracyMode", Integer.valueOf(this.Tu)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    s.a(this, paramParcel, paramInt);
  }
  
  public static class a
  {
    private DataType Sp;
    private DataSource Sq;
    private long Tt = -1L;
    private int Tu = 2;
    
    public a b(DataSource paramDataSource)
    {
      this.Sq = paramDataSource;
      return this;
    }
    
    public a b(DataType paramDataType)
    {
      this.Sp = paramDataType;
      return this;
    }
    
    public Subscription iZ()
    {
      boolean bool2 = false;
      if ((this.Sq != null) || (this.Sp != null)) {}
      for (boolean bool1 = true;; bool1 = false)
      {
        o.a(bool1, "Must call setDataSource() or setDataType()");
        if ((this.Sp != null) && (this.Sq != null))
        {
          bool1 = bool2;
          if (!this.Sp.equals(this.Sq.getDataType())) {}
        }
        else
        {
          bool1 = true;
        }
        o.a(bool1, "Specified data type is incompatible with specified data source");
        return new Subscription(this, null);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/Subscription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */