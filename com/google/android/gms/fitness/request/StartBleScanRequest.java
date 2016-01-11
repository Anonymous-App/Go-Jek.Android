package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.jr;
import java.util.Collections;
import java.util.List;

public class StartBleScanRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<StartBleScanRequest> CREATOR = new ac();
  private final int BR;
  private final List<DataType> SB;
  private final l UQ;
  private final int UR;
  
  StartBleScanRequest(int paramInt1, List<DataType> paramList, IBinder paramIBinder, int paramInt2)
  {
    this.BR = paramInt1;
    this.SB = paramList;
    this.UQ = l.a.ay(paramIBinder);
    this.UR = paramInt2;
  }
  
  private StartBleScanRequest(Builder paramBuilder)
  {
    this.BR = 2;
    this.SB = jr.b(Builder.a(paramBuilder));
    this.UQ = Builder.b(paramBuilder);
    this.UR = Builder.c(paramBuilder);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public List<DataType> getDataTypes()
  {
    return Collections.unmodifiableList(this.SB);
  }
  
  public int getTimeoutSecs()
  {
    return this.UR;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public IBinder jC()
  {
    return this.UQ.asBinder();
  }
  
  public String toString()
  {
    return n.h(this).a("dataTypes", this.SB).a("timeoutSecs", Integer.valueOf(this.UR)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ac.a(this, paramParcel, paramInt);
  }
  
  public static class Builder
  {
    private l UQ;
    private int UR = 10;
    private DataType[] Uy = new DataType[0];
    
    public Builder a(l paraml)
    {
      this.UQ = paraml;
      return this;
    }
    
    public StartBleScanRequest build()
    {
      if (this.UQ != null) {}
      for (boolean bool = true;; bool = false)
      {
        o.a(bool, "Must set BleScanCallback");
        return new StartBleScanRequest(this, null);
      }
    }
    
    public Builder setBleScanCallback(BleScanCallback paramBleScanCallback)
    {
      a(a.a.je().a(paramBleScanCallback));
      return this;
    }
    
    public Builder setDataTypes(DataType... paramVarArgs)
    {
      this.Uy = paramVarArgs;
      return this;
    }
    
    public Builder setTimeoutSecs(int paramInt)
    {
      boolean bool2 = true;
      if (paramInt > 0)
      {
        bool1 = true;
        o.b(bool1, "Stop time must be greater than zero");
        if (paramInt > 60) {
          break label40;
        }
      }
      label40:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        o.b(bool1, "Stop time must be less than 1 minute");
        this.UR = paramInt;
        return this;
        bool1 = false;
        break;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/StartBleScanRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */