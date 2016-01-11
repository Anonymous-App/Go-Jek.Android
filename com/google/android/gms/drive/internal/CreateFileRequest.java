package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class CreateFileRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<CreateFileRequest> CREATOR = new j();
  final int BR;
  final String Nn;
  final Contents Of;
  final MetadataBundle Ol;
  final Integer Om;
  final DriveId On;
  final boolean Oo;
  final int Op;
  final int Oq;
  
  CreateFileRequest(int paramInt1, DriveId paramDriveId, MetadataBundle paramMetadataBundle, Contents paramContents, Integer paramInteger, boolean paramBoolean, String paramString, int paramInt2, int paramInt3)
  {
    if ((paramContents != null) && (paramInt3 != 0)) {
      if (paramContents.getRequestId() != paramInt3) {
        break label67;
      }
    }
    label67:
    for (boolean bool = true;; bool = false)
    {
      o.b(bool, "inconsistent contents reference");
      if (((paramInteger != null) && (paramInteger.intValue() != 0)) || (paramContents != null) || (paramInt3 != 0)) {
        break;
      }
      throw new IllegalArgumentException("Need a valid contents");
    }
    this.BR = paramInt1;
    this.On = ((DriveId)o.i(paramDriveId));
    this.Ol = ((MetadataBundle)o.i(paramMetadataBundle));
    this.Of = paramContents;
    this.Om = paramInteger;
    this.Nn = paramString;
    this.Op = paramInt2;
    this.Oo = paramBoolean;
    this.Oq = paramInt3;
  }
  
  public CreateFileRequest(DriveId paramDriveId, MetadataBundle paramMetadataBundle, int paramInt1, int paramInt2, ExecutionOptions paramExecutionOptions)
  {
    this(2, paramDriveId, paramMetadataBundle, null, Integer.valueOf(paramInt2), paramExecutionOptions.hP(), paramExecutionOptions.hO(), paramExecutionOptions.hQ(), paramInt1);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    j.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/CreateFileRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */