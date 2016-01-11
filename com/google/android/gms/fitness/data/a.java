package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kw;

public final class a
  implements SafeParcelable
{
  public static final Parcelable.Creator<a> CREATOR = new b();
  public static final a Sw = new a("com.google.android.gms", String.valueOf(6171000), null);
  private final int BR;
  private final String BZ;
  private final String Sx;
  private final String Sy;
  
  a(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    this.BR = paramInt;
    this.BZ = ((String)o.i(paramString1));
    this.Sx = "";
    this.Sy = paramString3;
  }
  
  public a(String paramString1, String paramString2, String paramString3)
  {
    this(1, paramString1, "", paramString3);
  }
  
  private boolean a(a parama)
  {
    return (this.BZ.equals(parama.BZ)) && (n.equal(this.Sx, parama.Sx)) && (n.equal(this.Sy, parama.Sy));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof a)) && (a((a)paramObject)));
  }
  
  public String getPackageName()
  {
    return this.BZ;
  }
  
  public String getVersion()
  {
    return this.Sx;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.BZ, this.Sx, this.Sy });
  }
  
  a iA()
  {
    return new a(kw.bt(this.BZ), kw.bt(this.Sx), kw.bt(this.Sy));
  }
  
  public String iz()
  {
    return this.Sy;
  }
  
  public String toString()
  {
    return String.format("Application{%s:%s:%s}", new Object[] { this.BZ, this.Sx, this.Sy });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    b.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */