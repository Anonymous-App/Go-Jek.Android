package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class e
  implements Parcelable.Creator<CloseContentsAndUpdateMetadataRequest>
{
  static void a(CloseContentsAndUpdateMetadataRequest paramCloseContentsAndUpdateMetadataRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramCloseContentsAndUpdateMetadataRequest.BR);
    b.a(paramParcel, 2, paramCloseContentsAndUpdateMetadataRequest.Od, paramInt, false);
    b.a(paramParcel, 3, paramCloseContentsAndUpdateMetadataRequest.Oe, paramInt, false);
    b.a(paramParcel, 4, paramCloseContentsAndUpdateMetadataRequest.Of, paramInt, false);
    b.a(paramParcel, 5, paramCloseContentsAndUpdateMetadataRequest.No);
    b.a(paramParcel, 6, paramCloseContentsAndUpdateMetadataRequest.Nn, false);
    b.c(paramParcel, 7, paramCloseContentsAndUpdateMetadataRequest.Og);
    b.H(paramParcel, i);
  }
  
  public CloseContentsAndUpdateMetadataRequest Y(Parcel paramParcel)
  {
    int i = 0;
    String str = null;
    int k = a.C(paramParcel);
    boolean bool = false;
    Contents localContents = null;
    MetadataBundle localMetadataBundle = null;
    DriveId localDriveId = null;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        j = a.g(paramParcel, m);
        break;
      case 2: 
        localDriveId = (DriveId)a.a(paramParcel, m, DriveId.CREATOR);
        break;
      case 3: 
        localMetadataBundle = (MetadataBundle)a.a(paramParcel, m, MetadataBundle.CREATOR);
        break;
      case 4: 
        localContents = (Contents)a.a(paramParcel, m, Contents.CREATOR);
        break;
      case 5: 
        bool = a.c(paramParcel, m);
        break;
      case 6: 
        str = a.o(paramParcel, m);
        break;
      case 7: 
        i = a.g(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new CloseContentsAndUpdateMetadataRequest(j, localDriveId, localMetadataBundle, localContents, bool, str, i);
  }
  
  public CloseContentsAndUpdateMetadataRequest[] bh(int paramInt)
  {
    return new CloseContentsAndUpdateMetadataRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */