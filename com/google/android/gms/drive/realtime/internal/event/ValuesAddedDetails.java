package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ValuesAddedDetails
  implements SafeParcelable
{
  public static final Parcelable.Creator<ValuesAddedDetails> CREATOR = new h();
  final int BR;
  final String RN;
  final int RO;
  final int Rr;
  final int Rs;
  final int mIndex;
  
  ValuesAddedDetails(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString, int paramInt5)
  {
    this.BR = paramInt1;
    this.mIndex = paramInt2;
    this.Rr = paramInt3;
    this.Rs = paramInt4;
    this.RN = paramString;
    this.RO = paramInt5;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    h.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/realtime/internal/event/ValuesAddedDetails.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */