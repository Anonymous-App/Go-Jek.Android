package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import java.util.List;

public class e
  implements SafeParcelable
{
  public static final Parcelable.Creator<e> CREATOR = new f();
  private final int BR;
  private final DataSet Ts;
  
  e(int paramInt, DataSet paramDataSet)
  {
    this.BR = paramInt;
    this.Ts = paramDataSet;
  }
  
  private e(a parama)
  {
    this.BR = 1;
    this.Ts = a.a(parama);
  }
  
  private boolean a(e parame)
  {
    return n.equal(this.Ts, parame.Ts);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof e)) && (a((e)paramObject)));
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.Ts });
  }
  
  public DataSet iW()
  {
    return this.Ts;
  }
  
  public String toString()
  {
    return n.h(this).a("dataSet", this.Ts).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    f.a(this, paramParcel, paramInt);
  }
  
  public static class a
  {
    private DataSet Ts;
    
    public a b(DataSet paramDataSet)
    {
      this.Ts = paramDataSet;
      return this;
    }
    
    public e jj()
    {
      boolean bool2 = true;
      if (this.Ts != null)
      {
        bool1 = true;
        o.a(bool1, "Must set the data set");
        if (this.Ts.getDataPoints().isEmpty()) {
          break label76;
        }
        bool1 = true;
        label34:
        o.a(bool1, "Cannot use an empty data set");
        if (this.Ts.getDataSource().iM() == null) {
          break label81;
        }
      }
      label76:
      label81:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        o.a(bool1, "Must set the app package name for the data source");
        return new e(this, null);
        bool1 = false;
        break;
        bool1 = false;
        break label34;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */