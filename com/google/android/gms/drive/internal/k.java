package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class k
  implements Parcelable.Creator<CreateFolderRequest>
{
  static void a(CreateFolderRequest paramCreateFolderRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramCreateFolderRequest.BR);
    b.a(paramParcel, 2, paramCreateFolderRequest.On, paramInt, false);
    b.a(paramParcel, 3, paramCreateFolderRequest.Ol, paramInt, false);
    b.H(paramParcel, i);
  }
  
  public CreateFolderRequest ad(Parcel paramParcel)
  {
    MetadataBundle localMetadataBundle = null;
    int j = a.C(paramParcel);
    int i = 0;
    DriveId localDriveId = null;
    if (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
      }
      for (;;)
      {
        break;
        i = a.g(paramParcel, k);
        continue;
        localDriveId = (DriveId)a.a(paramParcel, k, DriveId.CREATOR);
        continue;
        localMetadataBundle = (MetadataBundle)a.a(paramParcel, k, MetadataBundle.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new CreateFolderRequest(i, localDriveId, localMetadataBundle);
  }
  
  public CreateFolderRequest[] bn(int paramInt)
  {
    return new CreateFolderRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */