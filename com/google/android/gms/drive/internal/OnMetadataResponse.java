package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class OnMetadataResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnMetadataResponse> CREATOR = new ap();
  final int BR;
  final MetadataBundle Ol;
  
  OnMetadataResponse(int paramInt, MetadataBundle paramMetadataBundle)
  {
    this.BR = paramInt;
    this.Ol = paramMetadataBundle;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public MetadataBundle il()
  {
    return this.Ol;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ap.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/OnMetadataResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */