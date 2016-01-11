package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ez
public final class dj
  implements SafeParcelable
{
  public static final di CREATOR = new di();
  public final String mimeType;
  public final String packageName;
  public final String rp;
  public final String rq;
  public final String rr;
  public final String rs;
  public final String rt;
  public final int versionCode;
  
  public dj(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    this.versionCode = paramInt;
    this.rp = paramString1;
    this.rq = paramString2;
    this.mimeType = paramString3;
    this.packageName = paramString4;
    this.rr = paramString5;
    this.rs = paramString6;
    this.rt = paramString7;
  }
  
  public dj(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    this(1, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    di.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/dj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */