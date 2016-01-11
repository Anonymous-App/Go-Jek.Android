package com.google.android.gms.fitness.data;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.c;
import com.google.android.gms.internal.kw;

public class DataSource
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataSource> CREATOR = new g();
  public static final String EXTRA_DATA_SOURCE = "vnd.google.fitness.data_source";
  public static final int TYPE_DERIVED = 1;
  public static final int TYPE_RAW = 0;
  private final int BR;
  private final int FD;
  private final Device SO;
  private final a SP;
  private final String SQ;
  private final boolean SR;
  private final String SS;
  private final DataType Sp;
  private final String mName;
  
  DataSource(int paramInt1, DataType paramDataType, String paramString1, int paramInt2, Device paramDevice, a parama, String paramString2, boolean paramBoolean)
  {
    this.BR = paramInt1;
    this.Sp = paramDataType;
    this.FD = paramInt2;
    this.mName = paramString1;
    this.SO = paramDevice;
    this.SP = parama;
    this.SQ = paramString2;
    this.SR = paramBoolean;
    this.SS = iN();
  }
  
  private DataSource(Builder paramBuilder)
  {
    this.BR = 3;
    this.Sp = Builder.a(paramBuilder);
    this.FD = Builder.b(paramBuilder);
    this.mName = Builder.c(paramBuilder);
    this.SO = Builder.d(paramBuilder);
    this.SP = Builder.e(paramBuilder);
    this.SQ = Builder.f(paramBuilder);
    this.SR = Builder.g(paramBuilder);
    this.SS = iN();
  }
  
  private boolean a(DataSource paramDataSource)
  {
    return this.SS.equals(paramDataSource.SS);
  }
  
  public static DataSource extract(Intent paramIntent)
  {
    if (paramIntent == null) {
      return null;
    }
    return (DataSource)c.a(paramIntent, "vnd.google.fitness.data_source", CREATOR);
  }
  
  private String getTypeString()
  {
    switch (this.FD)
    {
    default: 
      throw new IllegalArgumentException("invalid type value");
    case 0: 
      return "raw";
    }
    return "derived";
  }
  
  private String iN()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getTypeString());
    localStringBuilder.append(":").append(this.Sp.getName());
    if (this.SP != null) {
      localStringBuilder.append(":").append(this.SP.getPackageName());
    }
    if (this.SO != null) {
      localStringBuilder.append(":").append(this.SO.getStreamIdentifier());
    }
    if (this.SQ != null) {
      localStringBuilder.append(":").append(this.SQ);
    }
    return localStringBuilder.toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof DataSource)) && (a((DataSource)paramObject)));
  }
  
  public String getAppPackageName()
  {
    if (this.SP == null) {
      return null;
    }
    return this.SP.getPackageName();
  }
  
  public DataType getDataType()
  {
    return this.Sp;
  }
  
  public Device getDevice()
  {
    return this.SO;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public String getStreamIdentifier()
  {
    return this.SS;
  }
  
  public String getStreamName()
  {
    return this.SQ;
  }
  
  public int getType()
  {
    return this.FD;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return this.SS.hashCode();
  }
  
  public a iM()
  {
    return this.SP;
  }
  
  public boolean iO()
  {
    return this.SR;
  }
  
  public DataSource iP()
  {
    Device localDevice;
    if (this.SO == null)
    {
      localDevice = null;
      if (this.SP != null) {
        break label63;
      }
    }
    label63:
    for (a locala = null;; locala = this.SP.iA())
    {
      return new DataSource(3, this.Sp, this.mName, this.FD, localDevice, locala, kw.bt(this.SQ), this.SR);
      localDevice = this.SO.iT();
      break;
    }
  }
  
  public String toDebugString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.FD == 0)
    {
      str = "r";
      localStringBuilder = localStringBuilder.append(str).append(":").append(this.Sp.iQ());
      if (this.SP != null) {
        break label154;
      }
      str = "";
      label49:
      localStringBuilder = localStringBuilder.append(str);
      if (this.SO == null) {
        break label202;
      }
      str = ":" + this.SO.getModel() + ":" + this.SO.getUid();
      label103:
      localStringBuilder = localStringBuilder.append(str);
      if (this.SQ == null) {
        break label208;
      }
    }
    label154:
    label202:
    label208:
    for (String str = ":" + this.SQ;; str = "")
    {
      return str;
      str = "d";
      break;
      if (this.SP.equals(a.Sw))
      {
        str = ":gms";
        break label49;
      }
      str = ":" + this.SP.getPackageName();
      break label49;
      str = "";
      break label103;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("DataSource{");
    localStringBuilder.append(getTypeString());
    if (this.mName != null) {
      localStringBuilder.append(":").append(this.mName);
    }
    if (this.SP != null) {
      localStringBuilder.append(":").append(this.SP);
    }
    if (this.SO != null) {
      localStringBuilder.append(":").append(this.SO);
    }
    if (this.SQ != null) {
      localStringBuilder.append(":").append(this.SQ);
    }
    localStringBuilder.append(":").append(this.Sp);
    return "}";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    g.a(kw.c(this), paramParcel, paramInt);
  }
  
  public static final class Builder
  {
    private int FD = -1;
    private Device SO;
    private a SP;
    private String SQ = "";
    private boolean SR = false;
    private DataType Sp;
    private String mName;
    
    public DataSource build()
    {
      boolean bool2 = true;
      if (this.Sp != null)
      {
        bool1 = true;
        o.a(bool1, "Must set data type");
        if (this.FD < 0) {
          break label47;
        }
      }
      label47:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        o.a(bool1, "Must set data source type");
        return new DataSource(this, null);
        bool1 = false;
        break;
      }
    }
    
    public Builder setAppPackageName(Context paramContext)
    {
      return setAppPackageName(paramContext.getPackageName());
    }
    
    public Builder setAppPackageName(String paramString)
    {
      this.SP = new a(paramString, null, null);
      return this;
    }
    
    public Builder setDataType(DataType paramDataType)
    {
      this.Sp = paramDataType;
      return this;
    }
    
    public Builder setDevice(Device paramDevice)
    {
      this.SO = paramDevice;
      return this;
    }
    
    public Builder setName(String paramString)
    {
      this.mName = paramString;
      return this;
    }
    
    public Builder setObfuscated(boolean paramBoolean)
    {
      this.SR = paramBoolean;
      return this;
    }
    
    public Builder setStreamName(String paramString)
    {
      if (paramString != null) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Must specify a valid stream name");
        this.SQ = paramString;
        return this;
      }
    }
    
    public Builder setType(int paramInt)
    {
      this.FD = paramInt;
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/DataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */