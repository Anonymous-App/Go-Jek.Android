package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.c;
import com.google.android.gms.fitness.FitnessActivities;
import java.util.concurrent.TimeUnit;

public class Session
  implements SafeParcelable
{
  public static final Parcelable.Creator<Session> CREATOR = new p();
  public static final String EXTRA_SESSION = "vnd.google.fitness.session";
  public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.session/";
  private final int BR;
  private final long KS;
  private final int SC;
  private final a SP;
  private final long Sr;
  private final String Tq;
  private final String Tr;
  private final String mName;
  
  Session(int paramInt1, long paramLong1, long paramLong2, String paramString1, String paramString2, String paramString3, int paramInt2, a parama)
  {
    this.BR = paramInt1;
    this.KS = paramLong1;
    this.Sr = paramLong2;
    this.mName = paramString1;
    this.Tq = paramString2;
    this.Tr = paramString3;
    this.SC = paramInt2;
    this.SP = parama;
  }
  
  private Session(Builder paramBuilder)
  {
    this.BR = 2;
    this.KS = Builder.a(paramBuilder);
    this.Sr = Builder.b(paramBuilder);
    this.mName = Builder.c(paramBuilder);
    this.Tq = Builder.d(paramBuilder);
    this.Tr = Builder.e(paramBuilder);
    this.SC = Builder.f(paramBuilder);
    this.SP = Builder.g(paramBuilder);
  }
  
  private boolean a(Session paramSession)
  {
    return (this.KS == paramSession.KS) && (this.Sr == paramSession.Sr) && (n.equal(this.mName, paramSession.mName)) && (n.equal(this.Tq, paramSession.Tq)) && (n.equal(this.Tr, paramSession.Tr)) && (n.equal(this.SP, paramSession.SP)) && (this.SC == paramSession.SC);
  }
  
  public static Session extract(Intent paramIntent)
  {
    if (paramIntent == null) {
      return null;
    }
    return (Session)c.a(paramIntent, "vnd.google.fitness.session", CREATOR);
  }
  
  public static String getMimeType(String paramString)
  {
    return "vnd.google.fitness.session/" + paramString;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof Session)) && (a((Session)paramObject)));
  }
  
  public String getActivity()
  {
    return FitnessActivities.getName(this.SC);
  }
  
  public String getAppPackageName()
  {
    if (this.SP == null) {
      return null;
    }
    return this.SP.getPackageName();
  }
  
  public String getDescription()
  {
    return this.Tr;
  }
  
  public long getEndTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.Sr, TimeUnit.MILLISECONDS);
  }
  
  public String getIdentifier()
  {
    return this.Tq;
  }
  
  public String getName()
  {
    return this.mName;
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
    return n.hashCode(new Object[] { Long.valueOf(this.KS), Long.valueOf(this.Sr), this.Tq });
  }
  
  public int iB()
  {
    return this.SC;
  }
  
  public long iD()
  {
    return this.KS;
  }
  
  public long iE()
  {
    return this.Sr;
  }
  
  public a iM()
  {
    return this.SP;
  }
  
  public boolean isOngoing()
  {
    return this.Sr == 0L;
  }
  
  public String toString()
  {
    return n.h(this).a("startTime", Long.valueOf(this.KS)).a("endTime", Long.valueOf(this.Sr)).a("name", this.mName).a("identifier", this.Tq).a("description", this.Tr).a("activity", Integer.valueOf(this.SC)).a("application", this.SP).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    p.a(this, paramParcel, paramInt);
  }
  
  public static class Builder
  {
    private long KS = 0L;
    private int SC = 4;
    private a SP;
    private long Sr = 0L;
    private String Tq;
    private String Tr;
    private String mName = null;
    
    public Session build()
    {
      boolean bool2 = false;
      boolean bool1;
      StringBuilder localStringBuilder;
      if (this.KS > 0L)
      {
        bool1 = true;
        o.a(bool1, "Start time should be specified.");
        if (this.Sr != 0L)
        {
          bool1 = bool2;
          if (this.Sr <= this.KS) {}
        }
        else
        {
          bool1 = true;
        }
        o.a(bool1, "End time should be later than start time.");
        if (this.Tq == null)
        {
          localStringBuilder = new StringBuilder();
          if (this.mName != null) {
            break label111;
          }
        }
      }
      label111:
      for (String str = "";; str = this.mName)
      {
        this.Tq = (str + this.KS);
        return new Session(this, null);
        bool1 = false;
        break;
      }
    }
    
    public Builder cK(int paramInt)
    {
      this.SC = paramInt;
      return this;
    }
    
    public Builder setActivity(String paramString)
    {
      return cK(FitnessActivities.bp(paramString));
    }
    
    public Builder setDescription(String paramString)
    {
      if (paramString.length() <= 1000) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Session description cannot exceed %d characters", new Object[] { Integer.valueOf(1000) });
        this.Tr = paramString;
        return this;
      }
    }
    
    public Builder setEndTime(long paramLong, TimeUnit paramTimeUnit)
    {
      if (paramLong >= 0L) {}
      for (boolean bool = true;; bool = false)
      {
        o.a(bool, "End time should be positive.");
        this.Sr = paramTimeUnit.toMillis(paramLong);
        return this;
      }
    }
    
    public Builder setIdentifier(String paramString)
    {
      if ((paramString != null) && (TextUtils.getTrimmedLength(paramString) > 0)) {}
      for (boolean bool = true;; bool = false)
      {
        o.K(bool);
        this.Tq = paramString;
        return this;
      }
    }
    
    public Builder setName(String paramString)
    {
      if (paramString.length() <= 100) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Session name cannot exceed %d characters", new Object[] { Integer.valueOf(100) });
        this.mName = paramString;
        return this;
      }
    }
    
    public Builder setStartTime(long paramLong, TimeUnit paramTimeUnit)
    {
      if (paramLong > 0L) {}
      for (boolean bool = true;; bool = false)
      {
        o.a(bool, "Start time should be positive.");
        this.KS = paramTimeUnit.toMillis(paramLong);
        return this;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/Session.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */