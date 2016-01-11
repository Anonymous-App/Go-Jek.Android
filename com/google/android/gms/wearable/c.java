package com.google.android.gms.wearable;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class c
  implements SafeParcelable
{
  public static final Parcelable.Creator<c> CREATOR = new d();
  final int BR;
  private final int FD;
  private final String Sz;
  private final int auS;
  private final boolean auT;
  private boolean auU;
  private String auV;
  private final String mName;
  
  c(int paramInt1, String paramString1, String paramString2, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, String paramString3)
  {
    this.BR = paramInt1;
    this.mName = paramString1;
    this.Sz = paramString2;
    this.FD = paramInt2;
    this.auS = paramInt3;
    this.auT = paramBoolean1;
    this.auU = paramBoolean2;
    this.auV = paramString3;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof c)) {}
    do
    {
      return false;
      paramObject = (c)paramObject;
    } while ((!n.equal(Integer.valueOf(this.BR), Integer.valueOf(((c)paramObject).BR))) || (!n.equal(this.mName, ((c)paramObject).mName)) || (!n.equal(this.Sz, ((c)paramObject).Sz)) || (!n.equal(Integer.valueOf(this.FD), Integer.valueOf(((c)paramObject).FD))) || (!n.equal(Integer.valueOf(this.auS), Integer.valueOf(((c)paramObject).auS))) || (!n.equal(Boolean.valueOf(this.auT), Boolean.valueOf(((c)paramObject).auT))));
    return true;
  }
  
  public String getAddress()
  {
    return this.Sz;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public int getRole()
  {
    return this.auS;
  }
  
  public int getType()
  {
    return this.FD;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Integer.valueOf(this.BR), this.mName, this.Sz, Integer.valueOf(this.FD), Integer.valueOf(this.auS), Boolean.valueOf(this.auT) });
  }
  
  public boolean isConnected()
  {
    return this.auU;
  }
  
  public boolean isEnabled()
  {
    return this.auT;
  }
  
  public String pS()
  {
    return this.auV;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("ConnectionConfiguration[ ");
    localStringBuilder.append("mName=" + this.mName);
    localStringBuilder.append(", mAddress=" + this.Sz);
    localStringBuilder.append(", mType=" + this.FD);
    localStringBuilder.append(", mRole=" + this.auS);
    localStringBuilder.append(", mEnabled=" + this.auT);
    localStringBuilder.append(", mIsConnected=" + this.auU);
    localStringBuilder.append(", mEnabled=" + this.auV);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    d.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */