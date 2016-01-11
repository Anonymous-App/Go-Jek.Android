package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataDeleteRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataDeleteRequest> CREATOR = new d();
  private final int BR;
  private final long KS;
  private final List<DataType> SB;
  private final long Sr;
  private final List<DataSource> Uk;
  private final List<Session> Ul;
  private final boolean Um;
  private final boolean Un;
  
  DataDeleteRequest(int paramInt, long paramLong1, long paramLong2, List<DataSource> paramList, List<DataType> paramList1, List<Session> paramList2, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.BR = paramInt;
    this.KS = paramLong1;
    this.Sr = paramLong2;
    this.Uk = Collections.unmodifiableList(paramList);
    this.SB = Collections.unmodifiableList(paramList1);
    this.Ul = paramList2;
    this.Um = paramBoolean1;
    this.Un = paramBoolean2;
  }
  
  private DataDeleteRequest(Builder paramBuilder)
  {
    this.BR = 1;
    this.KS = Builder.a(paramBuilder);
    this.Sr = Builder.b(paramBuilder);
    this.Uk = Collections.unmodifiableList(Builder.c(paramBuilder));
    this.SB = Collections.unmodifiableList(Builder.d(paramBuilder));
    this.Ul = Collections.unmodifiableList(Builder.e(paramBuilder));
    this.Um = Builder.f(paramBuilder);
    this.Un = Builder.g(paramBuilder);
  }
  
  private boolean a(DataDeleteRequest paramDataDeleteRequest)
  {
    return (this.KS == paramDataDeleteRequest.KS) && (this.Sr == paramDataDeleteRequest.Sr) && (n.equal(this.Uk, paramDataDeleteRequest.Uk)) && (n.equal(this.SB, paramDataDeleteRequest.SB)) && (n.equal(this.Ul, paramDataDeleteRequest.Ul)) && (this.Um == paramDataDeleteRequest.Um) && (this.Un == paramDataDeleteRequest.Un);
  }
  
  public boolean deleteAllData()
  {
    return this.Um;
  }
  
  public boolean deleteAllSessions()
  {
    return this.Un;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof DataDeleteRequest)) && (a((DataDeleteRequest)paramObject)));
  }
  
  public List<DataSource> getDataSources()
  {
    return this.Uk;
  }
  
  public List<DataType> getDataTypes()
  {
    return this.SB;
  }
  
  public long getEndTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.Sr, TimeUnit.MILLISECONDS);
  }
  
  public List<Session> getSessions()
  {
    return this.Ul;
  }
  
  public long getStartTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.KS, TimeUnit.MILLISECONDS);
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Long.valueOf(this.KS), Long.valueOf(this.Sr) });
  }
  
  public long iD()
  {
    return this.KS;
  }
  
  public long iE()
  {
    return this.Sr;
  }
  
  public boolean jg()
  {
    return this.Um;
  }
  
  public boolean jh()
  {
    return this.Un;
  }
  
  public String toString()
  {
    return n.h(this).a("startTimeMillis", Long.valueOf(this.KS)).a("endTimeMillis", Long.valueOf(this.Sr)).a("dataSources", this.Uk).a("dateTypes", this.SB).a("sessions", this.Ul).a("deleteAllData", Boolean.valueOf(this.Um)).a("deleteAllSessions", Boolean.valueOf(this.Un)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    d.a(this, paramParcel, paramInt);
  }
  
  public static class Builder
  {
    private long KS;
    private List<DataType> SB = new ArrayList();
    private long Sr;
    private List<DataSource> Uk = new ArrayList();
    private List<Session> Ul = new ArrayList();
    private boolean Um = false;
    private boolean Un = false;
    
    private void ji()
    {
      if (this.Ul.isEmpty()) {
        return;
      }
      Iterator localIterator = this.Ul.iterator();
      label23:
      Session localSession;
      if (localIterator.hasNext())
      {
        localSession = (Session)localIterator.next();
        if ((localSession.getStartTime(TimeUnit.MILLISECONDS) < this.KS) || (localSession.getEndTime(TimeUnit.MILLISECONDS) > this.Sr)) {
          break label111;
        }
      }
      label111:
      for (boolean bool = true;; bool = false)
      {
        o.a(bool, "Session %s is outside the time interval [%d, %d]", new Object[] { localSession, Long.valueOf(this.KS), Long.valueOf(this.Sr) });
        break label23;
        break;
      }
    }
    
    public Builder addDataSource(DataSource paramDataSource)
    {
      boolean bool2 = true;
      if (!this.Um)
      {
        bool1 = true;
        o.b(bool1, "All data is already marked for deletion");
        if (paramDataSource == null) {
          break label60;
        }
      }
      label60:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        o.b(bool1, "Must specify a valid data source");
        if (!this.Uk.contains(paramDataSource)) {
          this.Uk.add(paramDataSource);
        }
        return this;
        bool1 = false;
        break;
      }
    }
    
    public Builder addDataType(DataType paramDataType)
    {
      boolean bool2 = true;
      if (!this.Um)
      {
        bool1 = true;
        o.b(bool1, "All data is already marked for deletion");
        if (paramDataType == null) {
          break label60;
        }
      }
      label60:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        o.b(bool1, "Must specify a valid data type");
        if (!this.SB.contains(paramDataType)) {
          this.SB.add(paramDataType);
        }
        return this;
        bool1 = false;
        break;
      }
    }
    
    public Builder addSession(Session paramSession)
    {
      boolean bool2 = true;
      if (!this.Un)
      {
        bool1 = true;
        o.b(bool1, "All sessions already marked for deletion");
        if (paramSession == null) {
          break label67;
        }
        bool1 = true;
        label23:
        o.b(bool1, "Must specify a valid session");
        if (paramSession.getEndTime(TimeUnit.MILLISECONDS) <= 0L) {
          break label72;
        }
      }
      label67:
      label72:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        o.b(bool1, "Must specify a session that has already ended");
        this.Ul.add(paramSession);
        return this;
        bool1 = false;
        break;
        bool1 = false;
        break label23;
      }
    }
    
    public DataDeleteRequest build()
    {
      boolean bool2 = false;
      boolean bool1;
      int i;
      if ((this.KS > 0L) && (this.Sr > this.KS))
      {
        bool1 = true;
        o.a(bool1, "Must specify a valid time interval");
        if ((!this.Um) && (this.Uk.isEmpty()) && (this.SB.isEmpty())) {
          break label124;
        }
        i = 1;
        label65:
        if ((!this.Un) && (this.Ul.isEmpty())) {
          break label129;
        }
      }
      label124:
      label129:
      for (int j = 1;; j = 0)
      {
        if (i == 0)
        {
          bool1 = bool2;
          if (j == 0) {}
        }
        else
        {
          bool1 = true;
        }
        o.a(bool1, "No data or session marked for deletion");
        ji();
        return new DataDeleteRequest(this, null);
        bool1 = false;
        break;
        i = 0;
        break label65;
      }
    }
    
    public Builder deleteAllData()
    {
      if ((this.SB.isEmpty()) && (this.Uk.isEmpty())) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Specific data source/type already specified for deletion. DataSources: %s DataTypes: %s", new Object[] { this.Uk, this.SB });
        this.Um = true;
        return this;
      }
    }
    
    public Builder deleteAllSessions()
    {
      o.b(this.Ul.isEmpty(), "Specific sessions already added for deletion: %s", new Object[] { this.Ul });
      this.Un = true;
      return this;
    }
    
    public Builder setTimeInterval(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    {
      if (paramLong1 > 0L)
      {
        bool = true;
        o.b(bool, "Invalid start time :%d", new Object[] { Long.valueOf(paramLong1) });
        if (paramLong2 <= paramLong1) {
          break label82;
        }
      }
      label82:
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Invalid end time :%d", new Object[] { Long.valueOf(paramLong2) });
        this.KS = paramTimeUnit.toMillis(paramLong1);
        this.Sr = paramTimeUnit.toMillis(paramLong2);
        return this;
        bool = false;
        break;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/DataDeleteRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */