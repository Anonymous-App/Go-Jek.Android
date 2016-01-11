package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class hg
  implements SafeParcelable
{
  public static final hh CREATOR = new hh();
  final int BR;
  final String BZ;
  final String Ca;
  final String Cb;
  
  hg(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    this.BR = paramInt;
    this.BZ = paramString1;
    this.Ca = paramString2;
    this.Cb = paramString3;
  }
  
  public hg(String paramString1, String paramString2, String paramString3)
  {
    this(1, paramString1, paramString2, paramString3);
  }
  
  public int describeContents()
  {
    hh localhh = CREATOR;
    return 0;
  }
  
  public String toString()
  {
    return String.format("DocumentId[packageName=%s, corpusName=%s, uri=%s]", new Object[] { this.BZ, this.Ca, this.Cb });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    hh localhh = CREATOR;
    hh.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/hg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */