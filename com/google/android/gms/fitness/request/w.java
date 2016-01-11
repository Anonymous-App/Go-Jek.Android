package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Session;
import java.util.concurrent.TimeUnit;

public class w
  implements SafeParcelable
{
  public static final Parcelable.Creator<w> CREATOR = new x();
  private final int BR;
  private final Session St;
  
  w(int paramInt, Session paramSession)
  {
    this.BR = paramInt;
    this.St = paramSession;
  }
  
  private w(a parama)
  {
    this.BR = 1;
    this.St = a.a(parama);
  }
  
  private boolean a(w paramw)
  {
    return n.equal(this.St, paramw.St);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof w)) && (a((w)paramObject)));
  }
  
  public Session getSession()
  {
    return this.St;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.St });
  }
  
  public String toString()
  {
    return n.h(this).a("session", this.St).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    x.a(this, paramParcel, paramInt);
  }
  
  public static class a
  {
    private Session St;
    
    public a b(Session paramSession)
    {
      if (paramSession.getEndTime(TimeUnit.MILLISECONDS) == 0L) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Cannot start a session which has already ended");
        this.St = paramSession;
        return this;
      }
    }
    
    public w jA()
    {
      return new w(this, null);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/w.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */