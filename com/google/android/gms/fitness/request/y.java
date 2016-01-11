package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class y
  implements SafeParcelable
{
  public static final Parcelable.Creator<y> CREATOR = new z();
  private final int BR;
  private final String Tq;
  private final String mName;
  
  y(int paramInt, String paramString1, String paramString2)
  {
    this.BR = paramInt;
    this.mName = paramString1;
    this.Tq = paramString2;
  }
  
  private y(a parama)
  {
    this.BR = 1;
    this.mName = a.a(parama);
    this.Tq = a.b(parama);
  }
  
  private boolean a(y paramy)
  {
    return (n.equal(this.mName, paramy.mName)) && (n.equal(this.Tq, paramy.Tq));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof y)) && (a((y)paramObject)));
  }
  
  public String getIdentifier()
  {
    return this.Tq;
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
    return n.hashCode(new Object[] { this.mName, this.Tq });
  }
  
  public String toString()
  {
    return n.h(this).a("name", this.mName).a("identifier", this.Tq).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    z.a(this, paramParcel, paramInt);
  }
  
  public static class a
  {
    private String Tq;
    private String mName;
    
    public a bu(String paramString)
    {
      this.mName = paramString;
      return this;
    }
    
    public a bv(String paramString)
    {
      this.Tq = paramString;
      return this;
    }
    
    public y jB()
    {
      return new y(this, null);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/y.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */