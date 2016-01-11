package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ao
  implements SafeParcelable
{
  public static final Parcelable.Creator<ao> CREATOR = new ap();
  public final m avA;
  public final int statusCode;
  public final int versionCode;
  
  ao(int paramInt1, int paramInt2, m paramm)
  {
    this.versionCode = paramInt1;
    this.statusCode = paramInt2;
    this.avA = paramm;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ap.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/internal/ao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */