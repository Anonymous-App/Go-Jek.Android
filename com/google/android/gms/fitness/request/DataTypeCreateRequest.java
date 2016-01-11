package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataTypeCreateRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataTypeCreateRequest> CREATOR = new i();
  private final int BR;
  private final List<Field> SY;
  private final String mName;
  
  DataTypeCreateRequest(int paramInt, String paramString, List<Field> paramList)
  {
    this.BR = paramInt;
    this.mName = paramString;
    this.SY = Collections.unmodifiableList(paramList);
  }
  
  private DataTypeCreateRequest(Builder paramBuilder)
  {
    this.BR = 1;
    this.mName = Builder.a(paramBuilder);
    this.SY = Collections.unmodifiableList(Builder.b(paramBuilder));
  }
  
  private boolean a(DataTypeCreateRequest paramDataTypeCreateRequest)
  {
    return (n.equal(this.mName, paramDataTypeCreateRequest.mName)) && (n.equal(this.SY, paramDataTypeCreateRequest.SY));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof DataTypeCreateRequest)) && (a((DataTypeCreateRequest)paramObject)));
  }
  
  public List<Field> getFields()
  {
    return this.SY;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.mName, this.SY });
  }
  
  public String toString()
  {
    return n.h(this).a("name", this.mName).a("fields", this.SY).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    i.a(this, paramParcel, paramInt);
  }
  
  public static class Builder
  {
    private List<Field> SY = new ArrayList();
    private String mName;
    
    public Builder addField(Field paramField)
    {
      if (!this.SY.contains(paramField)) {
        this.SY.add(paramField);
      }
      return this;
    }
    
    public Builder addField(String paramString, int paramInt)
    {
      if ((paramString != null) || (!paramString.isEmpty())) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Invalid name specified");
        return addField(new Field(paramString, paramInt));
      }
    }
    
    public DataTypeCreateRequest build()
    {
      boolean bool2 = true;
      if (this.mName != null)
      {
        bool1 = true;
        o.a(bool1, "Must set the name");
        if (this.SY.isEmpty()) {
          break label52;
        }
      }
      label52:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        o.a(bool1, "Must specify the data fields");
        return new DataTypeCreateRequest(this, null);
        bool1 = false;
        break;
      }
    }
    
    public Builder setName(String paramString)
    {
      this.mName = paramString;
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/DataTypeCreateRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */