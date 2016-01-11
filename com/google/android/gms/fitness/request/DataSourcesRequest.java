package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.jr;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataSourcesRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataSourcesRequest> CREATOR = new h();
  private final int BR;
  private final List<DataType> SB;
  private final List<Integer> Uw;
  private final boolean Ux;
  
  DataSourcesRequest(int paramInt, List<DataType> paramList, List<Integer> paramList1, boolean paramBoolean)
  {
    this.BR = paramInt;
    this.SB = paramList;
    this.Uw = paramList1;
    this.Ux = paramBoolean;
  }
  
  private DataSourcesRequest(Builder paramBuilder)
  {
    this.BR = 2;
    this.SB = jr.b(Builder.a(paramBuilder));
    this.Uw = Arrays.asList(jr.a(Builder.b(paramBuilder)));
    this.Ux = Builder.c(paramBuilder);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public List<DataType> getDataTypes()
  {
    return Collections.unmodifiableList(this.SB);
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public boolean jo()
  {
    return this.Ux;
  }
  
  List<Integer> jp()
  {
    return this.Uw;
  }
  
  public String toString()
  {
    n.a locala = n.h(this).a("dataTypes", this.SB).a("sourceTypes", this.Uw);
    if (this.Ux) {
      locala.a("includeDbOnlySources", "true");
    }
    return locala.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    h.a(this, paramParcel, paramInt);
  }
  
  public static class Builder
  {
    private boolean Ux = false;
    private DataType[] Uy = new DataType[0];
    private int[] Uz = { 0, 1 };
    
    public DataSourcesRequest build()
    {
      boolean bool2 = true;
      if (this.Uy.length > 0)
      {
        bool1 = true;
        o.a(bool1, "Must add at least one data type");
        if (this.Uz.length <= 0) {
          break label49;
        }
      }
      label49:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        o.a(bool1, "Must add at least one data source type");
        return new DataSourcesRequest(this, null);
        bool1 = false;
        break;
      }
    }
    
    public Builder setDataSourceTypes(int... paramVarArgs)
    {
      this.Uz = paramVarArgs;
      return this;
    }
    
    public Builder setDataTypes(DataType... paramVarArgs)
    {
      this.Uy = paramVarArgs;
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/DataSourcesRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */