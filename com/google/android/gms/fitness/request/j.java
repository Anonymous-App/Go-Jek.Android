package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class j
  implements SafeParcelable
{
  public static final Parcelable.Creator<j> CREATOR = new k();
  private final int BR;
  private final String mName;
  
  j(int paramInt, String paramString)
  {
    this.BR = paramInt;
    this.mName = paramString;
  }
  
  public j(String paramString)
  {
    this.BR = 1;
    this.mName = paramString;
  }
  
  private boolean a(j paramj)
  {
    return n.equal(this.mName, paramj.mName);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof j)) && (a((j)paramObject)));
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
    return n.hashCode(new Object[] { this.mName });
  }
  
  public String toString()
  {
    return n.h(this).a("name", this.mName).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    k.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */