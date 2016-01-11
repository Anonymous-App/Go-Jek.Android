package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;

public class OnContentsResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnContentsResponse> CREATOR = new ai();
  final int BR;
  final Contents Ox;
  final boolean Po;
  
  OnContentsResponse(int paramInt, Contents paramContents, boolean paramBoolean)
  {
    this.BR = paramInt;
    this.Ox = paramContents;
    this.Po = paramBoolean;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Contents id()
  {
    return this.Ox;
  }
  
  public boolean ie()
  {
    return this.Po;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ai.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/OnContentsResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */