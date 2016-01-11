package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class am
  implements SafeParcelable
{
  public static final Parcelable.Creator<am> CREATOR = new an();
  public final long avN;
  public final String label;
  public final String packageName;
  public final int versionCode;
  
  am(int paramInt, String paramString1, String paramString2, long paramLong)
  {
    this.versionCode = paramInt;
    this.packageName = paramString1;
    this.label = paramString2;
    this.avN = paramLong;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    an.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/internal/am.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */