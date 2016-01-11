package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class TextInsertedDetails
  implements SafeParcelable
{
  public static final Parcelable.Creator<TextInsertedDetails> CREATOR = new f();
  final int BR;
  final int RL;
  final int mIndex;
  
  TextInsertedDetails(int paramInt1, int paramInt2, int paramInt3)
  {
    this.BR = paramInt1;
    this.mIndex = paramInt2;
    this.RL = paramInt3;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    f.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/realtime/internal/event/TextInsertedDetails.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */