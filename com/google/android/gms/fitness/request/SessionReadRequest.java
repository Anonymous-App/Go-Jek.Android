package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SessionReadRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<SessionReadRequest> CREATOR = new t();
  private final int BR;
  private final long KS;
  private final List<DataType> SB;
  private final long Sr;
  private final String UN;
  private boolean UO;
  private final List<String> UP;
  private final List<DataSource> Uk;
  private final boolean Uv;
  private final String vL;
  
  SessionReadRequest(int paramInt, String paramString1, String paramString2, long paramLong1, long paramLong2, List<DataType> paramList, List<DataSource> paramList1, boolean paramBoolean1, boolean paramBoolean2, List<String> paramList2)
  {
    this.BR = paramInt;
    this.UN = paramString1;
    this.vL = paramString2;
    this.KS = paramLong1;
    this.Sr = paramLong2;
    this.SB = Collections.unmodifiableList(paramList);
    this.Uk = Collections.unmodifiableList(paramList1);
    this.UO = paramBoolean1;
    this.Uv = paramBoolean2;
    this.UP = paramList2;
  }
  
  private SessionReadRequest(Builder paramBuilder)
  {
    this.BR = 3;
    this.UN = Builder.a(paramBuilder);
    this.vL = Builder.b(paramBuilder);
    this.KS = Builder.c(paramBuilder);
    this.Sr = Builder.d(paramBuilder);
    this.SB = Collections.unmodifiableList(Builder.e(paramBuilder));
    this.Uk = Collections.unmodifiableList(Builder.f(paramBuilder));
    this.UO = Builder.g(paramBuilder);
    this.Uv = Builder.h(paramBuilder);
    this.UP = Collections.unmodifiableList(Builder.i(paramBuilder));
  }
  
  private boolean a(SessionReadRequest paramSessionReadRequest)
  {
    return (n.equal(this.UN, paramSessionReadRequest.UN)) && (this.vL.equals(paramSessionReadRequest.vL)) && (this.KS == paramSessionReadRequest.KS) && (this.Sr == paramSessionReadRequest.Sr) && (n.equal(this.SB, paramSessionReadRequest.SB)) && (n.equal(this.Uk, paramSessionReadRequest.Uk)) && (this.UO == paramSessionReadRequest.UO) && (this.UP.equals(paramSessionReadRequest.UP)) && (this.Uv == paramSessionReadRequest.Uv);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof SessionReadRequest)) && (a((SessionReadRequest)paramObject)));
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
  
  public List<String> getExcludedPackages()
  {
    return this.UP;
  }
  
  public String getSessionId()
  {
    return this.vL;
  }
  
  public String getSessionName()
  {
    return this.UN;
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
    return n.hashCode(new Object[] { this.UN, this.vL, Long.valueOf(this.KS), Long.valueOf(this.Sr) });
  }
  
  public long iD()
  {
    return this.KS;
  }
  
  public long iE()
  {
    return this.Sr;
  }
  
  public boolean includeSessionsFromAllApps()
  {
    return this.UO;
  }
  
  public boolean jl()
  {
    return this.Uv;
  }
  
  public boolean jz()
  {
    return this.UO;
  }
  
  public String toString()
  {
    return n.h(this).a("sessionName", this.UN).a("sessionId", this.vL).a("startTimeMillis", Long.valueOf(this.KS)).a("endTimeMillis", Long.valueOf(this.Sr)).a("dataTypes", this.SB).a("dataSources", this.Uk).a("sessionsFromAllApps", Boolean.valueOf(this.UO)).a("excludedPackages", this.UP).a("useServer", Boolean.valueOf(this.Uv)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    t.a(this, paramParcel, paramInt);
  }
  
  public static class Builder
  {
    private long KS = 0L;
    private List<DataType> SB = new ArrayList();
    private long Sr = 0L;
    private String UN;
    private boolean UO = false;
    private List<String> UP = new ArrayList();
    private List<DataSource> Uk = new ArrayList();
    private boolean Uv = false;
    private String vL;
    
    public SessionReadRequest build()
    {
      if (this.KS > 0L)
      {
        bool = true;
        o.b(bool, "Invalid start time: %s", new Object[] { Long.valueOf(this.KS) });
        if ((this.Sr <= 0L) || (this.Sr <= this.KS)) {
          break label89;
        }
      }
      label89:
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Invalid end time: %s", new Object[] { Long.valueOf(this.Sr) });
        return new SessionReadRequest(this, null);
        bool = false;
        break;
      }
    }
    
    public Builder enableServerQueries()
    {
      this.Uv = true;
      return this;
    }
    
    public Builder excludePackage(String paramString)
    {
      o.b(paramString, "Attempting to use a null package name");
      if (!this.UP.contains(paramString)) {
        this.UP.add(paramString);
      }
      return this;
    }
    
    public Builder read(DataSource paramDataSource)
    {
      o.b(paramDataSource, "Attempting to add a null data source");
      if (!this.Uk.contains(paramDataSource)) {
        this.Uk.add(paramDataSource);
      }
      return this;
    }
    
    public Builder read(DataType paramDataType)
    {
      o.b(paramDataType, "Attempting to use a null data type");
      if (!this.SB.contains(paramDataType)) {
        this.SB.add(paramDataType);
      }
      return this;
    }
    
    public Builder readSessionsFromAllApps()
    {
      this.UO = true;
      return this;
    }
    
    public Builder setSessionId(String paramString)
    {
      this.vL = paramString;
      return this;
    }
    
    public Builder setSessionName(String paramString)
    {
      this.UN = paramString;
      return this;
    }
    
    public Builder setTimeInterval(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    {
      this.KS = paramTimeUnit.toMillis(paramLong1);
      this.Sr = paramTimeUnit.toMillis(paramLong2);
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/SessionReadRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */