package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.q;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SessionReadResult
  implements Result, SafeParcelable
{
  public static final Parcelable.Creator<SessionReadResult> CREATOR = new f();
  private final int BR;
  private final Status CM;
  private final List<q> UZ;
  private final List<Session> Ul;
  
  SessionReadResult(int paramInt, List<Session> paramList, List<q> paramList1, Status paramStatus)
  {
    this.BR = paramInt;
    this.Ul = paramList;
    this.UZ = Collections.unmodifiableList(paramList1);
    this.CM = paramStatus;
  }
  
  public SessionReadResult(List<Session> paramList, List<q> paramList1, Status paramStatus)
  {
    this.BR = 3;
    this.Ul = paramList;
    this.UZ = Collections.unmodifiableList(paramList1);
    this.CM = paramStatus;
  }
  
  public static SessionReadResult H(Status paramStatus)
  {
    return new SessionReadResult(new ArrayList(), new ArrayList(), paramStatus);
  }
  
  private boolean b(SessionReadResult paramSessionReadResult)
  {
    return (this.CM.equals(paramSessionReadResult.CM)) && (n.equal(this.Ul, paramSessionReadResult.Ul)) && (n.equal(this.UZ, paramSessionReadResult.UZ));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof SessionReadResult)) && (b((SessionReadResult)paramObject)));
  }
  
  public List<DataSet> getDataSet(Session paramSession)
  {
    o.b(this.Ul.contains(paramSession), "Attempting to read data for session %s which was not returned", new Object[] { paramSession });
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.UZ.iterator();
    while (localIterator.hasNext())
    {
      q localq = (q)localIterator.next();
      if (n.equal(paramSession, localq.getSession())) {
        localArrayList.add(localq.iW());
      }
    }
    return localArrayList;
  }
  
  public List<DataSet> getDataSet(Session paramSession, DataType paramDataType)
  {
    o.b(this.Ul.contains(paramSession), "Attempting to read data for session %s which was not returned", new Object[] { paramSession });
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.UZ.iterator();
    while (localIterator.hasNext())
    {
      q localq = (q)localIterator.next();
      if ((n.equal(paramSession, localq.getSession())) && (paramDataType.equals(localq.iW().getDataType()))) {
        localArrayList.add(localq.iW());
      }
    }
    return localArrayList;
  }
  
  public List<Session> getSessions()
  {
    return this.Ul;
  }
  
  public Status getStatus()
  {
    return this.CM;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.CM, this.Ul, this.UZ });
  }
  
  public List<q> jL()
  {
    return this.UZ;
  }
  
  public String toString()
  {
    return n.h(this).a("status", this.CM).a("sessions", this.Ul).a("sessionDataSets", this.UZ).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    f.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/result/SessionReadResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */